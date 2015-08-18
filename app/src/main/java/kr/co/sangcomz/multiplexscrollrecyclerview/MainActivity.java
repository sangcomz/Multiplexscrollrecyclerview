package kr.co.sangcomz.multiplexscrollrecyclerview;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        viewPager = (ViewPager) findViewById(R.id.viewpager);       //viewpager xml 아이디 연걸
        tabLayout = (TabLayout) findViewById(R.id.tabs);            //tabLayout xml 아이디 연걸
        setSupportActionBar(toolbar);   //AppCompatActivity actionbar를 설정
        setUpViewPager(viewPager, new MainFragmentAdapter(getSupportFragmentManager()));
    }

    /**
     * viewPager에 adapter를 설정해준다.
     */
    public void setUpViewPager(ViewPager viewPager, MainFragmentAdapter mainFragmentAdapter) {
        mainFragmentAdapter.addFragment(new Fragment(), "One"); //adapter에 Fragment를 더해준다.
        mainFragmentAdapter.addFragment(new Fragment(), "Two"); //adapter에 Fragment를 더해준다.
        mainFragmentAdapter.addFragment(new Fragment(), "Three"); //adapter에 Fragment를 더해준다.
        viewPager.setAdapter(mainFragmentAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    //http://blog.daum.net/mailss/19 FragmentPagerAdapter 설명
    public class MainFragmentAdapter extends FragmentStatePagerAdapter {
        private final List<android.support.v4.app.Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public MainFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(android.support.v4.app.Fragment fragment, String title) {
            mFragments.add(fragment); //받은 프레그먼트를 리스트에 더해준다.
            mFragmentTitles.add(title);//받은 String을 리스트에 더해준다.
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentTitles.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }




}
