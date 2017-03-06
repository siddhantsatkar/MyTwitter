/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dataaccess;

import business.Follow;
import business.Tweet;
import business.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author Giridhar Anuchand Kathiresan <giridharanuchand@gmail.com>
 */
public class FollowDB {
    // Database URL  
    // private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    private   static final String DB_URL = "jdbc:mysql://localhost/mytwitter";
     //  Database credentials
   private static final String USER = "root";
   private static final String PASS = "root";
   
   public static ArrayList searchNotifyFollowers(User theUserDet)
    {
        //search in the database and find the User, if does not exist return null; if exist make a User object and return it.
    System.out.println("control is within select method");
       //implement insert into database
     Connection conn = null;
   Statement stmt = null;
   ResultSet rs = null; 
   ArrayList<Follow> list_followers = new ArrayList<Follow>(); 
    Follow followData = new Follow();
//    userData = null; 
   try{
      //STEP 2: Register JDBC driver
      Class.forName("com.mysql.jdbc.Driver");

      //STEP 3: Open a connection
      System.out.println("Connecting to a selected database...");
      System.out.println("DB_URL"+DB_URL);
      conn = DriverManager.getConnection(DB_URL, USER, PASS);
      System.out.println("Connected database successfully...");
      
      
      //STEP 4: Execute a query
      System.out.println("Creating statement...");
      stmt = conn.createStatement();
      String sql = "SELECT * FROM mytwitter.FOLLOW " + "WHERE followDate >='" +theUserDet.getLastLoginTime() +"'";         
      System.out.println("sql is "+sql);
      rs = stmt.executeQuery(sql);
      SimpleDateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy"); 
      while(rs.next())
      {
           java.util.Date uDate = formatter.parse(rs.getString("followDate").trim());
           followData.setFollowDate(uDate);
           followData.setFollowedUserId(rs.getInt("followedUserId"));
           followData.setUserId(rs.getInt("UserId"));
           list_followers.add(followData); 
      }
   }catch(SQLException se){
      //Handle errors for JDBC
      se.printStackTrace();
   }catch(Exception e){
      //Handle errors for Class.forName
      e.printStackTrace();
   }finally{
      //finally block used to close resources
      try{
         if(stmt!=null)
            conn.close();
      }catch(SQLException se){
      }// do nothing
      try{
         if(conn!=null)
            conn.close();
      }catch(SQLException se){
         se.printStackTrace();
      }//end finally try
   }//end try
   
 //   System.out.println("userData fullname is "+userData.getFullName()); 
//   if(userData.getEmail() != null)
//       return userData; 
//   else
//       return null; 
    return list_followers;   
    }
}
