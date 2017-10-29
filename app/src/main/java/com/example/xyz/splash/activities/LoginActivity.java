package com.example.xyz.splash.activities;

import android.content.Intent;
import android.os.Build;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.xyz.splash.R;
import com.example.xyz.splash.util.PreferenceUtil;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private TextInputLayout tiEmail;
    private TextInputLayout tiPassword;
    private EditText etEmail;
    private EditText etPassword;
    private Button btnLogin;
    private View mProgressView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.statusBarColor));
        }

        tiEmail = (TextInputLayout) findViewById(R.id.ti_email);
        tiPassword = (TextInputLayout) findViewById(R.id.ti_password);
        etEmail = (EditText) findViewById(R.id.et_email);
        etPassword = (EditText) findViewById(R.id.et_password);
        btnLogin = (Button) findViewById(R.id.log_in_button);
        mProgressView = findViewById(R.id.login_progress);

        etEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                tiEmail.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        etPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                tiPassword.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.log_in_button:

                if (etEmail.length() == 0) {
                    tiEmail.setError("Email field cannot be empty");
                } else if(etPassword.length() == 0) {
                    tiPassword.setError("Password field cannot be empty");
                } else {
                    //mProgressView.setVisibility(View.VISIBLE);
                    btnLogin.setVisibility(View.GONE);
                    PreferenceUtil.saveSignupCredentials(this, PreferenceUtil.LOGIN_DETAILS, PreferenceUtil.EMAIL, PreferenceUtil.PASSWORD);
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }

                break;
        }
    }

}
