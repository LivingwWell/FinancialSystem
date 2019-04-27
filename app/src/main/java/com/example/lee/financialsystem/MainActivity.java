package com.example.lee.financialsystem;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.callback.ItemDragAndSwipeCallback;
import com.chad.library.adapter.base.listener.OnItemSwipeListener;
import com.example.lee.financialsystem.data.BaseData;
import com.example.lee.financialsystem.data.DBHelper;
import com.example.lee.financialsystem.acivity.AddActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public DBHelper mHelper;
    private SQLiteDatabase mDatabase;
    List<BaseData> baseDataList;
    private RecyclerView recyclerView;
    private MyAdpter myAdpter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mHelper = new DBHelper(this, "DBHelper.db", null, 1);
        mDatabase = mHelper.getWritableDatabase();
        initView();
        initData();
    }


    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

        recyclerView = findViewById(R.id.recyclerView);

    }

    //加载数据
    private void initData() {
        System.out.println("initData");
        baseDataList = new ArrayList<BaseData>();
        baseDataList.clear();
        Cursor cursor = mDatabase.query("basedata", null, null, null, null, null, null);
        cursor.moveToFirst();
        if (cursor.moveToPosition(0) != true) {
            Log.e("MainActivity", "moveToPosition return fails, maybe table not created!!!");
        } else {
            do {
                Integer id = cursor.getInt(cursor.getColumnIndex("_id"));
                String date = cursor.getString(cursor.getColumnIndex("date"));
                String type = cursor.getString(cursor.getColumnIndex("type"));
                String value = cursor.getString(cursor.getColumnIndex("value"));
                String context = cursor.getString(cursor.getColumnIndex("context"));
                String mill = cursor.getString(cursor.getColumnIndex("bill"));
                BaseData baseData = new BaseData(id, date, type, value, context, mill);
                baseDataList.add(baseData);
            } while (cursor.moveToNext());
        }

        myAdpter = new MyAdpter(R.layout.item_database, baseDataList);
        myAdpter.setUpFetchEnable(true);
        myAdpter.setUpFetchListener(new BaseQuickAdapter.UpFetchListener() {
            @Override
            public void onUpFetch() {
                //startUpFetch();
            }
        });
        ItemDragAndSwipeCallback itemDragAndSwipeCallback = new ItemDragAndSwipeCallback(myAdpter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemDragAndSwipeCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
        myAdpter.enableSwipeItem();
        myAdpter.setOnItemSwipeListener(onItemSwipeListener);
        myAdpter.setOnItemClickListener(onItemClickListener);
        recyclerView.setAdapter(myAdpter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    //滑动删除回调
    OnItemSwipeListener onItemSwipeListener = new OnItemSwipeListener() {
        @Override
        public void onItemSwipeStart(RecyclerView.ViewHolder viewHolder, int pos) {

        }

        @Override
        public void clearView(RecyclerView.ViewHolder viewHolder, int pos) {

        }

        @Override
        public void onItemSwiped(RecyclerView.ViewHolder viewHolder, int pos) {
            delete(pos);
        }

        @Override
        public void onItemSwipeMoving(Canvas canvas, RecyclerView.ViewHolder viewHolder, float dX, float dY, boolean isCurrentlyActive) {

        }
    };
//弹出Dialog修改信息
    BaseQuickAdapter.OnItemClickListener onItemClickListener = new BaseQuickAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(BaseQuickAdapter adapter, View view, final int position) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("修改信息");
            View view1 = LayoutInflater.from(MainActivity.this).inflate(R.layout.dialog_item, null);
            builder.setView(view1);
            final EditText dialog_vaule = view1.findViewById(R.id.dialog_value);
            final EditText dialog_bill = view1.findViewById(R.id.dialog_bill);
            final EditText dialog_date = view1.findViewById(R.id.dialog_date);
            final EditText dialog_type = view1.findViewById(R.id.dialog_type);
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    baseDataList.get(position).setBill(dialog_bill.getText().toString());
                    baseDataList.get(position).setDate(dialog_date.getText().toString());
                    baseDataList.get(position).setValue(dialog_vaule.getText().toString());
                    baseDataList.get(position).setType(dialog_type.getText().toString());
                    updata(position,dialog_date.getText().toString(),dialog_vaule.getText().toString()
                            ,dialog_bill.getText().toString(),dialog_type.getText().toString());
                }
            });
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            builder.show();
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
    }

    //删除
    private void delete(int position) {
        mDatabase.delete("basedata", "_id=?", new String[]{"" + baseDataList.get(position).getId()});
        baseDataList.remove(position);
        myAdpter.notifyItemRemoved(position);
    }

    private void updata(int position,String date,String value,String bill,String type){
        ContentValues values=new ContentValues();
        values.put("date",date);
        values.put("type",type);
        values.put("bill",bill);
        values.put("value",value);
        mDatabase.update("basedata",values,"_id=?",new String[]{"" + baseDataList.get(position).getId()});
        myAdpter.notifyItemRemoved(position);
    }

}

