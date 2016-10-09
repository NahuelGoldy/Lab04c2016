package dam.isi.frsf.utn.edu.ar.laboratorio04;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by Nahuel SG on 09/10/2016.
 */

public class PreferenciasActivity extends PreferenceActivity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferencias);
    }
}