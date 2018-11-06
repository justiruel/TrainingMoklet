package irul.com.trainingmoklet;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import irul.com.trainingmoklet.model.Warung;

public class AddWarungActivity extends AppCompatActivity {
    EditText edt_nama, edt_latitude, edt_longitude;
    Button button;
    Realm realm;
    RealmHelper realmHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_warung_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Add Point");

        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);
        realmHelper = new RealmHelper(realm);

        edt_nama = findViewById(R.id.edt_nama);
        edt_latitude = findViewById(R.id.edt_latitude);
        edt_longitude = findViewById(R.id.edt_longitude);

        edt_latitude.setText(String.valueOf(getIntent().getExtras().getDouble("LATITUDE")));
        edt_longitude.setText(String.valueOf(getIntent().getExtras().getDouble("LONGITUDE")));

        button = findViewById(R.id.button);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Warung warung = new Warung();
                    warung.setNama(edt_nama.getText().toString());
                    warung.setLatitude(Double.parseDouble(edt_latitude.getText().toString()));
                    warung.setLongitude(Double.parseDouble(edt_longitude.getText().toString()));

                    realmHelper = new RealmHelper(realm);
                    realmHelper.save_warung(warung);
                    Toast.makeText(getApplicationContext(), "Point Added", Toast.LENGTH_SHORT).show();
                    finish();
                }catch (Exception e){
                    //Toast.makeText(getApplicationContext(), "Meal already added before", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
