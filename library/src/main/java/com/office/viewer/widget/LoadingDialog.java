package com.office.viewer.widget;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.widget.TextView;

import com.office.viewer.R;

public class LoadingDialog {


    private Dialog mDialog;
    private TextView mTextView;

    public LoadingDialog(Context context) {
        mDialog = new Dialog(context, R.style.LoadingDialog);
        mDialog.setContentView(R.layout.dialog_loading_layout);
        mDialog.setCancelable(false);
        mTextView = mDialog.findViewById(R.id.tv_loading_tip);
    }

    public void show(String message) {
        if (TextUtils.isEmpty(message)) {
            message = "...";
        }
        mTextView.setText(message);
        mDialog.show();
    }

    public void dismiss() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }

    public Dialog getDialog() {
        return mDialog;
    }

}
