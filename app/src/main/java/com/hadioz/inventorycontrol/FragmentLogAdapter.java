package com.hadioz.inventorycontrol;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.hadioz.inventorycontrol.api.APIService;
import com.hadioz.inventorycontrol.api.APIUtil;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentLogAdapter  extends FragmentStateAdapter {

    private String id;


    public FragmentLogAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle, String id) {
        super(fragmentManager, lifecycle);
        this.id = id;


    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 1:
                return new output(id);
        }
        return new input(id);
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
