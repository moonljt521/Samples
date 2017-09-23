package com.moon.samples.databinding;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableField;


/**
 * author: moon
 * created on: 17/9/22 下午12:25
 * description:
 */
public class NameBody {

    private int id;

    public ObservableField<String> name = new ObservableField<>();
    public ObservableField<String> imageUrl = new ObservableField<>();


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ObservableField<String> getName() {
        return name;
    }

    public void setName(ObservableField<String> name) {
        this.name = name;
    }


}
