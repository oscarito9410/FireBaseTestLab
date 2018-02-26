package com.cetech.firebasetestlab.Dialog;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

/**
 * Created by oemy9 on 25/02/2018.
 */

public class UpdateStoreDialog extends DialogFragment {

    public static final String ARG_MESSAGE = "MESSAGE";

    public static UpdateStoreDialog newInstance (String message) {
        Bundle args = new Bundle();
        args.putString(ARG_MESSAGE, message);
        UpdateStoreDialog fragment = new UpdateStoreDialog();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog (Bundle savedInstanceState) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        dialogBuilder.setMessage(getArguments().getString(ARG_MESSAGE));
        dialogBuilder.setCancelable(false);
        dialogBuilder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick (DialogInterface dialogInterface, int i) {
                navigatePlayStore(getActivity());
            }
        });
        dialogBuilder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick (DialogInterface dialogInterface, int i) {
            }
        });
        return dialogBuilder.create();
    }

    private void navigatePlayStore (Context ctx) {
        try {
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse("market://details?id=" + ctx.getPackageName()));
            ctx.startActivity(i);
        } catch (ActivityNotFoundException ex) {
            Toast.makeText(ctx, "Parece que no tiene play store instalada", Toast.LENGTH_SHORT).show();
        }
    }
}
