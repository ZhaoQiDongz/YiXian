package myapplication.jiyun.com.yixian.view.view;

import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import myapplication.jiyun.com.yixian.R;
import myapplication.jiyun.com.yixian.base.BaseActivity;
import myapplication.jiyun.com.yixian.view.fragment.CenterFragment;
import myapplication.jiyun.com.yixian.view.fragment.DiscoverFragment;
import myapplication.jiyun.com.yixian.view.fragment.JocketFragment;
import myapplication.jiyun.com.yixian.view.fragment.LiveFragment;
import myapplication.jiyun.com.yixian.view.fragment.MyFragment;

public class MainActivity extends BaseActivity {

    private FrameLayout frameLayout;
    private RadioButton zhibo;
    private RadioButton daintai;
    private RadioButton list;
    private RadioButton faxian;
    private RadioButton my;
    private RadioGroup yixian_rg;
    public LiveFragment liveFragment;
    public JocketFragment jocketFragment;
    public CenterFragment centerFragment;
    public DiscoverFragment discoverFragment;
    public MyFragment myFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;

    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initView() {
        frameLayout=findViewById(R.id.layout);
        yixian_rg=findViewById(R.id.yixian_rg);
        AddFragment(R.id.layout,LiveFragment.class,null);
        yixian_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.zhibo:
                        AddFragment(R.id.layout,LiveFragment.class,null);
                        break;
                    case R.id.daintai:
                        AddFragment(R.id.layout,JocketFragment.class,null);
                        break;
                    case R.id.list:
                        AddFragment(R.id.layout,CenterFragment.class,null);
                        break;
                    case R.id.faxian:
                        AddFragment(R.id.layout,DiscoverFragment.class,null);
                        break;
                    case R.id.my:
                        AddFragment(R.id.layout,MyFragment.class,null);
                        break;
                }
            }
        });

    }
}
