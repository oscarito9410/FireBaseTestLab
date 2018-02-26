package com.cetech.firebasetestlab;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

/**
 * Created by oemy9 on 25/02/2018.
 */

public class ApplicationBase extends Application {

    @Override
    public void onCreate () {
        super.onCreate();
        initConfigFireBase(this);
    }

    private void initConfigFireBase (Context context) {
        final SessionManager mManager = new SessionManager(context);
        final FirebaseRemoteConfig mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder()
                .setDeveloperModeEnabled(BuildConfig.DEBUG)
                .build();
        mFirebaseRemoteConfig.setConfigSettings(configSettings);
        mFirebaseRemoteConfig.setDefaults(ConfigFireBase.getDefaults());
        long cacheExpiration = 3600;

        if (mFirebaseRemoteConfig.getInfo().getConfigSettings().isDeveloperModeEnabled()) {
            cacheExpiration = 0;
        }

        mFirebaseRemoteConfig.fetch(cacheExpiration).addOnCompleteListener(new OnCompleteListener <Void>() {
            @Override
            public void onComplete (@NonNull Task <Void> task) {
                FirebaseRemoteConfig remoteConfig = FirebaseRemoteConfig.getInstance();
                if (task.isSuccessful()) {
                    mFirebaseRemoteConfig.activateFetched();
                    mManager.add(ConfigFireBase.FIREBASELAB_LAST_VERSION_CODE, remoteConfig.getLong(ConfigFireBase.FIREBASELAB_LAST_VERSION_CODE));
                    mManager.add(ConfigFireBase.FIREBASELAB_DIALOG_TITLE, remoteConfig.getString(ConfigFireBase.FIREBASELAB_DIALOG_TITLE));
                }
            }

        });

    }
}