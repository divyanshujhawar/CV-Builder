package com.example.android.cvbuilderapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class InterestActivity extends AppCompatActivity {
    EditText etInterest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interest);

        etInterest = (EditText)findViewById(R.id.etInterest);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbarUser);
        setSupportActionBar(myToolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nav_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_settings:
                // User chose the "Settings" item, show the app settings UI...
                return true;

            case R.id.action_save:
                // User chose the "Settings" item, show the app settings UI...
                finish();
                startActivity(new Intent(InterestActivity.this, UserFormActivity.class));
                return true;

            case R.id.action_cancel:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                finish();
                startActivity(new Intent(InterestActivity.this, UserFormActivity.class));
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }
}
