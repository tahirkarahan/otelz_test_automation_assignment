package com.otelz.pages;

import java.util.Arrays;

public class TestSpilt {
    public static void main(String[] args) {
        String price = "₺ 30₺ 29";
        price = price.replace("₺ 30", "");
        System.out.println(price);
    }
}
