package com.cetech.firebasetestlab;

import java.util.HashMap;

/**
 * Created by oemy9 on 25/02/2018.
 */

public class ConfigFireBase {
    public static final String FIREBASELAB_LAST_VERSION_CODE = "firebaselab_last_version_code";
    public static final String FIREBASELAB_DIALOG_TITLE = "firebaselab_dialog_title";

    /**
     * @return Regresa los valores defaults de firebase
     */
    public static HashMap <String, Object> getDefaults () {
        HashMap <String, Object> defuaultsValues = new HashMap <>();
        defuaultsValues.put(FIREBASELAB_LAST_VERSION_CODE, BuildConfig.VERSION_CODE);
        defuaultsValues.put(FIREBASELAB_DIALOG_TITLE, "Existe una nueva versión de la app");
        return defuaultsValues;
    }
}
