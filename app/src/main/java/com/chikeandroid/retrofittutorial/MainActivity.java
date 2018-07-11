package com.chikeandroid.retrofittutorial;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chikeandroid.retrofittutorial.adapter.xAdapter;
import com.chikeandroid.retrofittutorial.data.model.Answer;
import com.chikeandroid.retrofittutorial.data.model.Item;
import com.chikeandroid.retrofittutorial.data.model.Owner;
import com.chikeandroid.retrofittutorial.data.remote.ApiUtils;
import com.chikeandroid.retrofittutorial.data.remote.SOService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Chike on 12/11/2016.
 */

public class MainActivity extends AppCompatActivity {

    //    private List<Owner> mOwner;
    //    private ViewPager sliderView;
    //    private LinearLayout mLinearLayout;
    //    private AnswersAdapter mAdapter;

    private xAdapter mAdapster;
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private SOService mService;

    ProgressDialog progress;

    private boolean isRefreshing = false;
    String LOG_TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mService = ApiUtils.getSOService();

        /*sliderView = (SliderView) findViewById(R.id.image_slider);
        mLinearLayout = (LinearLayout) findViewById(R.id.pagesContainer);*/

        mRecyclerView = findViewById(R.id.rv_answers);
        swipeRefreshLayout = findViewById(R.id.swiperefresh);

        mAdapster = new xAdapter(this, new ArrayList<Item>(0));

        initAdapter();

        //refreshListener
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                                                    @Override
                                                    public void onRefresh() {
                                                        Log.i(LOG_TAG, "onRefresh called from SwipeRefreshLayout");
                                                        isRefreshing = true;
                                                        loadAnswers();
                                                    }
                                                }
        );

        showDialog();
        loadAnswers();
    }

    public void initAdapter(){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapster);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST);
        mRecyclerView.addItemDecoration(itemDecoration);
    }

    public void showDialog() {
        progress = new ProgressDialog(this);
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setMessage("Loading");
        progress.show();
    }

    public void loadAnswers() {
        mService.getAnswers().enqueue(new Callback<Answer>() {
            @Override
            public void onResponse(Call<Answer> call, Response<Answer> response) {
                progress.dismiss();
                if (response.isSuccessful()) {
                    if (isRefreshing) {
                        swipeRefreshLayout.setRefreshing(false);
                        isRefreshing = false;
                    }
                    mAdapster.updateAnswers(response.body().getItems());

                } else {
                    Toast toast = new Toast(getApplicationContext());
                    Toast.makeText(getApplicationContext(), "No Response", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
                    toast.show();
                }
            }

            @Override
            public void onFailure(Call<Answer> call, Throwable t) {
                progress.dismiss();
                showErrorMessage();
            }
        });

    }

    public void showErrorMessage() {
        Toast.makeText(this, "Error loading posts", Toast.LENGTH_SHORT).show();
    }

    /*private void setupSlider() {
        sliderView.setDurationScroll(800);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(FragmentSlider.newInstance("https://image.tmdb.org/t/p/w250_and_h141_bestv2/zYFQM9G5j9cRsMNMuZAX64nmUMf.jpg"));
        fragments.add(FragmentSlider.newInstance("https://image.tmdb.org/t/p/w250_and_h141_bestv2/rXBB8F6XpHAwci2dihBCcixIHrK.jpg"));
        fragments.add(FragmentSlider.newInstance("https://image.tmdb.org/t/p/w250_and_h141_bestv2/biN2sqExViEh8IYSJrXlNKjpjxx.jpg"));
        fragments.add(FragmentSlider.newInstance("https://image.tmdb.org/t/p/w250_and_h141_bestv2/o9OKe3M06QMLOzTl3l6GStYtnE9.jpg"));

        SliderPagerAdapter mAdapter = new SliderPagerAdapter(getSupportFragmentManager(), fragments);
        sliderView.setAdapter(mAdapter);
        SliderIndicator mIndicator = new SliderIndicator(this, mLinearLayout, sliderView, R.drawable.circle_indicator);
        mIndicator.setPageCount(fragments.size());
        mIndicator.show();
    }*/

    public void showDialog(Activity activity, String msg, String img) {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog_1);

        ImageView ivDialog = dialog.findViewById(R.id.dialog_imageview);
        TextView tvDialog = dialog.findViewById(R.id.text_dialog);
        tvDialog.setText(msg);
        Picasso.get().load(img).into(ivDialog);

        dialog.show();
    }
}
