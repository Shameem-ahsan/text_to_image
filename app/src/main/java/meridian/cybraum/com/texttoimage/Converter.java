package meridian.cybraum.com.texttoimage;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.WindowManager;

public class Converter {
    Context mContext;

    // Convert Text to image Method
    // pass the paramter : text , size , stroke ,color,typeface
    public  Converter( Context mContext){
        this.mContext=mContext;


    }
    public Bitmap textAsBitmap(String text, float textSize, float stroke,
                               int color, Typeface typeface) {


        TextPaint paint = new TextPaint();
        paint.setColor(color);
        paint.setTextSize(textSize);
        paint.setStrokeWidth(stroke);
        paint.setTypeface(typeface);

        paint.setAntiAlias(true);
        paint.setTextAlign(Paint.Align.LEFT);
        //////////////////////////////////////
        int w =200;
        int h = 200;




        paint.setAntiAlias(true);

        //////////////////////////////////////

        float baseline = (int) (-paint.ascent() + 3f); // ascent() is negative

        StaticLayout staticLayout = new StaticLayout(text, 0, text.length(),
                paint, WindowManager.LayoutParams.FLAG_FULLSCREEN, android.text.Layout.Alignment.ALIGN_NORMAL, 1.0f,
                1.0f, false);

        int linecount = staticLayout.getLineCount();

        int height = 960/*(int) (baseline + paint.descent() + 3) * linecount + 10*/;

        Bitmap image = Bitmap
                .createBitmap(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN, Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(image);
        canvas.drawARGB(0xFF, 0xFF, 0xFF, 0xFF);
        //canvas.drawText("WATERMARK", 220, 10, paint);
        Bitmap waterMark = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.nn);
        canvas.drawBitmap(waterMark, -100, 10, null);
        staticLayout.draw(canvas);

        return image;

    }

    // Adding Border to bitmap
    public Bitmap addBorder(Bitmap bmp, int borderSize, int borderColor) {
        Bitmap bmpWithBorder = Bitmap.createBitmap(bmp.getWidth() + borderSize
                * 2, bmp.getHeight() + borderSize * 2, bmp.getConfig());
        Canvas canvas = new Canvas(bmpWithBorder);
        canvas.drawColor(borderColor);
        canvas.drawBitmap(bmp, borderSize, borderSize, null);
        return bmpWithBorder;
    }

}