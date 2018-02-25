package com.cetech.firebasetestlab;

import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.cetech.firebasetestlab.Dialog.UpdateStoreDialog;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private SessionManager mManager;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        evaluateOpenDialog();
    }

    private void evaluateOpenDialog () {
        mManager = new SessionManager(this);
        FragmentManager fm = getFragmentManager();
        if (BuildConfig.VERSION_CODE < mManager.getLong(ConfigFireBase.FIREBASELAB_LAST_VERSION_CODE))
            UpdateStoreDialog.newInstance(mManager.getString(ConfigFireBase.FIREBASELAB_DIALOG_TITLE)).show(fm, "prueba");
        else
            Log.d(TAG, "evaluateOpenDialog: VERSION CODE IS THE SAME ");
    }
}
