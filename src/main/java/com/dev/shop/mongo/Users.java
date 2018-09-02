package com.dev.shop.mongo;

import com.dev.shop.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;
import sun.usagetracker.UsageTrackerClient;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Users {

    @Id
    private String id;

    private String userId;
    private String userPassword;
    private String userNickname;
    private String userEmail;
    private String userName;
    private ArrayList<String> userPurchase;


    public String getId() {
        return id;
    }

    public ArrayList<String> getUserPurchase() {
        return userPurchase;
    }

    public void setUserPurchase(ArrayList<String> userPurchase) {
        this.userPurchase = userPurchase;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public Users() {

    }

    public Users(String user_id, String user_password, String user_nickname, String user_email, String user_name) {
        this.userId = user_id;
        this.userPassword = user_password;
        this.userNickname = user_nickname;
        this.userEmail = user_email;
        this.userName = user_name;
        this.userPurchase = new ArrayList<String>();
    }
}
