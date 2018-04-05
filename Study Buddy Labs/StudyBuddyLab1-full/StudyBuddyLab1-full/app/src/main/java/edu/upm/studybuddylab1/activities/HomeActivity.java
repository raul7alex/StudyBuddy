package edu.upm.studybuddylab1.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import edu.upm.studybuddylab1.R;

public class HomeActivity extends BaseActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().show();

    }

}
