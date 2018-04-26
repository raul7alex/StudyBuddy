package edu.upm.studybuddy.helper;

import android.util.Patterns;

public class ValidationHelper {

    public static boolean isValidPassword(String password) {
        if (password == null || "".equals(password)) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean isValidEmail(CharSequence email) {
        if (email == null || email.equals("")) {
            return false;
        } else {
            return Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }
    }

    public static boolean arePasswordsSame(String password1, String password2) {
        if (password1.equals(password2)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isValidName(String name) {
        if (name == null || "".equals(name)) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean isValidPhone(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.equals("")) {
            return false;
        } else {
            return Patterns.PHONE.matcher(phoneNumber).matches();
        }
    }



}
