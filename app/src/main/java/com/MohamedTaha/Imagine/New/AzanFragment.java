package com.MohamedTaha.Imagine.New;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.MohamedTaha.Imagine.New.Adapter.AdapterAzan;
import com.MohamedTaha.Imagine.New.mvp.model.azan.Azan;
import com.MohamedTaha.Imagine.New.mvp.model.azan.Datum;
import com.MohamedTaha.Imagine.New.rest.APIServices;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.MohamedTaha.Imagine.New.rest.RetrofitClient.getRetrofit;


/**
 * A simple {@link Fragment} subclass.
 */
public class AzanFragment extends Fragment {
    APIServices apiServices;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    List<Datum>datumList = new ArrayList<>();

    public AzanFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_azan, container, false);
        ButterKnife.bind(this, view);
        apiServices = getRetrofit().create(APIServices.class);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        Call<Azan> azanCall = apiServices.getPrayerTimes("51.508515", "0.1254872", false);
        azanCall.enqueue(new Callback<Azan>() {
            @Override
            public void onResponse(Call<Azan> call, Response<Azan> response) {
                Azan azan = response.body();

                if (azan.getStatus().equals("OK")) {
                    progressBar.setVisibility(View.GONE);
                    datumList.addAll(azan.getData());
                    AdapterAzan adapterAzan = new AdapterAzan(azan.getData());
                    //adapterAzan.setAzanList(azan.getData());
                    recyclerView.setAdapter(adapterAzan);
                    //   Toast.makeText(getActivity(), "OK: " + azan.getData().get(0).getTimings().getFajr().substring(0, 5), Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getActivity(), "NO", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);

                }
            }

            @Override
            public void onFailure(Call<Azan> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
            }
        });
        return view;
    }
}
