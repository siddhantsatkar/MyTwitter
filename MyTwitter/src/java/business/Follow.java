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
public class Follow implements Serializable {
    
    private int UserId; 
    private int followedUserId; 
    private Date followDate; 

    public Date getFollowDate() {
        return followDate;
    }

    public void setFollowDate(Date followDate) {
        this.followDate = followDate;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int UserId) {
        this.UserId = UserId;
    }

    public int getFollowedUserId() {
        return followedUserId;
    }

    public void setFollowedUserId(int followedUserId) {
        this.followedUserId = followedUserId;
    }

   
   
    
    
    
}
