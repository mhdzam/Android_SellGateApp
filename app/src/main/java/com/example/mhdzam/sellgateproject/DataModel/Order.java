package com.example.mhdzam.sellgateproject.DataModel;

/**
 * Created by MhdZam on 2/6/2018.
 */

public class Order {
    public String Id;
    public String Name;
    public String TotalPrice;
    public String SubmitDate;
    public String Status;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        TotalPrice = totalPrice;
    }

    public String getSubmitDate() {
        return SubmitDate;
    }

    public void setSubmitDate(String submitDate) {
        SubmitDate = submitDate;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
