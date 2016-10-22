package color.dom.com.picturecolorelement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private ImageView mv1,mv2,mv3,mv4,mv5,mv6;
    private ImageView showImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showImage=(ImageView)findViewById(R.id.ivImage);
        mv1=(ImageView)findViewById(R.id.mv1);
        mv2=(ImageView)findViewById(R.id.mv2);
        mv3=(ImageView)findViewById(R.id.mv3);
        mv4=(ImageView)findViewById(R.id.mv4);
        mv5=(ImageView)findViewById(R.id.mv5);
        mv6=(ImageView)findViewById(R.id.mv6);
    }
}
