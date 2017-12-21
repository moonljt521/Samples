package dongyin.com.arouter_moudle.router_b;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import dongyin.com.arouter_moudle.R;


@Route(path = "/routerb/RouterBActivity")
public class Router_B_Activity extends AppCompatActivity implements View.OnClickListener{

    @Autowired(name = "key1")
    public String name;

    @Autowired(name = "key2")
    public ARouterBody aRouter;

    @Autowired(name = "key3")
    public ObjParcelable objParcelable;

    @Autowired(name = "keyinterceptor")
    public String interceperValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_router__b_);

        findViewById(R.id.arouter_module_btn1).setOnClickListener(this);

        ARouter.getInstance().inject(this);


        StringBuilder sb = new StringBuilder();

        if (objParcelable != null){
            sb.append(objParcelable.toString());
        }
        if (aRouter !=null){
            sb.append(aRouter.toString());
        }

        if (interceperValue !=null){
            sb.append(interceperValue);
        }

        ((TextView)findViewById(R.id.arouter_module_content)).setText("params = "+sb.toString());

        Toast.makeText(getApplicationContext(),sb.toString(),Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.arouter_module_btn1){


            setResult(123);
            finish();
        }

    }
}
