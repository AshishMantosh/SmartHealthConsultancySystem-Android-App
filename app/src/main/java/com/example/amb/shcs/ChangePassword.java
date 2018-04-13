package com.example.amb.shcs;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ChangePassword extends AppCompatActivity {

    EditText _newPass1;
    EditText _newPass2;
    EditText _oldPass;
    Button _changeBtn;

    String userName;
    String email;
    String checkType;

    MyUserDBHandler dbHandler;

    String newPass1;
    String newPass2;
    String oldPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        _newPass1 = findViewById(R.id.newPass1);
        _newPass2 = findViewById(R.id.newPass2);
        _oldPass = findViewById(R.id.oldPass);
        _changeBtn = findViewById(R.id.changeBtn);

        // You can be pretty confident that the intent will not be null here.
        Intent intentData = getIntent();

        // Get the extras (if there are any)
        Bundle extras = intentData.getExtras();
        if (extras != null) {
            userName = extras.getString("userName");
            email = extras.getString("email");
            checkType = extras.getString("checkType");
        }

        _changeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newPass1 = _newPass1.getText().toString();
                newPass2 = _newPass2.getText().toString();
                oldPass = _oldPass.getText().toString();

                if(newPass1.equals(newPass2))
                {
                    Update();
                }
                else
                {
                    Toast.makeText(getBaseContext(), "Entered New Passwords don't match. Try Again" , Toast.LENGTH_SHORT).show();
                }

            }
        });

        dbHandler = new MyUserDBHandler(this, null, null, 12);
    }

    private void Update()
    {

        _changeBtn.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(ChangePassword.this,
                R.style.MyTheme);
        progressDialog.setIndeterminate(true);
        progressDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
        progressDialog.setMessage("Processing...");
        progressDialog.show();

        int id = dbHandler.findUserID(userName, oldPass);

        String strID = String.valueOf(id);

        Toast.makeText(getBaseContext(), strID , Toast.LENGTH_SHORT).show();


        dbHandler.updatePassword(strID, userName, email, newPass1, checkType);

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onSignupSuccess or onSignupFailed
                        // depending on success
                        onComplete();
                        // onSignupFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }

    public void onComplete() {
        _changeBtn.setEnabled(true);
        setResult(RESULT_OK, null);
        Toast.makeText(getBaseContext(), "Done", Toast.LENGTH_SHORT).show();

        Intent I = new Intent(this, PatientDashboard.class);
        startActivity(I);

    }
}
