package com.example.do_i_need_it;

class Model {
    private String title;
    private String price;
    private String website;
    private String location;



    public Model() {
    }

    public Model(String title, String price, String website, String location) {
        this.title = title;
        this.price = price;
        this.website = website;
        this.location = location;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
