package com.example.annotation.testannotation;

import com.example.annotation.seriable.Seriable;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


/**
 * author: moon
 * created on: 17/8/23 下午6:45
 * description:
 */
public class TestGetAnnotation {


    public static void main(String[] args) {

        Annotation[] annotations = MoonBody.class.getAnnotations();
        for (Annotation a : annotations) {
            System.out.println(a);

        }

        System.out.println("------------");

        IFurit iFurit = new Apple();
        iFurit.eat();



    }
}

@SuppressWarnings("ser")
@Deprecated
class MoonBody implements Serializable {

}


interface IFurit {
    void eat();
}

class Apple implements IFurit {

    @Override
    public void eat() {
        System.out.println("eat apple....");
    }
}

@Retention(RetentionPolicy.RUNTIME)
@interface Myannotation {

    public Class<?> getMyTarget();
}

@Myannotation(getMyTarget = Apple.class)
class Factory {

    public static <T> T getInstance()  {

        Myannotation myannotation = Factory.class.getAnnotation(Myannotation.class);
        try {
            return (T) myannotation.getMyTarget().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }

}
