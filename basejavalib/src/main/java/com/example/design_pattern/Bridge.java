package com.example.design_pattern;


/**
 * 桥接模式
 */
public class Bridge {

    public static void main(String[] args) {

        new Bridge().test();

//        List list;

    }

    public void test(){
        AbsHuman i = new I();

        IHuman i1 = new A();

        i.setiHuman(i1);

        i.eat();

        IHuman i2 = new B();

        i.setiHuman(i2);

        i.eat();
    }

    public interface IHuman {
        void eat();
        void drink();
    }

    public class A implements IHuman{

        @Override
        public void eat() {
            System.out.println("A eat");
        }

        @Override
        public void drink() {
            System.out.println("A drink");
        }
    }

    public class B implements IHuman{

        @Override
        public void eat() {
            System.out.println("B eat");
        }

        @Override
        public void drink() {
            System.out.println("B drink");
        }
    }

    public abstract class AbsHuman {

        public IHuman getiHuman() {
            return iHuman;
        }

        public void setiHuman(IHuman iHuman) {
            this.iHuman = iHuman;
        }

        IHuman iHuman;

        abstract void eat();

        abstract void drink();
    }


    public class I extends AbsHuman {

        @Override
        void eat() {
            getiHuman().eat();
        }

        @Override
        void drink() {
            getiHuman().drink();
        }
    }

}
