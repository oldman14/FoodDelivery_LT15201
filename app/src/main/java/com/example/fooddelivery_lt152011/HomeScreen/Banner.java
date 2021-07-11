package com.example.fooddelivery_lt152011.HomeScreen;

public class Banner {
    public int BannerId;
    public String bannerImg;

    public Banner(String bannerImg) {
        this.bannerImg = bannerImg;
    }

    public int getBannerId() {
        return BannerId;
    }

    public void setBannerId(int bannerId) {
        BannerId = bannerId;
    }

    public String getBannerImg() {
        return bannerImg;
    }

    public void setBannerImg(String bannerImg) {
        this.bannerImg = bannerImg;
    }
}
