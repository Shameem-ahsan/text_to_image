package meridian.cybraum.com.texttoimage;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

    EditText str_et;
    Button mybtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        str_et = (EditText) findViewById(R.id.str_id);
        mybtn = (Button) findViewById(R.id.btn_id);
        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {

            requestPermission();
        }
        mybtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                String data = str_et.getText().toString();

                // Convert Text to Image
                Converter convert = new Converter(MainActivity.this);

                // text , size , stroke ,color,typeface
                Bitmap bmp = convert.textAsBitmap(data, 17, 5, Color.BLACK, Typeface.createFromAsset(getApplicationContext().getAssets(),"times.ttf"));
                Bitmap image = convert.addBorder(bmp, 2, Color.BLACK);
                System.out.println("____________image___________________"+image);


                // file name appending with system date
                Calendar c = Calendar.getInstance();
                SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss");
                String filename = "Image_" + f.format(c.getTime());
                System.out.println("_____________filename____________________"+filename);

                 // saving image to sdcard
                SaveImage saveimg = new SaveImage();
                // pass bit and filename
                saveimg.storeImage(image, filename);

                Toast.makeText(getApplicationContext(),
                        "Saved As :" + filename, Toast.LENGTH_SHORT).show();

            }
        });

    }
    private void requestPermission() {
        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
    }
}