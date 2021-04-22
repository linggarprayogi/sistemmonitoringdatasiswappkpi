/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemmonitoringdatasiswappkpi;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author prayogi
 */
public class forminputdatapeserta extends javax.swing.JDialog {

     Statement stat;
   ResultSet set;
   java.sql.PreparedStatement ps;
   String sql;
   File filenya ;
   BufferedImage foto ;
   String s;
    public forminputdatapeserta(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        jdatetgllahir.setDateFormatString("dd-MM-yyyy");
    }

    public void inputDataPeserta(String id_peserta, String nama_peserta, String jenis_kelamin,String tempat_lahir,String tgl_lahir,String jurusan, 
              String angkatan,String no_hp, String alamat, String pendidikan_terakhir, String status_kerja, String pasfoto){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection koneksi = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_ppkpijakarta","root","");
            String query = "INSERT INTO peserta VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement prestat = koneksi.prepareStatement(query);
            InputStream is =new FileInputStream(new File(s));
            prestat.setString(1, id_peserta);
            prestat.setString(2, nama_peserta);
            prestat.setString(3, jenis_kelamin);
            prestat.setString(4, tempat_lahir);
            prestat.setString(5, tgl_lahir);
            prestat.setString(6, jurusan);
            prestat.setString(7, angkatan);
            prestat.setString(8, no_hp);
            prestat.setString(9, alamat);
            prestat.setString(10, pendidikan_terakhir);
            prestat.setString(11, status_kerja);
            prestat.setBlob(12, is);
            prestat.executeUpdate();
            koneksi.close();
        } catch (Exception ex){
            JOptionPane.showMessageDialog(null, "QUERY ERROR"+ex,"ERROR",JOptionPane.ERROR_MESSAGE);
        }
    }
     
     public void kosongnilaidatapeserta(){
     txtidpeserta.setText("");
     txtnamapeserta.setText("");
     txttempatlahir.setText("");
     jdatetgllahir.setDate(null);
     txtalamat.setText("");
     txtnohp.setText("");
     lblpasfoto.setIcon(null);
     txtdirectory.setText("");
     
    }
     
      public Vector tampildata(){
        Vector baris = new Vector();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection koneksi = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_ppkpijakarta", "root", "");
            String query = "SELECT * FROM peserta ORDER BY id_peserta";
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
      
      public void editData(String nama_peserta, String jenis_kelamin,String tempat_lahir,String tgl_lahir,String jurusan, 
              String angkatan,String no_hp, String alamat, String pendidikan_terakhir, String status){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection koneksi = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_ppkpijakarta","root","");
            String query = "UPDATE peserta SET nama_peserta=?,jenis_kelamin=?,tempat_lahir=?,tgl_lahir=?,jurusan=?,angkatan=?"
                    + ",no_hp=?,alamat=?,pendidikan_terakhir=?,status=? WHERE id_peserta='"+txtidpeserta.getText()+"'";
            PreparedStatement prestat = koneksi.prepareStatement(query);
            prestat.setString(1, nama_peserta);
            prestat.setString(2, jenis_kelamin);
            prestat.setString(3, tempat_lahir);
            prestat.setString(4, tgl_lahir);
            prestat.setString(5, jurusan);
            prestat.setString(6, angkatan);
            prestat.setString(7, no_hp);
            prestat.setString(8, alamat);
            prestat.setString(9, pendidikan_terakhir);
            prestat.setString(10, status);
            prestat.executeUpdate();
            koneksi.close();
        } catch (Exception ex){
            JOptionPane.showMessageDialog(null, "QUERY ERROR"+ex,"ERROR",JOptionPane.ERROR_MESSAGE);
        }}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jpanelinputdatapeserta = new javax.swing.JPanel();
        header = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        inputdata = new javax.swing.JPanel();
        jRadioButton4 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jLabel15 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        comboxstatus = new javax.swing.JComboBox<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtalamat = new javax.swing.JTextArea();
        jLabel16 = new javax.swing.JLabel();
        txtidpeserta = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtnamapeserta = new javax.swing.JTextField();
        jRadioButton1 = new javax.swing.JRadioButton();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txttempatlahir = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        comboxjurusan = new javax.swing.JComboBox<>();
        txtnohp = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        comboxpendidikanterakhir = new javax.swing.JComboBox<>();
        jRadioButton5 = new javax.swing.JRadioButton();
        jdatetgllahir = new com.toedter.calendar.JDateChooser();
        jButton5 = new javax.swing.JButton();
        btnsimpan = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanelfoto = new javax.swing.JPanel();
        lblpasfoto = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        txtdirectory = new javax.swing.JTextField();
        btncetak = new javax.swing.JButton();
        btnrefresh = new javax.swing.JButton();
        btnedit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jpanelinputdatapeserta.setForeground(new java.awt.Color(255, 255, 255));

        header.setBackground(new java.awt.Color(66, 139, 202));

        jLabel13.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("INPUT DATA PESERTA");

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

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Peserta", "Nama Peserta", "Jenis Kelamin", "Tempat Lahir", "Tanggal Lahir", "Jurusan", "No Hp", "Alamat", "Pendidikan Terakhir", "Status"
            }
        ));
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable1);

        inputdata.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(66, 139, 202)));

        buttonGroup1.add(jRadioButton4);
        jRadioButton4.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jRadioButton4.setText("Pria");
        jRadioButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton4ActionPerformed(evt);
            }
        });

        buttonGroup2.add(jRadioButton3);
        jRadioButton3.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jRadioButton3.setText("2");
        jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton3ActionPerformed(evt);
            }
        });

        buttonGroup2.add(jRadioButton2);
        jRadioButton2.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jRadioButton2.setText("1");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        jLabel15.setText("Pendidikan Terakhir");

        jLabel14.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        jLabel14.setText("Status");

        comboxstatus.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        comboxstatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--- STATUS ---", "PESERTA", "LULUS" }));
        comboxstatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboxstatusActionPerformed(evt);
            }
        });

        txtalamat.setColumns(20);
        txtalamat.setRows(5);
        txtalamat.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jScrollPane4.setViewportView(txtalamat);

        jLabel16.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        jLabel16.setText("Alamat");

        txtidpeserta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtidpesertaActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        jLabel17.setText("ID Peserta");

        jLabel18.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        jLabel18.setText("Nama Peserta");

        txtnamapeserta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnamapesertaActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jRadioButton1.setText("Wanita");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        jLabel19.setText("Jenis Kelamin");

        jLabel20.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        jLabel20.setText("Tempat Lahir");

        txttempatlahir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttempatlahirActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        jLabel21.setText("Tanggal Lahir");

        jLabel22.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        jLabel22.setText("Kejuruan");

        comboxjurusan.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        comboxjurusan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--- PILIH JURUSAN ---", "Teknik Informatika", "Bahasa Inggris", "Autocad", "Listrik Industri", "Listrik Instalasi Penerangan", "Elekronika Industri", "Kendaraan Ringan", "Lemari Pendingin", "Tata Udara", "CNC", "Las Listrik", "Akuntansi Komputer", "Administrasi Kantor", "Tata busana", "AutoCAD" }));
        comboxjurusan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboxjurusanActionPerformed(evt);
            }
        });

        txtnohp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnohpActionPerformed(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        jLabel23.setText("Angkatan");

        jLabel24.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        jLabel24.setText("No.Hp");

        comboxpendidikanterakhir.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        comboxpendidikanterakhir.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--- PENDIDIKAN TERAKHIR ---", "SD", "SMP/MTS", "SMA/SMK/MA", "S1", "S2", "S3" }));
        comboxpendidikanterakhir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboxpendidikanterakhirActionPerformed(evt);
            }
        });

        buttonGroup2.add(jRadioButton5);
        jRadioButton5.setText("3");
        jRadioButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout inputdataLayout = new javax.swing.GroupLayout(inputdata);
        inputdata.setLayout(inputdataLayout);
        inputdataLayout.setHorizontalGroup(
            inputdataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inputdataLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(inputdataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(inputdataLayout.createSequentialGroup()
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtidpeserta, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addGroup(inputdataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)))
                    .addGroup(inputdataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(inputdataLayout.createSequentialGroup()
                            .addGap(2, 2, 2)
                            .addGroup(inputdataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(inputdataLayout.createSequentialGroup()
                                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(comboxjurusan, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(inputdataLayout.createSequentialGroup()
                                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jRadioButton2)
                                    .addGap(18, 18, 18)
                                    .addComponent(jRadioButton3)
                                    .addGap(18, 18, 18)
                                    .addComponent(jRadioButton5)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(inputdataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(inputdataLayout.createSequentialGroup()
                                    .addGap(150, 150, 150)
                                    .addComponent(txtnohp, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(inputdataLayout.createSequentialGroup()
                            .addGroup(inputdataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(inputdataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(inputdataLayout.createSequentialGroup()
                                    .addGroup(inputdataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(inputdataLayout.createSequentialGroup()
                                            .addGap(20, 20, 20)
                                            .addComponent(jRadioButton4)
                                            .addGap(16, 16, 16)
                                            .addComponent(jRadioButton1))
                                        .addGroup(inputdataLayout.createSequentialGroup()
                                            .addGap(18, 18, 18)
                                            .addComponent(txtnamapeserta, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(inputdataLayout.createSequentialGroup()
                                            .addGap(18, 18, 18)
                                            .addGroup(inputdataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jdatetgllahir, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txttempatlahir, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGap(182, 182, 182)
                                    .addGroup(inputdataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(comboxpendidikanterakhir, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, inputdataLayout.createSequentialGroup()
                                    .addGap(220, 220, 220)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(comboxstatus, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        inputdataLayout.setVerticalGroup(
            inputdataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inputdataLayout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addGroup(inputdataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txtidpeserta, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(comboxpendidikanterakhir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(inputdataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(inputdataLayout.createSequentialGroup()
                        .addGroup(inputdataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(inputdataLayout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addGroup(inputdataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(inputdataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jRadioButton4)
                                        .addComponent(jLabel19))
                                    .addComponent(jRadioButton1))
                                .addGap(2, 2, 2)
                                .addGroup(inputdataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txttempatlahir, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel20)))
                            .addGroup(inputdataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel18)
                                .addComponent(txtnamapeserta, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(inputdataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(inputdataLayout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(jLabel21))
                            .addGroup(inputdataLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jdatetgllahir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(8, 8, 8)
                        .addGroup(inputdataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboxjurusan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22))
                        .addGap(8, 8, 8)
                        .addGroup(inputdataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jRadioButton2)
                            .addComponent(jLabel23)
                            .addComponent(jRadioButton3)
                            .addComponent(jRadioButton5)))
                    .addGroup(inputdataLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(inputdataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(inputdataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboxstatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))
                        .addGap(18, 18, 18)
                        .addGroup(inputdataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtnohp, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel24)))))
        );

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Close-ic.png"))); // NOI18N
        jButton5.setText("Batal");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        btnsimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/gtk-save-as.png"))); // NOI18N
        btnsimpan.setText("Simpan");
        btnsimpan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsimpanActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(66, 139, 202)));

        jPanelfoto.setBackground(new java.awt.Color(204, 204, 255));
        jPanelfoto.setBorder(javax.swing.BorderFactory.createTitledBorder("Foto"));
        jPanelfoto.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblpasfoto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblpasfoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/cacavav.png"))); // NOI18N
        jPanelfoto.add(lblpasfoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 18, 110, 137));

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/srchdokmn.png"))); // NOI18N
        jButton7.setText("Browse");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        txtdirectory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdirectoryActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(44, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelfoto, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtdirectory, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelfoto, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(txtdirectory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(10, Short.MAX_VALUE))
        );

        btncetak.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/print.png"))); // NOI18N
        btncetak.setText("Cetak Kartu Peserta");
        btncetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncetakActionPerformed(evt);
            }
        });

        btnrefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/lihat.png"))); // NOI18N
        btnrefresh.setText("Refresh");
        btnrefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnrefreshActionPerformed(evt);
            }
        });

        btnedit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/editdata.png"))); // NOI18N
        btnedit.setText("Edit");
        btnedit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneditActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpanelinputdatapesertaLayout = new javax.swing.GroupLayout(jpanelinputdatapeserta);
        jpanelinputdatapeserta.setLayout(jpanelinputdatapesertaLayout);
        jpanelinputdatapesertaLayout.setHorizontalGroup(
            jpanelinputdatapesertaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanelinputdatapesertaLayout.createSequentialGroup()
                .addGroup(jpanelinputdatapesertaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(header, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jpanelinputdatapesertaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jpanelinputdatapesertaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpanelinputdatapesertaLayout.createSequentialGroup()
                                .addComponent(btnrefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btncetak, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(342, 342, 342)
                                .addComponent(btnsimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnedit, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton5))
                            .addGroup(jpanelinputdatapesertaLayout.createSequentialGroup()
                                .addComponent(inputdata, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 936, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpanelinputdatapesertaLayout.setVerticalGroup(
            jpanelinputdatapesertaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanelinputdatapesertaLayout.createSequentialGroup()
                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpanelinputdatapesertaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inputdata, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpanelinputdatapesertaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpanelinputdatapesertaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton5)
                        .addComponent(btnsimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnedit))
                    .addGroup(jpanelinputdatapesertaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnrefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btncetak)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                .addContainerGap())
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

    private void txtdirectoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdirectoryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdirectoryActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG,GIF,and PNG Images","jpg","gif","png");
        chooser.setFileFilter(filter);
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        String dir = f.getAbsolutePath();

        txtdirectory.setText(dir);
        s = dir;
        try{
            Image image = ImageIO.read(f);
            ImageIcon imageIcon = new ImageIcon(image.getScaledInstance(110,127,300));

            lblpasfoto.setIcon(imageIcon);
            lblpasfoto.setText("");

        }
        catch (Exception e){
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void btnsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsimpanActionPerformed
        // TODO add your handling code here:
        SimpleDateFormat fm = new SimpleDateFormat("dd-MM-yyyy");
        String tgllahir = String.valueOf(fm.format(jdatetgllahir.getDate()));

        int tanya = JOptionPane.showConfirmDialog(null, "ANDA YAKIN DATA INI INGIN DISIMPAN?", "INFORMASI", JOptionPane.CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (tanya == JOptionPane.OK_OPTION) {

            inputDataPeserta(txtidpeserta.getText(),txtnamapeserta.getText(),gender,txttempatlahir.getText(),tgllahir,
                (String) comboxjurusan.getSelectedItem(),Angkatan,txtnohp.getText(),txtalamat.getText(),(String) comboxpendidikanterakhir.getSelectedItem(),
                (String) comboxstatus.getSelectedItem(),lblpasfoto.getText());

            JOptionPane.showMessageDialog(null, "DATA BERHASIL DISIMPAN", "INFORMASI", JOptionPane.INFORMATION_MESSAGE);

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
            Vector isiData = tampildata();
            jTable1.setModel(new DefaultTableModel(isiData, judulTabel));
            kosongnilaidatapeserta();

        } else { JOptionPane.showMessageDialog(null, "DATA TIDAK DAPAT DISIMPAN", "INFORMASI", JOptionPane.INFORMATION_MESSAGE);}
    }//GEN-LAST:event_btnsimpanActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        kosongnilaidatapeserta();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jRadioButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton5ActionPerformed
        Angkatan="3";
    }//GEN-LAST:event_jRadioButton5ActionPerformed

    private void comboxpendidikanterakhirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboxpendidikanterakhirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboxpendidikanterakhirActionPerformed

    private void txtnohpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnohpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnohpActionPerformed

    private void comboxjurusanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboxjurusanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboxjurusanActionPerformed

    private void txttempatlahirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttempatlahirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttempatlahirActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // TODO add your handling code here:
        gender="wanita";
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void txtnamapesertaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnamapesertaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnamapesertaActionPerformed

    private void txtidpesertaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtidpesertaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtidpesertaActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        // TODO add your handling code here:
        Angkatan="1";
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton3ActionPerformed
        // TODO add your handling code here:
        Angkatan="2";
    }//GEN-LAST:event_jRadioButton3ActionPerformed

    private void jRadioButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton4ActionPerformed
        // TODO add your handling code here:
        gender="pria";
    }//GEN-LAST:event_jRadioButton4ActionPerformed

    private void comboxstatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboxstatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboxstatusActionPerformed

    private void btncetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncetakActionPerformed
        SimpleDateFormat date;
                date = new SimpleDateFormat("dd/MM/yyyy");
        try {
    Class.forName("com.mysql.jdbc.Driver").newInstance();
    Connection koneksi = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_ppkpijakarta", "root", "");
    HashMap parameter =new HashMap();
    parameter.put("idpeserta",txtidpeserta.getText());
    File file = new File("src/sistemmonitoringdatasiswappkpi/kartupeserta.jrxml");
    JasperDesign JasperDesign = JRXmlLoader.load(file);
    JasperReport JasperReport = JasperCompileManager.compileReport(JasperDesign);
    JasperPrint jp = JasperFillManager.fillReport(JasperReport,parameter,koneksi );
   // JasperExportManager.exportReportToPdfFile(jp,"C:/Users/prayogi/Documents/laporandatapeserta.pdf");
    JasperViewer.viewReport(jp, false);
    this.dispose();
    } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data tidak dapat dicetak!","Cetak Data",JOptionPane.ERROR_MESSAGE);
    }     }//GEN-LAST:event_btncetakActionPerformed

    private void btnrefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnrefreshActionPerformed
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
            Vector isiData = tampildata();
            jTable1.setModel(new DefaultTableModel(isiData, judulTabel));
    }//GEN-LAST:event_btnrefreshActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        try{
     DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
        int baris = jTable1.getSelectedRow();
    int kolom = jTable1.getSelectedColumn();
    
    String dataTerpilih = jTable1.getValueAt(baris, kolom).toString();
    String kolom1 = jTable1.getValueAt(baris, 0).toString();
    String kolom2 = jTable1.getValueAt(baris, 1).toString();
    String kolom4 = jTable1.getValueAt(baris, 3).toString();
    String kolom6 = jTable1.getValueAt(baris, 5).toString();
    String kolom7 = jTable1.getValueAt(baris, 6).toString();
    String kolom8 = jTable1.getValueAt(baris, 7).toString();
    String kolom9 = jTable1.getValueAt(baris, 8).toString();
    String kolom10 = jTable1.getValueAt(baris, 9).toString();
    String kolom11 = jTable1.getValueAt(baris, 10).toString();
    
    txtidpeserta.setText(kolom1);
    txtnamapeserta.setText(kolom2);
   
   txttempatlahir.setText(kolom4);
   java.util.Date date = new SimpleDateFormat("dd-MM-yyyy").parse((String)model.getValueAt(baris,4));
   jdatetgllahir.setDate(date);
   comboxjurusan.setSelectedItem(kolom6);
   txtalamat.setText(kolom9);
   txtnohp.setText(kolom8);
   comboxpendidikanterakhir.setSelectedItem(kolom10);
   comboxstatus.setSelectedItem(kolom11);
 } catch (ParseException ex){
      Logger.getLogger(forminputdatapeserta.class.getName()).log(Level.SEVERE, null, ex);
 }
    }//GEN-LAST:event_jTable1MouseClicked

    private void btneditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditActionPerformed
     int tanya = JOptionPane.showConfirmDialog(null, "ANDA YAKIN INGIN MENYIMPAN PERUBAHAN DATA?", "INFORMASI", JOptionPane.CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (tanya == JOptionPane.OK_OPTION) {
             SimpleDateFormat fm = new SimpleDateFormat("dd-MM-yyyy");
        String tgllahir = String.valueOf(fm.format(jdatetgllahir.getDate()));
            
            editData(txtnamapeserta.getText(),gender,txttempatlahir.getText(),tgllahir,
                (String) comboxjurusan.getSelectedItem(),Angkatan,txtnohp.getText(),txtalamat.getText(),(String) comboxpendidikanterakhir.getSelectedItem(),
                (String) comboxstatus.getSelectedItem());
                   
            JOptionPane.showMessageDialog(null, "DATA BERHASIL DISIMPAN", "INFORMASI", JOptionPane.INFORMATION_MESSAGE);
   
        }
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
            Vector isiData = tampildata();
            jTable1.setModel(new DefaultTableModel(isiData, judulTabel));
            kosongnilaidatapeserta();
    }//GEN-LAST:event_btneditActionPerformed

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
            java.util.logging.Logger.getLogger(forminputdatapeserta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(forminputdatapeserta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(forminputdatapeserta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(forminputdatapeserta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                forminputdatapeserta dialog = new forminputdatapeserta(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btncetak;
    private javax.swing.JButton btnedit;
    private javax.swing.JButton btnrefresh;
    private javax.swing.JButton btnsimpan;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> comboxjurusan;
    private javax.swing.JComboBox<String> comboxpendidikanterakhir;
    private javax.swing.JComboBox<String> comboxstatus;
    private javax.swing.JPanel header;
    private javax.swing.JPanel inputdata;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanelfoto;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JRadioButton jRadioButton5;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    private com.toedter.calendar.JDateChooser jdatetgllahir;
    private javax.swing.JPanel jpanelinputdatapeserta;
    private javax.swing.JLabel lblpasfoto;
    private javax.swing.JTextArea txtalamat;
    private javax.swing.JTextField txtdirectory;
    private javax.swing.JTextField txtidpeserta;
    private javax.swing.JTextField txtnamapeserta;
    private javax.swing.JTextField txtnohp;
    private javax.swing.JTextField txttempatlahir;
    // End of variables declaration//GEN-END:variables
private String gender;
private String Angkatan;
}
