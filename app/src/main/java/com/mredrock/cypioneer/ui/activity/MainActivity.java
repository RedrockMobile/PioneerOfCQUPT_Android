package com.mredrock.cypioneer.ui.activity;



import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;
import android.util.Log;

import com.mredrock.cypioneer.BuildConfig;
import com.mredrock.cypioneer.R;
import com.mredrock.cypioneer.ui.fragment.bottom.HomePageFragment;
import com.mredrock.cypioneer.ui.fragment.bottom.InformationFragment;
import com.mredrock.cypioneer.ui.fragment.bottom.PersonalCenterFragment;
import com.mredrock.cypioneer.ui.fragment.bottom.StudyDiscussionFragment;

import java.util.ArrayList;
import java.util.List;
import com.mredrock.cypioneer.utils.DelayClose;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity--->";
    private List<Fragment> fragments = fragments = new ArrayList<>();
    FragmentManager fragmentManager;
    RadioGroup radioGroup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DelayClose.attachActivity(this);
        setContentView(R.layout.activity_main);
        initFragments();
        initView();
    }

    //初始化底部的4个Fragment，并加入数组中方便管理
    private void initFragments() {
        fragments.add(new HomePageFragment());
        fragments.add(new InformationFragment());
        fragments.add(new StudyDiscussionFragment());
        fragments.add(new PersonalCenterFragment());
    }
    private void initView() {
        /**
         * ----------------------BottomBar部分----------------------------------*/
        fragmentManager = getSupportFragmentManager();
        radioGroup = (RadioGroup) findViewById(R.id.rg_tab);

        //设置默认显示主页这个fragment
        FragmentTransaction transaction1 = fragmentManager.beginTransaction();
        transaction1.replace(R.id.content, fragments.get(0));
        transaction1.commit();

        //为BottomBar设置监听
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.bottom_home_page:
                        FragmentTransaction transaction2 = fragmentManager.beginTransaction();
                        transaction2.replace(R.id.content, fragments.get(0));
                        transaction2.commit();
                        break;
                    case R.id.bottom_information:
                        FragmentTransaction transaction3 = fragmentManager.beginTransaction();
                        transaction3.replace(R.id.content, new InformationFragment());
                        transaction3.commit();
                        break;
                    case R.id.bottom_study_discussion:
                        FragmentTransaction transaction4 = fragmentManager.beginTransaction();
                        transaction4.replace(R.id.content, fragments.get(2));
                        transaction4.commit();
                        break;
                    case R.id.bottom_personal_center:
                        FragmentTransaction transaction5 = fragmentManager.beginTransaction();
                        transaction5.replace(R.id.content, fragments.get(3));
                        transaction5.commit();
                        break;

                }
            }
        });


    }



    @Override
    public void onBackPressed() {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "onBackPressed");
        }
        DelayClose.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        DelayClose.detachActivity(this);
    }
}
