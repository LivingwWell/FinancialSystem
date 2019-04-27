package com.example.lee.financialsystem;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.lee.financialsystem.data.BaseData;

import java.util.List;

public class MyAdpter extends BaseItemDraggableAdapter<BaseData, BaseViewHolder> {

    public MyAdpter(@LayoutRes int layoutRes, List<BaseData> data) {
        super(layoutRes, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BaseData item) {
        helper.setText(R.id.tv_bill, item.getBill());
        helper.setText(R.id.tv_type, item.getType());
        helper.setText(R.id.tv_date, item.getDate());
        helper.setText(R.id.tv_value, item.getValue());
    }
}
