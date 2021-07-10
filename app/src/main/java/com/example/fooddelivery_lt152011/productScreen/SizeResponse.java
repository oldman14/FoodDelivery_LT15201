package com.example.fooddelivery_lt152011.productScreen;

import java.util.List;

public class SizeResponse {
    public List<Size> siezs;

    public SizeResponse(List<Size> siezs) {
        this.siezs = siezs;
    }

    public List<Size> getSiezs() {
        return siezs;
    }

    public void setSiezs(List<Size> siezs) {
        this.siezs = siezs;
    }
}
