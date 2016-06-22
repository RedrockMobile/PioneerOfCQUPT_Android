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

import com.mredrock.cypioneer.R;
import com.mredrock.cypioneer.cfg.Config;
import com.mredrock.cypioneer.ui.activity.AboutActivity;
import com.mredrock.cypioneer.ui.activity.ChangePassActivity;
import com.mredrock.cypioneer.ui.activity.LoginActivity;
import com.mredrock.cypioneer.utils.PinkUtils;
import com.mredrock.cypioneer.utils.SFUtil;

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
    private TextView usernameTextView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_personal_center, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        usernameTextView = (TextView) view.findViewById(R.id.person_username);
        usernameTextView.setOnClickListener(this);
        if (Config.user != null) {
            usernameTextView.setText(Config.user.getName());
        }
        view.findViewById(R.id.person_clear_cache).setOnClickListener(this);
        cacheSize = (TextView) view.findViewById(R.id.person_cache_size);
        refreshCacheSize();
        view.findViewById(R.id.person_change_password).setOnClickListener(this);
        view.findViewById(R.id.person_evaluate).setOnClickListener(this);
        view.findViewById(R.id.person_about).setOnClickListener(this);
        view.findViewById(R.id.person_exit_login).setOnClickListener(this);

    }

    @Override
    public void onResume() {
        if (Config.user != null) {
            usernameTextView.setText(Config.user.getName());
        }
        super.onResume();
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
        if (Config.cacheDir == null) {
            return "0kb";
        }
        File[] cacheFiles = Config.cacheDir.listFiles();
        for (File tmp : cacheFiles) {
            len += tmp.length();
        }
        return PinkUtils.byteToLarger(len);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.person_username:
                //需要一个回调来更新username
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                intent.putExtra("flag", true);
                startActivity(intent);
                break;
            case R.id.person_clear_cache:
                Observable.from(Config.cacheDir.listFiles())
                        .subscribe(new Action1<File>() {
                            @Override
                            public void call(File file) {
                                file.delete();
                            }
                        });
                Toast.makeText(getActivity(), R.string.clear_cache_success, Toast.LENGTH_SHORT).show();
                refreshCacheSize();
                break;
            case R.id.person_change_password:
                startActivity(new Intent(getActivity(), ChangePassActivity.class));
                break;
            case R.id.person_evaluate:
                //TODO 评价
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://202.202.43.42/lxyz/")));
                break;
            case R.id.person_about:
                startActivity(new Intent(getActivity(), AboutActivity.class));
                break;
            case R.id.person_exit_login:
                Config.user = null;
                SFUtil.getInstance().saveToken(null);
                startActivity(new Intent(getActivity(), LoginActivity.class));
                getActivity().finish();
                break;

        }
    }
}
