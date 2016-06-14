package com.xushuzhan.cypoineer_bottombar.bottom_fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.xushuzhan.cypoineer_bottombar.R;

/**
 * Created by xushuzhan on 2016/6/14.
 */
public class StudyDiscussionFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_study_dicussion, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Toast.makeText(getActivity(), "这是学习心得", Toast.LENGTH_SHORT).show();
    }
}
