package com.gr.facultyengagementbook.Authentication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gr.facultyengagementbook.Admin.HomeScreen.AdminHomeScreen;
import com.gr.facultyengagementbook.HomeScreen.HomeScreen;
import com.gr.facultyengagementbook.Model.DBHandler;
import com.gr.facultyengagementbook.R;

public class Login extends AppCompatActivity {

    private EditText mobileNo;
    private EditText password;
    private Button login;
    private TextView registerHere;
    private DBHandler dbHandler;
    private final Context context = this;

    public static final String PREFS = "Prefs";
    public static final String MOBILE_NO = "MobileNo";
    public static final String PASSWORD = "Password";

    SharedPreferences sharedPreferences;

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPreferences = getSharedPreferences(PREFS,MODE_PRIVATE);
        if(sharedPreferences.getString(MOBILE_NO,"").equals("9999999999"))
        {
            startActivity(new Intent(context,AdminHomeScreen.class));
            finish();
        }
        else if(!(sharedPreferences.getString(MOBILE_NO,"").equals(""))) {
            startActivity(new Intent(context, HomeScreen.class));
            finish();
        }

        initialize();
    }

    private void initialize() {

        dbHandler = new DBHandler(this,null,null,1);
        mobileNo = (EditText) findViewById(R.id.mobileNo_Login);
        password = (EditText) findViewById(R.id.password_Login);
        login = (Button) findViewById(R.id.LoginBtn);
        registerHere = (TextView) findViewById(R.id.registerHere_Login);
    }

    public void LoginBtnClicked(View view)

    {

        if(mobileNo.getText().toString().equals("") || password.getText().toString().equals(""))

            Toast.makeText(context, "Enter Details first!!", Toast.LENGTH_SHORT).show();

        else {

            if(mobileNo.getText().toString().equals("9999999999") && password.getText().toString().equals("admin"))
            {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(MOBILE_NO,mobileNo.getText().toString());
                editor.putString(PASSWORD,password.getText().toString());
                editor.commit();

                Intent intent = new Intent(context, AdminHomeScreen.class);
                startActivity(intent);
                finish();
            }

            else if(dbHandler.checkAuthentication(mobileNo.getText().toString(),password.getText().toString())) {


                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(MOBILE_NO,mobileNo.getText().toString());
                editor.putString(PASSWORD,password.getText().toString());
                editor.commit();


                Intent intent = new Intent(context,HomeScreen.class);
                startActivity(intent);
                finish();
            }

            else

                Toast.makeText(context, "Invalid User credentials!!", Toast.LENGTH_SHORT).show();
        }
    }

    public void RegisterHereClicked(View view) {

        startActivity(new Intent(context,SignUp.class));
        finish();
    }
}