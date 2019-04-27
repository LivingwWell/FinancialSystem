package com.example.lee.financialsystem.data;



public class BaseData {

    private int id;
    private String date;
    private String type;
    private String value;
    private String context;
    private String Bill;

    public BaseData(int id, String date, String type, String value, String context,String bill) {
        this.id = id;
        this.date = date;
        this.type = type;
        this.value = value;
        this.context = context;
        this.Bill=bill;
    }
    public String getBill() {
        return Bill;
    }

    public void setBill(String bill) {
        Bill = bill;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

}
