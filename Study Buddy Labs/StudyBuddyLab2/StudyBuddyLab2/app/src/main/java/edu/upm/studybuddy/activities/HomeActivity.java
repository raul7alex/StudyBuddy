package edu.upm.studybuddy.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import edu.upm.studybuddy.R;

public class HomeActivity extends BaseActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        //TODO instantierea meniului care va fi afisat in dreapta sus

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //TODO a se implementa ce se intampla la click pe itemul din meniu

        //TODO item ar trebui sa aiba id-ul == R.id.action_settings


        return super.onOptionsItemSelected(item);
    }

}
