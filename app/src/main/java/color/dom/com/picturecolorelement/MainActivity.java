package color.dom.com.picturecolorelement;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity implements View.OnClickListener {
    private ImageView mv1, mv2, mv3, mv4, mv5, mv6;
    private ImageView ivShow, showImage;
    private Palette palette;
    public Palette.Swatch s1, s2, s3, s4, s5, s6;
    private Button ivAdd, ivSave;
private Handler handler = new Handler(){
    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        switch (msg.what){
            case 0x123:
                GetPalette((String)msg.obj);
                break;
        }
    }
};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_design);
        showImage = (ImageView) findViewById(R.id.ivImage);
        mv1 = (ImageView) findViewById(R.id.mv1);
        mv2 = (ImageView) findViewById(R.id.mv2);
        mv3 = (ImageView) findViewById(R.id.mv3);
        mv4 = (ImageView) findViewById(R.id.mv4);
        mv5 = (ImageView) findViewById(R.id.mv5);
        mv6 = (ImageView) findViewById(R.id.mv6);
        ivShow = (ImageView) findViewById(R.id.ivshow);
        ivAdd = (Button) findViewById(R.id.ivadd);
        ivSave = (Button) findViewById(R.id.ivsave);
        mv1.setOnClickListener(this);
        mv2.setOnClickListener(this);
        mv3.setOnClickListener(this);
        mv4.setOnClickListener(this);
        mv5.setOnClickListener(this);
        mv6.setOnClickListener(this);
        ivAdd.setOnClickListener(this);
        ivSave.setOnClickListener(this);
//        GetPalette("/storage/emulated/0/Resource/Camera/CoolShow_Z000003.jpg");
    }

    private void GetPalette(String imageId) {
        Bitmap bmp = BitmapFactory.decodeFile(imageId);
        showImage.setImageBitmap(bmp);

        showImage.setImageBitmap(bmp);
//使用默认的调色板大小（16）
        Palette.generateAsync(bmp, new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                s1 = palette.getVibrantSwatch();
                s2 = palette.getDarkVibrantSwatch();
                s3 = palette.getLightVibrantSwatch();
                s4 = palette.getMutedSwatch();
                s5 = palette.getDarkMutedSwatch();
                s6 = palette.getLightMutedSwatch();
                if (s1 != null) {

                    mv1.setBackgroundColor(s1.getRgb());

                    s1.getPopulation();
                }
                if (s2 != null) {

                    mv2.setBackgroundColor(s2.getRgb());
                }
                if (s3 != null) {

                    mv3.setBackgroundColor(s3.getRgb());
                }
                if (s4 != null) {

                    mv4.setBackgroundColor(s4.getRgb());
                }
                if (s5 != null) {

                    mv5.setBackgroundColor(s5.getRgb());
                }
                if (s6 != null) {
                    mv6.setBackgroundColor(s6.getRgb());
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mv1:
                if (s1!=null)
                ivShow.setBackgroundColor(s1.getRgb());
                break;
            case R.id.mv2:
                if (s2!=null)
                ivShow.setBackgroundColor(s2.getRgb());
                break;
            case R.id.mv3:
                if (s3!=null)
                ivShow.setBackgroundColor(s3.getRgb());
                break;
            case R.id.mv4:
                if (s4!=null)
                ivShow.setBackgroundColor(s4.getRgb());
                break;
            case R.id.mv5:
                if (s5!=null)
                ivShow.setBackgroundColor(s5.getRgb());
                break;
            case R.id.mv6:
                if (s6!=null)
                ivShow.setBackgroundColor(s6.getRgb());
                break;
            case R.id.ivadd:
                Intent i = new Intent(
                        Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, 0x123);
                break;
            case R.id.ivsave:

                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0x123 && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            Log.i("tag","---------------------"+picturePath);
            Message msg = Message.obtain();
            msg.what=0x123;
            msg.obj=picturePath;
            handler.sendMessage(msg);


        }
    }
}
