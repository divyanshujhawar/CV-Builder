package com.example.android.cvbuilderapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.android.cvbuilderapp.R.id.etLogInEmail;
import static com.example.android.cvbuilderapp.R.id.etLogInPassword;

public class SocialLinkActivity extends AppCompatActivity {

    EditText etGithubLink, etFacebookLink, etLinkdinLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_link);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbarUser);
        setSupportActionBar(myToolbar);

        etFacebookLink = (EditText)findViewById(R.id.etFacebookLink);
        etGithubLink = (EditText)findViewById(R.id.etGithubLink);
        etLinkdinLink = (EditText)findViewById(R.id.etLinkdinLink);
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

                String facebookLink= etFacebookLink.getText().toString();
                String githubLink = etGithubLink.getText().toString();
                String linkdinLink = etLinkdinLink.getText().toString();
                if(TextUtils.isEmpty(facebookLink)){
                    //email is empty
                    Toast.makeText(this,"Please enter facebook link",Toast.LENGTH_SHORT).show();
                    //Stopping the function execution further
                    return false;
                }
                if(TextUtils.isEmpty(linkdinLink)){
                    //password is empty
                    Toast.makeText(this,"Please enter linkdinlink",Toast.LENGTH_SHORT).show();
                    //Stopping the function execution further
                    return false;
                }
                if(TextUtils.isEmpty(githubLink)){
                    //password is empty
                    Toast.makeText(this,"Please enter github link",Toast.LENGTH_SHORT).show();
                    //Stopping the function execution further
                    return false;
                }

                else{
                    finish();
                    startActivity(new Intent(SocialLinkActivity.this, UserFormActivity.class));
                    return true;
                }

            case R.id.action_cancel:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                finish();
                startActivity(new Intent(SocialLinkActivity.this, UserFormActivity.class));
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }


}
