package edu.upm.studybuddy.listeners;

import edu.upm.studybuddy.models.UserModel;

public interface MatchListener {

    void matchPressed(UserModel userId, boolean shouldCall);

}
