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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author prayogi
 */
public class inputnilai extends javax.swing.JDialog {

    /**
     * Creates new form inputnilai
     */
    public inputnilai(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    
    public void addData(String id_peserta,String kd_materi,String nilai_lat){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection koneksi = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_ppkpijakarta","root","");
            String query = "INSERT INTO nilailatihan VALUES (?,?,?)";
            PreparedStatement prestat = koneksi.prepareStatement(query);
            
            prestat.setString(1, id_peserta);
            prestat.setString(2, kd_materi);
            prestat.setString(3, nilai_lat);
          
            prestat.executeUpdate();
            koneksi.close();
        } catch (Exception ex){
            JOptionPane.showMessageDialog(null, "QUERY ERROR"+ex,"ERROR",JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
         public void hapusdatanilaiakhir (String id_peserta){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection koneksi = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_ppkpijakarta","root","");
            String query = "DELETE FROM nilaiakhir WHERE id_peserta=? ";
            PreparedStatement prestat = koneksi.prepareStatement(query);
            prestat.setString(1, id_peserta);
            prestat.executeUpdate();
            koneksi.close();
           
        } 
        catch (Exception ex){
//            JOptionPane.showMessageDialog(null, "QUERRY ERROR"+ex,"ERROR",JOptionPane.ERROR_MESSAGE);
        }
      }
    
    public void addDatanilaiakhir (String id_peserta, String nilai_akhir){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection koneksi = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_ppkpijakarta","root","");
            String query = "INSERT INTO nilaiakhir VALUES (?,?)";
            PreparedStatement prestat = koneksi.prepareStatement(query);
            prestat.setString(1, id_peserta);
            prestat.setString(2, nilai_akhir);
       
            prestat.executeUpdate();
            koneksi.close();
             JOptionPane.showMessageDialog(null,"Nilai akhir berhasil disimpan");
        } catch (Exception ex){
            JOptionPane.showMessageDialog(null, "QUERY ERROR"+ex,"ERROR",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    public Vector tampildata(){
       Vector baris = new Vector();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection koneksi = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_ppkpijakarta", "root", "");
            String query = "SELECT materi.materi, materi.kd_materi, nilailatihan.nilai_lat FROM nilailatihan,materi WHERE nilailatihan.id_peserta='"+txtidpeserta.getText().trim()+"' and materi.kd_materi = nilailatihan.kd_materi";
            PreparedStatement prestat = koneksi.prepareStatement(query);
            ResultSet rs = prestat.executeQuery();
            while(rs.next()){
                Vector kolom = new Vector();
                kolom.add(rs.getString("kd_materi"));
                kolom.add(rs.getString("materi"));
                kolom.add(rs.getString("nilai_lat"));
                baris.add(kolom);
            }   
            koneksi.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "QUERY ERROR"+ex,"ERROR",JOptionPane.ERROR_MESSAGE);
        }
        return baris;
    }
    public void kosongkantable(){
        DefaultTableModel Model = (DefaultTableModel)jTabledatanilai.getModel();
        int baris = Model.getRowCount();
        for (int a=0; a<baris; a++)
        {
            Model.removeRow(0);
           // Model.setRowCount(0);
        }
    }
    
     private void hitungnilaiakhir(){
    int total = 0 ;
    int jmlrow = 0 ;
    int nilakhir = 0;
        for (int i=0; i<jTabledatanilai.getRowCount();i++){
            int amount = Integer.parseInt((String)jTabledatanilai.getValueAt(i,2));
            total += amount;
            jmlrow+=1;
        }
        nilakhir = total/jmlrow ;
        txtnilaiakhir.setText(""+nilakhir);
            
}
     
     public void updateData(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection koneksi = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_ppkpijakarta","root","");
            String query = "UPDATE peserta SET status='LULUS' WHERE id_peserta='"+txtidpeserta.getText()+"'";
            PreparedStatement prestat = koneksi.prepareStatement(query);
            prestat.executeUpdate();
            koneksi.close();
           
        } catch (Exception ex){
            JOptionPane.showMessageDialog(null, "QUERY ERROR"+ex,"ERROR",JOptionPane.ERROR_MESSAGE);
        }
     
     }
     
     public void hapusdata (String id_peserta,String kd_materi){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection koneksi = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_ppkpijakarta","root","");
            String query = "DELETE FROM nilailatihan WHERE id_peserta=? and kd_materi=?";
            PreparedStatement prestat = koneksi.prepareStatement(query);
            prestat.setString(1, id_peserta);
            prestat.setString(2, kd_materi);
            prestat.executeUpdate();
            koneksi.close();
           
        } catch (Exception ex){
            JOptionPane.showMessageDialog(null, "QUERRY ERROR"+ex,"ERROR",JOptionPane.ERROR_MESSAGE);
        }
      }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTabledatanilai = new javax.swing.JTable();
        header = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtkodemateri = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtidpeserta = new javax.swing.JTextField();
        btntambah = new javax.swing.JButton();
        btncekid = new javax.swing.JButton();
        txtnama = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtnilai = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        btnsimpannilaiakhir = new javax.swing.JButton();
        txtnilaiakhir = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        btnbatal = new javax.swing.JButton();
        btnhapus = new javax.swing.JButton();
        txtnamamateri = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jTabledatanilai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Kode Materi", "Materi", "Nilai"
            }
        ));
        jTabledatanilai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabledatanilaiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTabledatanilai);

        header.setBackground(new java.awt.Color(66, 139, 202));

        jLabel13.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("INPUT NILAI");

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 806, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(276, 276, 276))
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel14.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        jLabel14.setText("ID Peserta");

        jLabel15.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        jLabel15.setText("Kode Materi");

        btntambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/addstt.png"))); // NOI18N
        btntambah.setText("Add");
        btntambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntambahActionPerformed(evt);
            }
        });

        btncekid.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/srchdokmn.png"))); // NOI18N
        btncekid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncekidActionPerformed(evt);
            }
        });

        txtnama.setEditable(false);

        jLabel17.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        jLabel17.setText("Nama");

        jLabel18.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        jLabel18.setText("Nilai");

        btnsimpannilaiakhir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/save.png"))); // NOI18N
        btnsimpannilaiakhir.setText("Simpan Nilai Akhir");
        btnsimpannilaiakhir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsimpannilaiakhirActionPerformed(evt);
            }
        });

        txtnilaiakhir.setEditable(false);

        jLabel19.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        jLabel19.setText("Nilai Akhir");

        btnbatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Close-ic.png"))); // NOI18N
        btnbatal.setText("Clear");
        btnbatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbatalActionPerformed(evt);
            }
        });

        btnhapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/clear.png"))); // NOI18N
        btnhapus.setText("Delete");
        btnhapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhapusActionPerformed(evt);
            }
        });

        txtnamamateri.setEditable(false);
        txtnamamateri.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtnamamateriMouseClicked(evt);
            }
        });
        txtnamamateri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnamamateriActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        jLabel16.setText("Nama Materi");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnsimpannilaiakhir)
                .addGap(36, 36, 36)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtnilaiakhir, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtnama, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(84, 84, 84)
                                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtidpeserta, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(btncekid, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(136, 136, 136)
                                        .addComponent(jLabel18))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(136, 136, 136)
                                        .addComponent(txtnilai, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtkodemateri, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(txtnamamateri, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnbatal)
                                .addGap(5, 5, 5)
                                .addComponent(btnhapus, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btntambah, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btntambah, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel14)
                                .addComponent(txtidpeserta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btncekid, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtnama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17))
                        .addGap(13, 13, 13)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(jLabel16)
                            .addComponent(jLabel18))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnhapus, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnbatal, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtkodemateri, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtnamamateri, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtnilai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtnilaiakhir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addComponent(btnsimpannilaiakhir))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btntambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntambahActionPerformed
        addData(txtidpeserta.getText(),txtkodemateri.getText(),txtnilai.getText());
        
        Vector judulTabel = new Vector();
        judulTabel.add("Kode Materi");
        judulTabel.add("Materi");
        judulTabel.add("Nilai");
        Vector isiData= tampildata();
        jTabledatanilai.setModel(new DefaultTableModel(isiData, judulTabel)); 
        hitungnilaiakhir();
        txtkodemateri.setText("");
     // txtmateri.setText("");
        txtnilai.setText("");
        txtkodemateri.setEditable(true);
        txtnamamateri.setText("");
    }//GEN-LAST:event_btntambahActionPerformed

    private void btncekidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncekidActionPerformed
        String no = txtidpeserta.getText();  
         try{
          Class.forName("com.mysql.jdbc.Driver").newInstance();
            com.mysql.jdbc.Connection koneksi = (com.mysql.jdbc.Connection) DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/db_ppkpijakarta", "root", "");
            com.mysql.jdbc.Statement statement = (com.mysql.jdbc.Statement) koneksi.createStatement();
            String sql="SELECT peserta.nama_peserta FROM peserta WHERE peserta.id_peserta like '"+no+"'";
             ResultSet rs = statement.executeQuery(sql);
            if (rs.next()){     
            txtnama.setText(rs.getString(1));
            btncekid.setEnabled(false);  
            txtidpeserta.setEnabled(false);
            
            Vector judulTabel = new Vector();
            judulTabel.add("Kode Materi");
            judulTabel.add("Materi");
            judulTabel.add("Nilai");
            Vector isiData= tampildata();
            jTabledatanilai.setModel(new DefaultTableModel(isiData, judulTabel));
            hitungnilaiakhir();
            }
            else{
            JOptionPane.showMessageDialog(null,"No ID Belum Terdaftar");
            statement.close();
            koneksi.close();
                }
        }catch(Exception ex){
                JOptionPane.showMessageDialog(null,"Silahkan input nilai ");
        }
    }//GEN-LAST:event_btncekidActionPerformed

    private void btnsimpannilaiakhirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsimpannilaiakhirActionPerformed
            hapusdatanilaiakhir(txtidpeserta.getText());
            addDatanilaiakhir (txtidpeserta.getText(),txtnilaiakhir.getText());     
       int nilaiakhir = Integer.parseInt(txtnilaiakhir.getText());
       if (nilaiakhir > 70){
           JOptionPane.showMessageDialog(null,"PESERTA DINYATAKAN KOMPETENT");
           updateData();
           btncekid.setEnabled(true);
            txtkodemateri.setText("");
 //        txtmateri.setText("");
         txtnilai.setText("");
         txtnama.setText("");
         txtidpeserta.setText(""); 
        txtidpeserta.setEnabled(true);
         txtnilaiakhir.setText("");
       txtnamamateri.setText("");
         kosongkantable();
       } else {
           JOptionPane.showMessageDialog(null,"PESERTA BELUM KOMPETENT"); 
               }
            
    }//GEN-LAST:event_btnsimpannilaiakhirActionPerformed

    private void btnbatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbatalActionPerformed
        btncekid.setEnabled(true);
        txtkodemateri.setText("");
 //       txtmateri.setText("");
        txtnilai.setText("");
        txtnama.setText("");
        txtidpeserta.setText(""); 
       txtidpeserta.setEnabled(true);
       txtnilaiakhir.setText("");
       txtkodemateri.setEditable(true);
       txtnamamateri.setText("");
       kosongkantable();
    }//GEN-LAST:event_btnbatalActionPerformed

    private void btnhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhapusActionPerformed
      hapusdata(txtidpeserta.getText(),txtkodemateri.getText());
       
         Vector judulTabel = new Vector();
        judulTabel.add("Kode Materi");
        judulTabel.add("Materi");
        judulTabel.add("Nilai");
        Vector isiTable=tampildata();
        jTabledatanilai.setModel(new DefaultTableModel(isiTable, judulTabel));
        hitungnilaiakhir();
        txtkodemateri.setText("");
       txtnamamateri.setText("");
       txtnilai.setText("");
    }//GEN-LAST:event_btnhapusActionPerformed

    private void jTabledatanilaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabledatanilaiMouseClicked
       
     DefaultTableModel model = (DefaultTableModel)jTabledatanilai.getModel();
        int baris = jTabledatanilai.getSelectedRow();
    int kolom = jTabledatanilai.getSelectedColumn();
    
    String dataTerpilih = jTabledatanilai.getValueAt(baris, kolom).toString();
    String kolom1 = jTabledatanilai.getValueAt(baris, 0).toString();
    String kolom2 = jTabledatanilai.getValueAt(baris, 1).toString();
    String kolom3 = jTabledatanilai.getValueAt(baris, 2).toString();
   txtkodemateri.setText(kolom1);
   txtnamamateri.setText(kolom2);
   //txtmateri.setText(kolom3);
   txtnilai.setText(kolom3);
        
 
    }//GEN-LAST:event_jTabledatanilaiMouseClicked

    private void txtnamamateriMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtnamamateriMouseClicked
       String param = txtkodemateri.getText();  
         try{
          Class.forName("com.mysql.jdbc.Driver").newInstance();
            com.mysql.jdbc.Connection koneksi = (com.mysql.jdbc.Connection) DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/db_ppkpijakarta", "root", "");
            com.mysql.jdbc.Statement statement = (com.mysql.jdbc.Statement) koneksi.createStatement();
            String sql="SELECT materi.materi FROM materi WHERE materi.kd_materi like '"+param+"'";
             ResultSet rs = statement.executeQuery(sql);
            if (rs.next())  {
            txtnamamateri.setText(rs.getString(1));
            txtkodemateri.setEditable(false);
            }
            else{
            JOptionPane.showMessageDialog(null,"Kode Materi Belum Terdaftar");
            statement.close();
            koneksi.close();
                }
        }catch(Exception ex){
                JOptionPane.showMessageDialog(null,"Silahkan input nilai ");
        }
    }//GEN-LAST:event_txtnamamateriMouseClicked

    private void txtnamamateriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnamamateriActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnamamateriActionPerformed

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
            java.util.logging.Logger.getLogger(inputnilai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(inputnilai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(inputnilai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(inputnilai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                inputnilai dialog = new inputnilai(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnbatal;
    private javax.swing.JButton btncekid;
    private javax.swing.JButton btnhapus;
    private javax.swing.JButton btnsimpannilaiakhir;
    private javax.swing.JButton btntambah;
    private javax.swing.JPanel header;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTabledatanilai;
    private javax.swing.JTextField txtidpeserta;
    private javax.swing.JTextField txtkodemateri;
    private javax.swing.JTextField txtnama;
    private javax.swing.JTextField txtnamamateri;
    private javax.swing.JTextField txtnilai;
    private javax.swing.JTextField txtnilaiakhir;
    // End of variables declaration//GEN-END:variables
}
