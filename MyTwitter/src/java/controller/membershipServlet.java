/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import business.Follow;
import business.User;
import business.Tweet;
import business.TweetUser;
import dataaccess.FollowDB;
import dataaccess.TweetDB;
import dataaccess.UserDB;
import java.io.File;
import java.io.FilePermission;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;
import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;
import java.util.Base64;
/**
 *
 * @author Giridhar Anuchand Kathiresan
 */
@WebServlet(name = "membershipServlet", urlPatterns = {"/membership"})
@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
                 maxFileSize=1024*1024*10,      // 10MB
                 maxRequestSize=1024*1024*50)   // 50MB
public class membershipServlet extends HttpServlet {
    public static String hashPassword(String password)
            throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.reset();
            md.update(password.getBytes());
            byte[] mdArray = md.digest();
            StringBuilder sb = new StringBuilder(mdArray.length * 2);
            for (byte b : mdArray) {
                int v = b & 0xff;
                        if (v < 16) {
                            sb.append('0');
                        }
            
            sb.append(Integer.toHexString(v));
        }
    return sb.toString();
    }
     public static String getSalt() {
         Random r = new SecureRandom();
         byte[] saltBytes = new byte[32];
         r.nextBytes(saltBytes);
         return Base64.getEncoder().encodeToString(saltBytes);
     }
     public static String hashAndSaltPassword(String password)
             throws NoSuchAlgorithmException {
         String salt = getSalt();
         return hashPassword(password + salt);
     }
     
     private static final String SAVE_DIR = "uploadfiles";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
   

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action=request.getParameter("action");
	HttpSession session=request.getSession();
        if(action.equals("logout")){
       //     HttpSession logOutSession = request.getSession(false); 
            User user_Log = (User)session.getAttribute("theUser"); 
             Date date = new Date(); 
//            java.util.Date utilDate = new java.util.Date();
//            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            String emailId_Log = user_Log.getEmail(); 
            user_Log.setLastLoginTime(date);
            UserDB.updateLogOutTime(user_Log);
                    session.invalidate();
                    getServletContext().getRequestDispatcher("/logout.jsp").forward(request, response);
                }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = "/login.jsp";
         // get current action
        String action = request.getParameter("action");
        
        // creating a session object 
        HttpSession session=request.getSession();
        
        ArrayList<Tweet> tweetList = new ArrayList<Tweet>(); 
        ArrayList trailList = new ArrayList(); 
        ArrayList<User> user_List = new ArrayList<User>(); 
        ArrayList<User> whoToFollow = new ArrayList<User>(); 
        HashMap<Tweet,User> viewtweets = new HashMap<Tweet,User>(); 
        ArrayList<TweetUser> tweetUserDet = new ArrayList<TweetUser>(); 
        String sb = ""; 
        String hashstr = "<font color='#2B7BB9'>"; 
        int tweetCount = 0; 

        // perform action and set URL to appropriate page
        if (action.equals("logIn")) {                
            // get parameters from the request
           System.out.println("Log In"); 
            try{
            String message = null;
            String email = request.getParameter("email");
            String password1 = request.getParameter("password"); 
            System.out.println("email is "+email);
            System.out.println("password is"+password1);
            String saltUser = "";
            saltUser = UserDB.salt(email);
            System.out.println("salt is"+saltUser);
            String password = hashPassword(password1+saltUser);
            
//            User theUser = UserDB.select(email,password); 
            User theUser = null; 
            theUser = UserDB.select(email,password); 
            System.out.println("the user is " +theUser); 
//            System.out.println("User name is "+theUser.getFullName());
//            if(!(theUser.getFullName().equals(null)))
//            {
//                url = "/login.jsp";   // the "home" page
//            }
        
           
            if(theUser.getFullName() != null)
            {
                System.out.println("theuser value is not null");
                    // set User object in request object and set URL
            request.setAttribute("user", theUser);
            
            // Binds the user object to the session
            session.setAttribute("theUser",theUser);
            user_List = UserDB.selectAll(); 
//            tweetList = TweetDB.search();
            int userId_Nu = theUser.getUserId(); 
            tweetList = TweetDB.search(email,userId_Nu);
            String temp1 = null; 
            String temp2 = null; 
//            trailList = TweetDB.search();
            for(int i=0; i<tweetList.size(); i++)
            {
//                System.out.println("tweetList "+trailList.get(i)); 
                System.out.println("tweetList "+tweetList.get(i).getEmail());
                temp1 = tweetList.get(i).getEmail().trim(); 
                System.out.println("user list size "+user_List.size());
                System.out.println("i value "+i);
                TweetUser tuObj = new TweetUser(); 
                for(int j=0; j<user_List.size(); j++)
                {
                    System.out.println("for loop");
                    System.out.println("j value "+j);
                    System.out.println("user_List getEmail() "+user_List.get(j).getEmail());
                    temp2 = user_List.get(j).getEmail().trim();
                    System.out.println("temp1 "+temp1);
                    System.out.println("temp2 "+temp2);
//                    if(tweetList.get(i).getEmail().equals(user_List.get(j).getEmail()))
                    if(temp1.equals(temp2))
                    {
                           System.out.println("if condition");
                           System.out.println("user_List.get(j).getPicfilepath() " +user_List.get(j).getPicfilepath());
                           tuObj.setPicfilepath(user_List.get(j).getPicfilepath());
                           System.out.println("tuobj.setpicfilepath "+tuObj.getPicfilepath());
                           tuObj.setFullName(user_List.get(j).getFullName());
                           tuObj.setNickName(user_List.get(j).getNickName());
                           tuObj.setDate(tweetList.get(i).getDate());
                           tuObj.setTweetid(tweetList.get(i).getTweetid());
                           tuObj.setEmail(tweetList.get(i).getEmail());
                            System.out.println("testing tuObj " +tuObj.getTweetid());
                           // Hash tag text
                           String twitText = tweetList.get(i).getTweet();
                           System.out.println("twitText "+twitText);
                           String[] eachString = twitText.split(" ");
                           for(int x=0; x<eachString.length; x++)
                           {
                               String tempStr1 = eachString[x]; 
                               char checkHash = tempStr1.charAt(0); 
//                               char checkspace = tempStr1.charAt(1); 
                               if(checkHash == '#' || checkHash == '@')
                               {
//                                    eachString[x] = eachString[x].replace(eachString[x],"<font color='#2B7BB9'>eachString[x]</font>");
//                                    hashstr.concat("<font color='#2B7BB9'>"); 
                                  
//                                    hashstr.concat(eachString[x]); 
//                                    hashstr.concat("</font>"); 
                                      hashstr = hashstr +eachString[x] +"</font>"; 
                                      eachString[x] = hashstr; 
                                      hashstr = "<font color='#2B7BB9'>";  
                                    System.out.println("eachString[x] "+eachString[x]);
                               }
                     
                           }
                           // Appending the strings 
                          
                           for(int y=0; y<eachString.length; y++)
                           {
//                               sb.concat(eachString[y]); 
//                               sb.concat(" "); 
                               sb = sb + " " +eachString[y]; 
                           }
                           
//                           tuObj.setText(tweetList.get(i).getTweet());
                           System.out.println("sb value "+sb);
                           tuObj.setText(sb);
                           sb =""; 
                           tweetUserDet.add(tuObj); 
//                        viewtweets.put(tweetList.get(i), user_List.get(j)); 
                           
                    }
                    else
                    {
                        System.out.println("else");
                            
                    }
                }
            }
            
//            session.setAttribute("tweetList", tweetList);
//            session.setAttribute("viewtweets", viewtweets);
            session.setAttribute("tweetUserDet", tweetUserDet);
            
            // code for who to follow 
            for(int k=0; k<user_List.size(); k++)
            {
                if(!(theUser.getEmail().trim().equals(user_List.get(k).getEmail().trim())))
                {
                    whoToFollow.add(user_List.get(k)); 
                }
            }
            session.setAttribute("whoToFollow", whoToFollow);
            

            
            // code for no of tweets 
            tweetCount = TweetDB.count(theUser); 
            session.setAttribute("tweetCount", tweetCount);
            
            // Logic for notifications starts here 
            ArrayList<Tweet> notifyTweets = new ArrayList<Tweet>(); 
            notifyTweets = TweetDB.searchNotifyTweets(theUser); 
            // Logic to convert tweet class to tweetUser class begins here
            ArrayList<TweetUser> tweetUserNotify = new ArrayList<TweetUser>(); 
            ArrayList<User> user_List2 = new ArrayList<User>(); 
            user_List2 = UserDB.selectAll(); 
String sb2 = ""; 
String hashstr2 = "<font color='#2B7BB9'>"; 
            String temp3 = null; 
            String temp4 = null; 
//            trailList = TweetDB.search();
            for(int i=0; i<notifyTweets.size(); i++)
            {
//                System.out.println("notifyTweets "+trailList.get(i)); 
                System.out.println("notifyTweets "+notifyTweets.get(i).getEmail());
                temp3 = notifyTweets.get(i).getEmail().trim(); 
                System.out.println("user list size "+user_List2.size());
                System.out.println("i value "+i);
                TweetUser tuObj2 = new TweetUser(); 
                for(int j=0; j<user_List2.size(); j++)
                {
                    System.out.println("for loop");
                    System.out.println("j value "+j);
                    System.out.println("user_List2 getEmail() "+user_List2.get(j).getEmail());
                    temp4 = user_List2.get(j).getEmail().trim();
                    System.out.println("temp3 "+temp3);
                    System.out.println("temp4 "+temp4);
//                    if(notifyTweets.get(i).getEmail().equals(user_List2.get(j).getEmail()))
                    if(temp3.equals(temp4))
                    {
                           System.out.println("if condition");
                           System.out.println("user_List2.get(j).getPicfilepath() " +user_List2.get(j).getPicfilepath());
                           tuObj2.setPicfilepath(user_List2.get(j).getPicfilepath());
                           System.out.println("tuObj2.setpicfilepath "+tuObj2.getPicfilepath());
                           tuObj2.setFullName(user_List2.get(j).getFullName());
                           tuObj2.setNickName(user_List2.get(j).getNickName());
                           tuObj2.setDate(notifyTweets.get(i).getDate());
                           
                           // Hash tag text
                           String twitText2 = notifyTweets.get(i).getTweet();
                           System.out.println("twitText2 "+twitText2);
                           String[] eachString2 = twitText2.split(" ");
                           for(int x=0; x<eachString2.length; x++)
                           {
                               String tempStr2 = eachString2[x]; 
                               char checkHash2 = tempStr2.charAt(0); 
//                               char checkspace = tempStr2.charAt(1); 
                               if(checkHash2 == '#' || checkHash2 == '@')
                               {
//                                    eachString2[x] = eachString2[x].replace(eachString2[x],"<font color='#2B7BB9'>eachString2[x]</font>");
//                                    hashstr2.concat("<font color='#2B7BB9'>"); 
                                  
//                                    hashstr2.concat(eachString2[x]); 
//                                    hashstr2.concat("</font>"); 
                                      hashstr2 = hashstr2 +eachString2[x] +"</font>"; 
                                      eachString2[x] = hashstr2; 
                                      hashstr2 = "<font color='#2B7BB9'>";  
                                    System.out.println("eachString2[x] "+eachString2[x]);
                               }
                     
                           }
                           // Appending the strings 
                          
                           for(int y=0; y<eachString2.length; y++)
                           {
//                               sb2.concat(eachString2[y]); 
//                               sb2.concat(" "); 
                               sb2 = sb2 + " " +eachString2[y]; 
                           }
                           
//                           tuObj2.setText(notifyTweets.get(i).getTweet());
                           System.out.println("sb2 value "+sb2);
                           tuObj2.setText(sb2);
                           sb2 =""; 
                           tweetUserNotify.add(tuObj2); 
//                        viewtweets.put(notifyTweets.get(i), user_List2.get(j)); 
                           
                    }
                    else
                    {
                        System.out.println("else");
                            
                    }
                }
            }
            // Logic to convert tweet class to tweetUser Class ends here 
            ArrayList<Follow> notifyFollowers = new ArrayList<Follow>(); 
            notifyFollowers = FollowDB.searchNotifyFollowers(theUser); 
            User eachFollower  = new User(); 
            ArrayList<User> followerDetails = new ArrayList<User>(); 
            // Logic to get all the user details of the followers starts here 
            for(int p=0; p<notifyFollowers.size(); p++)
            {
                eachFollower = UserDB.selectUser_Id(notifyFollowers.get(p).getFollowedUserId()); 
                followerDetails.add(eachFollower); 
            }
            // Logic to get all the user details of the followers ends here 
            int r=0, s=0, t =0; 
            ArrayList sortedNotification = new ArrayList<>(); 
            HashMap<String, ArrayList> notifyList = new HashMap<String, ArrayList>();
            int notifySize = notifyTweets.size() + notifyFollowers.size(); 
            String[] tweetFollow = new String[notifySize]; 
            while(r < notifyTweets.size() && s < notifyFollowers.size())
            {
                if(notifyTweets.get(r).getDate().after(notifyFollowers.get(s).getFollowDate()))
                {
                    tweetFollow[t] = "tweet"; 
                    t++; 
                    sortedNotification.add(tweetUserNotify.get(r)); 
                    r++; 
                    notifyList.put(tweetFollow[t], sortedNotification); 
                }
                else
                {
                    tweetFollow[t] = "follow"; 
                    t++; 
                    sortedNotification.add(followerDetails.get(s)); 
                    s++; 
                    notifyList.put(tweetFollow[t], sortedNotification);
                }
            }
            while(r < notifyTweets.size() )
            {
                tweetFollow[t] = "tweet";      
                sortedNotification.add(tweetUserNotify.get(r)); 
                notifyList.put(tweetFollow[t], sortedNotification); 
                t++; 
                r++; 
            }
             while(s < notifyFollowers.size() )
            {
                tweetFollow[t] = "follow";           
                sortedNotification.add(followerDetails.get(s));
                notifyList.put(tweetFollow[t], sortedNotification);
                t++; 
                s++; 
            }
       //      notifyList.get(tweetFollow[t]); 
             session.setAttribute("notifyType", tweetFollow);
             session.setAttribute("notifyValue", sortedNotification);
            session.setAttribute("notifications", notifyList);
            
            
            
            // Logic for notifications ends here 
            
            url = "/home.jsp";   // the "home" page
            }
            
             else
            {
                System.out.println("theuser value is null");
                message = "No user found or Username/ Password is incorrect"; 
//                request.setAttribute("message", message);
//                throw new IllegalArgumentException(message);
                url="/login.jsp";
            }
                      request.setAttribute("message", message);
        
            }catch (NoSuchAlgorithmException ex) {
             System.out.println(ex);
         }
        }
        
        // profile updates logic 
        if (action.equals("update")) {                
            // get parameters from the request
           System.out.println("Update"); 
           try{
           String message = null;
            String fullName = request.getParameter("fullName");
            String email = request.getParameter("email");
            System.out.println("email value "+email);
            String dobDay = request.getParameter("dob_Day");
            String dobMonth = request.getParameter("dob_Month");
            String dobYear = request.getParameter("dob_Year");
            String nickName = request.getParameter("nickName");
            String password1 = request.getParameter("password");     
            String saltUser = "";
            saltUser = UserDB.salt(email);
            System.out.println("salt is"+saltUser);
            String password = hashPassword(password1+saltUser);
            System.out.println("password is"+password);    
            if (dobMonth.equals("2") && (dobDay.equals("29")||dobDay.equals("30")||dobDay.equals("31"))){
            
                message = "February has only 28 days";
                url = "/signup.jsp";
            }
            else if ((dobMonth.equals("4")||dobMonth.equals("6")||dobMonth.equals("9")||dobMonth.equals("11")) && (dobDay.equals("31"))){
                message = "This month has only 30days";
                url = "/signup.jsp";
            }
            else if (password.length()<7){
                message = "Password must be 7 characters or above";
                url = "/signup.jsp";

            }
            // store data in User object and save User object in database
//            User user = new User(fullName, dobDay, dobMonth, dobYear, password );
            else
            {
                 User user = new User(fullName, email, dobDay, dobMonth, dobYear, nickName, password, saltUser);
                UserDB.update(user);
                // Logic for uploading the profile pic starts here 
//                String SAVE_DIR = "uploadfiles";
                // gets absolute path of the web application
        String appPath = request.getServletContext().getRealPath("");
         System.out.println("appPath "+appPath);
                System.out.println("else of membership servlet upload pic");
        // constructs path of the directory to save uploaded file
        String savePath = appPath + File.separator + SAVE_DIR;
            System.out.println("savePath "+savePath);
         
        // creates the save directory if it does not exists
        File fileSaveDir = new File(savePath);
            System.out.println("fileSaveDir.exist() "+fileSaveDir.exists());
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }
        /* Testing begins 
          for (Part part : request.getParts()) {
           String fileName = extractFileName(part);
            System.out.println("fileName "+fileName);
//           theUser = (User)request.getSession().getAttribute("theUser");
//            System.out.println("savePath + File.separator + fileName is "+savePath + File.separator + fileName);
            FilePermission permission = new FilePermission(savePath + File.separator + fileName, "write");
            part.write(savePath + File.separator + fileName);
            UserDB.updatepic(user, fileName);
            user.setPicfilepath(fileName);
        } 
        Testing ends */
       Part part = request.getPart ("file");
           String fileName = extractFileName(part);
            System.out.println("fileName "+fileName);
//           theUser = (User)request.getSession().getAttribute("theUser");
//            System.out.println("savePath + File.separator + fileName is "+savePath + File.separator + fileName);
            FilePermission permission = new FilePermission(savePath + File.separator + fileName, "write");
            part.write(savePath + File.separator + fileName);
            UserDB.updatepic(user, fileName);
            user.setPicfilepath(fileName);
        
        // Logic for uploading the profile pic ends here 
           
//            User theUser = UserDB.select(user.getEmail());
            
            // set User object in request object and set URL
            request.setAttribute("user", user);
            
            // Binds the user object to the session
            session.setAttribute("theUser",user);
            url = "/home.jsp";   // the "home" page
            }
            request.setAttribute("message", message);
           } catch(NoSuchAlgorithmException ex) {
             System.out.println(ex);
         }
        }
     
        // sign up logic
       if (action.equals("signUp")) {                
            // get parameters from the request
           System.out.println("Sign Up");
           try{
           String message = null;
            String fullName = request.getParameter("fullName");
            String email = request.getParameter("email");
            String dobDay = request.getParameter("dob_Day");
            String dobMonth = request.getParameter("dob_Month");
            String dobYear = request.getParameter("dob_Year");
            String nickName = request.getParameter("nickName");
            String password1 = request.getParameter("password");
            
                String salt = getSalt();
                System.out.println(salt);
                String password = hashPassword(password1+salt);
            
// Date of birth validation 
            if (dobMonth.equals("2") && (dobDay.equals("29")||dobDay.equals("30")||dobDay.equals("31"))){
            
                message = "February has only 28 days";
                url = "/signup.jsp";
            }
            else if ((dobMonth.equals("4")||dobMonth.equals("6")||dobMonth.equals("9")||dobMonth.equals("11")) && (dobDay.equals("31"))){
                message = "This month has only 30days";
                url = "/signup.jsp";
            }
            else if (password.length()<7){
                message = "Password must be 7 characters or above";
                url = "/signup.jsp";

            }
        else{
            // store data in User object and save User object in database
            User user = new User(fullName, email, dobDay, dobMonth, dobYear, nickName, password, salt );
            UserDB.insert(user);
            
            // set User object in request object and set URL
            request.setAttribute("user", user);
            
            // Binds the user object to the session
            session.setAttribute("theUser",user);
            
            // code for getting other tweets 
            user_List = UserDB.selectAll(); 
//            tweetList = TweetDB.search();
            // code change for getting the tweets related to the user 
            int userId_Nu = user.getUserId(); 
            tweetList = TweetDB.search(email,userId_Nu);
            // code change for getting the tweets related to the user 
            String temp1 = null; 
            String temp2 = null; 
//            trailList = TweetDB.search();
            for(int i=0; i<tweetList.size(); i++)
            {
//                System.out.println("tweetList "+trailList.get(i)); 
                System.out.println("tweetList "+tweetList.get(i).getEmail());
                temp1 = tweetList.get(i).getEmail().trim(); 
                System.out.println("user list size "+user_List.size());
                System.out.println("i value "+i);
                TweetUser tuObj = new TweetUser(); 
                for(int j=0; j<user_List.size(); j++)
                {
                    System.out.println("for loop");
                    System.out.println("j value "+j);
                    System.out.println("user_List getEmail() "+user_List.get(j).getEmail());
                    temp2 = user_List.get(j).getEmail().trim();
                    System.out.println("temp1 "+temp1);
                    System.out.println("temp2 "+temp2);
//                    if(tweetList.get(i).getEmail().equals(user_List.get(j).getEmail()))
                    if(temp1.equals(temp2))
                    {
                           System.out.println("if condition");
                           tuObj.setPicfilepath(user_List.get(j).getPicfilepath());
                           System.out.println("tuobj.setpicfilepath "+tuObj.getPicfilepath());
                           tuObj.setFullName(user_List.get(j).getFullName());
                           tuObj.setNickName(user_List.get(j).getNickName());
                           tuObj.setDate(tweetList.get(i).getDate());
                           tuObj.setTweetid(tweetList.get(i).getTweetid());
                           tuObj.setEmail(tweetList.get(i).getEmail());
                           System.out.println("testing tuObj " +tuObj.getTweetid());
//                           tuObj.setText(tweetList.get(i).getTweet());
                           // Hash tag code begins
                           String twitText = tweetList.get(i).getTweet();
                           System.out.println("twitText "+twitText);
                           String[] eachString = twitText.split(" ");
                           for(int x=0; x<eachString.length; x++)
                           {
                               String tempStr1 = eachString[x]; 
                               char checkHash = tempStr1.charAt(0); 
//                               char checkspace = tempStr1.charAt(1); 
                               if(checkHash == '#' || checkHash == '@')
                               {
//                                    eachString[x] = eachString[x].replace(eachString[x],"<font color='#2B7BB9'>eachString[x]</font>");
//                                    hashstr.concat("<font color='#2B7BB9'>"); 
                                  
//                                    hashstr.concat(eachString[x]); 
//                                    hashstr.concat("</font>"); 
                                      hashstr = hashstr +eachString[x] +"</font>"; 
                                      eachString[x] = hashstr; 
                                      hashstr = "<font color='#2B7BB9'>";  
                                    System.out.println("eachString[x] "+eachString[x]);
                               }
                     
                           }
                           // Appending the strings 
                          
                           for(int y=0; y<eachString.length; y++)
                           {
//                               sb.concat(eachString[y]); 
//                               sb.concat(" "); 
                               sb = sb + " " +eachString[y]; 
                           }
                           
//                           tuObj.setText(tweetList.get(i).getTweet());
                           System.out.println("sb value "+sb);
                           tuObj.setText(sb);
                           sb =""; 
						   
						   // Hashtag code ends here
                           tweetUserDet.add(tuObj); 
//                        viewtweets.put(tweetList.get(i), user_List.get(j)); 
                           
                    }
                    else
                    {
                        System.out.println("else");
                            
                    }
                }
            }
            
//            session.setAttribute("tweetList", tweetList);
//            session.setAttribute("viewtweets", viewtweets);
            session.setAttribute("tweetUserDet", tweetUserDet);
            
           
            
            // code for who to follow 
            for(int k=0; k<user_List.size(); k++)
            {
                if(!(user.getEmail().trim().equals(user_List.get(k).getEmail().trim())))
                {
                    whoToFollow.add(user_List.get(k)); 
                }
            }
            session.setAttribute("whoToFollow", whoToFollow);
            
            url = "/home.jsp";   // the "home" page
            }
            request.setAttribute("message", message);
           }catch (NoSuchAlgorithmException ex) {
             System.out.println(ex);
         }
       }
         if (action.equals("forgot")) {                
            // get parameters from the request
           System.out.println("forgot");
            try{
            String email = request.getParameter("email");
            String ALPHA_CAPS  = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	    String ALPHA   = "abcdefghijklmnopqrstuvwxyz";
	    String NUM     = "0123456789";
	    String SPL_CHARS   = "!@#$%^&*_=+-/";
	 
	    Random rnd = new Random();
        char[] pswd = new char[12];
        int index = -1;
        for (int i = 0; i < 3; i++) {
            pswd[++index] = ALPHA_CAPS.charAt(rnd.nextInt(ALPHA_CAPS.length()));
            pswd[++index] = ALPHA.charAt(rnd.nextInt(ALPHA.length()));
            pswd[++index] = NUM.charAt(rnd.nextInt(NUM.length()));
            pswd[++index] = SPL_CHARS.charAt(rnd.nextInt(SPL_CHARS.length()));
        }

        String newPassword1 = new String(pswd);
        String saltUser = "";
            saltUser = UserDB.salt(email);
            System.out.println("salt is"+saltUser);
            String newPassword = hashPassword(newPassword1+saltUser);
            
        
           UserDB.updatepass(email, newPassword);
           User theUser = null; 
            theUser = UserDB.selectemail(email); 
            System.out.println("the user is " +theUser); 
           
           // send email to user
            String to = email;
            String from = theUser.getEmail();
            String subject = "Forgot password mail";
            String body = "Dear " + theUser.getFullName() + ",\n\n" +
                "your new password is " +newPassword1;
                 
     
            boolean isBodyHTML = false;

            try
            {
                MailUtilGmail.sendMail(to, from, subject, body, isBodyHTML);
            }
            catch (MessagingException e)
            {
                String errorMessage = 
                    "ERROR: Unable to send email. " + 
                        "Check Tomcat logs for details.<br>" +
                    "NOTE: You may need to configure your system " + 
                        "as described in chapter 14.<br>" +
                    "ERROR MESSAGE: " + e.getMessage();
                request.setAttribute("errorMessage", errorMessage);
                this.log(
                    "Unable to send email. \n" +
                    "Here is the email you tried to send: \n" +
                    "=====================================\n" +
                    "TO: " + email + "\n" +
                    "FROM: " + from + "\n" +
                    "SUBJECT: " + subject + "\n" +
                    "\n" +
                    body + "\n\n");
            }       
             //System.out.println("password"+passobj.getPassword());
           //logout logic
//           if (action.equals("logout")) { 
//               
//                if(session!=null)
//session.invalidate();  
//               
//               
//           }
            }catch(NoSuchAlgorithmException ex) {
             System.out.println(ex);
         }
         }
        
        // forward request and response objects to specified URL
        getServletContext()
            .getRequestDispatcher(url)
            .forward(request, response);
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

     /**
     * Extracts file name from HTTP header content-disposition
     */
    private String extractFileName(Part part) {
        System.out.println("extractFileName");
        System.out.println("Part "+part);
        String contentDisp = part.getHeader("content-disposition");
        System.out.println("contentDisp "+contentDisp);
        String[] items = contentDisp.split(";");
        for (String s : items) {
            System.out.println("s "+s);
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length()-1);
            }
        }
        return "";
    }
    
}
