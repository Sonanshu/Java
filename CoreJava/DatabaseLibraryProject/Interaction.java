/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Random;

/**
 *
 * @author trinkush
 */
public class Interaction {

    public static void main(String[] args) {
        Db obj = new Db("root", "", "javadb");
        try {
            Connection con = obj.ConnecttivityMySql();
            //System.out.println("Connected");
            obj.insert("insert into information(id,name)" + " values(29,'java')");
            //obj.myinsert(100);
 // obj.myinsert("tablename","name","id", "rollno");
            ResultSet rs = obj.retrieve("select * from information");
            while (rs.next()) {
                System.out.println(rs.getString("id"));
                
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
