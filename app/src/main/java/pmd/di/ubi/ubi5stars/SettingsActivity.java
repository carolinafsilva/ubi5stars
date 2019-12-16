package pmd.di.ubi.ubi5stars;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class SettingsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private SharedPreferences oSP;
    private SharedPreferences.Editor oSPE;
    private boolean initSpin = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        oSP = getSharedPreferences("Local_Settings", 0);

        Spinner spin = findViewById(R.id.s_spin1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.languages, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);
        spin.setOnItemSelectedListener(this);

        // Aparecer a linguagem atual da aplicação
        int spinvalue = oSP.getInt("lang", -1);
        if (spinvalue != -1)
            spin.setSelection(spinvalue);


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        if (initSpin)
            initSpin = false;
        else {
            String lang = parent.getItemAtPosition(position).toString();
            Toast.makeText(parent.getContext(), lang, Toast.LENGTH_SHORT).show();

            oSPE = oSP.edit();
            oSPE.putInt("lang", position);
            oSPE.apply();

            setLocale(lang);
            Intent i = new Intent(this, SettingsActivity.class);
            startActivity(i);
            finish();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void setLocale(String lang) {
        Locale myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
    }

    // Idioma
    // Apagar dados/cache
    // Abrir página de créditos? Licensas, datas, agradecimentos?
}
