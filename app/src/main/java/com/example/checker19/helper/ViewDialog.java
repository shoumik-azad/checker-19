package com.example.checker19.helper;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;

import com.example.checker19.R;


public class ViewDialog extends Dialog {

    public ViewDialog(Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //setIndeterminate(true);
        //setMessage(context.getResources().getString(R.string.please_wait));
        setContentView(R.layout.custom_loading_layout);
    }
}
