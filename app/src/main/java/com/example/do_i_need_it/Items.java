package com.example.do_i_need_it;

class Items {
    String Title;
    String Price;
    String Website;
    String Location;


    public Items(String title, String price, String website, String location) {
        Title = title;
        Price = price;
        Website = website;
        Location = location;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getWebsite() {
        return Website;
    }

    public void setWebsite(String website) {
        Website = website;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }
}
