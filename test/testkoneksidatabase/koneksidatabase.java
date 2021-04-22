/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testkoneksidatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author prayogi
 */
public class koneksidatabase {
   void testkoneksi(){
}
     public static void main(final String[] args){
try {

Class.forName("com.mysql.jdbc.Driver").newInstance();
Connection koneksi = DriverManager.getConnection(
"jdbc:mysql://localhost:3306/db_ppkpijakarta", "root", "");
System.out.println("koneksi berhasil");
JOptionPane.showMessageDialog(null," mantap koneksi berhasil kawan","Informasi", JOptionPane.INFORMATION_MESSAGE);
koneksi.close();
    }catch(Exception e)
    {
        JOptionPane.showMessageDialog(null,"koneksi gagal");

koneksidatabase test = new koneksidatabase();
test.testkoneksi();
System.exit(0);
    }
}
}
