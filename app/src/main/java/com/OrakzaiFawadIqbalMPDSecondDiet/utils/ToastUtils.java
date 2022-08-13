//Fawad Iqbal Orakzai S2027118
package com.OrakzaiFawadIqbalMPDSecondDiet.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;

import com.OrakzaiFawadIqbalMPDSecondDiet.R;

public class ToastUtils {

    public MutableLiveData<Boolean> progressDialogMessageLiveData = new MutableLiveData<>();
    MutableLiveData<Object> infoToastLiveData = new MutableLiveData<>();

    public ToastUtils() {
    }

    private void resetInfoToastMessage() {
        infoToastLiveData.setValue(null);
    }

    public void showToast(String msg) {
        infoToastLiveData.postValue(msg);
    }

    public void showProgressDialog() {
        progressDialogMessageLiveData.postValue(true);
    }

    public void hideProgressDialog() {
        progressDialogMessageLiveData.postValue(false);
    }

    public Dialog onShowProgressDialog(
            AppCompatActivity activity,
            Dialog dialog,
            Boolean showLoader
    ) {
        if (dialog != null)
            dialog.dismiss();

        if (activity.isFinishing() || !showLoader) {
            return null;
        }
        dialog = createProgressDialog(activity);
        dialog.show();
        return dialog;
    }

    private Dialog createProgressDialog(Context context) {
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.progress_dialog);
        dialog.getWindow().setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT
        );
        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        return dialog;
    }

    private void showToastSnackBar(Context context, Object stringResId) {
        try {
            String msg;
            if (stringResId instanceof Integer) {
                msg = context.getString((Integer) stringResId);
            } else if (stringResId instanceof String) {
                msg = (String) stringResId;
            } else {
                msg = "" + stringResId;
            }
            if (msg.isEmpty()) {
                return;
            }
            Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
        } catch (Exception ignored) {

        }
    }

    public void initToastObserver(AppCompatActivity activity) {
        infoToastLiveData.observe(activity, value -> {
            if (value == null) {
                return;
            }
            showToastSnackBar(activity, value);
            resetInfoToastMessage();
        });
    }
}
