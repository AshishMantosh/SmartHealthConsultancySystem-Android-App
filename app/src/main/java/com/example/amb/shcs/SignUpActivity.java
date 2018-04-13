package com.example.amb.shcs;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {

    EditText _nameText;
    EditText _typeText;
    EditText _emailText;
    EditText _passwordText;
    Button _signupButton;
    TextView _loginLink;
//    TextView _usersDatabase;

    MyUserDBHandler dbHandler;

    String name;
    String type;
    String email;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        _nameText = findViewById(R.id.input_name);
        _typeText = findViewById(R.id.input_type);
        _emailText = findViewById(R.id.createEmailUsername);
        _passwordText = findViewById(R.id.createPassword);
        _signupButton = findViewById(R.id.signUpButton);
        _loginLink = findViewById(R.id.linkLogin);
//        _usersDatabase = findViewById(R.id.usersDatabase);

        _signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUp();
            }
        });

        _loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        dbHandler = new MyUserDBHandler(this, null, null, 12);
//        printDatabase();
    }

    //Print the database
    public void printDatabase(){
        String dbString = dbHandler.databaseToString();
//        _usersDatabase.setText(dbString);
        _nameText.setText("");
        _typeText.setText("");
        _emailText.setText("");
        _passwordText.setText("");
    }

    public void signUp() {

        if (!validate()) {
            onSignupFailed();
            return;
        }

        _signupButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(SignUpActivity.this,
                R.style.MyTheme);
        progressDialog.setIndeterminate(true);
        progressDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();

        name = _nameText.getText().toString();
        type = _typeText.getText().toString();
        email = _emailText.getText().toString();
        password = _passwordText.getText().toString();

        // TODO: Implement your own signup logic here.
        User user = new User(email, password, name, type);
        dbHandler.addUser(user);
        printDatabase();

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onSignupSuccess or onSignupFailed
                        // depending on success
                        onSignupSuccess();
                        // onSignupFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);


    }


    public void onSignupSuccess() {
        _signupButton.setEnabled(true);
        setResult(RESULT_OK, null);
        Toast.makeText(getBaseContext(), "Account Created", Toast.LENGTH_SHORT).show();

        String type = dbHandler.checkUserType(email, password);

        if(type.equals("Patient"))
        {
            Toast.makeText(getBaseContext(), "Complete your registration, New Patient." , Toast.LENGTH_SHORT).show();
            Intent I = new Intent(this, MedicalFile.class);
            I.putExtra("userName", name);
            startActivity(I);
        }
        else
        {
            Toast.makeText(getBaseContext(), "Complete your registration, Doctor.", Toast.LENGTH_SHORT).show();
            Intent I = new Intent(this, DoctorReg.class);
            I.putExtra("DoctorName", name);
            startActivity(I);
        }

        //        finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_SHORT).show();

        _signupButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String name = _nameText.getText().toString();
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            _nameText.setError("at least 3 characters");
            valid = false;
        } else {
            _nameText.setError(null);
        }

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
