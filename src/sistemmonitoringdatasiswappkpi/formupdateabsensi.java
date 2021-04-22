/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemmonitoringdatasiswappkpi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author prayogi
 */
public class formupdateabsensi extends javax.swing.JDialog {

    
    public formupdateabsensi(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        jDateabsensi.setDateFormatString("dd/MM/yyyy");
        btnupdate.setEnabled(false);
        txttgl.setVisible(false);
    }

     public void updateData(String keterangan){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection koneksi = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_ppkpijakarta","root","");
            String query = "UPDATE absensi SET keterangan=? WHERE id_peserta='"+txtidpeserta.getText()+"' and tanggal='"+txttgl.getText()+"'";
            PreparedStatement prestat = koneksi.prepareStatement(query);
            prestat.setString(1, keterangan);
            prestat.executeUpdate();
            koneksi.close();
           
        } catch (Exception ex){
            JOptionPane.showMessageDialog(null, "QUERY ERROR"+ex,"ERROR",JOptionPane.ERROR_MESSAGE);
        }
     
     }
     
      public Vector tampildataabsensi(){
        Vector baris = new Vector();
         SimpleDateFormat fm = new SimpleDateFormat("dd-MM-yyyy");
        String tgl = String.valueOf(fm.format(jDateabsensi.getDate()));
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection koneksi = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_ppkpijakarta", "root", "");
            String query = "SELECT * FROM absensi WHERE id_peserta='"+txtidpeserta.getText()+"' ORDER BY id_peserta";
            PreparedStatement prestat = koneksi.prepareStatement(query);
            ResultSet rs = prestat.executeQuery();
            while(rs.next()){
                Vector kolom = new Vector();
                
                kolom.add(rs.getString("id_peserta"));
                kolom.add(rs.getString("tanggal"));
                kolom.add(rs.getString("jam"));
                kolom.add(rs.getString("keterangan"));
                baris.add(kolom);
            }   
            koneksi.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "QUERY ERROR"+ex,"ERROR",JOptionPane.ERROR_MESSAGE);
        }
        return baris;
    }
      
      public Vector tampildataabsensi2(){ //tampilan setelah update
        Vector baris = new Vector();
         SimpleDateFormat fm = new SimpleDateFormat("dd-MM-yyyy");
        String tgl = String.valueOf(fm.format(jDateabsensi.getDate()));
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection koneksi = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_ppkpijakarta", "root", "");
            String query = "SELECT * FROM absensi WHERE id_peserta='"+txtidpeserta.getText()+"' and tanggal='"+txttgl.getText()+"' ORDER BY id_peserta";
            PreparedStatement prestat = koneksi.prepareStatement(query);
            ResultSet rs = prestat.executeQuery();
            while(rs.next()){
                Vector kolom = new Vector();
                
                kolom.add(rs.getString("id_peserta"));
                kolom.add(rs.getString("tanggal"));
                kolom.add(rs.getString("jam"));
                kolom.add(rs.getString("keterangan"));
                baris.add(kolom);
            }   
            koneksi.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "QUERY ERROR"+ex,"ERROR",JOptionPane.ERROR_MESSAGE);
        }
        return baris;
    }
     
     public void tampil(){
           Vector judulTabel = new Vector();
        judulTabel.add("ID Peserta");
        judulTabel.add("Tanggal");
        judulTabel.add("Jam");
        judulTabel.add("Keterangan");
        Vector isiTable=tampildataabsensi();
        jTable1.setModel(new DefaultTableModel(isiTable, judulTabel)); 
    }
     public void tampil2(){
           Vector judulTabel = new Vector();
        judulTabel.add("ID Peserta");
        judulTabel.add("Tanggal");
        judulTabel.add("Jam");
        judulTabel.add("Keterangan");
        Vector isiTable=tampildataabsensi2();
        jTable1.setModel(new DefaultTableModel(isiTable, judulTabel)); 
    }
     
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        inputdata = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        comboxketerangan = new javax.swing.JComboBox<>();
        txtidpeserta = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        btncari = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        jDateabsensi = new com.toedter.calendar.JDateChooser();
        btnupdate = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        txttgl = new javax.swing.JTextField();
        header = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(954, 528));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID Peserta", "Tanggal", "Jam", "Keterangan"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        inputdata.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(66, 139, 202)));

        jLabel14.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        jLabel14.setText("Keterangan");

        comboxketerangan.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        comboxketerangan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "IJIN", "SAKIT", "TANPA KETERANGAN" }));

        txtidpeserta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtidpesertaActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        jLabel17.setText("ID Peserta");

        btncari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/srchdokmn.png"))); // NOI18N
        btncari.setText("Cari");
        btncari.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btncari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncariActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        jLabel18.setText("Tanggal");

        btnupdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/editdata.png"))); // NOI18N
        btnupdate.setText("Update");
        btnupdate.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnupdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnupdateActionPerformed(evt);
            }
        });

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Close-ic.png"))); // NOI18N
        jButton5.setText("Batal");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        txttgl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttglActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout inputdataLayout = new javax.swing.GroupLayout(inputdata);
        inputdata.setLayout(inputdataLayout);
        inputdataLayout.setHorizontalGroup(
            inputdataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inputdataLayout.createSequentialGroup()
                .addGroup(inputdataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(inputdataLayout.createSequentialGroup()
                        .addGap(152, 152, 152)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(inputdataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btncari, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jDateabsensi, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(inputdataLayout.createSequentialGroup()
                        .addGap(152, 152, 152)
                        .addGroup(inputdataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, inputdataLayout.createSequentialGroup()
                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtidpeserta, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, inputdataLayout.createSequentialGroup()
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(comboxketerangan, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txttgl, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(inputdataLayout.createSequentialGroup()
                        .addGap(256, 256, 256)
                        .addComponent(btnupdate, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton5)))
                .addContainerGap(151, Short.MAX_VALUE))
        );
        inputdataLayout.setVerticalGroup(
            inputdataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inputdataLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(inputdataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(inputdataLayout.createSequentialGroup()
                        .addGroup(inputdataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(txtidpeserta, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateabsensi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel18))
                .addGap(12, 12, 12)
                .addComponent(btncari, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(inputdataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(comboxketerangan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txttgl, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addGroup(inputdataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnupdate, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5)))
        );

        header.setBackground(new java.awt.Color(66, 139, 202));

        jLabel13.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("UPDATE DATA ABSENSI");

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 931, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(151, 151, 151))
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(header, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 942, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(inputdata, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(166, 166, 166))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inputdata, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtidpesertaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtidpesertaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtidpesertaActionPerformed

    private void btnupdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnupdateActionPerformed
     int tanya = JOptionPane.showConfirmDialog(null, "ANDA YAKIN DATA INI INGIN DISIMPAN?", "INFORMASI", JOptionPane.CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (tanya == JOptionPane.OK_OPTION) {
            SimpleDateFormat fm = new SimpleDateFormat("dd-MM-yyyy");
        String tgl = String.valueOf(fm.format(jDateabsensi.getDate()));
            
            updateData((String)comboxketerangan.getSelectedItem());
            JOptionPane.showMessageDialog(null, "DATA BERHASIL DIUPDATE", "INFORMASI", JOptionPane.INFORMATION_MESSAGE);
            tampil2();
                btnupdate.setEnabled(false);
                btncari.setEnabled(true);
        }
    }//GEN-LAST:event_btnupdateActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
       txtidpeserta.setText("");
    }//GEN-LAST:event_jButton5ActionPerformed

    private void btncariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncariActionPerformed
         String param = txtidpeserta.getText();  
         SimpleDateFormat fm = new SimpleDateFormat("dd/MM/yyyy");
        String tgl = String.valueOf(fm.format(jDateabsensi.getDate()));
         try{
          Class.forName("com.mysql.jdbc.Driver").newInstance();
            com.mysql.jdbc.Connection koneksi = (com.mysql.jdbc.Connection) DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/db_ppkpijakarta", "root", "");
            com.mysql.jdbc.Statement statement = (com.mysql.jdbc.Statement) koneksi.createStatement();
            String sql="SELECT * FROM absensi WHERE id_peserta like '"+param+"' and tanggal = '"+tgl+"'";
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()){     
            comboxketerangan.getModel().setSelectedItem(rs.getString(4));
            txttgl.setText(rs.getString(2));
            btnupdate.setEnabled(true);
            btncari.setEnabled(false);
            }
       
            else{
            JOptionPane.showMessageDialog(null,"Data tidak ditemukan");
            statement.close();
            koneksi.close();
                }
        }catch(Exception ex){
        }
    }//GEN-LAST:event_btncariActionPerformed

    private void txttglActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttglActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttglActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(formupdateabsensi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(formupdateabsensi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(formupdateabsensi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(formupdateabsensi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                formupdateabsensi dialog = new formupdateabsensi(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btncari;
    private javax.swing.JButton btnupdate;
    private javax.swing.JComboBox<String> comboxketerangan;
    private javax.swing.JPanel header;
    private javax.swing.JPanel inputdata;
    private javax.swing.JButton jButton5;
    private com.toedter.calendar.JDateChooser jDateabsensi;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtidpeserta;
    private javax.swing.JTextField txttgl;
    // End of variables declaration//GEN-END:variables
}
