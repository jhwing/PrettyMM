package jhw.pretty.common.loadmore;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

/**
 * Created by jihongwen on 16/6/30.
 */

public class LoadMoreUtil {

    RecyclerView recyclerView;

    LoadMoreAdapter loadMoreAdapter;

    View mFootView;

    OnLoadMoreListener onLoadMoreListener;

    public void setRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
        loadMoreAdapter = new LoadMoreAdapter(recyclerView.getAdapter());
        recyclerView.setAdapter(loadMoreAdapter);
        recyclerView.addOnScrollListener(new LoadMoreScrollListener());
    }

    public void setFootView(View footView) {
        mFootView = footView;
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }

    public void showFootView() {
        loadMoreAdapter.addFootView(mFootView);

    }

    public void hideFootView() {
        loadMoreAdapter.removeFooterView(mFootView);
    }

    public interface OnLoadMoreListener {
        void onLoadMore();
    }

    class LoadMoreScrollListener extends RecyclerView.OnScrollListener {

        /**
         * 当前RecyclerView类型
         */
        protected LayoutManagerType layoutManagerType;

        /**
         * 最后一个的位置
         */
        private int[] lastPositions;

        /**
         * 最后一个可见的item的位置
         */
        private int lastVisibleItemPosition;

        /**
         * 当前滑动的状态
         */
        private int currentScrollState = 0;

        private int offset = 3;

        private boolean isUp = false;


        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            if (dy > 0) {
                isUp = true;
            } else {
                isUp = false;
            }
            RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();

            if (layoutManagerType == null) {
                if (layoutManager instanceof LinearLayoutManager) {
                    layoutManagerType = LayoutManagerType.LinearLayout;
                } else if (layoutManager instanceof GridLayoutManager) {
                    layoutManagerType = LayoutManagerType.GridLayout;
                } else if (layoutManager instanceof StaggeredGridLayoutManager) {
                    layoutManagerType = LayoutManagerType.StaggeredGridLayout;
                } else {
                    throw new RuntimeException(
                            "Unsupported LayoutManager used. Valid ones are LinearLayoutManager, GridLayoutManager and StaggeredGridLayoutManager");
                }
            }

            switch (layoutManagerType) {
                case LinearLayout:
                    lastVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
                    break;
                case GridLayout:
                    lastVisibleItemPosition = ((GridLayoutManager) layoutManager).findLastVisibleItemPosition();
                    break;
                case StaggeredGridLayout:
                    StaggeredGridLayoutManager staggeredGridLayoutManager = (StaggeredGridLayoutManager) layoutManager;
                    if (lastPositions == null) {
                        lastPositions = new int[staggeredGridLayoutManager.getSpanCount()];
                    }
                    staggeredGridLayoutManager.findLastVisibleItemPositions(lastPositions);
                    lastVisibleItemPosition = findMax(lastPositions);
                    break;
            }
        }

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            currentScrollState = newState;
            RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
            int visibleItemCount = layoutManager.getChildCount();
            int totalItemCount = layoutManager.getItemCount();
            if (isUp && (visibleItemCount > 0 && currentScrollState == RecyclerView.SCROLL_STATE_IDLE
                    && (lastVisibleItemPosition) >= totalItemCount - 1 - offset
                    && totalItemCount > offset) && lastVisibleItemPosition > visibleItemCount) {
                if (onLoadMoreListener != null) {
                    onLoadMoreListener.onLoadMore();
                    showFootView();
                }
            }
        }

        /**
         * 取数组中最大值
         *
         * @param lastPositions
         * @return
         */
        private int findMax(int[] lastPositions) {
            int max = lastPositions[0];
            for (int value : lastPositions) {
                if (value > max) {
                    max = value;
                }
            }

            return max;
        }
    }

    public enum LayoutManagerType {
        LinearLayout,
        StaggeredGridLayout,
        GridLayout
    }
}
