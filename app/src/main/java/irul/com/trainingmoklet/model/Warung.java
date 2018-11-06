package irul.com.trainingmoklet.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Warung extends RealmObject {

    @PrimaryKey
    private int id;
    private String nama;
    private Double latitude;
    private Double longitude;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}


