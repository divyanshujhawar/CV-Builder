package com.example.android.cvbuilderapp;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.android.cvbuilderapp.R.id.etLogInEmail;

public class UserFormActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;

    Button btPersonalDet;
    Button btEducat;
    Button btCert;
    Button btProExp;
    Button btSocialLink;
    Button btInterest;
    Button btSkills;

    EditText etCVName;

    String details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_form);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbarUser);
        setSupportActionBar(myToolbar);

        etCVName = (EditText) findViewById(R.id.etCVName);


        btPersonalDet = (Button) findViewById(R.id.btPersonalDet);
        btPersonalDet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserFormActivity.this, PersonalDetailsActivity.class));
            }
        });

        btEducat = (Button) findViewById(R.id.btEducat);
        btEducat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserFormActivity.this, EducationalDetailsActivity.class));
            }
        });


        btCert = (Button) findViewById(R.id.btCert);
        btCert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserFormActivity.this, ProjectsActivity.class));
            }
        });

        btProExp = (Button) findViewById(R.id.btProExp);
        btProExp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserFormActivity.this, ExperienceDetailsActivity.class));
            }
        });

        btSocialLink = (Button) findViewById(R.id.btSocialLink);
        btSocialLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserFormActivity.this, SocialLinkActivity.class));
            }
        });

        btSkills = (Button) findViewById(R.id.btSkills);
        btSkills.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserFormActivity.this, SkillsActivity.class));
            }
        });

        btInterest = (Button) findViewById(R.id.btInterest);
        btInterest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserFormActivity.this, InterestActivity.class));
            }
        });


    mDrawerLayout=(DrawerLayout)

    findViewById(R.id.activity_user_form);

    mToggle=new

    ActionBarDrawerToggle(this,mDrawerLayout, R.string.open, R.string.close);

    mDrawerLayout.addDrawerListener(mToggle);
    mToggle.syncState();

    getSupportActionBar()

    .

    setDisplayHomeAsUpEnabled(true);

    getSupportActionBar()

    .

    setHomeButtonEnabled(true);

}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nav_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch (item.getItemId()) {
            case R.id.action_settings:
                // User chose the "Settings" item, show the app settings UI...
                return true;

            case R.id.action_save:
                // User chose the "Settings" item, show the app settings UI...
                startActivity(new Intent(UserFormActivity.this, NavDrawer.class));
                return true;

            case R.id.action_cancel:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                finish();
                startActivity(new Intent(UserFormActivity.this, NavDrawer.class));
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            finish();
            startActivity(new Intent(UserFormActivity.this, NavDrawer.class));
        } else if (id == R.id.nav_create) {

        } else if (id == R.id.nav_view) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_rate) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
