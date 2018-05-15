package myapplication.jiyun.com.yixian.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class BaseActivity<T extends BasePrisenter> extends AppCompatActivity {
    private BaseFragment lastFragment;
    protected T presenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        App.context = this;
        presenter = getPresenter();
        if (presenter != null) {
            presenter.AcctachView(this);
        }
        initView();
        loadData();

    }
    protected abstract int getLayoutId();
    @Override
    protected void onResume() {
        super.onResume();
        App.context = this;
    }

    @Override
    protected void onPause() {
        super.onPause();
        App.context = null;
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (presenter !=null) {
            presenter.DateView();
        }
    }

    public T getPresenter() {
        Type type = getClass().getGenericSuperclass();
        if (BaseActivity.class.equals(type)) {
            return null;
        }
        Type[] arguments = ((ParameterizedType) type).getActualTypeArguments();
        Class<T> aClass = (Class<T>) arguments[0];
        try {
            return aClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;

    }
    public BaseFragment AddFragment (int getLayoutid ,Class<? extends BaseFragment> fragment, Bundle bundle) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        String simpleName = fragment.getSimpleName();
        BaseFragment fragmentByTag = (BaseFragment) manager.findFragmentByTag(simpleName);
        try {
            if (fragmentByTag == null) {
                fragmentByTag = fragment.newInstance();
                transaction.add(getLayoutid,fragmentByTag,simpleName);
            } if (bundle != null) {
                fragmentByTag.setArguments(bundle);
            }
            if (lastFragment !=null) {
                transaction.hide(lastFragment);
            }
            transaction.show(fragmentByTag);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }



        lastFragment = fragmentByTag;
        transaction.commit();
        return fragmentByTag;
    }
    protected abstract void loadData();
    protected abstract void initView();
}
