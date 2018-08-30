package id.co.iconpln.insidaltt.util;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import id.co.iconpln.insidaltt.R;

/**
 * Created by ramannada on 23/04/2018.
 */

public class DialogFullscreenImage extends DialogFragment {
    private final String TAG = DialogFullscreenImage.class.getSimpleName();
    private String foto;
    public static DialogFullscreenImage getInstance(String foto) {
        DialogFullscreenImage dialogFullscreenImage = new DialogFullscreenImage();

        Bundle args = new Bundle();
        args.putString("foto", foto);
        dialogFullscreenImage.setArguments(args);

        return dialogFullscreenImage;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            savedInstanceState = getArguments();
        }

        if (savedInstanceState != null) {
            foto = savedInstanceState.getString("foto");
        }
        setStyle(DialogFragment.STYLE_NO_FRAME,
                android.R.style.Theme_Holo_Light_NoActionBar_Fullscreen);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(inflater.getContext())
                .inflate(R.layout.view_image_fullscreen, container, false);

        ImageView ivFoto = view.findViewById(R.id.iv_photo);
        if (foto !=null) {
            Log.d(TAG, "onCreateView: foto tidak null");
            byte[] byteFoto = Base64.decode(foto, Base64.DEFAULT);
            Glide.with(inflater.getContext())
                    .load(byteFoto)
                    .into(ivFoto);
        }

        return view;
    }
}
