package irul.com.trainingmoklet;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Preferences {
    static final String KEY_NAMA = "nama";
    static final String KEY_EMAIL = "email";
    static final String KEY_ALAMAT = "alamat";

    /** Pendlakarasian Shared Preferences yang berdasarkan paramater context */
    public static SharedPreferences getSharedPreference(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static String getKeyNama(Context context) {
        return getSharedPreference(context).getString(KEY_NAMA,"");
    }

    public static String getKeyEmail(Context context) {
        return getSharedPreference(context).getString(KEY_EMAIL,"");
    }

    public static String getKeyAlamat(Context context) {
        return getSharedPreference(context).getString(KEY_ALAMAT,"");
    }

    public static void setKeyNama(Context context, String nama) {
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(KEY_NAMA, nama);
        editor.apply();
    }

    public static void setKeyEmail(Context context, String email) {
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(KEY_EMAIL, email);
        editor.apply();
    }

    public static void setKeyAlamat(Context context, String alamat) {
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(KEY_ALAMAT, alamat);
        editor.apply();
    }
}