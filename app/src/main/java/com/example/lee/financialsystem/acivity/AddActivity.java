package com.example.lee.financialsystem.acivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lee.financialsystem.MainActivity;
import com.example.lee.financialsystem.R;
import com.example.lee.financialsystem.data.BaseData;
import com.example.lee.financialsystem.data.DBHelper;
import com.example.lee.financialsystem.view.CustomDatePicker;
import com.example.lee.financialsystem.view.DateFormatUtils;

import java.util.ArrayList;
import java.util.List;

public class AddActivity extends AppCompatActivity implements View.OnClickListener{
    private LinearLayout linearLayout,linearLayout2,linearLayout3,linearLayout4;
    private EditText et_value,et_content;
    private Button bt_add;
    private ImageView imageView;
    private TextView tx_type,tx_type2,tx_date;
    private CustomDatePicker mTimerPicker;
    public DBHelper oh;
    private SQLiteDatabase db;
    List<BaseData> baseDataList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        initView();
        initData();
    }

    private void initData() {
           oh=new DBHelper(this,"DBHelper.db",null,1);
           db=oh.getWritableDatabase();
           baseDataList=new ArrayList<BaseData>();
           BaseData baseData1=new BaseData(0,tx_date.getText().toString(),tx_type2.getText().toString(),et_value.getText().toString(),
                   et_content.getText().toString(),tx_type2.getText().toString());
           baseDataList.add(baseData1);
    }

    private void initView() {
        linearLayout=findViewById(R.id.linelayout);
        linearLayout2=findViewById(R.id.linelayout2);
        linearLayout3=findViewById(R.id.linelayout3);
        linearLayout4=findViewById(R.id.linelayout4);
        et_value=findViewById(R.id.et_vaule);
        et_content=findViewById(R.id.et_content);
        bt_add=findViewById(R.id.bt_add);
        imageView=findViewById(R.id.imgeview);
        tx_type=findViewById(R.id.tv_type);
        tx_type2=findViewById(R.id.tx_type2);
        tx_date=findViewById(R.id.tx_date);
        initTimerPicker();
        linearLayout.setOnClickListener(this);
        linearLayout2.setOnClickListener(this);
        linearLayout4.setOnClickListener(this);
        bt_add.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.linelayout:
                final AlertDialog.Builder builder=new AlertDialog.Builder(AddActivity.this);
                builder.setTitle("收支类型");
                final String[] type={"收入","支出"};
                builder.setSingleChoiceItems(type, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case 0:
                                imageView.setImageResource(R.drawable.input);
                                tx_type.setText("收入");
                                Toast.makeText(AddActivity.this,"收入",Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                                break;
                            case 1:
                                imageView.setImageResource(R.drawable.out);
                                tx_type.setText("支出");
                                Toast.makeText(AddActivity.this,"支出",Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                                break;
                        }
                    }
                });
                builder.show();
                break;
            case R.id.linelayout4:
                final AlertDialog.Builder builder1=new AlertDialog.Builder(AddActivity.this);
                if (tx_type.getText().equals("收入")){
                    builder1.setTitle("收入类型");
                    final String[] type1={"工作","兼职","其他"};
                    builder1.setSingleChoiceItems(type1, 0, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which){
                                case 0:
                                    tx_type2.setText("工作");
                                    Toast.makeText(AddActivity.this,"工作",Toast.LENGTH_SHORT).show();
                                    linearLayout3.setVisibility(View.GONE);
                                    dialog.dismiss();
                                    break;
                                case 1:
                                    tx_type2.setText("兼职");
                                    Toast.makeText(AddActivity.this,"兼职",Toast.LENGTH_SHORT).show();
                                    linearLayout3.setVisibility(View.GONE);
                                    dialog.dismiss();
                                    break;
                                case 2:
                                    tx_type2.setText("其他");
                                    Toast.makeText(AddActivity.this,"其他",Toast.LENGTH_SHORT).show();
                                    linearLayout3.setVisibility(View.VISIBLE);
                                    dialog.dismiss();
                                    break;
                            }
                        }
                    });
                    builder1.show();
                }else if (tx_type.getText().equals("支出")){
                    builder1.setTitle("支出类型");
                    final String[] type1={"早餐","午餐","晚餐" ,"其他"};
                    builder1.setSingleChoiceItems(type1, 0, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which){
                                case 0:
                                    tx_type2.setText("早餐");
                                    Toast.makeText(AddActivity.this,"早餐",Toast.LENGTH_SHORT).show();
                                    linearLayout3.setVisibility(View.GONE);
                                    dialog.dismiss();
                                    break;
                                case 1:
                                    tx_type2.setText("午餐");
                                    Toast.makeText(AddActivity.this,"午餐",Toast.LENGTH_SHORT).show();
                                    linearLayout3.setVisibility(View.GONE);
                                    dialog.dismiss();
                                    break;
                                case 2:
                                    tx_type2.setText("晚餐");
                                    Toast.makeText(AddActivity.this,"晚餐",Toast.LENGTH_SHORT).show();
                                    linearLayout3.setVisibility(View.GONE);
                                    dialog.dismiss();
                                    break;
                                case 3:
                                    tx_type2.setText("其他");
                                    Toast.makeText(AddActivity.this,"其他",Toast.LENGTH_SHORT).show();
                                    linearLayout3.setVisibility(View.VISIBLE);
                                    dialog.dismiss();
                                    break;
                            }
                        }
                    });
                    builder1.show();
                }
                break;
            case R.id.linelayout2:
                mTimerPicker.show(tx_date.getText().toString());
                break;
            case R.id.bt_add:
                   Insert();
                readDataFromDatabase();
                Toast.makeText(this,"添加成功！！！",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(AddActivity.this, MainActivity.class);

                startActivity(intent);
                break;
        }
    }

    private void initTimerPicker() {
        String beginTime = "2001-10-17 18:00";
        String endTime = DateFormatUtils.long2Str(System.currentTimeMillis(), true);

        tx_date.setText(beginTime);

        // 通过日期字符串初始化日期，格式请用：yyyy-MM-dd HH:mm
        mTimerPicker = new CustomDatePicker(this, new CustomDatePicker.Callback() {
            @Override
            public void onTimeSelected(long timestamp) {
                tx_date.setText(DateFormatUtils.long2Str(timestamp, true));
            }
        }, beginTime, endTime);
        // 允许点击屏幕或物理返回键关闭
        mTimerPicker.setCancelable(true);
        // 显示时和分
        mTimerPicker.setCanShowPreciseTime(true);
        // 允许循环滚动
        mTimerPicker.setScrollLoop(true);
        // 允许滚动动画
        mTimerPicker.setCanShowAnim(true);
    }

    public void Insert(){
        for (BaseData baseData:baseDataList){
            ContentValues values=new ContentValues();
            values.put("date",tx_date.getText().toString());
            values.put("context",et_content.getText().toString());
            values.put("value",et_value.getText().toString());
            values.put("type",tx_type.getText().toString());
            if(!"".equals(et_content.getText().toString().trim()) && "其他".equals(tx_type2.getText().toString())){
                values.put("bill",et_content.getText().toString().trim());
            }else{
                values.put("bill",tx_type2.getText().toString());
            }
//            values.put("bill",tx_type2.getText().toString());
            db.insert("basedata",null,values);
        }
    }
    public void readDataFromDatabase() {

        Cursor cursor = db.query("basedata", null, null, null, null, null, null, null);

        while(cursor.moveToNext())
        {
            int id = cursor.getInt(cursor.getColumnIndex("_id"));
            String date = cursor.getString(cursor.getColumnIndex("date"));
            String type = cursor.getString(cursor.getColumnIndex("type"));
            String bill = cursor.getString(cursor.getColumnIndex("bill"));
            String context = cursor.getString(cursor.getColumnIndex("context"));
            String value = cursor.getString(cursor.getColumnIndex("value"));
            Log.d("addActivity",   "id"+id+"type:"+ type + ", data:" + date + ", value:"
                    + value + ", Bill:" + bill + ", context:" + context);
        }
    }


}
