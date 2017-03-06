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
 * @author Giridhar Anuchand Kathiresan <giridharanuchand@gmail.com>
 */
public class Tweet implements Serializable {
     private int tweetid;
     private String text;
     private String email; 
     private Date date; 
     private int mentionedUserID; 

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getMentionedUserID() {
        return mentionedUserID;
    }

    public void setMentionedUserID(int mentionedUserID) {
        this.mentionedUserID = mentionedUserID;
    }
     
     public Tweet()
     {
         
     }
     
     public Tweet(String email, Date date, String text)
     {
         this.email = email; 
         this.date = date; 
         this.text = text;  
     }
     
     public Tweet(String email, Date date, String text, int mentionedUserID)
     {
         this.email = email; 
         this.date = date; 
         this.text = text; 
         this.mentionedUserID = mentionedUserID; 
     }
     
     // Getters and setters 
     public String getTweet()
     {
         return text; 
     }
     
     public void setTweet(String text)
     {
         this.text = text; 
     }
     
      public String getEmail()
     {
         return email; 
     }
     
     public void setEmail(String email)
     {
         this.email = email; 
     }
     
       public Date getDate()
     {
         return date; 
     }
     
     public void setDate(Date date)
     {
         this.date = date; 
     }
     
      public int getTweetid() {
        return tweetid;
    }

    public void setTweetid(int tweetid) {
        this.tweetid = tweetid;
    }
}
