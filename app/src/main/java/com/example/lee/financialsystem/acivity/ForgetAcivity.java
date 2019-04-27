package com.example.lee.financialsystem.acivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lee.financialsystem.R;

/**
 * Created by lee on 2019/4/13.
 */

public class ForgetAcivity extends AppCompatActivity {
      private Button bt_forget;
      private EditText et_idnum,et_newpsw;
      private String idmun;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forget_activity);
        bt_forget=findViewById(R.id.bt_forget);
        et_idnum=findViewById(R.id.id_num);
        et_newpsw=findViewById(R.id.newpsw);
        SharedPreferences sharedPreferences=getSharedPreferences("user",MODE_PRIVATE);
        idmun=sharedPreferences.getString("IDnum","");
        Log.d("LoginActivity", "IDnum: "+idmun);
        bt_forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_idnum.getText().toString().equals(idmun)){
                    SharedPreferences sharedPreferences=getSharedPreferences("user",MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString("password",et_newpsw.getText().toString());
                    editor.commit();
                    Toast.makeText(ForgetAcivity.this,"修改成功!",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(ForgetAcivity.this,LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }else {
                    Snackbar.make(v,"身份证号码不对！",Snackbar.LENGTH_SHORT).show();
                }
            }
        });
    }
}
