package dongyin.com.arouter_moudle.router_b;

import android.content.Context;

import com.alibaba.android.arouter.facade.service.SerializationService;
import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.lang.reflect.Type;

/**
 * author: moon
 * created on: 17/12/19 下午1:15
 * description:
 */

public class ARouterBody
//        implements SerializationService
{

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

//    @Override
//    public <T> T json2Object(String input, Class<T> clazz) {
//        return JSON.parseObject(input,clazz);
//    }
//
//    @Override
//    public String object2Json(Object instance) {
//        return JSON.toJSONString(instance);
//    }
//
//    @Override
//    public <T> T parseObject(String input, Type clazz) {
//        return null;
//    }
//
//    @Override
//    public void init(Context context) {
//
//    }
}
