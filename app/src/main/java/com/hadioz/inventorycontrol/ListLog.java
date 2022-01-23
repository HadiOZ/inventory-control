package com.hadioz.inventorycontrol;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.AsyncTask;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.hadioz.inventorycontrol.api.APIService;
import com.hadioz.inventorycontrol.api.APIUtil;

import org.json.JSONArray;
import org.json.JSONObject;

import java.nio.channels.AsynchronousChannelGroup;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListLog extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager2 pager2;
    private FragmentLogAdapter adapter;
    private ArrayList<Log> dataLog = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getSupportActionBar().setTitle(getIntent().getStringExtra(ProductAdapter.EXTRA_PRODUCT_NAME));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_log);

        LoadData(getIntent().getStringExtra(ProductAdapter.EXTRA_ID));

        tabLayout = (TabLayout) findViewById(R.id.tl_log);
        pager2 = (ViewPager2) findViewById(R.id.vp2_log);

        FragmentManager fm = getSupportFragmentManager();
        adapter = new FragmentLogAdapter(fm, getLifecycle(), getIntent().getStringExtra(ProductAdapter.EXTRA_ID));
        pager2.setAdapter(adapter);

        tabLayout.addTab(tabLayout.newTab().setText("Input"));
        tabLayout.addTab(tabLayout.newTab().setText("Output"));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        pager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });
    }
//
//    public class LoadData extends AsyncTask<String,String,String> {
//        private String dataTemp;
//
//        @Override
//        protected String doInBackground(String... strings) {
//            APIService apiService = new APIUtil().getAPIService();
//            apiService.getLog(strings[0]).enqueue(new Callback<String>() {
//                @Override
//                public void onResponse(Call<String> call, Response<String> response) {
//                    dataTemp = response.body();
//                    //String data = response.body();
//                }
//                @Override
//                public void onFailure(Call<String> call, Throwable t) {
//                    t.printStackTrace();
//                }
//            });
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            super.onPostExecute(s);
//            if (!dataTemp.equals("null")) {
//                try {
//                    JSONArray dataArray = new JSONArray(dataTemp);
//                    for (int i = 0; i < dataArray.length(); i++) {
//                        JSONObject object = (JSONObject) dataArray.get(i);
//
//                        Log log = new Log(object.getString("admin"), object.getString("action").charAt(0), object.getInt("amount"));
//                        log.setDate(object.getString("date"));
//                        log.setId(object.getString("id"));
//                        dataLog.add(log);
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//            }
//            adapter.notifyDataSetChanged();
//        }
//    }

    private void LoadData(String id) {
        APIService apiService = new APIUtil().getAPIService();
        apiService.getLog(id).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String data = response.body();
                if (!data.equals("null")) {
                    android.util.Log.d("Data", response.body());
                    try {
                        JSONArray dataArray = new JSONArray(data);
                        for (int i = 0; i < dataArray.length(); i++) {
                            JSONObject object = (JSONObject) dataArray.get(i);

                            Log log = new Log(object.getString("admin"), object.getString("action").charAt(0), object.getInt("amount"));
                            log.setDate(object.getString("date"));
                            log.setId(object.getString("id"));
                            dataLog.add(log);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
                android.util.Log.d("size list log", Integer.toString(dataLog.size()));
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}