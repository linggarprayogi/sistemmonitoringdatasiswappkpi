package sistemmonitoringdatasiswappkpi;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Vector;
import java.util.logging.Logger;
import java.util.logging.Level;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author prayogi
 */
public class homemenu extends javax.swing.JFrame {
 java.sql.Statement stat;
   ResultSet set;
   PreparedStatement ps;
   String sql;
   Connection koneksi;
  
   java.util.Date tglsekarang = new java.util.Date();
    private SimpleDateFormat smpdtfmt = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
    //diatas adalah pengaturan format penulisan, bisa diubah sesuai keinginan.
    private String tanggal = smpdtfmt.format(tglsekarang);
    private com.mysql.jdbc.Statement stm;
    private com.mysql.jdbc.Connection Con;
    
    
    /**
     * Creates new form homemenu
     */
    public homemenu() {
        initComponents();
        sembunyikan(true);
        Locale locale = new Locale("id","ID");
        Locale.setDefault(locale);
        lbltgl.setText(tanggal);
        txttanggal.setText(lbltgl.getText());
        setJam();
        tampiltabel();
        tombolaktif(true);
    }
    
        public void konekdatabase(){
            try{
                Class.forName("com.mysql.jdbc.Driver");
                koneksi=DriverManager.getConnection("jdbc:mysql://localhost:3306/db_ppkpijakarta","root","");
                }
            catch (ClassNotFoundException | SQLException e){
                System.err.println("Exception: "+e.getMessage());
                                                            }
                                    }
    
        
    public void buttontampil(boolean t){
        btninputdatapeserta.setEnabled(!t);
        btninputdatainstruktur.setEnabled(!t);
   //     btnlihatdatapeserta.setEnabled(!t);
        btnlihatdatainstruktur.setEnabled(!t);
    //    btnupdatedatapeserta.setEnabled(!t);
        btnupdatedataabsensi.setEnabled(!t);
        btnlaporandatapeserta.setEnabled(!t);
        btnlaporandataabsensi.setEnabled(!t);
    } 
    
    public void Login(){
    try
    {
    String hasil ="0";
  
    String sql = "Select * from user where username = '"+txtusername.getText().trim()+"'"+"and password = '"+String.valueOf(txtpassword.getPassword())+"'";
    stat = koneksi.createStatement();
    set =stat.executeQuery(sql);
    
    if (set.next())
    {
        if (set.getString("level").equals("Admin")){
        jPanel10.setVisible(false);
        jpanelmenuutama.setVisible(true);
//         btnlihatdatapeserta.setEnabled(true);
        btnlihatdatainstruktur.setEnabled(true);
//        btnupdatedatapeserta.setEnabled(true);
        btnupdatedataabsensi.setEnabled(true);
        btnlaporandatapeserta.setEnabled(true);
        btnlaporandataabsensi.setEnabled(true);
        }
        else if (set.getString("level").equals("Instruktur")){
        jPanel10.setVisible(false);
        jpanelmenuutama.setVisible(true);
        btninputdatapeserta.setEnabled(false);
        btninputdatainstruktur.setEnabled(false);
//        btnupdatedatapeserta.setEnabled(false);
        }
        else if (set.getString("level").equals("Kepala Kejuruan")){
        jPanel10.setVisible(false);
        jpanelmenuutama.setVisible(true);
        btninputdatapeserta.setEnabled(false);
        btnupdatedataabsensi.setEnabled(false);
        btnlaporandataabsensi.setEnabled(false);
        }

    else {
        JOptionPane.showMessageDialog(null,"Anda Gagal Login!!! Username dan Password tidak cocok","Peringatan",JOptionPane.WARNING_MESSAGE);
    txtusername.setText("");
    txtpassword.setText("");
    txtusername.requestFocus();
        }}}
   catch (Exception e){
       Logger.getLogger(homemenu.class.getName()).log(Level.SEVERE, null, e);
        System.out.println("kesalahan :" +e.toString());
    } 
    }
        
     public void sembunyikan(boolean t){
        txtnama.setVisible(!t);
        txtkejuruan.setVisible(!t);
        txtjam.setVisible(!t);
        txttanggal.setVisible(!t);
        comboxketerangan.setVisible(!t);
        btncariid.setVisible(!t);
    }
    
    public void tombolaktif(boolean t){
        btnabsen.setEnabled(!t);
        btnbatal.setEnabled(!t);
    }

    public final void setJam(){
ActionListener taskPerformer = new ActionListener() {

            @Override
public void actionPerformed(ActionEvent evt) {
String nol_jam = "", nol_menit = "",nol_detik = "";

java.util.Date dateTime = new java.util.Date();
int nilai_jam = dateTime.getHours();
int nilai_menit = dateTime.getMinutes();
int nilai_detik = dateTime.getSeconds();

if(nilai_jam <= 9) nol_jam= "0";
if(nilai_menit <= 9) nol_menit= "0";
if(nilai_detik <= 9) nol_detik= "0";

String jam = nol_jam + Integer.toString(nilai_jam);
String menit = nol_menit + Integer.toString(nilai_menit);
String detik = nol_detik + Integer.toString(nilai_detik);

lblwaktu.setText(jam+":"+menit+":"+detik+"");
}
};
new Timer(1000, taskPerformer).start();
}
 
    public void inputAbsenPeserta(String id_peserta, String tanggal, String jam, String keterangan){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            java.sql.Connection koneksi = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_ppkpijakarta","root","");
            String query = "INSERT INTO absensi VALUES (?,?,?,?)";
            PreparedStatement prestat = koneksi.prepareStatement(query);
            prestat.setString(1, id_peserta);
            prestat.setString(2, tanggal);
            prestat.setString(3, jam);
            prestat.setString(4, keterangan);
            prestat.executeUpdate();
            koneksi.close();
        } catch (Exception ex){
            JOptionPane.showMessageDialog(null, "QUERY ERROR"+ex,"ERROR",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public Vector tampildata(){
        Vector baris = new Vector();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            java.sql.Connection koneksi = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_ppkpijakarta", "root", "");
            String query = "SELECT * From absensi WHERE tanggal='"+txttanggal.getText().trim()+"'ORDER BY jam DESC";
            //String query = "SELECT peserta.id_peserta,peserta.nama_peserta,peserta.jurusan,absensi.tanggal,absensi.jam,absensi.keterangan"
                //    + " FROM absensi,peserta WHERE absensi.tanggal='"+txttanggal.getText().trim()+"' and peserta.id_peserta='"+txtidpeserta.getText().trim()+"'ORDER BY jam DESC";
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
    
    public void tampiltabel(){
    Vector judulTabel = new Vector();
        judulTabel.add("ID peserta");
        judulTabel.add("Tanggal");
        judulTabel.add("Jam");
        judulTabel.add("Keterangan");
        Vector isiData = tampildata();
        tblabsen.setModel(new DefaultTableModel(isiData, judulTabel)); 
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")                     
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jTabbedPanehomemenu = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        PanelLogin = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        txtusername = new javax.swing.JTextField();
        txtpassword = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        Panelabsensi = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblabsen = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        btncariid = new javax.swing.JButton();
        jPanelimage = new javax.swing.JPanel();
        lblimage = new javax.swing.JLabel();
        txtkejuruan = new javax.swing.JTextField();
        txttanggal = new javax.swing.JTextField();
        txtnama = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        btnabsen = new javax.swing.JButton();
        txtidpeserta = new javax.swing.JTextField();
        txtjam = new javax.swing.JTextField();
        comboxketerangan = new javax.swing.JComboBox<>();
        lbltgl = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblwaktu = new javax.swing.JLabel();
        btnbatal = new javax.swing.JButton();
        jpanelmenuutama = new javax.swing.JPanel();
        btninputdatapeserta = new javax.swing.JButton();
        btninputdatainstruktur = new javax.swing.JButton();
        btnupdatedataabsensi = new javax.swing.JButton();
        btnlaporandatapeserta = new javax.swing.JButton();
        btnlaporandataabsensi = new javax.swing.JButton();
        btnlihatdatainstruktur = new javax.swing.JButton();
        btnlogout = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistem Informasi Absensi dan Monitoring Data Peserta Pusat Pelatihan Kerja Pengembangan Industri");
        setPreferredSize(new java.awt.Dimension(1200, 610));

        jPanel2.setLayout(new java.awt.CardLayout());

        jPanel10.setPreferredSize(new java.awt.Dimension(1200, 610));

        jTabbedPanehomemenu.setForeground(new java.awt.Color(99, 128, 177));
        jTabbedPanehomemenu.setPreferredSize(new java.awt.Dimension(1397, 610));

        jPanel3.setBackground(new java.awt.Color(99, 128, 177));
        jPanel3.setPreferredSize(new java.awt.Dimension(1397, 610));
        jPanel3.setLayout(new java.awt.CardLayout());

        PanelLogin.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setForeground(new java.awt.Color(0, 0, 0));

        txtusername.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txtusername.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(66, 139, 202), 1, true));
        txtusername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtusernameActionPerformed(evt);
            }
        });

        txtpassword.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txtpassword.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(66, 139, 202), 1, true));

        jButton1.setBackground(new java.awt.Color(40, 40, 40));
        jButton1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Login into your account");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(40, 40, 40));
        jLabel2.setText("Username");

        jLabel7.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(40, 40, 40));
        jLabel7.setText("Password");

        jPanel5.setBackground(new java.awt.Color(66, 139, 202));

        jLabel1.setBackground(new java.awt.Color(66, 139, 202));
        jLabel1.setFont(new java.awt.Font("Dialog", 1, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("LOGIN");

        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/dki_login_logo.png"))); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(151, 151, 151))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(0, 9, Short.MAX_VALUE)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(50, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                            .addComponent(txtusername, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(47, 47, 47))
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtpassword, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addContainerGap()))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 345, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtusername, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtpassword, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout PanelLoginLayout = new javax.swing.GroupLayout(PanelLogin);
        PanelLogin.setLayout(PanelLoginLayout);
        PanelLoginLayout.setHorizontalGroup(
            PanelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelLoginLayout.createSequentialGroup()
                .addGap(394, 394, 394)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(461, Short.MAX_VALUE))
        );
        PanelLoginLayout.setVerticalGroup(
            PanelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelLoginLayout.createSequentialGroup()
                .addGap(108, 108, 108)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(163, Short.MAX_VALUE))
        );

        jPanel3.add(PanelLogin, "card4");

        jTabbedPanehomemenu.addTab("LOGIN ADMIN", jPanel3);

        Panelabsensi.setBackground(new java.awt.Color(255, 255, 255));

        jPanel6.setBackground(new java.awt.Color(66, 139, 202));

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 30)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("PUSAT PELATIHAN KERJA PENGEMBANGAN INDUSTRI");
        jLabel9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/logo_dki_mini copy.png"))); // NOI18N

        jLabel16.setFont(new java.awt.Font("Dialog", 1, 33)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("ABSENSI PESERTA");
        jLabel16.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 1042, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 1006, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
        );

        tblabsen.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Peserta", "Nama", "Kejuruan", "Tanggal", "Jam", "Keterangan"
            }
        ));
        jScrollPane2.setViewportView(tblabsen);
        if (tblabsen.getColumnModel().getColumnCount() > 0) {
            tblabsen.getColumnModel().getColumn(1).setHeaderValue("Nama");
            tblabsen.getColumnModel().getColumn(2).setHeaderValue("Kejuruan");
        }

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btncariid.setText("Cari ID");
        btncariid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncariidActionPerformed(evt);
            }
        });
        jPanel1.add(btncariid, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 430, -1, 30));

        jPanelimage.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Foto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N

        lblimage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblimage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/cacavav.png"))); // NOI18N

        javax.swing.GroupLayout jPanelimageLayout = new javax.swing.GroupLayout(jPanelimage);
        jPanelimage.setLayout(jPanelimageLayout);
        jPanelimageLayout.setHorizontalGroup(
            jPanelimageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblimage, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
        );
        jPanelimageLayout.setVerticalGroup(
            jPanelimageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblimage, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
        );

        jPanel1.add(jPanelimage, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 150, 190));

        txtkejuruan.setEditable(false);
        txtkejuruan.setEnabled(false);
        jPanel1.add(txtkejuruan, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 10, 114, -1));

        txttanggal.setEditable(false);
        txttanggal.setEnabled(false);
        jPanel1.add(txttanggal, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 430, 114, -1));

        txtnama.setEditable(false);
        txtnama.setEnabled(false);
        txtnama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnamaActionPerformed(evt);
            }
        });
        jPanel1.add(txtnama, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 114, -1));

        jLabel6.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Masukkan no ID");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 120, 131, -1));

        btnabsen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/absensi_ico.png"))); // NOI18N
        btnabsen.setText("Absen");
        btnabsen.setBorder(null);
        btnabsen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnabsenActionPerformed(evt);
            }
        });
        btnabsen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnabsenKeyPressed(evt);
            }
        });
        jPanel1.add(btnabsen, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 190, 90, 30));

        txtidpeserta.setBackground(new java.awt.Color(240, 241, 251));
        txtidpeserta.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(99, 164, 220), 1, true));
        txtidpeserta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtidpesertaActionPerformed(evt);
            }
        });
        txtidpeserta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtidpesertaKeyPressed(evt);
            }
        });
        jPanel1.add(txtidpeserta, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 140, 190, 30));

        txtjam.setEditable(false);
        txtjam.setEnabled(false);
        jPanel1.add(txtjam, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 10, 114, -1));

        comboxketerangan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "HADIR", "IJIN", "SAKIT", "TANPA KETERANGAN" }));
        comboxketerangan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboxketeranganActionPerformed(evt);
            }
        });
        jPanel1.add(comboxketerangan, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 430, 131, -1));

        lbltgl.setFont(new java.awt.Font("Dialog", 0, 20)); // NOI18N
        lbltgl.setForeground(new java.awt.Color(0, 0, 0));
        lbltgl.setText("dd/mm/yyyy");
        jPanel1.add(lbltgl, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 40, 131, -1));

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/calendar-icon_34471.png"))); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 40, 35, 25));

        jLabel4.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/clock-icon_34468.png"))); // NOI18N
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 70, 37, 43));

        lblwaktu.setFont(new java.awt.Font("Dialog", 1, 33)); // NOI18N
        lblwaktu.setForeground(new java.awt.Color(255, 102, 102));
        lblwaktu.setText("hh:mm:ss");
        jPanel1.add(lblwaktu, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 70, -1, -1));

        btnbatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Close-ic.png"))); // NOI18N
        btnbatal.setText("Batal");
        btnbatal.setBorder(null);
        btnbatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbatalActionPerformed(evt);
            }
        });
        jPanel1.add(btnbatal, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 190, 90, 30));

        javax.swing.GroupLayout PanelabsensiLayout = new javax.swing.GroupLayout(Panelabsensi);
        Panelabsensi.setLayout(PanelabsensiLayout);
        PanelabsensiLayout.setHorizontalGroup(
            PanelabsensiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelabsensiLayout.createSequentialGroup()
                .addGroup(PanelabsensiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(PanelabsensiLayout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 778, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PanelabsensiLayout.setVerticalGroup(
            PanelabsensiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelabsensiLayout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelabsensiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );

        jTabbedPanehomemenu.addTab("FORM ABSENSI", Panelabsensi);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1208, Short.MAX_VALUE)
            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel10Layout.createSequentialGroup()
                    .addComponent(jTabbedPanehomemenu, javax.swing.GroupLayout.PREFERRED_SIZE, 1208, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 616, Short.MAX_VALUE)
            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel10Layout.createSequentialGroup()
                    .addComponent(jTabbedPanehomemenu, javax.swing.GroupLayout.PREFERRED_SIZE, 616, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel2.add(jPanel10, "card3");

        jpanelmenuutama.setBackground(java.awt.Color.white);

        btninputdatapeserta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/737243.png"))); // NOI18N
        btninputdatapeserta.setText("INPUT DATA PESERTA");
        btninputdatapeserta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btninputdatapesertaActionPerformed(evt);
            }
        });

        btninputdatainstruktur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/instrukturicon.png"))); // NOI18N
        btninputdatainstruktur.setText("INPUT NILAI");
        btninputdatainstruktur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btninputdatainstrukturActionPerformed(evt);
            }
        });

        btnupdatedataabsensi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/update.png"))); // NOI18N
        btnupdatedataabsensi.setText("UPDATE DATA ABSENSI");
        btnupdatedataabsensi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnupdatedataabsensiActionPerformed(evt);
            }
        });

        btnlaporandatapeserta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/curriculum.png"))); // NOI18N
        btnlaporandatapeserta.setText("LAPORAN DATA PESERTA");
        btnlaporandatapeserta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlaporandatapesertaActionPerformed(evt);
            }
        });

        btnlaporandataabsensi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/analytics.png"))); // NOI18N
        btnlaporandataabsensi.setText("LAPORAN ABSENSI");
        btnlaporandataabsensi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlaporandataabsensiActionPerformed(evt);
            }
        });

        btnlihatdatainstruktur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/target (1).png"))); // NOI18N
        btnlihatdatainstruktur.setText("INPUT MATERI PELATIHAN");
        btnlihatdatainstruktur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlihatdatainstrukturActionPerformed(evt);
            }
        });

        btnlogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/logout.png"))); // NOI18N
        btnlogout.setText("LOG OUT");
        btnlogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlogoutActionPerformed(evt);
            }
        });

        jPanel11.setBackground(new java.awt.Color(66, 139, 202));

        jLabel13.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("PUSAT PELATIHAN KERJA PENGEMBANGAN INDUSTRI");
        jLabel13.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/logo_dki_mini copy.png"))); // NOI18N

        jLabel25.setFont(new java.awt.Font("Dialog", 1, 33)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("SISTEM MONITORING DAN ABSENSI DATA PESERTA");
        jLabel25.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 1042, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 1006, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jpanelmenuutamaLayout = new javax.swing.GroupLayout(jpanelmenuutama);
        jpanelmenuutama.setLayout(jpanelmenuutamaLayout);
        jpanelmenuutamaLayout.setHorizontalGroup(
            jpanelmenuutamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanelmenuutamaLayout.createSequentialGroup()
                .addGap(104, 104, 104)
                .addGroup(jpanelmenuutamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btninputdatainstruktur, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btninputdatapeserta, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE))
                .addGap(79, 79, 79)
                .addGroup(jpanelmenuutamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnlihatdatainstruktur)
                    .addComponent(btnupdatedataabsensi, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(65, 65, 65)
                .addGroup(jpanelmenuutamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnlaporandatapeserta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnlaporandataabsensi, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jpanelmenuutamaLayout.createSequentialGroup()
                        .addComponent(btnlogout)
                        .addGap(10, 10, 10)))
                .addContainerGap(144, Short.MAX_VALUE))
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jpanelmenuutamaLayout.setVerticalGroup(
            jpanelmenuutamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpanelmenuutamaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                .addGroup(jpanelmenuutamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpanelmenuutamaLayout.createSequentialGroup()
                        .addGroup(jpanelmenuutamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnlaporandataabsensi)
                            .addGroup(jpanelmenuutamaLayout.createSequentialGroup()
                                .addGroup(jpanelmenuutamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jpanelmenuutamaLayout.createSequentialGroup()
                                        .addComponent(btninputdatapeserta, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(17, 17, 17))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpanelmenuutamaLayout.createSequentialGroup()
                                        .addComponent(btnlihatdatainstruktur, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)))
                                .addGroup(jpanelmenuutamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnlaporandatapeserta)
                                    .addComponent(btnupdatedataabsensi, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btninputdatainstruktur, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(115, 115, 115))
                    .addComponent(btnlogout, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(141, 141, 141))
        );

        jPanel2.add(jpanelmenuutama, "card3");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btncariidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncariidActionPerformed
        // TODO add your handling code here:
        String id = txtidpeserta.getText();  // TODO add your handling code here:
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection koneksi = (Connection) DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/db_ppkpijakarta", "root", "");
            Statement statement = (Statement) koneksi.createStatement();
            String sql="SELECT * FROM peserta WHERE id_peserta like '"+id+"'";
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()){
                txtidpeserta.setText(rs.getString(1));
                txtnama.setText(rs.getString(2));
                txtkejuruan.setText(rs.getString(6));
                Blob blob = rs.getBlob("pasfoto");
                byte[] data = blob.getBytes(1, (int) blob.length());
                //testing
                ImageIcon icon = new ImageIcon(data);
                lblimage.setIcon(icon);
                tombolaktif(false);
            }
            else{
                JOptionPane.showMessageDialog(null,"Data tidak ditemukan");
                txtidpeserta.setText("");
                statement.close();
                koneksi.close();
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,"Data tidak ditemukan"+ex);
        }
    }//GEN-LAST:event_btncariidActionPerformed

    private void txtidpesertaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtidpesertaKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btncariidActionPerformed(new ActionEvent(evt.getSource(), evt.getID(),null));
            btnabsen.requestFocus();  }
    }//GEN-LAST:event_txtidpesertaKeyPressed

    private void btnabsenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnabsenActionPerformed
        // TODO add your handling code here:
        txtjam.setText(lblwaktu.getText());
        int tanya = JOptionPane.showConfirmDialog(null, "ANDA YAKIN DATA INI INGIN DISIMPAN?", "INFORMASI", JOptionPane.CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (tanya == JOptionPane.OK_OPTION) {

            inputAbsenPeserta(txtidpeserta.getText(),txttanggal.getText(),txtjam.getText(),(String)comboxketerangan.getSelectedItem());

            JOptionPane.showMessageDialog(null, "DATA BERHASIL DISIMPAN", "INFORMASI", JOptionPane.INFORMATION_MESSAGE);

            tampiltabel();

        }
        txtidpeserta.setText("");
        txtnama.setText("");
        txtjam.setText("");
        lblimage.setIcon(null);
        comboxketerangan.setSelectedItem("HADIR");
        tombolaktif(true);
    }//GEN-LAST:event_btnabsenActionPerformed

    private void btnabsenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnabsenKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {}
        btnabsenActionPerformed(new ActionEvent(evt.getSource(), evt.getID(),null));
    }//GEN-LAST:event_btnabsenKeyPressed

    private void comboxketeranganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboxketeranganActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboxketeranganActionPerformed

    private void txtnamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnamaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnamaActionPerformed

    private void btnbatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbatalActionPerformed
        // TODO add your handling code here:
        txtidpeserta.setText("");
        txtnama.setText("");
        txtjam.setText("");
        lblimage.setIcon(null);
        comboxketerangan.setSelectedItem("HADIR");
        tombolaktif(true);
    }//GEN-LAST:event_btnbatalActionPerformed

    private void txtusernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtusernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtusernameActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
       konekdatabase();Login();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtidpesertaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtidpesertaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtidpesertaActionPerformed

    private void btninputdatapesertaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btninputdatapesertaActionPerformed
        new forminputdatapeserta(this, rootPaneCheckingEnabled).show();
    }//GEN-LAST:event_btninputdatapesertaActionPerformed

    private void btninputdatainstrukturActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btninputdatainstrukturActionPerformed
       new inputnilai(this, rootPaneCheckingEnabled).show();
    }//GEN-LAST:event_btninputdatainstrukturActionPerformed

    private void btnlaporandatapesertaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlaporandatapesertaActionPerformed
        new laporandatapeserta(this, rootPaneCheckingEnabled).show();
    }//GEN-LAST:event_btnlaporandatapesertaActionPerformed

    private void btnlaporandataabsensiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlaporandataabsensiActionPerformed
        new laporanabsensi(this, rootPaneCheckingEnabled).show();
    }//GEN-LAST:event_btnlaporandataabsensiActionPerformed

    private void btnlogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlogoutActionPerformed
        new homemenu().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnlogoutActionPerformed

    private void btnlihatdatainstrukturActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlihatdatainstrukturActionPerformed
      new forminputmateri(this, rootPaneCheckingEnabled).show();
    }//GEN-LAST:event_btnlihatdatainstrukturActionPerformed

    private void btnupdatedataabsensiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnupdatedataabsensiActionPerformed
        new formupdateabsensi(this, rootPaneCheckingEnabled).show();
    }//GEN-LAST:event_btnupdatedataabsensiActionPerformed

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
            java.util.logging.Logger.getLogger(homemenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(homemenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(homemenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(homemenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new homemenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelLogin;
    private javax.swing.JPanel Panelabsensi;
    private javax.swing.JButton btnabsen;
    private javax.swing.JButton btnbatal;
    private javax.swing.JButton btncariid;
    private javax.swing.JButton btninputdatainstruktur;
    private javax.swing.JButton btninputdatapeserta;
    private javax.swing.JButton btnlaporandataabsensi;
    private javax.swing.JButton btnlaporandatapeserta;
    private javax.swing.JButton btnlihatdatainstruktur;
    private javax.swing.JButton btnlogout;
    private javax.swing.JButton btnupdatedataabsensi;
    private javax.swing.JComboBox<String> comboxketerangan;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanelimage;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPanehomemenu;
    private javax.swing.JPanel jpanelmenuutama;
    private javax.swing.JLabel lblimage;
    private javax.swing.JLabel lbltgl;
    private javax.swing.JLabel lblwaktu;
    private javax.swing.JTable tblabsen;
    private javax.swing.JTextField txtidpeserta;
    private javax.swing.JTextField txtjam;
    private javax.swing.JTextField txtkejuruan;
    private javax.swing.JTextField txtnama;
    private javax.swing.JPasswordField txtpassword;
    private javax.swing.JTextField txttanggal;
    private javax.swing.JTextField txtusername;
    // End of variables declaration//GEN-END:variables
}
