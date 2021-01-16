package com.MohamedTaha.Imagine.New;

import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AbsListView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ReScrollUtil {
    private FloatingActionButton floatingActionButton;
    private RecyclerView recyclerView;

    public ReScrollUtil(FloatingActionButton floatingActionButton, RecyclerView recyclerView) {
        this.floatingActionButton = floatingActionButton;
        this.recyclerView = recyclerView;
        addOnGlobalLayoutListener();
    }

    private void addOnGlobalLayoutListener() {
        recyclerView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) {
                    recyclerView.setOnScrollListener(new ScrollListener());
                } else {
                    recyclerView.addOnScrollListener(new ScrollListener());
                }

            }
        });
    }

    private void setFabVisibility(boolean isVisible) {
        // Animation animationFadeOut = AnimationUtils.loadAnimation(floatingActionButton.getContext(), android.R.anim.fade_out);
        //Animation animationFadeIn = AnimationUtils.loadAnimation(floatingActionButton.getContext(), android.R.anim.fade_in);
        if (isVisible) {
            //  floatingActionButton.setAnimation(animationFadeIn);
            floatingActionButton.setVisibility(View.VISIBLE);
        } else {
            //floatingActionButton.setAnimation(animationFadeOut);
            floatingActionButton.setVisibility(View.INVISIBLE);
        }
    }

    class ScrollListener extends RecyclerView.OnScrollListener {

        @Override
        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                Log.d("TAG", "SCROLL_STATE_TOUCH_SCROLL");

            }
            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                Log.d("TAG", "SCROLL_STATE_IDLE");

            }
            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_FLING) {
                Log.d("TAG", "SCROLL_STATE_FLING");

            }
        }

        @Override
        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            if (isRecyclerScrollable()) {
              //  Log.d("TAGO", "isRecyclerScrollable");
                if (!recyclerView.canScrollVertically(1)) {
                    setFabVisibility(true);
                //    Log.d("TAGO", "setFabVisibility true");
                } else {
                    setFabVisibility(false);
                    //Log.d("TAGO", "setFabVisibility false");
                }
            } else {
               // Log.d("TAGO", "isRecyclerScrollable not");

            }
        }

        private boolean isRecyclerScrollable() {
            return recyclerView.computeVerticalScrollRange() >
                    recyclerView.getHeight();
        }
    }

    public void onClickRecyclerView(int idFloatAction) {
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == idFloatAction) {
                    recyclerView.smoothScrollToPosition(0);
                }

            }
        });
    }
}
