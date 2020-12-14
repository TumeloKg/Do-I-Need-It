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

    public String getPrice() {
        return Price;
    }

    public String getWebsite() {
        return Website;
    }

    public String getLocation() {
        return Location;
    }
}
