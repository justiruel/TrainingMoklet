package irul.com.trainingmoklet;

import android.util.Log;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import irul.com.trainingmoklet.model.Makanan;
import irul.com.trainingmoklet.model.Warung;


public class RealmHelper {

    Realm realm;

    public  RealmHelper(Realm realm){
        this.realm = realm;
    }

    // untuk menyimpan data
    public void save(final Makanan makanan){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                if (realm != null){
                    Makanan model = realm.copyToRealm(makanan);
                }else{
                    //Log.e("", "DB not Found");
                }
            }
        });
    }

    // untuk memanggil semua data
    public List<Makanan> getAllMakanan(){
        RealmResults<Makanan> results = realm.where(Makanan.class).findAll();
        return results;
    }

    // untuk menghapus data
    public void delete(Integer id){
        final RealmResults<Makanan> model = realm.where(Makanan.class).equalTo("idMeal", id).findAll();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                model.deleteFromRealm(0);
            }
        });
    }

    //delete semua realm data di mahasiswa.db
    public void delete_all_realm_data(){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.deleteAll();
            }
        });
    }



    //simpan data warung
    public void save_warung(final Warung warung){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                if (realm != null){
                    Log.e("Created", "DB was created");
                    Number currentIdNum = realm.where(Warung.class).max("id");
                    int nextId;
                    if (currentIdNum == null){
                        nextId = 1;
                    }else {
                        nextId = currentIdNum.intValue() + 1;
                    }
                    warung.setId(nextId);
                    realm.copyToRealm(warung);
                }else{
                    Log.e("", "DB not Found");
                }
            }
        });
    }

    //tampil warung
    public List<Warung> getAllWarung(){
        RealmResults<Warung> results = realm.where(Warung.class).findAll();
        return results;
    }

}

