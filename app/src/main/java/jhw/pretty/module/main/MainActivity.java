package jhw.pretty.module.main;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.List;

import jhw.pretty.R;
import jhw.pretty.base.activity.BaseAppCompatActivity;
import jhw.pretty.bean.RespData;
import jhw.pretty.common.loadmore.FootView;
import jhw.pretty.common.loadmore.LoadMoreUtil;
import jhw.pretty.module.main.adapter.MainListAdapter;

public class MainActivity extends BaseAppCompatActivity implements MainContract.IMainView {

    Toolbar toolbar;

    SwipeRefreshLayout swipeRefreshLayout;

    RecyclerView recyclerList;

    LoadMoreUtil loadMoreUtil;

    MainListAdapter mAdapter;

    MainContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPresenter = new MainPresenter(this);
        mPresenter.initData(0);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });
        recyclerList = (RecyclerView) findViewById(R.id.recyclerList);
        recyclerList.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new MainListAdapter();
        recyclerList.setAdapter(mAdapter);

        loadMoreUtil = new LoadMoreUtil();
        loadMoreUtil.setFootView(FootView.getLayoutView(this, recyclerList));
        loadMoreUtil.setRecyclerView(recyclerList);
        loadMoreUtil.setOnLoadMoreListener(new LoadMoreUtil.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                mPresenter.loadData(0);
            }
        });
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
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void refresh() {
        mPresenter.initData(0);
    }

    @Override
    public void resetLoadMore() {
        loadMoreUtil.hideFootView();
    }

    @Override
    public void showLoadingView() {

    }

    @Override
    public void hideLoadingView() {

    }
}
