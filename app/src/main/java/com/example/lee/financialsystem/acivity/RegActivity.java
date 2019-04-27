package com.example.lee.financialsystem.acivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lee.financialsystem.R;

/**
 * Created by lee on 2019/4/13.
 */

public class RegActivity extends AppCompatActivity {
    private EditText editText,editText2,editText3,editText4;
    private Button button;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reg_activity);
        initview();
    }

    private void initview() {
        editText=findViewById(R.id.editText);
        editText2=findViewById(R.id.editText2);
        editText3=findViewById(R.id.editText3);
        editText4=findViewById(R.id.editText4);
        button=findViewById(R.id.button);
        initdata();
    }

    private void initdata() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText2.getText().equals(editText3.getText())){
                    Snackbar.make(v,"前后两次密码不对！",Snackbar.LENGTH_SHORT).show();
                }else if (editText2.getText().toString().equals("")||editText.getText().toString().equals("")){
                    Snackbar.make(v,"密码和用户名不能为空！",Snackbar.LENGTH_SHORT).show();
                }else if (editText4.getText().toString().length()<17){
                    Snackbar.make(v,"请输入有效身份证！！",Snackbar.LENGTH_SHORT).show();
                }else {
                    SharedPreferences sharedPreferences=getSharedPreferences("user",MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString("username",editText.getText().toString());
                    editor.putString("password",editText2.getText().toString());
                    editor.putString("IDnum",editText4.getText().toString());
                    editor.commit();
                    Toast.makeText(RegActivity.this,"注册成功!",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(RegActivity.this,LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
