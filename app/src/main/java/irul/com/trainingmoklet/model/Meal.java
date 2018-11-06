package irul.com.trainingmoklet.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Meal {
    @SerializedName("idMeal")
    private Integer idMeal;

    @SerializedName("strMeal")

    private String strMeal;

    @SerializedName("strMealThumb")
    private String strMealThumb;

    public String getStrMeal() {
        return strMeal;
    }

    public String getStrMealThumb() {
        return strMealThumb;
    }

    public Integer getIdMeal() { return idMeal; }

    public void setIdMeal(Integer idMeal) {
        this.idMeal = idMeal;
    }

    public void setStrMeal(String strMeal) {
        this.strMeal = strMeal;
    }

    public void setStrMealThumb(String strMealThumb) {
        this.strMealThumb = strMealThumb;
    }
}
