package irul.com.trainingmoklet.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import irul.com.trainingmoklet.R;
import irul.com.trainingmoklet.model.Meal;

public class DrakorAdapter extends RecyclerView.Adapter<DrakorAdapter.ViewHolder> {

    //ArrayList<Drakor> drakorList;
    ArrayList<Meal> mealList;


    public DrakorAdapter(ArrayList<Meal> mealList) {
        this.mealList = mealList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.drakor_list, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Meal meal = mealList.get(position);
        holder.tvTitle.setText(meal.getStrMeal());
        //holder.ivFoto.setImageDrawable(drakor.foto);
        Picasso.get().load(meal.getStrMealThumb()).into(holder.ivFoto);
    }

    @Override
    public int getItemCount() {
        if (mealList != null)
            return mealList.size();
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivFoto;
        TextView tvTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            ivFoto = itemView.findViewById(R.id.imageView);
            tvTitle = itemView.findViewById(R.id.textViewJudul);
        }
    }
}
