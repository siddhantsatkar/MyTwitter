/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;
import business.User;
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author xl
 */
public class UserDB {
    // Database URL  
    // private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    private   static final String DB_URL = "jdbc:mysql://localhost/mytwitter";
     //  Database credentials
   private static final String USER = "root";
   private static final String PASS = "root";
    public static long insert(User user) {
        System.out.println("control is within user insert");
       //implement insert into database
     Connection conn = null;
   Statement stmt = null;
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
       System.out.println("user.getSalt() "+user.getSalt());
       System.out.println("user.getPassword() "+user.getPassword());
      String sql = "INSERT INTO mytwitter.USER (fullName, email, dobDay, dobMonth, dobYear, nickName, password, salt ) " + "VALUES ('" + user.getFullName() + "'," +
     "'" + user.getEmail() + "'," +
	 "'" + user.getDobDay() + "'," +
	 "'" + user.getDobMonth() + "'," +
	 "'" + user.getDobYear() + "'," +
	 "'" + user.getNickName() + "'," +
	 "'" + user.getPassword() + "'," +
         "'" + user.getSalt() + "')";
          
      stmt.executeUpdate(sql);

   
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
   System.out.println("Goodbye!");
        return 0;
    }
    
     public static void updateLogOutTime(User userLog) {
        System.out.println("control is within user update LogOut time");
        ResultSet rs = null;
        User userData = new User();
        String pass;
        //implement update into database
        Connection conn = null;
        Statement stmt = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            System.out.println("DB_URL" + DB_URL);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");

            // Testing 
            // conn.setAutoCommit(false);
            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
      //      System.out.println("password" + password + " " + email);
            String sql = "UPDATE mytwitter.USER SET Last_Login_Time ='" + userLog.getLastLoginTime() + "' WHERE EMAIL ='" + userLog.getEmail() + "'";
//	 + user.getPassword() + "'";

            System.out.println("the answr is" + sql);
            stmt.executeUpdate(sql);
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null) {
                    conn.close();
                }
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
//        return userData;
    }
    
    
      public static void updatepass(String email, String password) {
        System.out.println("control is within user update");
        ResultSet rs = null;
        User userData = new User();
        String pass;
        //implement update into database
        Connection conn = null;
        Statement stmt = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            System.out.println("DB_URL" + DB_URL);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");

            // Testing 
            // conn.setAutoCommit(false);
            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            System.out.println("password" + password + " " + email);
            String sql = "UPDATE mytwitter.USER SET password ='" + password + "' WHERE EMAIL ='" + email + "'";
//	 + user.getPassword() + "'";

            System.out.println("the answr is" + sql);
            stmt.executeUpdate(sql);
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null) {
                    conn.close();
                }
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
//        return userData;
    }
    
    public static long update(User user) {
        System.out.println("control is within user update");
       //implement update into database
     Connection conn = null;
   Statement stmt = null;
   try{
      //STEP 2: Register JDBC driver
      Class.forName("com.mysql.jdbc.Driver");

      //STEP 3: Open a connection
      System.out.println("Connecting to a selected database...");
      System.out.println("DB_URL"+DB_URL);
      conn = DriverManager.getConnection(DB_URL, USER, PASS);
      System.out.println("Connected database successfully...");
      
      // Testing 
      // conn.setAutoCommit(false);
      
      //STEP 4: Execute a query
      System.out.println("Creating statement...");
      stmt = conn.createStatement();
      String sql = "UPDATE mytwitter.USER SET fullName = '" + user.getFullName() + "', dobDay ='" + user.getDobDay() + "', dobMonth ='" 
	 + user.getDobMonth() + "', dobYear ='"
	 + user.getDobYear() + "', password='" 
	 + user.getPassword() + "', salt ='" +user.getSalt() +"' WHERE EMAIL ='" +user.getEmail() +"'";
//	 + user.getPassword() + "'";
          
      stmt.executeUpdate(sql);

   
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
   System.out.println("Goodbye!");
        return 0;
    }
    
    public static void updatepic(User user,String filepath) {
        System.out.println("control is within user update epic");
        System.out.println("filepath "+filepath);
       //implement insert into database
     Connection conn = null;
   Statement stmt = null;
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
      String sqlm = "UPDATE mytwitter.USER SET picfilepath = '" +filepath +"' WHERE email = '" + user.getEmail() + "'"; 
          
      stmt.executeUpdate(sqlm);

   
   }catch(SQLException se){
   }catch(ClassNotFoundException e){
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
   System.out.println("Goodbye!");
       
    }
    
    public static User select(String emailAddress, String password)
    {
        //search in the database and find the User, if does not exist return null; if exist make a User object and return it.
    System.out.println("control is within select method");
       //implement insert into database
     Connection conn = null;
   Statement stmt = null;
   ResultSet rs = null; 
    User userData = new User();
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
      String sql = "SELECT * FROM mytwitter.USER " + "WHERE EMAIL = '" + emailAddress + "' AND PASSWORD ='" +password +"'";         
      System.out.println("sql is "+sql);
//      User userData = stmt.executeUpdate(sql);
//      int resultSet = stmt.executeUpdate(sql);
      rs = stmt.executeQuery(sql);
      while(rs.next())
      {
           System.out.println("Result set full name "+rs.getString("fullName"));
           userData.setFullName(rs.getString("fullName"));
           userData.setNickName(rs.getString("nickName"));
           userData.setDobDay(rs.getString("dobDay"));
           userData.setDobMonth(rs.getString("dobMonth"));
           userData.setDobYear(rs.getString("dobYear"));
           userData.setEmail(rs.getString("email"));
           userData.setPassword(rs.getString("password"));
           userData.setPicfilepath(rs.getString("picfilepath"));
           userData.setUserId(rs.getInt("userId"));
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
    return userData;   
    }
    
    // Logic for getting user id from the nickname 
    public static String salt(String email)
    {
        //search in the database and find the User, if does not exist return null; if exist make a User object and return it.
    System.out.println("control is within salt method of saltUser");
       //implement insert into database
     Connection conn = null;
   Statement stmt = null;
   ResultSet rs = null; 
    User userData2 = new User();
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
      String sql = "SELECT salt FROM mytwitter.USER " + "WHERE EMAIL = '" + email + "'";         
      System.out.println("sql is "+sql);
//      User userData = stmt.executeUpdate(sql);
//      int resultSet = stmt.executeUpdate(sql);
      rs = stmt.executeQuery(sql);
       while(rs.next())
      {
           System.out.println("Result set salt "+rs.getString("salt"));
           userData2.setSalt(rs.getString("salt"));
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
    return userData2.getSalt();   
    }
    
      
    public static int select(String nickName)
    {
        //search in the database and find the User, if does not exist return null; if exist make a User object and return it.
    System.out.println("control is within select method of nickname");
       //implement insert into database
     Connection conn = null;
   Statement stmt = null;
   ResultSet rs = null; 
    User userData2 = new User();
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
      String sql = "SELECT * FROM mytwitter.USER " + "WHERE NICKNAME = '" + nickName + "'";         
      System.out.println("sql is "+sql);
//      User userData = stmt.executeUpdate(sql);
//      int resultSet = stmt.executeUpdate(sql);
      rs = stmt.executeQuery(sql);
      while(rs.next())
      {
           System.out.println("Result set full name "+rs.getString("fullName"));
           userData2.setUserId(rs.getInt("userId"));
           userData2.setFullName(rs.getString("fullName"));
           userData2.setNickName(rs.getString("nickName"));
           userData2.setDobDay(rs.getString("dobDay"));
           userData2.setDobMonth(rs.getString("dobMonth"));
           userData2.setDobYear(rs.getString("dobYear"));
           userData2.setEmail(rs.getString("email"));
           userData2.setPassword(rs.getString("password"));
           userData2.setPicfilepath(rs.getString("picfilepath"));
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
    return userData2.getUserId();   
    }
    
      
    
    public static ArrayList selectAll()
    {
        //search in the database and find the User, if does not exist return null; if exist make a User object and return it.
    System.out.println("control is within allUsers method");
       //implement insert into database
     Connection conn = null;
   Statement stmt = null;
   ResultSet rs = null; 
    ArrayList userList = new ArrayList(); 
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
      String sql = "SELECT * FROM mytwitter.USER ";         
      System.out.println("sql is "+sql);
//      User userData = stmt.executeUpdate(sql);
//      int resultSet = stmt.executeUpdate(sql);
      rs = stmt.executeQuery(sql);
      while(rs.next())
      {
           System.out.println("Result set full name "+rs.getString("fullName"));
           User eachUser = new User();
           eachUser.setFullName(rs.getString("fullName"));
           eachUser.setNickName(rs.getString("nickName"));
      //     eachUser.setDobDay(rs.getString("dobDay"));
      //     eachUser.setDobMonth(rs.getString("dobMonth"));
      //     eachUser.setDobYear(rs.getString("dobYear"));
           eachUser.setEmail(rs.getString("email"));
           eachUser.setPicfilepath(rs.getString("picfilepath"));
           System.out.println("eachUser.setPicfilePath "+eachUser.getPicfilepath());
      //     eachUser.setPassword(rs.getString("password"));
           userList.add(eachUser); 
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

    return userList;   
    }
    
      public static User selectemail(String emailAddress) {
        //search in the database and find the User, if does not exist return null; if exist make a User object and return it.
        System.out.println("control is within select method");
        //implement insert into database
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        User userData = new User();
//    userData = null; 
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            System.out.println("DB_URL" + DB_URL);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql = "SELECT * FROM mytwitter.USER " + "WHERE EMAIL = '" + emailAddress + "'";
            System.out.println("sql is " + sql);
//      User userData = stmt.executeUpdate(sql);
//      int resultSet = stmt.executeUpdate(sql);
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                System.out.println("Result set full name " + rs.getString("fullName"));
                userData.setFullName(rs.getString("fullName"));
                userData.setNickName(rs.getString("nickName"));
                userData.setDobDay(rs.getString("dobDay"));
                userData.setDobMonth(rs.getString("dobMonth"));
                userData.setDobYear(rs.getString("dobYear"));
                userData.setEmail(rs.getString("email"));
                userData.setPassword(rs.getString("password"));
            }
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null) {
                    conn.close();
                }
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try

        //   System.out.println("userData fullname is "+userData.getFullName()); 
//   if(userData.getEmail() != null)
//       return userData; 
//   else
//       return null; 
        return userData;
    }
      
      // Logic for getting the user details by userid
      public static User selectUser_Id(int userIdFollower) {
        //search in the database and find the User, if does not exist return null; if exist make a User object and return it.
        System.out.println("control is within select method");
        //implement insert into database
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        User userData = new User();
//    userData = null; 
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            System.out.println("DB_URL" + DB_URL);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql = "SELECT * FROM mytwitter.USER " + "WHERE userID = " + userIdFollower ;
            System.out.println("sql is " + sql);
//      User userData = stmt.executeUpdate(sql);
//      int resultSet = stmt.executeUpdate(sql);
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                System.out.println("Result set full name " + rs.getString("fullName"));
                userData.setFullName(rs.getString("fullName"));
                userData.setNickName(rs.getString("nickName"));
                userData.setDobDay(rs.getString("dobDay"));
                userData.setDobMonth(rs.getString("dobMonth"));
                userData.setDobYear(rs.getString("dobYear"));
                userData.setEmail(rs.getString("email"));
                userData.setPassword(rs.getString("password"));
            }
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null) {
                    conn.close();
                }
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try

        //   System.out.println("userData fullname is "+userData.getFullName()); 
//   if(userData.getEmail() != null)
//       return userData; 
//   else
//       return null; 
        return userData;
    }
}
