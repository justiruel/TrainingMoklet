package irul.com.trainingmoklet.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import irul.com.trainingmoklet.R;
import irul.com.trainingmoklet.adapter.DrakorAdapter;
import irul.com.trainingmoklet.model.Drakor;
import irul.com.trainingmoklet.model.Meal;
import irul.com.trainingmoklet.model.Meals;
import irul.com.trainingmoklet.restfull.ApiClient;
import irul.com.trainingmoklet.restfull.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Tab2Ori extends Fragment {

    ArrayList<Drakor> mList = new ArrayList<>();
    DrakorAdapter mAdapter;
    RecyclerView recyclerView;
    ArrayList<Meal> mListm = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab2, container, false);
        String strtext = getArguments().getString("edttext");
        Toast.makeText(getContext(), strtext, Toast.LENGTH_SHORT).show();
        recyclerView = view.findViewById(R.id.recyclerView);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 4);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new DrakorAdapter(mListm);
        recyclerView.setAdapter(mAdapter);
        fillData();

        return view;
    }

    private void fillData() {
//        Resources resources = getResources();
//        String[] arJudul = resources.getStringArray(R.array.places);
//        TypedArray a = resources.obtainTypedArray(R.array.places_picture);
//        Drawable[] arFoto = new Drawable[a.length()];
//        for (int i = 0; i < arFoto.length; i++) {
//            arFoto[i] = a.getDrawable(i);
//        }
//        a.recycle();
//        for (int i = 0; i < arJudul.length; i++) {
//            mList.add(new Drakor(arJudul[i], arFoto[i]));
//        }
//        mAdapter.notifyDataSetChanged();

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<Meals> call = apiService.getData();
        call.enqueue(new Callback<Meals>() {
            @Override
            public void onResponse(Call<Meals> call, Response<Meals> response) {
                List<Meal> meals = response.body().getMeals();
                Log.wtf("meals",meals.get(0).getStrMeal());

                mListm.addAll(meals);
                //Toast.makeText(getContext(), mListm.get(0).getStrMealThumb(), Toast.LENGTH_SHORT).show();
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Meals> call, Throwable t) {
                Log.wtf("hasil",t.getMessage());
            }
        });
    }
}
