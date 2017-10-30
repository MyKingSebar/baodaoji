package com.test.ys.saomiaotest;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.youngsee.posterdisplayer.R;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class MessageDialog
        extends Dialog {
    private static MessageDialog dialog = null;
    Runnable runnable = new Runnable() {
        public void run() {
            MessageDialog.dialog.dismiss();
        }
    };
    Handler timehandler = new Handler();
    private TextView tvMsg;

    private MessageDialog(Context paramContext) {
        super(paramContext);
        View view = ((LayoutInflater) paramContext.getSystemService(LAYOUT_INFLATER_SERVICE)).inflate(R.layout.dialog_message, null);
//    View view = ((LayoutInflater)paramContext.getSystemService(LAYOUT_INFLATER_SERVICE)).inflate(R.layout.dialog_message, null);
        this.tvMsg = ((TextView) view.findViewById(R.id.tv_msg));
        view.findViewById(R.id.bt_confirm).setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                MessageDialog.this.dismiss();
            }
        });
        addContentView(view, new LinearLayout.LayoutParams(-2, -2));
    }

    public static MessageDialog getInstance(Context paramContext) {
        if (dialog == null) {

        }
        try {
            if (dialog == null) {
                dialog = new MessageDialog(paramContext);
                dialog.onWindowFocusChanged(false);
            }
            return dialog;
        } finally {

        }
    }
//    private MessageDialog(Context paramContext, int paramInt) {
//        super(paramContext, paramInt);
//        View view = ((LayoutInflater) paramContext.getSystemService(LAYOUT_INFLATER_SERVICE)).inflate(R.layout.dialog_message, null);
////    View view = ((LayoutInflater)paramContext.getSystemService(LAYOUT_INFLATER_SERVICE)).inflate(R.layout.dialog_message, null);
//        this.tvMsg = ((TextView) view.findViewById(R.id.tv_msg));
//        view.findViewById(R.id.bt_confirm).setOnClickListener(new View.OnClickListener() {
//            public void onClick(View paramAnonymousView) {
//                MessageDialog.this.dismiss();
//            }
//        });
//        addContentView(view, new LinearLayout.LayoutParams(-2, -2));
//    }
//
//    public static MessageDialog getInstance(Context paramContext) {
//        if (dialog == null) {
//        }
//        try {
//            if (dialog == null) {
//                dialog = new MessageDialog(paramContext, 2131296463);
//                dialog.onWindowFocusChanged(false);
//            }
//            return dialog;
//        } finally {
//        }
//    }

    public MessageDialog setMsg(String paramString) {
        this.tvMsg.setText(paramString);
        this.timehandler.postDelayed(this.runnable, 15000L);
        return this;
    }
}


