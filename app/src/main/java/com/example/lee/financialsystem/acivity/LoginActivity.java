package com.example.lee.financialsystem.acivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lee.financialsystem.MainActivity;
import com.example.lee.financialsystem.R;

/**
 * Created by lee on 2019/4/13.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private Button login, registered;
    private EditText et_user, et_psw;
    private String username, password;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private String TAG = "LoginActivity";
    private TextView forget;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init() {
        login = findViewById(R.id.login);
        registered = findViewById(R.id.registered);
        et_psw = findViewById(R.id.et_psw);
        et_user = findViewById(R.id.et_user);
        forget = findViewById(R.id.forget);
        login.setOnClickListener(this);
        registered.setOnClickListener(this);
        forget.setOnClickListener(this);

        sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
        username = sharedPreferences.getString("username", "");
        password = sharedPreferences.getString("password", "");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
                if (et_psw.getText().toString().equals("")||et_user.getText().toString().equals("")){
                    Toast.makeText(LoginActivity.this, "用户名和密码不对", Toast.LENGTH_SHORT).show();
                }else {
                    if (et_user.getText().toString().equals(username) && et_psw.getText().toString().equals(password)) {
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginActivity.this, "用户名和密码不对", Toast.LENGTH_SHORT).show();
                    }
                }
                Log.d(TAG, "username:" + username);
                Log.d(TAG, "et_user:" + et_user.getText());
                Log.d(TAG, "password:" + password);
                Log.d(TAG, "et_psw:" + et_psw.getText());
                break;
            case R.id.registered:
                Intent intent2 = new Intent(LoginActivity.this, RegActivity.class);
                startActivity(intent2);
                break;
            case R.id.forget:
                Intent intent3 = new Intent(LoginActivity.this, ForgetAcivity.class);
                startActivity(intent3);
                break;
        }
    }
}
