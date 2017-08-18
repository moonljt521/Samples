package com.moon.samples.annotation;


import com.example.annotation.seriable.Seriable;

/**
 * author: moon
 * created on: 17/8/15 下午6:15
 * description:
 */
public class TestCar {

    @Seriable
    private int id;

    @Seriable
    private float price;

    @Seriable
    private String name;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
