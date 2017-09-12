package com.example.android.cvbuilderapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import static android.R.id.edit;
import static android.R.id.message;

public class PersonalDetailsActivity extends AppCompatActivity {
    static final String stateuser = "user";
    private String name="Manan";

    EditText etFirstName;
    EditText etLastName;
    EditText etBirthDate;
    EditText etMail;
    EditText etPhoneNo;
    EditText etGender;
    EditText etNationality;
    EditText etLinkdinLink;
    EditText etFacebookLink;
    EditText etGithubLink;

    ImageView imgView;

    private static int RESULT_LOAD_IMG = 1;
    String imgDecodableString;

    Calendar myCalendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_details);

        if (savedInstanceState != null) {
            name = savedInstanceState.getString(stateuser);
            Log.i("ss", name);

                etFirstName.setText(name);

        }

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbarUser);
        setSupportActionBar(myToolbar);

        etFirstName = (EditText) findViewById(R.id.etFirstname);
        etLastName = (EditText) findViewById(R.id.etLastName);
        etBirthDate = (EditText) findViewById(R.id.etBirthDate);
        etMail = (EditText) findViewById(R.id.etMail);
        etPhoneNo = (EditText) findViewById(R.id.etPhoneNo);
        etGender = (EditText) findViewById(R.id.etGender);
        etNationality = (EditText) findViewById(R.id.etNationality);
        etLinkdinLink = (EditText) findViewById(R.id.etLinkdinLink);
        etFacebookLink = (EditText) findViewById(R.id.etFacebookLink);
        etGithubLink = (EditText) findViewById(R.id.etGithubLink);
        imgView = (ImageView) findViewById(R.id.ivProfileImage);

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };
        etBirthDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new DatePickerDialog(PersonalDetailsActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadImagefromGallery(v);
            }
        });



    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {


        savedInstanceState.putString(stateuser, name);
        Log.i("ss",savedInstanceState.toString());
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String myString = savedInstanceState.getString(stateuser);
        etFirstName.setText(myString);
    }

    private void updateLabel() {

        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        etBirthDate.setText(sdf.format(myCalendar.getTime()));
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
                startActivity(new Intent(PersonalDetailsActivity.this, UserFormActivity.class));
                return true;

            case R.id.action_cancel:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                finish();
                startActivity(new Intent(PersonalDetailsActivity.this, UserFormActivity.class));
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }

    public void loadImagefromGallery(View view) {
        // Create intent to Open Image applications like Gallery, Google Photos
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        // Start the Intent
        startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            // When an Image is picked
            if (requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK
                    && null != data) {
                // Get the Image from data

                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};

                // Get the cursor
                Cursor cursor = getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                // Move to first row
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                imgDecodableString = cursor.getString(columnIndex);
                cursor.close();

                // Set the Image in ImageView after decoding the String
                //imgView.setImageBitmap(BitmapFactory.decodeFile(imgDecodableString));

                Bitmap simage = BitmapFactory.decodeFile(imgDecodableString);
                Drawable d = new BitmapDrawable(simage);

                imgView.setImageURI(selectedImage);

            } else {
                Toast.makeText(this, "You haven't picked Image",
                        Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG)
                    .show();
        }

    }


}

