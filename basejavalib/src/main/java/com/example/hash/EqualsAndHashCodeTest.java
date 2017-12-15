package com.example.hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * author: moon
 * created on: 17/9/21 上午10:43
 * description:
 */
public class EqualsAndHashCodeTest {

    public static void main(String[] args) {

        Bean b1 = new Bean();
        Bean b2 = new Bean();
        b1.setIdentino("111");
        b2.setIdentino("111");

        b1.setStuid(1);
        b2.setStuid(1);

        System.out.println(b1.equals(b2));

        HashMap<Object,String> map = new HashMap<>();
        map.put(b1,"ljt");

        System.out.println(map.get(b2));


        List list = new ArrayList();
        list.add(b1);

        System.out.println( ">>"+list.contains(b2));


    }

}


class Bean {
    private int stuid;

    private String identino;

    private String name;

    public int getStuid() {
        return stuid;
    }

    public void setStuid(int stuid) {
        this.stuid = stuid;
    }

    public String getIdentino() {
        return identino;
    }

    public void setIdentino(String identino) {
        this.identino = identino;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int result = 17;
        if (name !=null){
            result = 31 * result + name.hashCode();
        }
        if (identino !=null){
            result = 31 * result + identino.hashCode();
        }
        result = 31 * result + stuid;
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if ( o == this ){
            return true;
        }

        if ( !(o instanceof Bean)){
            return false;
        }

        Bean b = (Bean) o;
        return b.identino.equals(identino) && b.stuid == stuid;
    }


}

