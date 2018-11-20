/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Rex1054
 */
public class lahan extends javax.swing.JPanel {

    Connection con;
    Statement stm;
    ResultSet rs;
    String sql, sql1, sql2;
    Timer harian, perjam;

    Random randoman = new Random();
    boolean[] statusLahan = {false, false, false, false, false, false, false, false, false, false,
        false, false, false, false, false, false};
    int[] jenisPohon = new int[16];
    int[] statusAir = new int[16];
    int[] umurPohon = new int[16];
    int[] maxUmur = new int[16];
    int[] maxAir = new int[16];
    int[] minAir = new int[16];
    int[] dataBibit = new int[4];
    int[] pemupukan = new int[16];
    int[] level = new int[16];
    int[] levelHari = new int[16];
    int[] pemestisida = new int[16];
    int[] krisisAir = new int[16];
    int[] maxPupuk = new int[16];
    int[] maxPest = new int[16];
    String[] siapPanen = new String[16];
    String[] namaPohon = new String[16];
    String[] urlIMG = new String[16];
    String[] statusPohon = new String[16];
    int hari, random, uangTemp, pestTemp, pupukTemp, jam = 0, menit = 0;

    public void getHari() {
        db DB = new db();
        DB.config();
        con = (Connection) DB.con;
        stm = DB.stm;
        try {
            sql = "SELECT * FROM `hari`";
            rs = stm.executeQuery(sql);
            if (rs.next()) {

                hari = rs.getShort("hari");
                day.setText(String.valueOf(hari));

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "error LH01: " + e);
        }
    }

    public void getBibit() {
        db DB = new db();
        DB.config();
        con = (Connection) DB.con;
        stm = DB.stm;
        int i = 0;
        // ambil data bibit
        try {
            sql = "SELECT * FROM `inventori` WHERE `id`>1 && `id` <6";
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                dataBibit[i] = rs.getInt("jumlah");
                i++;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "error INV01-3: gagal konek db");
        }
    }

    public void updateBibit(int indexBibit) {
        db DB = new db();
        DB.config();
        con = (Connection) DB.con;
        stm = DB.stm;
        try {
            sql2 = "UPDATE `inventori` SET `jumlah`=" + dataBibit[indexBibit] + " WHERE `id` = " + (indexBibit + 2);
            stm.execute(sql2);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "error INV097: " + e);
        }
    }

    public void getPestisida() {
        db DB = new db();
        DB.config();
        con = (Connection) DB.con;
        stm = DB.stm;
        try {
            sql = "SELECT * FROM `inventori` WHERE `barang` = \"pestisida\"";
            rs = stm.executeQuery(sql);
            if (rs.next()) {

                pestTemp = rs.getInt("jumlah");
                pestisida.setText(String.valueOf(pestTemp));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "error LH01-2: gagal konek db");
        }
    }

    public void getUang() {
        db DB = new db();
        DB.config();
        con = (Connection) DB.con;
        stm = DB.stm;
        try {
            sql = "SELECT * FROM `inventori` WHERE `barang` = \"uang\"";
            rs = stm.executeQuery(sql);
            if (rs.next()) {

                uangTemp = rs.getInt("jumlah");
                uang.setText(String.valueOf(uangTemp));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "error LH01-1: gagal konek db");
        }
    }

    public void getPupuk() {
        db DB = new db();
        DB.config();
        con = (Connection) DB.con;
        stm = DB.stm;
        try {
            sql = "SELECT * FROM `inventori` WHERE `barang` = \"pupuk\"";
            rs = stm.executeQuery(sql);
            if (rs.next()) {

                pupukTemp = rs.getInt("jumlah");
                pupuk.setText(String.valueOf(pupukTemp));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "error LH01-3: gagal konek db");
        }
    }

    public void tanam(int lahan) {

        db DB = new db();
        DB.config();
        con = (Connection) DB.con;
        stm = DB.stm;
        try {
            sql1 = "UPDATE `lahan` SET `pohon`=" + jenisPohon[lahan - 1]
                    + ",`air`=" + statusAir[lahan - 1]
                    + ",`umur`=" + umurPohon[lahan - 1]
                    + ",`status`=\"sehat\""
                    + ", `img`=\"" + urlIMG[lahan - 1] + "\""
                    + ", `maxAir`=" + maxAir[lahan - 1] + ""
                    + ", `maxUmur`=" + maxUmur[lahan - 1] + ""
                    + ", `berpohon`=\"true\""
                    + ", `level`=1 WHERE `lahan` = " + lahan;
            stm.execute(sql1);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "error INV099: " + e);
        }
    }

    public void siram(int lahan) {
        db DB = new db();
        DB.config();
        con = (Connection) DB.con;
        stm = DB.stm;
        try {
            sql = "UPDATE `lahan` SET `air`=" + statusAir[lahan - 1] + " WHERE lahan = " + lahan;
            stm.execute(sql);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "error INV099: " + e);
        }
    }

    public void memupuk(int lahan) {
        if (pupukTemp <= 0) {
            JOptionPane.showMessageDialog(null, "Pupuk habis, silahkan beli");
        } else {
            if (pemupukan[lahan] > maxPupuk[lahan]) {
                JOptionPane.showMessageDialog(null, "Pemupukan sudah cukup untuk level ini");
            } else {
                JOptionPane.showMessageDialog(null, "Pemupukan Berhasil");
                pemupukan[lahan] += 1;
                pupukTemp -= 1;
                db DB = new db();
                DB.config();
                con = (Connection) DB.con;
                stm = DB.stm;
                try {
                    sql1 = "UPDATE `lahan` SET `pupuk`=" + pemupukan[lahan] + " WHERE lahan = " + (lahan+1);
                    stm.execute(sql1);
                    sql2 = "UPDATE `inventori` SET `jumlah`=" + pupukTemp + " WHERE barang = \"pupuk\"";
                    stm.execute(sql2);
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "error INV099: " + e);
                }
            }
        }
    }
    
    public void memestisida(int lahan) {
    if (pestTemp <= 0) {
            JOptionPane.showMessageDialog(null, "Pestisida habis, silahkan beli");
        } else {
            if (statusPohon[lahan].equals("sakit")){
                JOptionPane.showMessageDialog(null, "Pestisida Berhasil, pohon sudah sehat");
                pemestisida[lahan] += 1;
                pestTemp -= 1;
                statusPohon[lahan] = "sehat";
                db DB = new db();
                DB.config();
                con = (Connection) DB.con;
                stm = DB.stm;
                try {
                    sql1 = "UPDATE `lahan` SET `pestisida`=" + pemestisida[lahan] + ", `status`=\"sehat\" WHERE lahan = " + (lahan+1);
                    stm.execute(sql1);
                    sql2 = "UPDATE `inventori` SET `jumlah`=" + pestTemp + " WHERE barang = \"pestisida\"";
                    stm.execute(sql2);
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "error INV099: " + e);
                }
            } else {
            JOptionPane.showMessageDialog(null, "Pohon tidak butuh pestisida");
            }
        }
    }
    
    public void panen(int pohon) {
        String barang="";
        if (pohon == 9) {
            barang = "damar";
        } else
            if (pohon == 10) {
            barang = "gaharu";
        } else
                if (pohon == 11) {
            barang = "jati";
        } else
                    if (pohon == 12) {
            barang = "mahoni";
        }
        db DB = new db();
                DB.config();
                con = (Connection) DB.con;
                stm = DB.stm;
                try {
                    sql2 = "UPDATE `inventori` SET `kayu`=(`kayu`+1) WHERE barang=\""+barang+"\"";
                    stm.execute(sql2);
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "error INV099: " + e);
                }
    }

    public void levelUp(int i) {
        if (levelHari[i] <= umurPohon[i]) {
            if (pemupukan[i] < maxPupuk[i]) {
                JOptionPane.showMessageDialog(null, "Silahkan melakukan pemupukan pada lahan " + (i + 1));
            } else {
                if (level[i] < 3) {
                level[i] += 1;
                } else {
                level[i] = 3;
                }
                //ganti level
                if (level[i] == 2) {
                    //ganti ikon pohon damar level 2
                    if (jenisPohon[i] == 1) {
                        urlIMG[i] = "/main/IMG/pohon/damar2.png";
                        jenisPohon[i] = 5;
                        maxAir[i] = 400;
                        level[i] = 2;
                        minAir[i] = 170;
                        try {
                            sql = "UPDATE `lahan` SET pohon=5, maxAir=400, level=2"
                                    + ",`img`=\"" + urlIMG[i] + "\" WHERE `lahan` = " + (i + 1);
                            stm.execute(sql);
                        } catch (SQLException e) {
                            JOptionPane.showMessageDialog(null, "error INV096: " + e);
                        }
                    } else //ganti ikon pohon gaharu level 2
                    if (jenisPohon[i] == 2) {
                        urlIMG[i] = "/main/IMG/pohon/gaharu2.png";
                        jenisPohon[i] = 6;
                        maxAir[i] = 450;
                        level[i] = 2;
                        minAir[i] = 180;
                        try {
                            sql = "UPDATE `lahan` SET pohon=6, maxAir=450, level=2"
                                    + ",`img`=\"" + urlIMG[i] + "\" WHERE `lahan` = " + (i + 1);
                            stm.execute(sql);
                        } catch (SQLException e) {
                            JOptionPane.showMessageDialog(null, "error INV096: " + e);
                        }
                    } else //ganti ikon pohon jati level 2
                    if (jenisPohon[i] == 3) {
                        urlIMG[i] = "/main/IMG/pohon/jati2.png";
                        jenisPohon[i] = 7;
                        maxAir[i] = 550;
                        level[i] = 2;
                        minAir[i] = 200;
                        try {
                            sql = "UPDATE `lahan` SET pohon=7, maxAir=550, level=2,"
                                    + "`img`=\"" + urlIMG[i] + "\" WHERE `lahan` = " + (i + 1);
                            stm.execute(sql);
                        } catch (SQLException e) {
                            JOptionPane.showMessageDialog(null, "error INV096: " + e);
                        }
                    } else //ganti ikon pohon mahoni level 2
                    if (jenisPohon[i] == 4) {
                        urlIMG[i] = "/main/IMG/pohon/mahoni2.png";
                        jenisPohon[i] = 8;
                        maxAir[i] = 500;
                        level[i] = 2;
                        minAir[i] = 190;
                        try {
                            sql = "UPDATE `lahan` SET pohon=8, maxAir=500, level=2,"
                                    + "`img`=\"" + urlIMG[i] + "\" WHERE `lahan` = " + (i + 1);
                            stm.execute(sql);
                        } catch (SQLException e) {
                            JOptionPane.showMessageDialog(null, "error INV096: " + e);
                        }
                    }
                } else //jika level pohon = 3
                if (level[i] == 5) {
                    //ganti ikon pohon damar level 3
                    if (jenisPohon[i] == 5) {
                        urlIMG[i] = "/main/IMG/pohon/damar3.png";
                        jenisPohon[i] = 9;
                        maxAir[i] = 600;
                        level[i] = 3;
                        minAir[i] = 270;
                        try {
                            sql = "UPDATE `lahan` SET pohon=9, maxAir=600, level=3,"
                                    + "`img`=\"" + urlIMG[i] + "\" WHERE `lahan` = " + (i + 1);
                            stm.execute(sql);
                        } catch (SQLException e) {
                            JOptionPane.showMessageDialog(null, "error INV096: " + e);
                        }
                    } else //ganti ikon pohon gaharu level 3
                    if (jenisPohon[i] == 6) {
                        urlIMG[i] = "/main/IMG/pohon/gaharu3.png";
                        jenisPohon[i] = 10;
                        maxAir[i] = 650;
                        level[i] = 3;
                        minAir[i] = 280;
                        try {
                            sql = "UPDATE `lahan` SET pohon=10, maxAir=650, level=3,"
                                    + "`img`=\"" + urlIMG[i] + "\" WHERE `lahan` = " + (i + 1);
                            stm.execute(sql);
                        } catch (SQLException e) {
                            JOptionPane.showMessageDialog(null, "error INV096: " + e);
                        }
                    } else //ganti ikon pohon jati level 3
                    if (jenisPohon[i] == 7) {
                        urlIMG[i] = "/main/IMG/pohon/jati3.png";
                        jenisPohon[i] = 11;
                        maxAir[i] = 750;
                        level[i] = 3;
                        minAir[i] = 300;
                        try {
                            sql = "UPDATE `lahan` SET pohon=11, maxAir=750, level=3,"
                                    + "`img`=\"" + urlIMG[i] + "\" WHERE `lahan` = " + (i + 1);
                            stm.execute(sql);
                        } catch (SQLException e) {
                            JOptionPane.showMessageDialog(null, "error INV096: " + e);
                        }
                    } else //ganti ikon pohon mahoni level 3
                    if (jenisPohon[i] == 8) {
                        urlIMG[i] = "/main/IMG/pohon/mahoni3.png";
                        jenisPohon[i] = 12;
                        maxAir[i] = 700;
                        level[i] = 3;
                        minAir[i] = 290;
                        try {
                            sql = "UPDATE `lahan` SET pohon=12, maxAir=700, level=3,"
                                    + "`img`=\"" + urlIMG[i] + "\" WHERE `lahan` = " + (i + 1);
                            stm.execute(sql);
                        } catch (SQLException e) {
                            JOptionPane.showMessageDialog(null, "error INV096: " + e);
                        }
                    }
                }
            }
        }
    }

    public void updateStatus() {
        db DB = new db();
        DB.config();
        con = (Connection) DB.con;
        stm = DB.stm;

        for (int i = 0; i < 16; i++) {
            // jika lahan kosong
            if (statusLahan[i] == false) {

            } else {
                // jika lahan tidak kosong,
                if (umurPohon[i] == maxUmur[i]) {
                    JOptionPane.showMessageDialog(null, "Pohon Siap Dipanen");
                    siapPanen[i] = "true";
                    try {
                            sql = "UPDATE `lahan` SET `siapPanen`=\"true\" WHERE `lahan` = " + (i + 1);
                            stm.execute(sql);
                        } catch (SQLException e) {
                            JOptionPane.showMessageDialog(null, "error INV099: " + e);
                        }
                } else
                if (statusAir[i] <= 45) {
                    // cek krisis air, jika krisis air mencapai angka 3, maka pohon mati
                    if (krisisAir[i] == 3) {
                        JOptionPane.showMessageDialog(null, "Pohon pada lahan " + i + " mati!");
                        umurPohon[i] += 0;
                        if (level[i] == 1) {
                            urlIMG[i] = "/main/IMG/pohon/mati1.png";
                        } else if (level[i] == 2) {
                            urlIMG[i] = "/main/IMG/pohon/mati2.png";
                        } else if (level[i] == 3) {
                            urlIMG[i] = "/main/IMG/pohon/mati3.png";
                        }
                        updateIcon();
                        // update url ikon pada database
                        try {
                            sql = "UPDATE `lahan` SET `status` = \"mati\", `img`=\"" + urlIMG[i] + "\" WHERE `lahan` = " + (i + 1);
                            stm.execute(sql);
                        } catch (SQLException e) {
                            JOptionPane.showMessageDialog(null, "error INV099: " + e);
                        }
                        //jika lahan kekeringan
                    } else {
                        JOptionPane.showMessageDialog(null, "Lahan " + (i + 1) + " kekeringan, silahkan disiram!");
                        umurPohon[i] += 0;
                        krisisAir[i] += 1;
                        //update status lahan pada database
                        try {
                            sql = "UPDATE `lahan` SET `status`= \"kering\", `critical`=" + krisisAir[i] + " WHERE `lahan` = " + (i + 1);
                            stm.execute(sql);
                        } catch (SQLException e) {
                            JOptionPane.showMessageDialog(null, "error INV099: " + e);
                        }
                    }
                } else {
                    //jika lahan tidak kekeringan dan tidak mati
                    random = randoman.nextInt(5);
                    if (random == 0) {
                        statusPohon[i] = "sakit";
                    statusAir[i] -= minAir[i];
                    umurPohon[i] += 1;
                    try {
                        sql = "UPDATE `lahan` SET `status`= \"sakit\", `critical`=0,`air`=" + statusAir[i] + ", `umur`=" + umurPohon[i] + " WHERE `lahan` = " + (i + 1);
                        stm.execute(sql);
                    } catch (SQLException e) {
                        JOptionPane.showMessageDialog(null, "error INV099: " + e);
                    }
                    } else {
                    statusPohon[i] = "sehat";
                    statusAir[i] -= minAir[i];
                    umurPohon[i] += 1;
                    try {
                        sql = "UPDATE `lahan` SET `status`= \"sehat\", `critical`=0,`air`=" + statusAir[i] + ", `umur`=" + umurPohon[i] + " WHERE `lahan` = " + (i + 1);
                        stm.execute(sql);
                    } catch (SQLException e) {
                        JOptionPane.showMessageDialog(null, "error INV099: " + e);
                    }
                    }
                    //cek batas level dan umur pohon
                    levelUp(i);
                }
            }
        }
        updateIcon();

    }

    public void updateHari() {
        db DB = new db();
        DB.config();
        con = (Connection) DB.con;
        stm = DB.stm;
        hari += 1;
        day.setText(String.valueOf(hari));
        try {
            sql = "UPDATE `hari` SET `hari`=" + hari + " WHERE `id`=1";
            stm.execute(sql);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "error LH02: " + e);
        }
    }

    public void bersih(int lahan) {
        db DB = new db();
        DB.config();
        con = (Connection) DB.con;
        stm = DB.stm;
        if (statusLahan[lahan] == false) {
            JOptionPane.showMessageDialog(null, "Lahan sudah bersih, tidak perlu dibersihkan lagi");
        } else {
            if (pemupukan[lahan] == 0 || pemestisida[lahan] == 0 || krisisAir[lahan] == 0) {
                try {
                    sql1 = "UPDATE `lahan` SET `pupuk`=99, `pestisida`=99, `critical` = 0 WHERE lahan = " + (lahan + 1);
                    stm.execute(sql1);
                    sql2 = "UPDATE `lahan` SET `air`=0, `umur`=0, `maxAir`=0, `maxUmur`=0"
                            + ", `pohon`=14, `level` = 0, `status`=null, `img`=null, `critical`=0, `berpohon`=\"false\","
                            + "`pupuk`=0, `pestisida`=0, `siapPanen`=\"false\" WHERE lahan = " + (lahan + 1);
                    stm.execute(sql2);
                    JOptionPane.showMessageDialog(null, "Lahan berhasil dibersihkan");
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "error INV099: " + e);
                }
            } else {

                try {
                    sql = "UPDATE `lahan` SET `air`=0, `umur`=0, `maxAir`=0, `maxUmur`=0"
                            + ", `pohon`=14, `status`=null, `img`=null, `critical`=0 `berpohon`=\"false\","
                            + "`pupuk`=0, `pestisida`=0, `siapPanen`=\"false\" WHERE lahan = " + (lahan + 1);
                    stm.execute(sql);
                    JOptionPane.showMessageDialog(null, "Lahan berhasil dibersihkan");
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "error INV099: " + e);
                }
            }
        }
    }

    public void getLahan() {
        db DB = new db();
        DB.config();
        con = (Connection) DB.con;
        stm = DB.stm;
        int i = 0;
        try {
            sql = "SELECT `lahan`.lahan, `lahan`.pohon, `lahan`.air, `lahan`.umur, "
                    + "`lahan`.umur, `lahan`.status, `lahan`.img, `lahan`.maxAir, "
                    + "`lahan`.maxUmur, `lahan`.level, `lahan`.pupuk, `lahan`.pestisida, "
                    + "`lahan`.critical, `lahan`.berpohon, `lahan`.siapPanen, `pohon`.airMin, `pohon`.hari, `pohon`.maxPest, "
                    + "`pohon`.maxPupuk from `lahan` join `pohon` on `lahan`.pohon = `pohon`.id order by `lahan`.lahan ASC";
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                jenisPohon[i] = rs.getInt("pohon");
                if (rs.getString("berpohon").equals("true")) {
                    statusLahan[i] = true;
                } else {
                    statusLahan[i] = false;
                }
                statusAir[i] = rs.getInt("air");
                umurPohon[i] = rs.getInt("umur");
                maxUmur[i] = rs.getInt("maxUmur");
                maxAir[i] = rs.getInt("maxAir");
                minAir[i] = rs.getInt("airMin");
                pemupukan[i] = rs.getInt("pupuk");
                pemestisida[i] = rs.getInt("pestisida");
                krisisAir[i] = rs.getInt("critical");
                level[i] = rs.getInt("level");
                levelHari[i] = rs.getInt("hari");
                maxPupuk[i] = rs.getInt("maxPupuk");
                maxPest[i] = rs.getInt("maxPest");
                siapPanen[i] = rs.getString("siapPanen");
                if (rs.getString("img") == null) {
                    urlIMG[i] = "/main/IMG/pohon/null.png";
                } else {
                    urlIMG[i] = rs.getString("img");
                }
                statusPohon[i] = rs.getString("status");
                if (rs.getInt("pohon") == 1 || rs.getInt("pohon") == 5 || rs.getInt("pohon") == 9) {
                    namaPohon[i] = "Damar";
                } else if (rs.getInt("pohon") == 2 || rs.getInt("pohon") == 6 || rs.getInt("pohon") == 10) {
                    namaPohon[i] = "Gaharu";
                } else if (rs.getInt("pohon") == 3 || rs.getInt("pohon") == 7 || rs.getInt("pohon") == 11) {
                    namaPohon[i] = "Jati";
                } else if (rs.getInt("pohon") == 4 || rs.getInt("pohon") == 8 || rs.getInt("pohon") == 12) {
                    namaPohon[i] = "Mahoni";
                } else {
                    namaPohon[i] = "-";
                }
                i++;

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "error INV098: " + e);
        }
    }

    public void phSetIcon(int pohon) {
        if (pohon == 1) {
            if (ph1.isSelected()) {
                ph1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/IMG/pohon/damar1.png")));
            } else if (ph2.isSelected()) {
                ph2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/IMG/pohon/damar1.png")));
            } else if (ph3.isSelected()) {
                ph3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/IMG/pohon/damar1.png")));
            } else if (ph4.isSelected()) {
                ph4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/IMG/pohon/damar1.png")));
            } else if (ph5.isSelected()) {
                ph5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/IMG/pohon/damar1.png")));
            } else if (ph6.isSelected()) {
                ph6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/IMG/pohon/damar1.png")));
            } else if (ph7.isSelected()) {
                ph7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/IMG/pohon/damar1.png")));
            } else if (ph8.isSelected()) {
                ph8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/IMG/pohon/damar1.png")));
            } else if (ph9.isSelected()) {
                ph9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/IMG/pohon/damar1.png")));
            } else if (ph10.isSelected()) {
                ph10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/IMG/pohon/damar1.png")));
            } else if (ph11.isSelected()) {
                ph11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/IMG/pohon/damar1.png")));
            } else if (ph12.isSelected()) {
                ph12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/IMG/pohon/damar1.png")));
            } else if (ph13.isSelected()) {
                ph13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/IMG/pohon/damar1.png")));
            } else if (ph14.isSelected()) {
                ph14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/IMG/pohon/damar1.png")));
            } else if (ph15.isSelected()) {
                ph15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/IMG/pohon/damar1.png")));
            } else if (ph16.isSelected()) {
                ph16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/IMG/pohon/damar1.png")));
            }
        } else if (pohon == 2) {
            if (ph1.isSelected()) {
                ph1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/IMG/pohon/gaharu1.png")));
            } else if (ph2.isSelected()) {
                ph2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/IMG/pohon/gaharu1.png")));
            } else if (ph3.isSelected()) {
                ph3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/IMG/pohon/gaharu1.png")));
            } else if (ph4.isSelected()) {
                ph4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/IMG/pohon/gaharu1.png")));
            } else if (ph5.isSelected()) {
                ph5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/IMG/pohon/gaharu1.png")));
            } else if (ph6.isSelected()) {
                ph6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/IMG/pohon/gaharu1.png")));
            } else if (ph7.isSelected()) {
                ph7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/IMG/pohon/gaharu1.png")));
            } else if (ph8.isSelected()) {
                ph8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/IMG/pohon/gaharu1.png")));
            } else if (ph9.isSelected()) {
                ph9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/IMG/pohon/gaharu1.png")));
            } else if (ph10.isSelected()) {
                ph10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/IMG/pohon/gaharu1.png")));
            } else if (ph11.isSelected()) {
                ph11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/IMG/pohon/gaharu1.png")));
            } else if (ph12.isSelected()) {
                ph12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/IMG/pohon/gaharu1.png")));
            } else if (ph13.isSelected()) {
                ph13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/IMG/pohon/gaharu1.png")));
            } else if (ph14.isSelected()) {
                ph14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/IMG/pohon/gaharu1.png")));
            } else if (ph15.isSelected()) {
                ph15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/IMG/pohon/gaharu1.png")));
            } else if (ph16.isSelected()) {
                ph16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/IMG/pohon/gaharu1.png")));
            }
        } else if (pohon == 3) {
            if (ph1.isSelected()) {
                ph1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/IMG/pohon/jati1.png")));
            } else if (ph2.isSelected()) {
                ph2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/IMG/pohon/jati1.png")));
            } else if (ph3.isSelected()) {
                ph3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/IMG/pohon/jati1.png")));
            } else if (ph4.isSelected()) {
                ph4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/IMG/pohon/jati1.png")));
            } else if (ph5.isSelected()) {
                ph5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/IMG/pohon/jati1.png")));
            } else if (ph6.isSelected()) {
                ph6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/IMG/pohon/jati1.png")));
            } else if (ph7.isSelected()) {
                ph7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/IMG/pohon/jati1.png")));
            } else if (ph8.isSelected()) {
                ph8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/IMG/pohon/jati1.png")));
            } else if (ph9.isSelected()) {
                ph9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/IMG/pohon/jati1.png")));
            } else if (ph10.isSelected()) {
                ph10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/IMG/pohon/jati1.png")));
            } else if (ph11.isSelected()) {
                ph11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/IMG/pohon/jati1.png")));
            } else if (ph12.isSelected()) {
                ph12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/IMG/pohon/jati1.png")));
            } else if (ph13.isSelected()) {
                ph13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/IMG/pohon/jati1.png")));
            } else if (ph14.isSelected()) {
                ph14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/IMG/pohon/jati1.png")));
            } else if (ph15.isSelected()) {
                ph15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/IMG/pohon/jati1.png")));
            } else if (ph16.isSelected()) {
                ph16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/IMG/pohon/jati1.png")));
            }
        } else if (pohon == 4) {
            if (ph1.isSelected()) {
                ph1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/IMG/pohon/mahoni1.png")));
            } else if (ph2.isSelected()) {
                ph2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/IMG/pohon/mahoni1.png")));
            } else if (ph3.isSelected()) {
                ph3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/IMG/pohon/mahoni1.png")));
            } else if (ph4.isSelected()) {
                ph4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/IMG/pohon/mahoni1.png")));
            } else if (ph5.isSelected()) {
                ph5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/IMG/pohon/mahoni1.png")));
            } else if (ph6.isSelected()) {
                ph6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/IMG/pohon/mahoni1.png")));
            } else if (ph7.isSelected()) {
                ph7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/IMG/pohon/mahoni1.png")));
            } else if (ph8.isSelected()) {
                ph8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/IMG/pohon/mahoni1.png")));
            } else if (ph9.isSelected()) {
                ph9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/IMG/pohon/mahoni1.png")));
            } else if (ph10.isSelected()) {
                ph10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/IMG/pohon/mahoni1.png")));
            } else if (ph11.isSelected()) {
                ph11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/IMG/pohon/mahoni1.png")));
            } else if (ph12.isSelected()) {
                ph12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/IMG/pohon/mahoni1.png")));
            } else if (ph13.isSelected()) {
                ph13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/IMG/pohon/mahoni1.png")));
            } else if (ph14.isSelected()) {
                ph14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/IMG/pohon/mahoni1.png")));
            } else if (ph15.isSelected()) {
                ph15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/IMG/pohon/mahoni1.png")));
            } else if (ph16.isSelected()) {
                ph16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/IMG/pohon/mahoni1.png")));
            }
        }
    }

    public void phSetNullIcon() {
        if (ph1.isSelected()) {
            ph1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/IMG/pohon/null.png")));
        } else if (ph2.isSelected()) {
            ph2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/IMG/pohon/null.png")));
        } else if (ph3.isSelected()) {
            ph3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/IMG/pohon/null.png")));
        } else if (ph4.isSelected()) {
            ph4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/IMG/pohon/null.png")));
        } else if (ph5.isSelected()) {
            ph5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/IMG/pohon/null.png")));
        } else if (ph6.isSelected()) {
            ph6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/IMG/pohon/null.png")));
        } else if (ph7.isSelected()) {
            ph7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/IMG/pohon/null.png")));
        } else if (ph8.isSelected()) {
            ph8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/IMG/pohon/null.png")));
        } else if (ph9.isSelected()) {
            ph9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/IMG/pohon/null.png")));
        } else if (ph10.isSelected()) {
            ph10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/IMG/pohon/null.png")));
        } else if (ph11.isSelected()) {
            ph11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/IMG/pohon/null.png")));
        } else if (ph12.isSelected()) {
            ph12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/IMG/pohon/null.png")));
        } else if (ph13.isSelected()) {
            ph13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/IMG/pohon/null.png")));
        } else if (ph14.isSelected()) {
            ph14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/IMG/pohon/null.png")));
        } else if (ph15.isSelected()) {
            ph15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/IMG/pohon/null.png")));
        } else if (ph16.isSelected()) {
            ph16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/IMG/pohon/null.png")));

        }
    }

    public void updateIcon() {
        ph1.setIcon(new javax.swing.ImageIcon(getClass().getResource(urlIMG[0])));
        ph2.setIcon(new javax.swing.ImageIcon(getClass().getResource(urlIMG[1])));
        ph3.setIcon(new javax.swing.ImageIcon(getClass().getResource(urlIMG[2])));
        ph4.setIcon(new javax.swing.ImageIcon(getClass().getResource(urlIMG[3])));
        ph5.setIcon(new javax.swing.ImageIcon(getClass().getResource(urlIMG[4])));
        ph6.setIcon(new javax.swing.ImageIcon(getClass().getResource(urlIMG[5])));
        ph7.setIcon(new javax.swing.ImageIcon(getClass().getResource(urlIMG[6])));
        ph8.setIcon(new javax.swing.ImageIcon(getClass().getResource(urlIMG[7])));
        ph9.setIcon(new javax.swing.ImageIcon(getClass().getResource(urlIMG[8])));
        ph10.setIcon(new javax.swing.ImageIcon(getClass().getResource(urlIMG[9])));
        ph11.setIcon(new javax.swing.ImageIcon(getClass().getResource(urlIMG[10])));
        ph12.setIcon(new javax.swing.ImageIcon(getClass().getResource(urlIMG[11])));
        ph13.setIcon(new javax.swing.ImageIcon(getClass().getResource(urlIMG[12])));
        ph14.setIcon(new javax.swing.ImageIcon(getClass().getResource(urlIMG[13])));
        ph15.setIcon(new javax.swing.ImageIcon(getClass().getResource(urlIMG[14])));
        ph16.setIcon(new javax.swing.ImageIcon(getClass().getResource(urlIMG[15])));
    }

    public void phClick(int index) {
        getBibit();
        JOptionPane pane = new JOptionPane("");
        Object[] options = new String[]{"Tanam", "Siram", "Pupuk", "Pestisida", "Panen", "Bersihkan", "Status", "Batal"};
        pane.setOptions(options);
        JDialog dialog = pane.createDialog(new JFrame(), "Lahan " + (index + 1));
        dialog.show();
        Object obj = pane.getValue();

        if (obj.equals("Tanam")) {
            if (statusLahan[index] == true) {
                JOptionPane.showMessageDialog(null, "Lahan sudah ditanami!");
                JOptionPane.showMessageDialog(null, "Gagal menanam!");
            } else {
                JOptionPane pane2 = new JOptionPane("Jumlah Bibit : \n"
                        + "Damar : " + dataBibit[0] + "\n"
                        + "Gaharu : " + dataBibit[1] + "\n"
                        + "Jati : " + dataBibit[2] + "\n"
                        + "Mahoni : " + dataBibit[3]);
                Object[] options2 = new String[]{"Damar", "Gaharu", "Jati", "Mahoni", "Batal"};
                pane2.setOptions(options2);
                JDialog dialog2 = pane2.createDialog(new JFrame(), "Pohon");
                dialog2.show();
                Object obj2 = pane2.getValue();
                if (obj2.equals("Damar")) {
                    if (dataBibit[0] == 0) {
                        JOptionPane.showMessageDialog(null, "Bibit Damar habis, silahkan beli bibit!");
                    } else {
                        phSetIcon(1);
                        dataBibit[0] -= 1;
                        jenisPohon[index] = 1;
                        statusAir[index] = 200;
                        statusLahan[index] = true;
                        umurPohon[index] = 1;
                        maxUmur[index] = 11;
                        maxAir[index] = 200;
                        urlIMG[index] = "/main/IMG/pohon/damar1.png";
                        JOptionPane.showMessageDialog(null, "Berhasil menanam!");
                        tanam(index + 1);
                        updateBibit(0);
                    }
                } else if (obj2.equals("Gaharu")) {
                    if (dataBibit[1] == 0) {
                        JOptionPane.showMessageDialog(null, "Bibit Gaharu habis, silahkan beli bibit!");
                    } else {
                        phSetIcon(2);
                        dataBibit[1] -= 1;
                        jenisPohon[index] = 2;
                        statusAir[index] = 250;
                        statusLahan[index] = true;
                        umurPohon[index] = 1;
                        maxUmur[index] = 14;
                        maxAir[index] = 250;
                        urlIMG[index] = "/main/IMG/pohon/gaharu1.png";
                        JOptionPane.showMessageDialog(null, "Berhasil menanam!");
                        tanam(index + 1);
                        updateBibit(1);
                    }
                } else if (obj2.equals("Jati")) {
                    if (dataBibit[2] == 0) {
                        JOptionPane.showMessageDialog(null, "Bibit Jati habis, silahkan beli bibit!");
                    } else {
                        phSetIcon(3);
                        dataBibit[2] -= 1;
                        jenisPohon[index] = 3;
                        statusAir[index] = 350;
                        statusLahan[index] = true;
                        umurPohon[index] = 1;
                        maxUmur[index] = 15;
                        maxAir[index] = 350;
                        urlIMG[index] = "/main/IMG/pohon/jati1.png";
                        JOptionPane.showMessageDialog(null, "Berhasil menanam!");
                        tanam(index + 1);
                        updateBibit(2);
                    }
                } else if (obj2.equals("Mahoni")) {
                    if (dataBibit[3] == 0) {
                        JOptionPane.showMessageDialog(null, "Bibit Mahoni habis, silahkan beli bibit!");
                    } else {
                        phSetIcon(4);
                        dataBibit[3] -= 1;
                        jenisPohon[index] = 4;
                        statusAir[index] = 300;
                        statusLahan[index] = true;
                        umurPohon[index] = 1;
                        maxUmur[index] = 12;
                        maxAir[index] = 300;
                        urlIMG[index] = "/main/IMG/pohon/mahoni1.png";
                        JOptionPane.showMessageDialog(null, "Berhasil menanam!");
                        tanam(index + 1);
                        updateBibit(3);
                    }
                }
                getLahan();
                updateIcon();
            }

        } else if (obj.equals("Siram")) {
            if (statusLahan[index] == false) {
                JOptionPane.showMessageDialog(null, "Silahkan melakukan penanaman terlebih dahulu");
            } else if (statusAir[index] == maxAir[index]) {
                JOptionPane.showMessageDialog(null, "Pohon masih segar, siram lagi di lain hari!");
            } else {
                statusAir[index] += 100;
                if (statusAir[index] >= maxAir[index]) {
                    statusAir[index] = maxAir[index];
                }
                siram(index + 1);
                JOptionPane.showMessageDialog(null, "Penyiraman berhasil!");
            }
        } else if (obj.equals("Panen")) {
            if (statusLahan[index] == false) {
                JOptionPane.showMessageDialog(null, "Silahkan menanam terlebih dahulu!");
            } else {
                if (siapPanen[index].equals("true")) {
                JOptionPane.showMessageDialog(null, "Berhasil Panen! \n"
                        + "Hasil panen dimasukkan ke dalam inventori");
                panen(jenisPohon[index]);
                bersih(index);
            phSetNullIcon();
            jenisPohon[index] = 0;
            statusAir[index] = 0;
            umurPohon[index] = 0;
            maxUmur[index] = 0;
            maxAir[index] = 0;
            urlIMG[index] = "";
            statusLahan[index] = false;
            siapPanen[index] = "false";
            updateIcon();
                }
            }
        } else if (obj.equals("Bersihkan")) {
            bersih(index);
            phSetNullIcon();
            jenisPohon[index] = 0;
            namaPohon[index] = "-";
            statusAir[index] = 0;
            umurPohon[index] = 0;
            maxAir[index] = 0;
            maxUmur[index] = 0;
            maxAir[index] = 0;
            urlIMG[index] = "";
            statusLahan[index] = false;
            statusPohon[index] = "-";
            siapPanen[index] = "false";
            pemupukan[index] = 0;
            maxPupuk[index] = 0;
            pemestisida[index] = 0;
            maxPest[index] = 0;
            level[index] = 0;
            levelHari[index] = 0;
            updateIcon();
        } else if (obj.equals("Status")) {
            JOptionPane.showMessageDialog(null, "Status Lahan : " + statusLahan[0] + "\n"
                    + "Kode Pohon : " + jenisPohon[index] + "\n"
                    + "Nama Pohon : " + namaPohon[index] + "\n"
                    + "Level : " + level[index] + "\n"
                    + "Status Pohon : " + statusPohon[index] + "\n"
                    + "Status Pemupukan : " + pemupukan[index] + " /"+maxPupuk[index]+"\n"
                    + "Status Pestisida : " + pemestisida[index] + " /"+maxPest[index]+"\n"
                    + "Status Air : " + statusAir[index] + "\n"
                    + "Kapasitas Air : " + maxAir[index] + "\n"
                    + "Level Up pada umur ke : " + levelHari[index] + "\n"
                    + "Umur Pohon : " + umurPohon[index] + "\n"
                    + "Umur Maksimal : " + maxUmur[index] +"\n"
                    + "Siap Panen : " + siapPanen[index]);
        } else if (obj.equals("Pupuk")) {
            memupuk(index);
        } else if (obj.equals("Pestisida")) {
            memestisida(index);
        }
    }

    /**
     * Creates new form lahan
     */
    public lahan() {
        initComponents();

        // ambil data hari
        getHari();
        // ambil data inventori
        // ambil data uang
        getUang();
        // ambil data pestisida
        getPestisida();
        // ambil data pupuk
        getPupuk();
        // ambil data bibit
        getBibit();
        // ambil data lahan
        getLahan();
        // update lahan
        updateIcon();

        //timer hari
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                updateStatus();
                updateHari();
            }
        };
        harian = new Timer();
        harian.schedule(task, 180000, 180000);

        //timer jam
        TimerTask taskjam = new TimerTask() {
            @Override
            public void run() {
                menit += 8;
                if (menit >= 60) {
                    jam += 1;
                    menit -= 60;
                    hour.setText(String.valueOf(jam) + ":" + String.valueOf(menit));
                } else if (jam >= 24) {
                    jam -= 24;
                    hour.setText(String.valueOf(jam) + ":" + String.valueOf(menit));
                } else {
                    hour.setText(String.valueOf(jam) + ":" + String.valueOf(menit));
                }
            }
        };
        perjam = new Timer();
        perjam.schedule(taskjam, 1000, 1000);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        phGroup = new javax.swing.ButtonGroup();
        pilihan = new javax.swing.JButton();
        nextDay = new javax.swing.JButton();
        day1 = new javax.swing.JLabel();
        hour = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        pupuk = new javax.swing.JLabel();
        pestisida = new javax.swing.JLabel();
        uang = new javax.swing.JLabel();
        day = new javax.swing.JLabel();
        ph16 = new javax.swing.JButton();
        ph15 = new javax.swing.JButton();
        ph14 = new javax.swing.JButton();
        ph13 = new javax.swing.JButton();
        ph12 = new javax.swing.JButton();
        ph11 = new javax.swing.JButton();
        ph10 = new javax.swing.JButton();
        ph9 = new javax.swing.JButton();
        ph8 = new javax.swing.JButton();
        ph7 = new javax.swing.JButton();
        ph6 = new javax.swing.JButton();
        ph5 = new javax.swing.JButton();
        ph4 = new javax.swing.JButton();
        ph3 = new javax.swing.JButton();
        ph2 = new javax.swing.JButton();
        ph1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        field = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(845, 661));
        setMinimumSize(new java.awt.Dimension(845, 661));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pilihan.setBorderPainted(false);
        pilihan.setContentAreaFilled(false);
        pilihan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pilihan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pilihanActionPerformed(evt);
            }
        });
        add(pilihan, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 470, 80, 30));

        nextDay.setText("Ganti Hari");
        nextDay.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        nextDay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextDayActionPerformed(evt);
            }
        });
        add(nextDay, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 560, -1, -1));

        day1.setFont(new java.awt.Font("Game Sans Serif 7", 1, 18)); // NOI18N
        day1.setForeground(new java.awt.Color(0, 0, 0));
        day1.setText("Jam");
        add(day1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, -1, -1));

        hour.setFont(new java.awt.Font("Game Sans Serif 7", 1, 18)); // NOI18N
        hour.setForeground(new java.awt.Color(0, 0, 0));
        hour.setText("jam");
        add(hour, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 50, 70, -1));

        jLabel2.setFont(new java.awt.Font("Game Sans Serif 7", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Hari ke");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        pupuk.setFont(new java.awt.Font("Game Sans Serif 7", 1, 18)); // NOI18N
        pupuk.setForeground(new java.awt.Color(0, 0, 0));
        pupuk.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        pupuk.setText("0");
        add(pupuk, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 20, 40, 30));

        pestisida.setFont(new java.awt.Font("Game Sans Serif 7", 1, 18)); // NOI18N
        pestisida.setForeground(new java.awt.Color(0, 0, 0));
        pestisida.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        pestisida.setText("0");
        add(pestisida, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 20, 40, 30));

        uang.setFont(new java.awt.Font("Game Sans Serif 7", 1, 18)); // NOI18N
        uang.setForeground(new java.awt.Color(0, 0, 0));
        uang.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        uang.setText("0");
        add(uang, new org.netbeans.lib.awtextra.AbsoluteConstraints(399, 19, 110, 30));

        day.setFont(new java.awt.Font("Game Sans Serif 7", 1, 18)); // NOI18N
        day.setForeground(new java.awt.Color(0, 0, 0));
        day.setText("hari");
        add(day, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 20, -1, -1));

        ph16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        ph16.setBorderPainted(false);
        phGroup.add(ph16);
        ph16.setContentAreaFilled(false);
        ph16.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ph16.setMaximumSize(new java.awt.Dimension(65, 101));
        ph16.setMinimumSize(new java.awt.Dimension(65, 101));
        ph16.setPreferredSize(new java.awt.Dimension(65, 101));
        ph16.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        ph16.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ph16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ph16MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ph16MouseExited(evt);
            }
        });
        ph16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ph16ActionPerformed(evt);
            }
        });
        add(ph16, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 260, 60, 100));

        ph15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        ph15.setBorderPainted(false);
        phGroup.add(ph15);
        ph15.setContentAreaFilled(false);
        ph15.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ph15.setMaximumSize(new java.awt.Dimension(65, 101));
        ph15.setMinimumSize(new java.awt.Dimension(65, 101));
        ph15.setPreferredSize(new java.awt.Dimension(65, 101));
        ph15.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        ph15.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ph15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ph15MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ph15MouseExited(evt);
            }
        });
        ph15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ph15ActionPerformed(evt);
            }
        });
        add(ph15, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 300, 60, 100));

        ph14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        ph14.setBorderPainted(false);
        phGroup.add(ph14);
        ph14.setContentAreaFilled(false);
        ph14.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ph14.setMaximumSize(new java.awt.Dimension(65, 101));
        ph14.setMinimumSize(new java.awt.Dimension(65, 101));
        ph14.setPreferredSize(new java.awt.Dimension(65, 101));
        ph14.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        ph14.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ph14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ph14MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ph14MouseExited(evt);
            }
        });
        ph14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ph14ActionPerformed(evt);
            }
        });
        add(ph14, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 330, 60, 100));

        ph13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        ph13.setBorderPainted(false);
        phGroup.add(ph13);
        ph13.setContentAreaFilled(false);
        ph13.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ph13.setMaximumSize(new java.awt.Dimension(65, 101));
        ph13.setMinimumSize(new java.awt.Dimension(65, 101));
        ph13.setPreferredSize(new java.awt.Dimension(65, 101));
        ph13.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        ph13.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ph13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ph13MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ph13MouseExited(evt);
            }
        });
        ph13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ph13ActionPerformed(evt);
            }
        });
        add(ph13, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 370, 60, 100));

        ph12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        ph12.setBorderPainted(false);
        phGroup.add(ph12);
        ph12.setContentAreaFilled(false);
        ph12.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ph12.setMaximumSize(new java.awt.Dimension(65, 101));
        ph12.setMinimumSize(new java.awt.Dimension(65, 101));
        ph12.setPreferredSize(new java.awt.Dimension(65, 101));
        ph12.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        ph12.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ph12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ph12MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ph12MouseExited(evt);
            }
        });
        ph12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ph12ActionPerformed(evt);
            }
        });
        add(ph12, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 225, 60, 100));

        ph11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        ph11.setBorderPainted(false);
        phGroup.add(ph11);
        ph11.setContentAreaFilled(false);
        ph11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ph11.setMaximumSize(new java.awt.Dimension(65, 101));
        ph11.setMinimumSize(new java.awt.Dimension(65, 101));
        ph11.setPreferredSize(new java.awt.Dimension(65, 101));
        ph11.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        ph11.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ph11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ph11MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ph11MouseExited(evt);
            }
        });
        ph11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ph11ActionPerformed(evt);
            }
        });
        add(ph11, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 260, 60, 100));

        ph10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        ph10.setBorderPainted(false);
        phGroup.add(ph10);
        ph10.setContentAreaFilled(false);
        ph10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ph10.setMaximumSize(new java.awt.Dimension(65, 101));
        ph10.setMinimumSize(new java.awt.Dimension(65, 101));
        ph10.setPreferredSize(new java.awt.Dimension(65, 101));
        ph10.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        ph10.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ph10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ph10MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ph10MouseExited(evt);
            }
        });
        ph10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ph10ActionPerformed(evt);
            }
        });
        add(ph10, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 300, 60, 100));

        ph9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        ph9.setBorderPainted(false);
        phGroup.add(ph9);
        ph9.setContentAreaFilled(false);
        ph9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ph9.setMaximumSize(new java.awt.Dimension(65, 101));
        ph9.setMinimumSize(new java.awt.Dimension(65, 101));
        ph9.setPreferredSize(new java.awt.Dimension(65, 101));
        ph9.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        ph9.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ph9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ph9MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ph9MouseExited(evt);
            }
        });
        ph9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ph9ActionPerformed(evt);
            }
        });
        add(ph9, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 335, 60, 100));

        ph8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        ph8.setBorderPainted(false);
        phGroup.add(ph8);
        ph8.setContentAreaFilled(false);
        ph8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ph8.setMaximumSize(new java.awt.Dimension(65, 101));
        ph8.setMinimumSize(new java.awt.Dimension(65, 101));
        ph8.setPreferredSize(new java.awt.Dimension(65, 101));
        ph8.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        ph8.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ph8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ph8MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ph8MouseExited(evt);
            }
        });
        ph8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ph8ActionPerformed(evt);
            }
        });
        add(ph8, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 180, 60, 100));

        ph7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        ph7.setBorderPainted(false);
        phGroup.add(ph7);
        ph7.setContentAreaFilled(false);
        ph7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ph7.setMaximumSize(new java.awt.Dimension(65, 101));
        ph7.setMinimumSize(new java.awt.Dimension(65, 101));
        ph7.setPreferredSize(new java.awt.Dimension(65, 101));
        ph7.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        ph7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ph7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ph7MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ph7MouseExited(evt);
            }
        });
        ph7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ph7ActionPerformed(evt);
            }
        });
        add(ph7, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 220, 60, 100));

        ph6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        ph6.setBorderPainted(false);
        phGroup.add(ph6);
        ph6.setContentAreaFilled(false);
        ph6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ph6.setMaximumSize(new java.awt.Dimension(65, 101));
        ph6.setMinimumSize(new java.awt.Dimension(65, 101));
        ph6.setPreferredSize(new java.awt.Dimension(65, 101));
        ph6.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        ph6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ph6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ph6MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ph6MouseExited(evt);
            }
        });
        ph6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ph6ActionPerformed(evt);
            }
        });
        add(ph6, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 260, 60, 100));

        ph5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        ph5.setBorderPainted(false);
        phGroup.add(ph5);
        ph5.setContentAreaFilled(false);
        ph5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ph5.setMaximumSize(new java.awt.Dimension(65, 101));
        ph5.setMinimumSize(new java.awt.Dimension(65, 101));
        ph5.setPreferredSize(new java.awt.Dimension(65, 101));
        ph5.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        ph5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ph5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ph5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ph5MouseExited(evt);
            }
        });
        ph5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ph5ActionPerformed(evt);
            }
        });
        add(ph5, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 300, 60, 100));

        ph4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        ph4.setBorderPainted(false);
        phGroup.add(ph4);
        ph4.setContentAreaFilled(false);
        ph4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ph4.setMaximumSize(new java.awt.Dimension(65, 101));
        ph4.setMinimumSize(new java.awt.Dimension(65, 101));
        ph4.setPreferredSize(new java.awt.Dimension(65, 101));
        ph4.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        ph4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ph4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ph4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ph4MouseExited(evt);
            }
        });
        ph4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ph4ActionPerformed(evt);
            }
        });
        add(ph4, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 147, 60, 100));

        ph3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        ph3.setBorderPainted(false);
        phGroup.add(ph3);
        ph3.setContentAreaFilled(false);
        ph3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ph3.setMaximumSize(new java.awt.Dimension(65, 101));
        ph3.setMinimumSize(new java.awt.Dimension(65, 101));
        ph3.setPreferredSize(new java.awt.Dimension(65, 101));
        ph3.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        ph3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ph3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ph3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ph3MouseExited(evt);
            }
        });
        ph3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ph3ActionPerformed(evt);
            }
        });
        add(ph3, new org.netbeans.lib.awtextra.AbsoluteConstraints(305, 185, 60, 100));

        ph2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        ph2.setBorderPainted(false);
        phGroup.add(ph2);
        ph2.setContentAreaFilled(false);
        ph2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ph2.setMaximumSize(new java.awt.Dimension(65, 101));
        ph2.setMinimumSize(new java.awt.Dimension(65, 101));
        ph2.setPreferredSize(new java.awt.Dimension(65, 101));
        ph2.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        ph2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ph2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ph2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ph2MouseExited(evt);
            }
        });
        ph2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ph2ActionPerformed(evt);
            }
        });
        add(ph2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 222, 60, 100));

        ph1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        ph1.setBorderPainted(false);
        phGroup.add(ph1);
        ph1.setContentAreaFilled(false);
        ph1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ph1.setMaximumSize(new java.awt.Dimension(65, 101));
        ph1.setMinimumSize(new java.awt.Dimension(65, 101));
        ph1.setPreferredSize(new java.awt.Dimension(65, 101));
        ph1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        ph1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ph1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ph1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ph1MouseExited(evt);
            }
        });
        ph1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ph1ActionPerformed(evt);
            }
        });
        add(ph1, new org.netbeans.lib.awtextra.AbsoluteConstraints(175, 260, 60, 100));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/IMG/lahan/lahan1.png"))); // NOI18N
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 180, -1, -1));

        field.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/IMG/lahan/lahan-background.png"))); // NOI18N
        add(field, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void pilihanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pilihanActionPerformed
        // Lahan Klik
    }//GEN-LAST:event_pilihanActionPerformed

    private void nextDayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextDayActionPerformed
        // Ganti Hari
        harian.cancel();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                updateHari();
                updateStatus();
            }
        };
        updateHari();
        updateStatus();
        harian = new Timer();
        harian.schedule(task, 180000, 180000);

        perjam.cancel();
        menit = 0;
        jam = 0;
        TimerTask taskjam = new TimerTask() {
            @Override
            public void run() {
                menit += 8;
                if (menit >= 60) {
                    jam += 1;
                    menit -= 60;
                    hour.setText(String.valueOf(jam) + ":" + String.valueOf(menit));
                } else if (jam >= 24) {
                    jam -= 24;
                    hour.setText(String.valueOf(jam) + ":" + String.valueOf(menit));
                } else {
                    hour.setText(String.valueOf(jam) + ":" + String.valueOf(menit));
                }
            }
        };
        perjam = new Timer();
        perjam.schedule(taskjam, 1000, 1000);
    }//GEN-LAST:event_nextDayActionPerformed

    private void ph1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ph1ActionPerformed
        // ph1
        phClick(0);
    }//GEN-LAST:event_ph1ActionPerformed

    //PH Enter
    private void ph1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ph1MouseEntered
        ph1.setBorderPainted(true);
    }//GEN-LAST:event_ph1MouseEntered

    private void ph2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ph2MouseEntered
        ph2.setBorderPainted(true);
    }//GEN-LAST:event_ph2MouseEntered

    private void ph3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ph3MouseEntered
        ph3.setBorderPainted(true);
    }//GEN-LAST:event_ph3MouseEntered

    private void ph4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ph4MouseEntered
        ph4.setBorderPainted(true);
    }//GEN-LAST:event_ph4MouseEntered

    private void ph5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ph5MouseEntered
        ph5.setBorderPainted(true);
    }//GEN-LAST:event_ph5MouseEntered

    private void ph6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ph6MouseEntered
        ph6.setBorderPainted(true);
    }//GEN-LAST:event_ph6MouseEntered

    private void ph7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ph7MouseEntered
        ph7.setBorderPainted(true);
    }//GEN-LAST:event_ph7MouseEntered

    private void ph8MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ph8MouseEntered
        ph8.setBorderPainted(true);
    }//GEN-LAST:event_ph8MouseEntered

    private void ph9MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ph9MouseEntered
        ph9.setBorderPainted(true);
    }//GEN-LAST:event_ph9MouseEntered

    private void ph10MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ph10MouseEntered
        ph10.setBorderPainted(true);
    }//GEN-LAST:event_ph10MouseEntered

    private void ph11MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ph11MouseEntered
        ph11.setBorderPainted(true);
    }//GEN-LAST:event_ph11MouseEntered

    private void ph12MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ph12MouseEntered
        ph12.setBorderPainted(true);
    }//GEN-LAST:event_ph12MouseEntered

    private void ph13MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ph13MouseEntered
        ph13.setBorderPainted(true);
    }//GEN-LAST:event_ph13MouseEntered

    private void ph14MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ph14MouseEntered
        ph14.setBorderPainted(true);
    }//GEN-LAST:event_ph14MouseEntered

    private void ph15MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ph15MouseEntered
        ph15.setBorderPainted(true);
    }//GEN-LAST:event_ph15MouseEntered

    private void ph16MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ph16MouseEntered
        ph16.setBorderPainted(true);
    }//GEN-LAST:event_ph16MouseEntered

    //PH Exit
    private void ph1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ph1MouseExited
        ph1.setBorderPainted(false);
    }//GEN-LAST:event_ph1MouseExited

    private void ph2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ph2MouseExited
        ph2.setBorderPainted(false);
    }//GEN-LAST:event_ph2MouseExited

    private void ph3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ph3MouseExited
        ph3.setBorderPainted(false);
    }//GEN-LAST:event_ph3MouseExited

    private void ph4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ph4MouseExited
        ph4.setBorderPainted(false);
    }//GEN-LAST:event_ph4MouseExited

    private void ph5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ph5MouseExited
        ph5.setBorderPainted(false);
    }//GEN-LAST:event_ph5MouseExited

    private void ph6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ph6MouseExited
        ph6.setBorderPainted(false);
    }//GEN-LAST:event_ph6MouseExited

    private void ph7MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ph7MouseExited
        ph7.setBorderPainted(false);
    }//GEN-LAST:event_ph7MouseExited

    private void ph8MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ph8MouseExited
        ph8.setBorderPainted(false);
    }//GEN-LAST:event_ph8MouseExited

    private void ph9MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ph9MouseExited
        ph9.setBorderPainted(false);
    }//GEN-LAST:event_ph9MouseExited

    private void ph10MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ph10MouseExited
        ph10.setBorderPainted(false);
    }//GEN-LAST:event_ph10MouseExited

    private void ph11MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ph11MouseExited
        ph11.setBorderPainted(false);
    }//GEN-LAST:event_ph11MouseExited

    private void ph12MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ph12MouseExited
        ph12.setBorderPainted(false);
    }//GEN-LAST:event_ph12MouseExited

    private void ph13MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ph13MouseExited
        ph13.setBorderPainted(false);
    }//GEN-LAST:event_ph13MouseExited

    private void ph14MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ph14MouseExited
        ph14.setBorderPainted(false);
    }//GEN-LAST:event_ph14MouseExited

    private void ph15MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ph15MouseExited
        ph15.setBorderPainted(false);
    }//GEN-LAST:event_ph15MouseExited

    private void ph16MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ph16MouseExited
        ph16.setBorderPainted(false);
    }//GEN-LAST:event_ph16MouseExited

    //PH Clicked
    private void ph2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ph2ActionPerformed
        // ph2
        phClick(1);
    }//GEN-LAST:event_ph2ActionPerformed

    private void ph3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ph3ActionPerformed
        // ph3
        phClick(2);
    }//GEN-LAST:event_ph3ActionPerformed

    private void ph4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ph4ActionPerformed
        // ph4
        phClick(3);
    }//GEN-LAST:event_ph4ActionPerformed

    private void ph5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ph5ActionPerformed
        // ph5
        phClick(4);
    }//GEN-LAST:event_ph5ActionPerformed

    private void ph6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ph6ActionPerformed
        // ph6
        phClick(5);
    }//GEN-LAST:event_ph6ActionPerformed

    private void ph7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ph7ActionPerformed
        // ph7
        phClick(6);
    }//GEN-LAST:event_ph7ActionPerformed

    private void ph8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ph8ActionPerformed
        // ph8
        phClick(7);
    }//GEN-LAST:event_ph8ActionPerformed

    private void ph9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ph9ActionPerformed
        // ph9
        phClick(8);
    }//GEN-LAST:event_ph9ActionPerformed

    private void ph10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ph10ActionPerformed
        // ph10
        phClick(9);
    }//GEN-LAST:event_ph10ActionPerformed

    private void ph11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ph11ActionPerformed
        // ph11
        phClick(10);
    }//GEN-LAST:event_ph11ActionPerformed

    private void ph12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ph12ActionPerformed
        // ph12
        phClick(11);
    }//GEN-LAST:event_ph12ActionPerformed

    private void ph13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ph13ActionPerformed
        // ph13
        phClick(12);
    }//GEN-LAST:event_ph13ActionPerformed

    private void ph14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ph14ActionPerformed
        // ph14
        phClick(13);
    }//GEN-LAST:event_ph14ActionPerformed

    private void ph15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ph15ActionPerformed
        // ph15
        phClick(14);
    }//GEN-LAST:event_ph15ActionPerformed

    private void ph16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ph16ActionPerformed
        // ph16
        phClick(15);
    }//GEN-LAST:event_ph16ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel day;
    public javax.swing.JLabel day1;
    private javax.swing.JLabel field;
    public javax.swing.JLabel hour;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton nextDay;
    private javax.swing.JLabel pestisida;
    private javax.swing.JButton ph1;
    private javax.swing.JButton ph10;
    private javax.swing.JButton ph11;
    private javax.swing.JButton ph12;
    private javax.swing.JButton ph13;
    private javax.swing.JButton ph14;
    private javax.swing.JButton ph15;
    private javax.swing.JButton ph16;
    private javax.swing.JButton ph2;
    private javax.swing.JButton ph3;
    private javax.swing.JButton ph4;
    private javax.swing.JButton ph5;
    private javax.swing.JButton ph6;
    private javax.swing.JButton ph7;
    private javax.swing.JButton ph8;
    private javax.swing.JButton ph9;
    private javax.swing.ButtonGroup phGroup;
    private javax.swing.JButton pilihan;
    private javax.swing.JLabel pupuk;
    private javax.swing.JLabel uang;
    // End of variables declaration//GEN-END:variables
}
