package com.gr.facultyengagementbook.Authentication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gr.facultyengagementbook.HomeScreen.HomeScreen;
import com.gr.facultyengagementbook.Model.DBHandler;
import com.gr.facultyengagementbook.Model.FacultyDetails;
import com.gr.facultyengagementbook.R;

public class SignUp extends AppCompatActivity {

    private EditText name;
    private final Context context = this;
    private EditText mobileNo;
    private EditText emailId;
    private EditText password;
    private EditText confirmPassword;
    private Button signUp;
    private DBHandler dbHandler;
    private FacultyDetails facultyDetails;

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initialize();
    }

    private void initialize() {

        dbHandler = new DBHandler(this,null,null,1);

        name = (EditText)findViewById(R.id.name_SignUp);
        mobileNo= (EditText)findViewById(R.id.mobileNo_SignUp);
        emailId = (EditText)findViewById(R.id.emailId_SignUp);
        password = (EditText)findViewById(R.id.password_SignUp);
        confirmPassword = (EditText)findViewById(R.id.confirm_password_SignUp);
        signUp = (Button) findViewById(R.id.signUpBtn);
    }

    public void signUpClicked(View view) {

        if(name.getText().toString().equals("") || mobileNo.getText().toString().equals("") ||
                emailId.getText().toString().equals("") || password.getText().toString().equals("")
                || confirmPassword.getText().toString().equals(""))

            Toast.makeText(context, "Enter All Details!!", Toast.LENGTH_SHORT).show();
        else {

            if (mobileNo.getText().toString().length()!=10)
                Toast.makeText(context, "Enter a valid Mobile No.", Toast.LENGTH_SHORT).show();
            else {
                if (!(password.getText().toString().equals(confirmPassword.getText().toString())))
                    Toast.makeText(context, "Passwords Don't match", Toast.LENGTH_SHORT).show();
                else {

                    if(dbHandler.checkAvailability(mobileNo.getText().toString())) {
                        facultyDetails = new FacultyDetails(name.getText().toString(),
                                mobileNo.getText().toString(),emailId.getText().toString(),
                                password.getText().toString());

                        if((dbHandler.addNewFaculty(facultyDetails))!=-1) {

                            SharedPreferences sharedPreferences = getSharedPreferences(Login.PREFS,MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString(Login.MOBILE_NO,mobileNo.getText().toString());
                            editor.putString(Login.PASSWORD,password.getText().toString());
                            editor.commit();

                            dbHandler.createNewFacultyTable("Mon",mobileNo.getText().toString());
                            dbHandler.createNewFacultyTable("Tue",mobileNo.getText().toString());
                            dbHandler.createNewFacultyTable("Wed",mobileNo.getText().toString());
                            dbHandler.createNewFacultyTable("Thu",mobileNo.getText().toString());
                            dbHandler.createNewFacultyTable("Fri",mobileNo.getText().toString());
                            dbHandler.createNewFacultyTable("Sat",mobileNo.getText().toString());

                            Intent intent = new Intent(context , HomeScreen.class);
                            startActivity(intent);
                            finish();
                        }
                        else
                            Toast.makeText(context, "Unable to Register!!", Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(context, "Mobile No. already registered", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}