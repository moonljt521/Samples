package com.example.annotation.runtime;

import java.lang.reflect.Field;

/**
 * author: moon
 * created on: 17/8/16 上午11:23
 * description:
 */
@InjectionValue(layoutId = 23)
public class Guest {

    @InjectionValue(value = "小红")
    public String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void main(String[] args){

        System.setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        InjectionValue annotationValue = Guest.class.getAnnotation(InjectionValue.class);

        System.out.println(">"+annotationValue);
        System.out.println(">"+annotationValue.value());
        System.out.println(">"+annotationValue.getClass().getName());
    }

    public void printValue(Object obj) {
        try {
            Field fields[] = obj.getClass().getDeclaredFields();

            for (Field field : fields){
                InjectionValue annotationValue = field.getAnnotation(InjectionValue.class);
                if (annotationValue!=null){

                    System.out.println("取出注入的属性："+ field.getName() +"，值为" + annotationValue.value());

                    obj.getClass().getMethod("setName"
                            ,new Class[]{String.class}).invoke(obj,annotationValue.value());
                }
            }


            InjectionValue guest = obj.getClass().getAnnotation(InjectionValue.class);
            if (guest!=null){
                System.out.println("取出注入的layoutId = "+guest.layoutId());
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
