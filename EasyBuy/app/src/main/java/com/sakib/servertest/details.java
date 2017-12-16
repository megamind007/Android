package com.sakib.servertest;

/**
 * Created by sakib on 22-Mar-16.
 */
public class details {

    private String name,price;

    public  details(String name,String price){

        this.setName(name);
        this.setPrice(price);

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
