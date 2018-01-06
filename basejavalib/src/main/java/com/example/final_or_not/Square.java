package com.example.final_or_not;

/**
 * author: moon
 * created on: 18/1/6 下午3:24
 * description:
 */
public class Square {

    private int width;

    private int height;

    private Square(){

    }

    public Square(int width , int height){
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "Square{" +
                "width=" + width +
                ", height=" + height +
                '}';
    }
}
