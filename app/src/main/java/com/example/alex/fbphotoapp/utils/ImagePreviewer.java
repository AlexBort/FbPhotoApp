package com.example.alex.fbphotoapp.utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.example.alex.fbphotoapp.R;

public class ImagePreviewer {

    public void show(Context context, ImageView source) {
        BitmapDrawable background = getBlurredScreenDrawable(context, source.getRootView());

        View dialogView = LayoutInflater.from(context).inflate(R.layout.layout_image_previewer, null);
        ImageView imageView =  dialogView.findViewById(R.id.iv_preview);

        Drawable copy = source.getDrawable().getConstantState().newDrawable();
        imageView.setImageDrawable(copy);

        final Dialog dialog = new Dialog(context, R.style.ImagePreviewerTheme);
        dialog.getWindow().setBackgroundDrawable(background);
        dialog.setContentView(dialogView);
        dialog.show();
    }

    private static BitmapDrawable getBlurredScreenDrawable(Context context, View screen) {
        Bitmap screenshot = takeScreenshot(screen);
        Bitmap blurred = blurBitmap(context, screenshot);
        return new BitmapDrawable(context.getResources(), blurred);
    }

    private static Bitmap takeScreenshot(View v) {
        Bitmap b = Bitmap.createBitmap( v.getLayoutParams().width, v.getLayoutParams().height, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        v.layout(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());
        v.draw(c);
        return b;
    }

    private static Bitmap blurBitmap(Context context, Bitmap bitmap) {
        float bitmapScale = 0.3f;
        float blurRadius = 10f;

        int width = Math.round(bitmap.getWidth() * bitmapScale);
        int height = Math.round(bitmap.getHeight() * bitmapScale);

        Bitmap inputBitmap = Bitmap.createScaledBitmap(bitmap, width, height, false);
        Bitmap outputBitmap = Bitmap.createBitmap(inputBitmap);

        RenderScript rs = RenderScript.create(context);
        ScriptIntrinsicBlur theIntrinsic = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
        Allocation tmpIn = Allocation.createFromBitmap(rs, inputBitmap);
        Allocation tmpOut = Allocation.createFromBitmap(rs, outputBitmap);
        theIntrinsic.setRadius(blurRadius);
        theIntrinsic.setInput(tmpIn);
        theIntrinsic.forEach(tmpOut);
        tmpOut.copyTo(outputBitmap);

        return outputBitmap;
    }
}
