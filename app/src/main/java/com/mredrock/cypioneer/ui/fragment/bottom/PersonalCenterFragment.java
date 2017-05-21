package com.mredrock.cypioneer.ui.fragment.bottom;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mredrock.cypioneer.R;
import com.mredrock.cypioneer.ui.activity.AboutActivity;
import com.mredrock.cypioneer.utils.CommonUtils;

import java.io.File;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;


/**
 * Created by xushuzhan on 2016/6/14
 * Settings.
 */
public class PersonalCenterFragment extends Fragment implements View.OnClickListener {
    private TextView cacheSize;
    private File cacheDir;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_personal_center, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        cacheDir = Glide.getPhotoCacheDir(getActivity());
        view.findViewById(R.id.person_clear_cache).setOnClickListener(this);
        cacheSize = (TextView) view.findViewById(R.id.person_cache_size);
        refreshCacheSize();
        view.findViewById(R.id.person_site).setOnClickListener(this);
        view.findViewById(R.id.person_about).setOnClickListener(this);

    }

    private void refreshCacheSize() {
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onStart();
                subscriber.onNext(getCacheLen());
                subscriber.onCompleted();
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                cacheSize.setText(s);
            }
        });

    }

    private String getCacheLen() {
        long len = 0;
        if (cacheDir == null) {
            return "0kb";
        }
        File[] cacheFiles = cacheDir.listFiles();
        for (File tmp : cacheFiles) {
            len += tmp.length();
        }
        return CommonUtils.byteToLarger(len);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.person_clear_cache:
                Observable.from(cacheDir.listFiles())
                        .subscribe(new Action1<File>() {
                            @Override
                            public void call(File file) {
                                file.delete();
                            }
                        });
                Toast.makeText(getActivity(), R.string.clear_cache_success, Toast.LENGTH_SHORT).show();
                refreshCacheSize();
                break;
            case R.id.person_site:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://202.202.43.42/lxyz/")));
                break;
            case R.id.person_about:
                startActivity(new Intent(getActivity(), AboutActivity.class));
                break;
        }
    }
}
