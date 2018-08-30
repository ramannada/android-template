package id.co.iconpln.insidaltt.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;

/**
 * Dibuat oleh Rizki Maulana pada 30/05/17.
 * rizmaulana@live.com
 * Mobile Apps Developer
 */

public class SimpleAlert {

    public SimpleAlert(final Activity context, String message, final OnConfirm onConfirm) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setMessage(message);
        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                onConfirm.onConfirm();
            }
        });
        final AlertDialog dialog = alertDialog.create();
        dialog.show();
    }

    public SimpleAlert(final Activity context, String message, boolean isCancelable, final OnConfirm onConfirm) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setMessage(message);
        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                onConfirm.onConfirm();
            }
        });
        final AlertDialog dialog = alertDialog.create();
        dialog.setCancelable(isCancelable);
        dialog.show();
    }


    public SimpleAlert(final Activity context, String message) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setMessage(message);
        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        final AlertDialog dialog = alertDialog.create();
        dialog.show();
    }

    public SimpleAlert(final Context context, String message) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setMessage(message);
        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        final AlertDialog dialog = alertDialog.create();
        dialog.show();
    }


    public SimpleAlert(final Activity context, String message, final OnChooser onChooser) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setMessage(message);
        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                onChooser.onOk();
            }
        });
        alertDialog.setNegativeButton("BATAL", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                onChooser.onCancel();
            }
        });

        final AlertDialog dialog = alertDialog.create();
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                onChooser.onCancel();
            }
        });
        dialog.show();
    }


    public SimpleAlert(final Activity context, String button, String message, final OnConfirm onChooser) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setMessage(message);
        alertDialog.setPositiveButton(button, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                onChooser.onConfirm();
            }
        });
        alertDialog.setNegativeButton("BATAL", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        final AlertDialog dialog = alertDialog.create();
        dialog.show();
    }

    public static void Toast(Activity context, String message) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setMessage(message);
        alertDialog.setPositiveButton("TUTUP", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        final AlertDialog dialog = alertDialog.create();
        dialog.show();
    }

    public static void DialogWithInput(Activity context, String message, View view, final OnChooser onChooser) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setMessage(message);
        alertDialog.setView(view);
        alertDialog.setPositiveButton("SIMPAN", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                onChooser.onOk();
            }
        });
        alertDialog.setNegativeButton("TUTUP", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                onChooser.onCancel();
            }
        });
        final AlertDialog dialog = alertDialog.create();
        dialog.show();
    }

    public static void DialogOption(Activity context, CharSequence[] options, DialogInterface.OnClickListener listener) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setItems(options, listener);
        alertDialog.show();
    }

    public interface OnConfirm {
        void onConfirm();

    }

    public interface OnChooser {
        void onOk();

        void onCancel();
    }

}
