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
public class formupdatedatapeserta extends javax.swing.JDialog {

    /**
     * Creates new form formupdatedatapeserta
     */
    public formupdatedatapeserta(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
            btnupdate.setEnabled(false);
    }

     public void updateData(String status){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection koneksi = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_ppkpijakarta","root","");
            String query = "UPDATE peserta SET status=? WHERE id_peserta='"+txtidpeserta.getText()+"'";
            PreparedStatement prestat = koneksi.prepareStatement(query);
            prestat.setString(1, status);
            prestat.executeUpdate();
            koneksi.close();
           
        } catch (Exception ex){
            JOptionPane.showMessageDialog(null, "QUERY ERROR"+ex,"ERROR",JOptionPane.ERROR_MESSAGE);
        }
         
     
     }
     
      public Vector tampildatapeserta(){
        Vector baris = new Vector();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection koneksi = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_ppkpijakarta", "root", "");
            String query = "SELECT * FROM peserta WHERE id_peserta='"+txtidpeserta.getText()+"' ORDER BY id_peserta";
            PreparedStatement prestat = koneksi.prepareStatement(query);
            ResultSet rs = prestat.executeQuery();
            while(rs.next()){
                Vector kolom = new Vector();
                
                kolom.add(rs.getString("id_peserta"));
                kolom.add(rs.getString("nama_peserta"));
                kolom.add(rs.getString("jenis_kelamin"));
                kolom.add(rs.getString("tempat_lahir"));
                kolom.add(rs.getString("tgl_lahir"));
                kolom.add(rs.getString("jurusan"));
                kolom.add(rs.getString("angkatan"));
                kolom.add(rs.getString("no_hp"));
                kolom.add(rs.getString("alamat"));
                kolom.add(rs.getString("pendidikan_terakhir"));
                kolom.add(rs.getString("status"));
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
            judulTabel.add("ID peserta");
            judulTabel.add("Nama Peserta");
            judulTabel.add("Jenis Kelamin");
            judulTabel.add("Tempat Lahir");
            judulTabel.add("Tanggal Lahir");
            judulTabel.add("Jurusan");
            judulTabel.add("Angkatan");
            judulTabel.add("No Hp");
            judulTabel.add("Alamat");
            judulTabel.add("Pendidikan terakhir");
            judulTabel.add("Status");
            Vector isiData = tampildatapeserta();
            jTable1.setModel(new DefaultTableModel(isiData, judulTabel));
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpanelinputdatapeserta = new javax.swing.JPanel();
        header = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        inputdata = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        comboxstatus = new javax.swing.JComboBox<>();
        txtidpeserta = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        btnupdate = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        btncari = new javax.swing.JButton();
        txtnama = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jpanelinputdatapeserta.setForeground(new java.awt.Color(255, 255, 255));

        header.setBackground(new java.awt.Color(66, 139, 202));

        jLabel13.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("UPDATE DATA PESERTA");

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

        inputdata.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(66, 139, 202)));

        jLabel14.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        jLabel14.setText("Status");

        comboxstatus.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        comboxstatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--- STATUS ---", "PESERTA", "LULUS" }));

        txtidpeserta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtidpesertaActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        jLabel17.setText("ID Peserta");

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

        btncari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/srchdokmn.png"))); // NOI18N
        btncari.setText("Cari");
        btncari.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btncari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncariActionPerformed(evt);
            }
        });

        txtnama.setEditable(false);
        txtnama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnamaActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        jLabel18.setText("Nama");

        javax.swing.GroupLayout inputdataLayout = new javax.swing.GroupLayout(inputdata);
        inputdata.setLayout(inputdataLayout);
        inputdataLayout.setHorizontalGroup(
            inputdataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, inputdataLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnupdate, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(135, 135, 135))
            .addGroup(inputdataLayout.createSequentialGroup()
                .addGap(159, 159, 159)
                .addGroup(inputdataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(inputdataLayout.createSequentialGroup()
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtnama, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(inputdataLayout.createSequentialGroup()
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtidpeserta, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(inputdataLayout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(comboxstatus, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btncari, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(84, Short.MAX_VALUE))
        );
        inputdataLayout.setVerticalGroup(
            inputdataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inputdataLayout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(inputdataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txtidpeserta, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btncari))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(inputdataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(txtnama, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(inputdataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboxstatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addGap(30, 30, 30)
                .addGroup(inputdataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(btnupdate, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Peserta", "Nama Peserta", "Jenis Kelamin", "Tempat Lahir", "Tanggal Lahir", "Jurusan", "Angkatan", "No Hp", "Alamat", "Pendidikan Terakhir", "Status"
            }
        ));
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(jTable1);

        javax.swing.GroupLayout jpanelinputdatapesertaLayout = new javax.swing.GroupLayout(jpanelinputdatapeserta);
        jpanelinputdatapeserta.setLayout(jpanelinputdatapesertaLayout);
        jpanelinputdatapesertaLayout.setHorizontalGroup(
            jpanelinputdatapesertaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanelinputdatapesertaLayout.createSequentialGroup()
                .addGroup(jpanelinputdatapesertaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpanelinputdatapesertaLayout.createSequentialGroup()
                        .addGroup(jpanelinputdatapesertaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, 942, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jpanelinputdatapesertaLayout.createSequentialGroup()
                                .addGap(174, 174, 174)
                                .addComponent(inputdata, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpanelinputdatapesertaLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 936, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jpanelinputdatapesertaLayout.setVerticalGroup(
            jpanelinputdatapesertaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanelinputdatapesertaLayout.createSequentialGroup()
                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(inputdata, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                .addGap(22, 22, 22))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jpanelinputdatapeserta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jpanelinputdatapeserta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtidpesertaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtidpesertaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtidpesertaActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
      txtidpeserta.setText("");
      txtnama.setText("");
    }//GEN-LAST:event_jButton5ActionPerformed

    private void btnupdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnupdateActionPerformed
        int tanya = JOptionPane.showConfirmDialog(null, "ANDA YAKIN DATA INI INGIN DISIMPAN?", "INFORMASI", JOptionPane.CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (tanya == JOptionPane.OK_OPTION) {
            
            updateData((String) comboxstatus.getSelectedItem());
            JOptionPane.showMessageDialog(null, "DATA BERHASIL DIUPDATE", "INFORMASI", JOptionPane.INFORMATION_MESSAGE);
            tampil();
            txtidpeserta.setText("");
             txtnama.setText("");
            btncari.setEnabled(true);
            btnupdate.setEnabled(false);
   
        }
     
    }//GEN-LAST:event_btnupdateActionPerformed

    private void btncariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncariActionPerformed
       String param = txtidpeserta.getText();  
         try{
          Class.forName("com.mysql.jdbc.Driver").newInstance();
            com.mysql.jdbc.Connection koneksi = (com.mysql.jdbc.Connection) DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/db_ppkpijakarta", "root", "");
            com.mysql.jdbc.Statement statement = (com.mysql.jdbc.Statement) koneksi.createStatement();
            String sql="SELECT * FROM peserta WHERE id_peserta like '"+param+"'";
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()){     
            txtnama.setText(rs.getString(2));
            comboxstatus.getModel().setSelectedItem(rs.getString(11));
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

    private void txtnamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnamaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnamaActionPerformed

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
            java.util.logging.Logger.getLogger(formupdatedatapeserta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(formupdatedatapeserta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(formupdatedatapeserta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(formupdatedatapeserta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                formupdatedatapeserta dialog = new formupdatedatapeserta(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btncari1;
    private javax.swing.JButton btnupdate;
    private javax.swing.JButton btnupdate1;
    private javax.swing.JComboBox<String> comboxstatus;
    private javax.swing.JComboBox<String> comboxstatus1;
    private javax.swing.JPanel header;
    private javax.swing.JPanel inputdata;
    private javax.swing.JPanel inputdata1;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JPanel jpanelinputdatapeserta;
    private javax.swing.JTextField txtidpeserta;
    private javax.swing.JTextField txtidpeserta1;
    private javax.swing.JTextField txtnama;
    private javax.swing.JTextField txtnama1;
    // End of variables declaration//GEN-END:variables
}
