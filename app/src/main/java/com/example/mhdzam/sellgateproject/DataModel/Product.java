package com.example.mhdzam.sellgateproject.DataModel;

/**
 * Created by MhdZam on 1/8/2018.
 */

/**
 * Created by Lincoln on 18/05/16.
 */
public class Product {
    private String name;
    private int price;
    private String icon;
    private int Id;


    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Product() {
    }

    public Product(String name, int price, String thumbnail, int id) {
        this.name = name;
        this.price = price;
        this.icon = thumbnail;
        this.Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getprice() {
        return price;
    }

    public void setprice(int numOfSongs) {
        this.price = price;
    }

    public String geticon() {
        return icon;
    }

    public void seticon(String icon) {
        this.icon = icon;
    }
}