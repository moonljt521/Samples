package dongyin.com.arouter_moudle.router_b;

import java.io.Serializable;

/**
 * author: moon
 * created on: 17/12/19 下午1:15
 * description:
 */

public class ARouterBody implements Serializable{

    private int id;

    private String name;

    private int age;

    public ARouterBody() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "ARouterBody{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
