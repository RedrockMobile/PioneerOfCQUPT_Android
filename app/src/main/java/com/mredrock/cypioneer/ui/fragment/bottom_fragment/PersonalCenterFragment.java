package com.mredrock.cypioneer.ui.fragment.bottom_fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mredrock.cypioneer.R;
import com.mredrock.cypioneer.ui.activity.LoginActivity;


/**
 * Created by xushuzhan on 2016/6/14.
 */
public class PersonalCenterFragment extends Fragment implements View.OnClickListener {
    private View view;
    private TextView cacheSize;
    private TextView username;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_personal_center, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        username = (TextView) view.findViewById(R.id.person_username);
        username.setOnClickListener(this);
// TODO       username.setText();
        view.findViewById(R.id.person_clear_cache).setOnClickListener(this);
        cacheSize = (TextView) view.findViewById(R.id.person_cache_size);
//    TODO    cacheSize.setText();
        view.findViewById(R.id.person_evaluate).setOnClickListener(this);
        view.findViewById(R.id.person_exit_login).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.person_username:
                //需要一个回调来更新username
                startActivity(new Intent(getActivity(), LoginActivity.class));
                break;
            case R.id.person_clear_cache:
                //TODO clear cache
                break;
            case R.id.person_evaluate:
//     TODO           startActivity();
                break;
            case R.id.person_about:
//     TODO           startActivity();
                break;
            case R.id.person_exit_login:
//TODO clear user info
                startActivity(new Intent(getActivity(), LoginActivity.class));
                break;
        }
    }
}
