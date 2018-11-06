package irul.com.trainingmoklet.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Makanan extends RealmObject {

    @PrimaryKey
    private int idMeal;
    private String strMeal;
    private String strMealThumb;

    public int getIdMeal() {
        return idMeal;
    }

    public void setIdMeal(int idMeal) {
        this.idMeal = idMeal;
    }

    public String getStrMeal() {
        return strMeal;
    }

    public void setStrMeal(String strMeal) {
        this.strMeal = strMeal;
    }

    public String getStrMealThumb() {
        return strMealThumb;
    }

    public void setStrMealThumb(String strMealThumb) {
        this.strMealThumb = strMealThumb;
    }
}


