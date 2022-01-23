package com.hadioz.inventorycontrol;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hadioz.inventorycontrol.api.APIService;
import com.hadioz.inventorycontrol.api.APIUtil;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class input extends Fragment {

    private RecyclerView listLog;
    private LogAdapter adapter;
    private ArrayList<Log> dataLog =new ArrayList<>();
    private String id;

    public input(String id) {
        this.id = id;
        // Required empty public constructor
    }

    private void LoadData(String id) {
        APIService apiService = new APIUtil().getAPIService();
        apiService.getLog(id).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                dataLog.clear();
                String data = response.body();
                if (!data.equals("null")) {
                    android.util.Log.d("Data", response.body());
                    try {
                        JSONArray dataArray = new JSONArray(data);
                        for (int i = 0; i < dataArray.length(); i++) {
                            JSONObject object = (JSONObject) dataArray.get(i);
                            String action = object.getString("action");
                            Log log = new Log(object.getString("admin"), action.charAt(0), object.getInt("amount"));
                            log.setDate(object.getString("date"));
                            log.setId(object.getString("id"));
                            if (log.getAction() == "i".charAt(0)) {
                                //android.util.Log.d("action", "i");
                                dataLog.add(log);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_input, container, false);
        // Inflate the layout for this fragment
        listLog = view.findViewById(R.id.rcv_log);

        adapter = new LogAdapter(dataLog, view.getContext());

        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(view.getContext());
        listLog.setLayoutManager(layoutManager);
        listLog.setAdapter(adapter);

        LoadData(id);

        return view;

    }
}