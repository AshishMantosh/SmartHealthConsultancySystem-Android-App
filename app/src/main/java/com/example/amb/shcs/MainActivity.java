package com.example.amb.shcs;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    EditText _emailText;
    EditText _passwordText;
    Button _loginButton;
    TextView _signUpLink;

    MyUserDBHandler dbHandler;

    String email;
    String password;
    String userName;
    String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _emailText = findViewById(R.id.inputEmail);
        _passwordText = findViewById(R.id.inputPassword);
        _loginButton = findViewById(R.id.loginButton);
        _signUpLink = findViewById(R.id.linkSignUp);

        _loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        _signUpLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });

        dbHandler = new MyUserDBHandler(this, null, null, 12);
    }

    public void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        _loginButton.setEnabled(false);


        email = _emailText.getText().toString();
        password = _passwordText.getText().toString();

        // TODO: Implement your own authentication logic here.
        if(dbHandler.checkUser(email, password) == true)
        {

            final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this,R.style.MyTheme);
            progressDialog.setIndeterminate(true);
            progressDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            progressDialog.setMessage("Authenticating...");
            progressDialog.show();

            type = dbHandler.checkUserType(email, password);

            if(type.equals("Patient"))
            {
                userName = dbHandler.getUserName(email, password);
                Toast.makeText(getBaseContext(), userName , Toast.LENGTH_SHORT).show();
                Intent I = new Intent(this, PatientDashboard.class);
                I.putExtra("userName", userName);
                I.putExtra("email", email);
                I.putExtra("checkType", type);
                startActivity(I);
            }
            else
            {
                userName = dbHandler.getUserName(email, password);
                Intent I = new Intent(this, DoctorDashboard.class);
                I.putExtra("doctorName", userName);
                startActivity(I);
            }

            new android.os.Handler().postDelayed(
                    new Runnable() {
                        public void run() {
                            // On complete call either onLoginSuccess or onLoginFailed
                            onLoginSuccess();
                            // onLoginFailed();
                            progressDialog.dismiss();
                        }
                    }, 3000);

        }
        else
        {
            onLoginFailed();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        }
    }


    @Override
    public void onBackPressed() {
        // disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {
        _loginButton.setEnabled(true);
        finish();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_SHORT).show();

        _emailText.setText("");
        _passwordText.setText("");

        _loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }
}
