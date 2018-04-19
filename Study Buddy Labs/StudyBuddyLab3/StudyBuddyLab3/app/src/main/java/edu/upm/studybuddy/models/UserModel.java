package edu.upm.studybuddy.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class UserModel implements Serializable{
    @SerializedName("uid")
    @Expose
    private String uid;
    @SerializedName("name")
    @Expose
    private String mName;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("math_rating")
    @Expose
    private int mMathRating;
    @SerializedName("android_rating")
    @Expose
    private int mAndroidRating;
    @SerializedName("java_rating")
    @Expose
    private int mJavaRating;
    @SerializedName("ios_rating")
    @Expose
    private int mIosRating;
    @SerializedName("matched_ids")
    @Expose
    private ArrayList<String> mMatchedIds;
    @SerializedName("match_attempt")
    @Expose
    private ArrayList<String> mMatchAttempt;

    public UserModel() {
    }

    public UserModel(String uid, String mName, String email, String phone, int mMathRating,
                     int mAndroidRating, int mJavaRating, int mIosRating,
                     ArrayList<String> mMatchedIds, ArrayList<String> mMatchAttempts) {
        this.uid = uid;
        this.mName = mName;
        this.email = email;
        this.phone = phone;
        this.mMathRating = mMathRating;
        this.mAndroidRating = mAndroidRating;
        this.mJavaRating = mJavaRating;
        this.mIosRating = mIosRating;
        this.mMatchedIds = mMatchedIds;
        this.mMatchAttempt = mMatchAttempts;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getmMathRating() {
        return mMathRating;
    }

    public void setmMathRating(int mMathRating) {
        this.mMathRating = mMathRating;
    }

    public int getmAndroidRating() {
        return mAndroidRating;
    }

    public void setmAndroidRating(int mAndroidRating) {
        this.mAndroidRating = mAndroidRating;
    }

    public int getmJavaRating() {
        return mJavaRating;
    }

    public void setmJavaRating(int mJavaRating) {
        this.mJavaRating = mJavaRating;
    }

    public int getmIosRating() {
        return mIosRating;
    }

    public void setmIosRating(int mIosRating) {
        this.mIosRating = mIosRating;
    }

    public ArrayList<String> getmMatchedIds() {
        return mMatchedIds;
    }

    public void setmMatchedIds(ArrayList<String> mMatchedIds) {
        this.mMatchedIds = mMatchedIds;
    }

    public ArrayList<String> getmMatchAttempt() {
        return mMatchAttempt;
    }

    public void setmMatchAttempt(ArrayList<String> mMatchAttempt) {
        this.mMatchAttempt = mMatchAttempt;
    }

    public void addMatchedUser(String userId){

        if (this.mMatchedIds == null) {

            this.mMatchedIds = new ArrayList<String>();

        }

        this.mMatchedIds.add(userId);
    }

    public void addMatchAttempt(String userId){

        if (this.mMatchAttempt == null ) {

            this.mMatchAttempt = new ArrayList<String>();

        }

        this.mMatchAttempt.add(userId);
    }

    public void removeMatchAttempt(String userId){

        if (this.mMatchAttempt == null) {
            return;
        }

        for (String s : this.mMatchAttempt){

            if (s.equalsIgnoreCase(userId)){

                this.mMatchAttempt.remove(s);

            }

        }

    }
}
