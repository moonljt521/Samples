package com.example.final_or_not;

/**
 * author: moon
 * created on: 18/1/6 下午3:36
 * description:
 */
public class SquareProxy {

    public static void main(String[] args) {

        Square square = new Square(1,2);

        SquareProxy squareProxy = new SquareProxy(square);

        squareProxy.print();

        square.setHeight(33);

        squareProxy.print();
    }

    private final Square square;

    public SquareProxy(Square square) {
        this.square = new Square(square.getWidth(),square.getHeight());
    }

    public Square getSquare() {
        return new Square(square.getWidth(),square.getHeight());
    }

    private void print(){
        System.out.println("w = " + square.getWidth() +", h = " + square.getHeight());
    }
}
