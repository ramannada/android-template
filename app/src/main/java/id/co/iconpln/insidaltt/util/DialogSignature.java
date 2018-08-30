package id.co.iconpln.insidaltt.util;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.github.gcacace.signaturepad.views.SignaturePad;

import id.co.iconpln.insidaltt.R;


/**
 * Created by labibmuhajir on 09/08/18.
 * labibmuhajir@yahoo.com
 */
public class DialogSignature extends DialogFragment implements SignaturePad.OnSignedListener {
    private final String TAG = DialogSignature.class.getSimpleName();

    private SignaturePad signaturePad;
    private Button btnSimpan;
    private Button btnHapus;
    private TextView tvInspektor;

    private DialogSignatureListener listener;

    public static DialogSignature getInstance(String name) {
        DialogSignature dialogSignature = new DialogSignature();

        Bundle b = new Bundle();
        b.putString("nama", name);

        dialogSignature.setArguments(b);
        return dialogSignature;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");

        try {
            listener = (DialogSignatureListener) getTargetFragment();
            Log.d(TAG, "onCreate: " + listener);
        } catch (ClassCastException e) {
            throw new ClassCastException(e.getMessage());
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater
                .inflate(R.layout.view_signature, container, false);

        signaturePad = view.findViewById(R.id.signature_pad);
        tvInspektor = view.findViewById(R.id.tv_inspektor);
        btnSimpan = view.findViewById(R.id.btn_simpan);
        btnHapus = view.findViewById(R.id.btn_hapus);

        signaturePad.setOnSignedListener(this);

        if (savedInstanceState != null) {
            final String nama =savedInstanceState.getString("nama");
            tvInspektor.setText(nama);
            btnSimpan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onSaveSignature(signaturePad.getSignatureBitmap(), nama);
                    dismiss();
                }
            });
        }

        btnHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signaturePad.clear();
            }
        });

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onSaveSignature(signaturePad.getSignatureBitmap(), null);
                dismiss();
            }
        });
        return view;
    }

    @Override
    public void onStartSigning() {

    }

    @Override
    public void onSigned() {
        btnSimpan.setEnabled(true);
        btnHapus.setEnabled(true);
    }

    @Override
    public void onClear() {
        btnHapus.setEnabled(false);
        btnSimpan.setEnabled(false);
    }

    public interface DialogSignatureListener{
        void onSaveSignature(Bitmap bitmap, String nama);
    }
}
