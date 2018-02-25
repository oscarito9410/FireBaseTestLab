package com.cetech.firebasetestlab;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by oemy9 on 02/04/2017.
 */

public class FireBaseTokenService extends FirebaseInstanceIdService {
    private static final String TAG = "FirebaseIDService";

    @Override
    public void onTokenRefresh () {
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "onTokenRefresh:" + refreshedToken);
    }
}
