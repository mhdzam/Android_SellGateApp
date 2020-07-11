package com.example.mhdzam.sellgateproject.DataModel;

import java.util.List;

/**
 * Created by MhdZam on 1/10/2018.
 */

public class ProductDetails {
    private String DESC;
    private String addedDate;
    private String Price;
    private String IconURL;
    private String Name;
    private String ID;
    private String[] Images;

    public String getDESC() {
        return DESC;
    }

    public void setDESC(String DESC) {
        this.DESC = DESC;
    }

    public String getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(String addedDate) {
        this.addedDate = addedDate;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getIconURL() {
        return IconURL;
    }

    public void setIconURL(String iconURL) {
        IconURL = iconURL;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String[] getImages() {
        return Images;
    }

    public void setImages(String[] images) {
        Images = images;
    }
}
