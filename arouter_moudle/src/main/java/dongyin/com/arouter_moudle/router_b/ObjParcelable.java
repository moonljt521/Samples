package dongyin.com.arouter_moudle.router_b;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * author: moon
 * created on: 17/12/19 下午2:34
 * description:
 */
public class ObjParcelable implements Parcelable {

    private int age;

    private int id;

    private String name;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.age);
        dest.writeInt(this.id);
        dest.writeString(this.name);
    }

    public ObjParcelable() {
    }

    protected ObjParcelable(Parcel in) {
        this.age = in.readInt();
        this.id = in.readInt();
        this.name = in.readString();
    }

    public static final Parcelable.Creator<ObjParcelable> CREATOR = new Parcelable.Creator<ObjParcelable>() {
        @Override
        public ObjParcelable createFromParcel(Parcel source) {
            return new ObjParcelable(source);
        }

        @Override
        public ObjParcelable[] newArray(int size) {
            return new ObjParcelable[size];
        }
    };

    @Override
    public String toString() {
        return "ObjParcelable{" +
                "age=" + age +
                ", id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
