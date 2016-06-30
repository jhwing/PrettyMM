package jhw.pretty.module.main;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import jhw.pretty.R;
import jhw.pretty.bean.RespData;
import jhw.pretty.module.main.adapter.MainListAdapter;

public class MainActivity extends AppCompatActivity implements MainContract.IMainView {

    RecyclerView recyclerList;

    MainListAdapter mAdapter;

    MainContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerList = (RecyclerView) findViewById(R.id.recyclerList);
        recyclerList.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new MainListAdapter();
        recyclerList.setAdapter(mAdapter);
        mPresenter = new MainPresenter(this);
        mPresenter.initData(0);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unBind(this);
    }

    @Override
    public Context getViewContext() {
        return this;
    }

    @Override
    public void initListData(List<RespData.ResultsBean> beanList) {
        mAdapter.setBeanList(beanList);
    }

    @Override
    public void notifyDataSetChanged() {
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showEmptyView(boolean show) {

    }

    @Override
    public void resetRefresh() {

    }

    @Override
    public void refresh() {
        mPresenter.initData(0);
    }

    @Override
    public void resetLoadMore() {

    }

    @Override
    public void showLoadingView() {

    }

    @Override
    public void hideLoadingView() {

    }
}
