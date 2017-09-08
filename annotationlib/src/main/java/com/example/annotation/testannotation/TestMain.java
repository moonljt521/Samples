package com.example.annotation.testannotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * author: moon
 * created on: 17/8/25 下午4:45
 * description:
 */
public class TestMain {

    public static void main(String[] args) {

//        System.setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

//        Car car = new Car();
//
//        MyAnnotation myAnnotation = car.getClass().getAnnotation(MyAnnotation.class);
//
//        System.out.println(">>>>"+myAnnotation.getClass().getName());
//
//
//        System.out.println(">>>>----"+myAnnotation.name());
//
//
//        System.out.println(">>>>----"+myAnnotation);
//
//
//        Field[] fields = Car.class.getDeclaredFields();
//
//        for (Field f : fields){
//
//            System.out.println("f = "+f.getName());
//
//            System.out.println("name = "+f.getAnnotation(MyAnnotation.class).name());
//
////            car.price = f.getAnnotation(MyAnnotation.class).name();
//
//            try {
//                try {
//                    car.getClass().getMethod("setPrice", new Class[]{String.class})
//                    .invoke(car,f.getAnnotation(MyAnnotation.class).name());
//                } catch (IllegalAccessException e) {
//                    e.printStackTrace();
//                } catch (InvocationTargetException e) {
//                    e.printStackTrace();
//                }
//            } catch (NoSuchMethodException e) {
//                e.printStackTrace();
//            }
//        }
//
//        System.out.println("值 = "+car.price);

        Car c = Factory.getInstance();
        c.getPrice();
    }

}


interface Car {
    void getPrice();
}


class BMW implements Car{

    @Override
    public void getPrice() {
        System.out.println("bmw 300w");
    }
}

class AUDI implements Car{

    @Override
    public void getPrice() {
        System.out.println("AUDI 200w");
    }
}


@Retention(RetentionPolicy.RUNTIME)
@interface MMM {

    Class<?> instance();
}

@MMM(instance = BMW.class)
class Factory {

    public static <T> T getInstance(){

        try {
            return (T) Factory.class.getAnnotation(MMM.class).instance().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }


}







