package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Db {

    public String username;
    public String password;
    public String location;
//private String columnname;

   public Db(String username, String password, String dbname) {
        this.username = username;
        this.password = password;
        this.location = "jdbc:mysql://localhost:3306/" + dbname;

    }
    Connection con;
    ResultSet rs;

    public Connection ConnecttivityMySql() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(this.location, this.username, this.password);
            this.con = con;
        } catch (Exception e) {
            System.out.println(e);
        }
        return con;
    }

    public String insert(String query) {
        /*  String columns[] = new String[length];
        String data[] = new String [length];
        Scanner input = new Scanner(System.in);*/
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(this.location, this.username, this.password);
            Statement st = con.createStatement();

            /*for (int i = 0; i < length; i++) {
                System.out.println("Enter Column " + i + ":");
                columns[i] = input.nextLine();
            }
            
            for (int i = 0; i < length; i++) {
                System.out.println("Enter column data"+i+":");
                data[i] = input.nextLine();
            }*/
            st.executeUpdate(query);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    /*public String myinsert(int... columnname){
     // String column[] = new String[columnname.length];
     //for (int i = 0; i < columnname.length; ++i) {
      //  String columns = columnname[i];
     // column[i]= columnname;
        //String name = columnname.getName();
        System.out.println("Number of arguments: " + columnname.length);
     for (int i: columnname)
            System.out.print(i + " ");
        System.out.println();

      //  String name   = columnname.getName();
       // int    score  = player.getScore();
return null;
     
}*/
    
   /*  public String myinsert(String tablename, String ... columnname){
         String str = "";
        System.out.println("Number of arguments: " + columnname.length);
        System.out.println("tablename"+ tablename);
     for (String i : columnname)
     {
         System.out.print(i + " ");
         str += i;
         str += ",";
     }
        System.out.println();
         System.out.println("insert into " + tablename + " values(" + str +");");
return null;
     
}*/
    public String delete(String query) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(this.location, this.username, this.password);
            Statement st = con.createStatement();

            st.executeUpdate(query);
        } catch (Exception e) {
            System.out.println(e);
        }
        return "data deleted";
    }

    public String update(String query) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(this.location, this.username, this.password);
            Statement st = con.createStatement();

            st.executeUpdate("insert into ");
        } catch (Exception e) {
            System.out.println(e);
        }
        return "data updated";
    }

    public ResultSet retrieve(String query) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(this.location, this.username, this.password);
            Statement st = con.createStatement();

            this.rs = st.executeQuery(query);
        } catch (Exception e) {
            System.out.println(e);
        }
        return rs;
    }
}
