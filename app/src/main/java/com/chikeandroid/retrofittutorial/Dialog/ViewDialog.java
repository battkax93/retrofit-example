package com.chikeandroid.retrofittutorial.Dialog;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.chikeandroid.retrofittutorial.R;
import com.squareup.picasso.Picasso;

/**
 * Created by Wayan-MECS on 7/6/2018.
 */

public class ViewDialog {

    public void showDialog2(Activity activity, String msg, String img){
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog_1);

        ImageView ivDialog = (ImageView) dialog.findViewById(R.id.dialog_imageview);
        TextView tvDialog = (TextView) dialog.findViewById(R.id.text_dialog);
        tvDialog.setText(msg);
        Picasso.get().load(img).into(ivDialog);

        dialog.show();

    }
}