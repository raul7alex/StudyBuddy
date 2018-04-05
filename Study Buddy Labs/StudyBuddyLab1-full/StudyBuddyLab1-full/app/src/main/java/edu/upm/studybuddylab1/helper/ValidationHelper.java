package edu.upm.studybuddylab1.helper;

import android.util.Patterns;

public class ValidationHelper {

    public static boolean isValidPassword(String password) {

        if (password!= null && !password.isEmpty()){

            return true;

        }

        return false;
    }

    //TODO se poate folosi metoda Patterns.EMAIL_ADDRESS.matcher(email).matches()
    public static boolean isValidEmail(String email) {

        if (email!=null && !email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()){

            return true;

        }

        return false;
    }

    public static boolean arePasswordsSame(String password1, String password2) {

        if (password1.equalsIgnoreCase(password2)){

            return true;

        }

        return false;
    }

    public static boolean isValidName(String name) {

        if (name != null && !name.isEmpty()){

            return true;

        }

        return false;
    }

    //TODO se poate folosi metoda Patterns.PHONE.matcher(phoneNumber).matches()
    public static boolean isValidPhone(String phoneNumber) {

        if (phoneNumber != null && !phoneNumber.isEmpty() && Patterns.PHONE.matcher(phoneNumber).matches()){

            return true;

        }

        return false;

    }



}
