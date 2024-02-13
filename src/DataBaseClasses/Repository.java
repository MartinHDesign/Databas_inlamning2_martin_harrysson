package DataBaseClasses;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class Repository {
    public List<Kund> getCustomersFromDatabase(){
        List<Kund> temp = new ArrayList<>();
        try{
            Properties p = new Properties();
            p.load(new FileInputStream("src/Settings.properties"));

            try (Connection c = DriverManager.getConnection(p.getProperty("connection"),
                    p.getProperty("name"),p.getProperty("password"))){

                Statement stmt = c.createStatement();
                ResultSet r = stmt.executeQuery("select * from kund");

                while (r.next()){
                    temp.add(new Kund(r.getInt("id"),r.getString("namn"),
                            r.getString("efternamn"),r.getString("adress"),
                            r.getString("ort")));
                }



            } catch (SQLException e){
                e.printStackTrace();
            }


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return temp;
    }
    public List<Färg> getFärgFromDatabase(){
        List<Färg> temp = new ArrayList<>();
        try{
            Properties p = new Properties();
            p.load(new FileInputStream("src/Settings.properties"));

            try (Connection c = DriverManager.getConnection(p.getProperty("connection"),
                    p.getProperty("name"),p.getProperty("password"))){

                Statement stmt = c.createStatement();
                ResultSet r = stmt.executeQuery("select * from färg");

                while (r.next()){
                    temp.add(new Färg(r.getInt("id"),r.getString("namn")));
                }



            } catch (SQLException e){
                e.printStackTrace();
            }


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return temp;
    }
    public List<Vara> getVaraFromDatabase(){
        List<Vara> temp = new ArrayList<>();
        try{
            Properties p = new Properties();
            p.load(new FileInputStream("src/Settings.properties"));

            try (Connection c = DriverManager.getConnection(p.getProperty("connection"),
                    p.getProperty("name"),p.getProperty("password"))){

                Statement stmt = c.createStatement();
                ResultSet r = stmt.executeQuery("select * from vara");

                while (r.next()){
                    temp.add(new Vara(r.getInt("id"),r.getString("märke"),r.getDouble("pris")));
                }



            } catch (SQLException e){
                e.printStackTrace();
            }


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return temp;
    }
    public List<Kategori> getKategoriFromDatabase(){
        List<Kategori> temp = new ArrayList<>();
        try{
            Properties p = new Properties();
            p.load(new FileInputStream("src/Settings.properties"));

            try (Connection c = DriverManager.getConnection(p.getProperty("connection"),
                    p.getProperty("name"),p.getProperty("password"))){

                Statement stmt = c.createStatement();
                ResultSet r = stmt.executeQuery("select * from kategori");

                while (r.next()){
                    temp.add(new Kategori(r.getInt("id"),r.getString("namn")));
                }


            } catch (SQLException e){
                e.printStackTrace();
            }


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return temp;
    }
    public List<Sko> getSkoFromDatabase(List<Färg> listaFärg, List<Vara> listaVara){
        List<Sko> temp = new ArrayList<>();
        try{
            Properties p = new Properties();
            p.load(new FileInputStream("src/Settings.properties"));

            try (Connection c = DriverManager.getConnection(p.getProperty("connection"),
                    p.getProperty("name"),p.getProperty("password"))){

                Statement stmt = c.createStatement();
                ResultSet r = stmt.executeQuery("select * from sko");

                while (r.next()){
                    temp.add(new Sko(r.getInt("id"),r.getInt("lagerSaldo"),
                            getFärgFromId(listaFärg,r.getInt("färgid")),r.getInt("storlek"),
                            getVaraFromId(listaVara,r.getInt("varaid"))));
                }


            } catch (SQLException e){
                e.printStackTrace();
            }


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return temp;
    }

    public List<Beställning> getBeställningFromDatabase(List<Kund> listaKunder){
        List<Beställning> temp = new ArrayList<>();
        try{
            Properties p = new Properties();
            p.load(new FileInputStream("src/Settings.properties"));

            try (Connection c = DriverManager.getConnection(p.getProperty("connection"),
                    p.getProperty("name"),p.getProperty("password"))){

                Statement stmt = c.createStatement();
                ResultSet r = stmt.executeQuery("select * from beställning");

                while (r.next()){

                    temp.add(new Beställning(r.getInt("id"),
                            getKundIdFromBeställning(listaKunder,r.getInt("kundid")),
                            LocalDate.parse(r.getString("datum"))));

                }



            } catch (SQLException e){
                e.printStackTrace();
            }


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return temp;
    }
    public List<Innehåller> getInnehållerFromDatabase(List<Beställning> bList, List<Sko> sList){
        List<Innehåller> temp = new ArrayList<>();
        try{
            Properties p = new Properties();
            p.load(new FileInputStream("src/Settings.properties"));

            try (Connection c = DriverManager.getConnection(p.getProperty("connection"),
                    p.getProperty("name"),p.getProperty("password"))){

                Statement stmt = c.createStatement();
                ResultSet r = stmt.executeQuery("select * from Innehåller");

                while (r.next()){
                    temp.add(new Innehåller(r.getInt("id"),
                            getBeställningFromId(bList,r.getInt("beställningid")),
                            getSkoFromId(sList,r.getInt("skoid"))));
                }


            } catch (SQLException e){
                e.printStackTrace();
            }


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return temp;
    }
    public List<Ingår> getIngårFromDatabase(List<Kategori> kList, List<Sko> sList){
        List<Ingår> temp = new ArrayList<>();
        try{
            Properties p = new Properties();
            p.load(new FileInputStream("src/Settings.properties"));

            try (Connection c = DriverManager.getConnection(p.getProperty("connection"),
                    p.getProperty("name"),p.getProperty("password"))){

                Statement stmt = c.createStatement();
                ResultSet r = stmt.executeQuery("select * from Ingår");

                while (r.next()){
                    temp.add(new Ingår(r.getInt("id"),
                            getSkoFromId(sList,r.getInt("skoid")),
                            getKategoriFromId(kList,r.getInt("kategoriid"))));
                }


            } catch (SQLException e){
                e.printStackTrace();
            }


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return temp;
    }
    public int kundId(String username){
        try{
            Properties p = new Properties();
            p.load(new FileInputStream("src/Settings.properties"));

            try (Connection c = DriverManager.getConnection(p.getProperty("connection"),
                    p.getProperty("name"),p.getProperty("password"))){

                PreparedStatement pstmt = c.prepareStatement("select * from kund where username = ?");
                pstmt.setString(1,username);
                ResultSet r = pstmt.executeQuery();

                r.next();
                return r.getInt("id");

            } catch (SQLException e){
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return -1;
    }
    public int addToCartSp(int kunId, int skoId, Integer beställningsId){
        Integer id = 0;
        try{
            Properties p = new Properties();
            p.load(new FileInputStream("src/Settings.properties"));

            try (Connection c = DriverManager.getConnection(p.getProperty("connection"),
                    p.getProperty("name"),p.getProperty("password"))){


                if (beställningsId != null) {
                    CallableStatement call = c.prepareCall("call addToCart(?,?,?,?)");
                    call.setInt(1, kunId);
                    call.setInt(2, skoId);
                    call.setInt(3, beställningsId);
                    call.executeQuery();
                    id = beställningsId;
                } else {
                    CallableStatement call = c.prepareCall("call addToCart(?,?,?,?)");
                    call.setInt(1, kunId);
                    call.setInt(2, skoId);
                    call.setNull(3, Types.INTEGER);
                    call.registerOutParameter(4, Types.INTEGER);
                    call.executeQuery();
                    id = call.getInt(4);
                }


            } catch (SQLException e){
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Varan är slut på lager");
            }


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return id;
    }

    public boolean checkPassword(String username, char[] password){
        try{
            Properties p = new Properties();
            p.load(new FileInputStream("src/Settings.properties"));

            try (Connection c = DriverManager.getConnection(p.getProperty("connection"),
                    p.getProperty("name"),p.getProperty("password"))){

                PreparedStatement pstmt = c.prepareStatement("select * from kund where username = ?");
                pstmt.setString(1,username);
                ResultSet r = pstmt.executeQuery();



                while (r.next()){
                    if (r.getString("password").equals(String.valueOf(password))){
                        return true;
                    }


                }


            } catch (SQLException e){
                e.printStackTrace();
            }


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        JOptionPane.showMessageDialog(null,"Wrong username or password");
        return false;
    }
    private Kategori getKategoriFromId(List<Kategori> lista, int id){
        return lista.stream().filter(v -> v.getId() == id).toList().get(0);
    }
    private Beställning getBeställningFromId(List<Beställning> lista, int id){
        return lista.stream().filter(v -> v.getId() == id).toList().get(0);
    }
    private Sko getSkoFromId(List<Sko> lista, int id){
        return lista.stream().filter(v -> v.getId() == id).toList().get(0);
    }

    private Vara getVaraFromId(List<Vara> lista, int id){
        return lista.stream().filter(v -> v.getId() == id).toList().get(0);
    }
    private Färg getFärgFromId(List<Färg> lista, int id){
        return lista.stream().filter(v -> v.getId() == id).toList().get(0);
    }
    public Kund getKundIdFromBeställning(List<Kund> lista, int id) {
        return lista.stream().filter(v -> v.getId() == id).toList().get(0);
    }
}
