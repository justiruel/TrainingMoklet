package irul.com.trainingmoklet.fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import irul.com.trainingmoklet.R;
import irul.com.trainingmoklet.RealmHelper;
import irul.com.trainingmoklet.adapter.DrakorAdapter;
import irul.com.trainingmoklet.adapter.DrakorHorAdapter;
import irul.com.trainingmoklet.adapter.RecyclerItemClickListener;
import irul.com.trainingmoklet.model.Makanan;
import irul.com.trainingmoklet.model.Meal;
import irul.com.trainingmoklet.model.Meals;
import irul.com.trainingmoklet.restfull.ApiClient;
import irul.com.trainingmoklet.restfull.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;
import static android.content.Context.RECEIVER_VISIBLE_TO_INSTANT_APPS;

public class Tab1 extends Fragment {
    //ArrayList<Drakor> mList = new ArrayList<>();
    ArrayList<Meal> mListm = new ArrayList<>();
    DrakorAdapter mAdapter;
    DrakorHorAdapter mHorAdapter;
    RecyclerView recyclerView;
    RecyclerView recyclerViewHorizon;

    Realm realm;
    RealmHelper realmHelper;
    List<Makanan> makananMakanan;
    Makanan makanan;
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab1, container, false);

        recyclerView = view.findViewById(R.id.recyclerView2);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new DrakorAdapter(mListm);
        recyclerView.setAdapter(mAdapter);
        fillData();

        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);
        realmHelper = new RealmHelper(realm);

        //realmHelper.delete_all_realm_data();

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                final int in = position;
                //Toast.makeText(getContext(), mListm.get(position).getStrMeal(), Toast.LENGTH_SHORT).show();
                Snackbar snackbar = Snackbar
                        .make(view, mListm.get(in).getStrMeal(), Snackbar.LENGTH_LONG)
                        .setAction("Add to Favorite", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                //Toast.makeText(getContext(), mListm.get(in).getStrMeal(), Toast.LENGTH_SHORT).show();
                                //Toast.makeText(getContext(), mListm.get(in).getIdMeal().toString(), Toast.LENGTH_SHORT).show();

                                //simpan data
                                makanan = new Makanan();
                                makanan.setIdMeal(mListm.get(in).getIdMeal());
                                makanan.setStrMeal(mListm.get(in).getStrMeal().toString());
                                makanan.setStrMealThumb(mListm.get(in).getStrMealThumb().toString());

                                try {
                                    realmHelper = new RealmHelper(realm);
                                    realmHelper.save(makanan);
                                    Toast.makeText(getContext(), "Meal added", Toast.LENGTH_SHORT).show();
                                }catch (Exception e){
                                    Toast.makeText(getContext(), "Meal already added before", Toast.LENGTH_SHORT).show();
                                }

                                //get data
//                                makananMakanan = realmHelper.getAllMakanan();
//                                for (Makanan m:makananMakanan) {
//                                    Toast.makeText(getContext(), m.getStrMeal().toString(), Toast.LENGTH_SHORT).show();
//                                }



                                //delete data
                                //realmHelper.delete(1);


                        }
                        });

                snackbar.show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                // ...
            }
        }));


        recyclerViewHorizon = view.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManagerHorizon = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewHorizon.setLayoutManager(layoutManagerHorizon);
        mHorAdapter = new DrakorHorAdapter(mListm);
        recyclerViewHorizon.setAdapter(mHorAdapter);

        /*startRecyclerView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false));*/

        //agar list horizontal sisi kanan terlihat ditambah ini di recyclerview android:clipToPadding="false" android:paddingRight="24dp"
        SnapHelper snapHelperStart = new GravitySnapHelper(Gravity.START);
        snapHelperStart.attachToRecyclerView(recyclerViewHorizon);


        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.activity_main_swipe_refresh_layout);
        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorAccent));
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fillData();
            }
        });

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
                mHorAdapter.notifyDataSetChanged();

                mSwipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<Meals> call, Throwable t) {
                Log.wtf("hasil",t.getMessage());
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });


    }
}
