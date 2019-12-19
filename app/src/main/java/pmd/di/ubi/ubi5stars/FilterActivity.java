package pmd.di.ubi.ubi5stars;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.CheckBox;

public class FilterActivity extends Activity {
    private CheckBox cbMonumentos;
    private CheckBox cbMuseus;
    private CheckBox cbArteUrbana;
    private CheckBox cbZonaLazer;
    private CheckBox cbZonaComercial;
    private CheckBox cbZonaDesportiva;
    private CheckBox cbZonaEstudantil;
    private CheckBox cbTransportes;
    private SharedPreferences sp;
    private SharedPreferences.Editor spEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        sp = getSharedPreferences("Filters", 0);

        DisplayMetrics display = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(display);
        int c = display.widthPixels;
        int l = display.heightPixels;

        getWindow().setLayout((int) (c * 0.8), (int) (l * 0.8));
        getWindow().setGravity(1);

        boolean monumento = sp.getBoolean("monumentos", true);
        boolean museu = sp.getBoolean("museus", true);
        boolean arteUrbana = sp.getBoolean("arteUrbana", false);
        boolean zonaLazer = sp.getBoolean("zonaLazer", false);
        boolean zonaComercial = sp.getBoolean("zonaComercial", false);
        boolean zonaDesportiva = sp.getBoolean("zonaDesportiva", false);
        boolean zonaEstudantil = sp.getBoolean("zonaEstudantil", false);
        boolean transportes = sp.getBoolean("transportes", false);

        cbMonumentos = findViewById(R.id.cb_monumentos);
        cbMuseus = findViewById(R.id.cb_museus);
        cbArteUrbana = findViewById(R.id.cb_arte_urbana);
        cbZonaLazer = findViewById(R.id.cb_zona_lazer);
        cbZonaComercial = findViewById(R.id.cb_zona_comercial);
        cbZonaDesportiva = findViewById(R.id.cb_zona_desportiva);
        cbZonaEstudantil = findViewById(R.id.cb_zona_estudantil);
        cbTransportes = findViewById(R.id.cb_transporte);

        cbMonumentos.setChecked(monumento);
        cbArteUrbana.setChecked(arteUrbana);
        cbMuseus.setChecked(museu);
        cbZonaEstudantil.setChecked(zonaEstudantil);
        cbZonaDesportiva.setChecked(zonaDesportiva);
        cbZonaComercial.setChecked(zonaComercial);
        cbZonaLazer.setChecked(zonaLazer);
        cbTransportes.setChecked(transportes);

    }

    public void onFilterClicked(View v) {
        spEditor = sp.edit();

        spEditor.putBoolean("monumentos", cbMonumentos.isChecked());
        spEditor.putBoolean("museus", cbMuseus.isChecked());
        spEditor.putBoolean("arteUrbana", cbArteUrbana.isChecked());
        spEditor.putBoolean("zonaLazer", cbZonaLazer.isChecked());
        spEditor.putBoolean("zonaComercial", cbZonaComercial.isChecked());
        spEditor.putBoolean("zonaDesportiva", cbZonaDesportiva.isChecked());
        spEditor.putBoolean("zonaEstudantil", cbZonaEstudantil.isChecked());
        spEditor.putBoolean("transportes", cbTransportes.isChecked());

        spEditor.apply();

        Intent i = new Intent(this, MapActivity.class);

        startActivity(i);
        super.finish();
    }

    public void onExitClicked(View v) {
        super.finish();
    }
}

