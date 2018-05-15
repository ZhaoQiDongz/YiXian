package myapplication.jiyun.com.yixian.base;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class BaseFragment<T extends BasePrisenter> extends Fragment {


    protected T presenter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutid(),container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = getPresenter();
        if (presenter != null) {
            presenter.AcctachView(this);
        }
        initView();
        loaddata();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (presenter !=null) {
            presenter.DateView();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter = getPresenter();
        if (presenter != null) {
            presenter.AcctachView(this);
        }
    }

    protected abstract int getLayoutid();
    protected abstract void initView();
    protected abstract void loaddata();
    public T getPresenter() {
        Type type = getClass().getGenericSuperclass();
        if (BaseFragment.class.equals(type)) {
            return null;
        }
        Type[] arguments = ((ParameterizedType)type).getActualTypeArguments();
        Class<T> aClass = (Class<T>) arguments[0];
        try {
            return aClass.newInstance();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden)
            onHidden();
        else
            onShow();

    }
    /**
     * 代表Fragment处于隐藏状态
     */
    protected abstract void onHidden();

    /**
     * 代表Fragment处于可见状态
     */
    protected abstract void onShow();

    /**
     * 设置标题
     * @return
     */
    public abstract void setTitle();
}
