package com.example.omd.library.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.omd.library.Adapters.NewsAdapter;
import com.example.omd.library.MVP.NewsMVP.Presenter;
import com.example.omd.library.MVP.NewsMVP.PresenterImp;
import com.example.omd.library.MVP.NewsMVP.ViewData;
import com.example.omd.library.Models.NewsModel;
import com.example.omd.library.R;
import com.yarolegovich.lovelydialog.LovelyStandardDialog;

import java.util.List;


public class News_Fragment extends Fragment implements ViewData {
    private RecyclerView news_recView;
    private RecyclerView.LayoutManager manager;
    private RecyclerView.Adapter adapter;
    private Context context ;
    private Presenter presenter;
    private ProgressBar progressBar;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_fragment,container,false);
        initView(view);
        presenter = new PresenterImp(this,context);
        presenter.getNewsData();

        return view;
    }



    private void initView(View view) {
        context = view.getContext();
        progressBar = (ProgressBar) view.findViewById(R.id.news_prgBar);
        progressBar.setVisibility(View.VISIBLE);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.news_swipe_refresh);
        swipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(getActivity(),R.color.centercolor));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getNewsData();


            }
        });
        news_recView = (RecyclerView) view.findViewById(R.id.news_recyclerView);
        manager = new LinearLayoutManager(context);
        news_recView.setLayoutManager(manager);
    }






    @Override
    public void onNewsDataSuccess(List<NewsModel> newsModelList) {
        adapter = new NewsAdapter(newsModelList,context);
        news_recView.setAdapter(adapter);
        news_recView.getAdapter().notifyDataSetChanged();
        swipeRefreshLayout.setRefreshing(false);

    }

    @Override
    public void onNewsDataFailed(String error) {
        CreateAlertDialog(error);
        swipeRefreshLayout.setRefreshing(false);


    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
        swipeRefreshLayout.setRefreshing(false);

    }
    private void CreateAlertDialog(String msg)
    {
        final LovelyStandardDialog dialog = new LovelyStandardDialog(getActivity())
                .setTopColor(ContextCompat.getColor(getActivity(),R.color.centercolor))
                .setTopTitle("Error")
                .setTopTitleColor(ContextCompat.getColor(getActivity(),R.color.base))
                .setPositiveButtonColorRes(R.color.centercolor)
                .setMessage(msg);
        dialog.setPositiveButton("Cancel", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.create();
        dialog.show();
    }


}
