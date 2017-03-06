/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @javabean for User Entity
 */
public class User implements Serializable {
    //define attributes fullname, ...
     private int userId; 

   
     private String fullName;
     private String email;
     private String dobDay;
     private String dobMonth;
     private String dobYear;
     private String nickName;
     private String password;
     private String picfilepath; 
     private Date Last_Login_Time; 
     private String salt;

    public String getPicfilepath() {
        return picfilepath;
    }

    public void setPicfilepath(String picfilepath) {
        this.picfilepath = picfilepath;
    }
    
    public Date getLastLoginTime() {
        return Last_Login_Time;
    }

    public void setLastLoginTime(Date Last_Login_Time) {
        this.Last_Login_Time = Last_Login_Time;
    }
     
     public User(String fullName, String email, String dobDay, String dobMonth, String dobYear, String nickName,String password, String salt ) {
        this.fullName = fullName;
        this.email = email;
        this.dobDay = dobDay; 
        this.dobMonth = dobMonth; 
        this.dobYear = dobYear; 
        this.nickName = nickName; 
        this.password = password;   
        this.salt = salt;

        System.out.println("User arg constructor");
        
    }
     
     public User(String fullName, String email, String dobDay, String dobMonth, String dobYear, String nickName,String password, String picfilepath,String salt) {
        this.fullName = fullName;
        this.email = email;
        this.dobDay = dobDay; 
        this.dobMonth = dobMonth; 
        this.dobYear = dobYear; 
        this.nickName = nickName; 
        this.password = password;  
        this.picfilepath = picfilepath; 
        this.salt = salt;

        System.out.println("User 8 arg constructor");
        
    }
     
     public User(String email, Date Last_Login_Time)
     {
         this.email = email; 
         this.Last_Login_Time = Last_Login_Time; 
     }
     
//     public User(String fullName, String dobDay, String dobMonth, String dobYear, String password ) {
//        this.fullName = fullName;
//        this.dobDay = dobDay; 
//        this.dobMonth = dobMonth; 
//        this.dobYear = dobYear; 
//        this.password = password;   
//        System.out.println("User 5 arg constructor");
//        
//    }
     
     public User()
     {
         
     }
    
    //define set/get methods for all attributes.
      public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
    
      public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
      public String getDobDay() {
        return dobDay;
    }

    public void setDobDay(String dobDay) {
        this.dobDay = dobDay;
    }
    
      public String getDobMonth() {
        return dobMonth;
    }

    public void setDobMonth(String dobMonth) {
        this.dobMonth = dobMonth;
    }
    
      public String getDobYear() {
        return dobYear;
    }

    public void setDobYear(String dobYear) {
        this.dobYear = dobYear;
    }
    
      public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    
      public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
     public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    
}
