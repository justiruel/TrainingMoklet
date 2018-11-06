package irul.com.trainingmoklet.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import irul.com.trainingmoklet.RealmHelper;
import irul.com.trainingmoklet.adapter.RecyclerItemClickListener;
import irul.com.trainingmoklet.model.Drakor;
import irul.com.trainingmoklet.R;
import irul.com.trainingmoklet.adapter.DrakorAdapter;
import irul.com.trainingmoklet.model.Makanan;
import irul.com.trainingmoklet.model.Meal;
import irul.com.trainingmoklet.model.Meals;
import irul.com.trainingmoklet.restfull.ApiClient;
import irul.com.trainingmoklet.restfull.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Tab2 extends Fragment {

    ArrayList<Drakor> mList = new ArrayList<>();
    DrakorAdapter mAdapter;
    RecyclerView recyclerView;
    ArrayList<Meal> mListm = new ArrayList<>();

    Realm realm;
    RealmHelper realmHelper;
    List<Makanan> makananMakanan;
    Meal meal;
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab2, container, false);
        String strtext = getArguments().getString("edttext");
        Toast.makeText(getContext(), strtext, Toast.LENGTH_SHORT).show();
        recyclerView = view.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        //GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 4);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new DrakorAdapter(mListm);
        recyclerView.setAdapter(mAdapter);

        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);
        realmHelper = new RealmHelper(realm);

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                final int in = position;
                Snackbar snackbar = Snackbar
                        .make(view, mListm.get(in).getStrMeal(), Snackbar.LENGTH_LONG)
                        .setAction("Remove meal from Favorite", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                //delete data
                                realmHelper.delete(mListm.get(in).getIdMeal());
                                mListm.remove(in);
                                mAdapter.notifyDataSetChanged();

                                Toast.makeText(getContext(), "Meal removed", Toast.LENGTH_SHORT).show();
                            }
                        });

                snackbar.show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                // ...
            }
        }));

        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.activity_main_swipe_refresh_layout);
        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorAccent));
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fillData();
            }
        });

        fillData();

        return view;
    }

    private void fillData() {
        makananMakanan = realmHelper.getAllMakanan();
        mListm.clear();
        int i =0;
        for (Makanan m:makananMakanan) {
            meal = new Meal();
            meal.setIdMeal(m.getIdMeal());
            meal.setStrMeal(m.getStrMeal());
            meal.setStrMealThumb(m.getStrMealThumb());
            mListm.add(meal);
            i++;
        }
        //mListm.addAll(mListm);
        mAdapter.notifyDataSetChanged();
        mSwipeRefreshLayout.setRefreshing(false);
    }
}
