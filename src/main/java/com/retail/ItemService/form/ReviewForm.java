package com.retail.ItemService.form;

public class ReviewForm {
    private String title;
    private String description;
    private int stars;
    private int customerID;


    public int getCustomerID() {
        return customerID;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getStars() {
        return stars;
    }
}