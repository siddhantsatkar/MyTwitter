/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dataaccess;
import business.Tweet; 
import business.User;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


/**
 *
 * @author Giridhar Anuchand Kathiresan <giridharanuchand@gmail.com>
 */
public class TweetDB {
    // Database URL  
    // private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    private   static final String DB_URL = "jdbc:mysql://localhost/mytwitter";
     //  Database credentials
   private static final String USER = "root";
   private static final String PASS = "root";
   
   public static long insert(Tweet tweet) {
        System.out.println("control is within TweetDB insert");
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
      if(tweet.getMentionedUserID() != 0)
      {    
      String sql = "INSERT INTO mytwitter.TWEET (email, date, text, mentionedUser) " + "VALUES ('" + tweet.getEmail() + "'," +
     "'" + tweet.getDate() + "'," +
	 "'" + tweet.getTweet() +"'," +
         "'" + tweet.getMentionedUserID() +     "')" ;
          
      stmt.executeUpdate(sql);
      }
      else
      {
            String sql2 = "INSERT INTO mytwitter.TWEET (email, date, text) " + "VALUES ('" + tweet.getEmail() + "'," +
     "'" + tweet.getDate() + "'," +
	 "'" + tweet.getTweet() + "')" ;
          
      stmt.executeUpdate(sql2);
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
   System.out.println("Goodbye!");
        return 0;
    }
   
      public static ArrayList search(String email, int userIdNu) {
        System.out.println("control is within TweetDB search");
       //implement insert into database
     Connection conn = null;
   Statement stmt = null;
   ResultSet rs = null; 
   ArrayList<Tweet> list_tweets = new ArrayList<Tweet>(); 
   ArrayList trail_list = new ArrayList(); 
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
      String sql = "SELECT * FROM mytwitter.TWEET WHERE mentionedUser = '" +userIdNu +"' OR EMAIL = '" +email +"'" ;
//      "SELECT * FROM mytwitter.USER " + "WHERE EMAIL = '" + emailAddress + "' AND PASSWORD ='" +password +"'";         
          
      rs = stmt.executeQuery(sql);
      SimpleDateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy"); 
      
//      int index = 0; 
      while(rs.next())
      {
           System.out.println("Result set tweet "+rs.getString("text"));
           Tweet tweetData = new Tweet();
           tweetData.setEmail(rs.getString("email"));
          
           System.out.println("rs.getString "+rs.getString("date"));
//           System.out.println("rs.getDate "+rs.getDate("date"));
//           java.util.Date uDate = rs.getDate("date"); 
//           java.sql.Date sDate  = new java.sql.Date(uDate.getTime());
//           System.out.println("udate "+sDate);
//            tweetData.setDate(sDate);
           
           
//         Date tempDate = formatter.parse(rs.getString("date"));
           // working part begins 
           java.util.Date uDate = formatter.parse(rs.getString("date").trim()); 
           tweetData.setDate(uDate);
           System.out.println("Tweet Data date "+tweetData.getDate());
           tweetData.setTweet(rs.getString("text"));
           System.out.println("tweetData "+tweetData.getTweet());
           tweetData.setTweetid(rs.getInt("tweetid"));
           list_tweets.add(tweetData); 
          
//           trail_list.add(rs.getString("text")); 
//           index++; 
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
   System.out.println("Goodbye!");
        // return list_tweets;
        return list_tweets;
    }
      
       public static ArrayList searchNotifyTweets(User theUserDet) {
        System.out.println("control is within TweetDB searchNotifyTweets");
       //implement insert into database
     Connection conn = null;
   Statement stmt = null;
   ResultSet rs = null; 
   ArrayList<Tweet> list_tweets = new ArrayList<Tweet>(); 
   ArrayList trail_list = new ArrayList(); 
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
      String sql = "SELECT * FROM mytwitter.TWEET WHERE date >= '" +theUserDet.getLastLoginTime() +"' AND mentionedUser = '" +theUserDet.getUserId()+"'" ;
//      "SELECT * FROM mytwitter.USER " + "WHERE EMAIL = '" + emailAddress + "' AND PASSWORD ='" +password +"'";         
          
      rs = stmt.executeQuery(sql);
      SimpleDateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy"); 
      
//      int index = 0; 
      while(rs.next())
      {
           System.out.println("Result set tweet "+rs.getString("text"));
           Tweet tweetData = new Tweet();
           tweetData.setEmail(rs.getString("email"));
          
           System.out.println("rs.getString "+rs.getString("date"));
//           System.out.println("rs.getDate "+rs.getDate("date"));
//           java.util.Date uDate = rs.getDate("date"); 
//           java.sql.Date sDate  = new java.sql.Date(uDate.getTime());
//           System.out.println("udate "+sDate);
//            tweetData.setDate(sDate);
           
           
//         Date tempDate = formatter.parse(rs.getString("date"));
           // working part begins 
           java.util.Date uDate = formatter.parse(rs.getString("date").trim()); 
           tweetData.setDate(uDate);
           System.out.println("Tweet Data date "+tweetData.getDate());
           tweetData.setTweet(rs.getString("text"));
           System.out.println("tweetData "+tweetData.getTweet());
           list_tweets.add(tweetData); 
          
//           trail_list.add(rs.getString("text")); 
//           index++; 
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
   System.out.println("Goodbye!");
        // return list_tweets;
        return list_tweets;
    }
      
      public static int count(User user) {
        System.out.println("control is within TweetDB count");
       //implement insert into database
     Connection conn = null;
   Statement stmt = null;
   ResultSet rs = null; 
   int count = 0; 
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
      String sql = "SELECT COUNT(*) AS TOTAL FROM mytwitter.TWEET WHERE EMAIL ='" +user.getEmail() +"'";
          
      rs = stmt.executeQuery(sql);
      while(rs.next())
      {
      if(rs.getInt("TOTAL") == 0)
      {
          count = 0; 
      }
      else
      {
          count = rs.getInt("TOTAL"); 
      }
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
   System.out.println("Goodbye!");
        // return list_tweets;
        return count;
    }
      
        public static void deletetweet(int tweetid) {
        System.out.println("control is within deletetweet");
            System.out.println("tweetId "+tweetid);
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
      String sqlm = "delete from mytwitter.TWEET  WHERE tweetId = " + tweetid; 
          
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
    
}
