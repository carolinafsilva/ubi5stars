package pmd.di.ubi.ubi5stars;

import android.app.Activity;
import android.content.Intent;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        DisplayMetrics display = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(display);
        int c = display.widthPixels;
        int l = display.heightPixels;

        getWindow().setLayout((int) (c * 0.8), (int) (l * 0.8));
        getWindow().setGravity(1);

        cbMonumentos = findViewById(R.id.cb_monumentos);
        cbMuseus = findViewById(R.id.cb_museus);
        cbArteUrbana = findViewById(R.id.cb_arte_urbana);
        cbZonaLazer = findViewById(R.id.cb_zona_lazer);
        cbZonaComercial = findViewById(R.id.cb_zona_comercial);
        cbZonaDesportiva = findViewById(R.id.cb_zona_desportiva);
        cbZonaEstudantil = findViewById(R.id.cb_zona_estudantil);
        cbTransportes = findViewById(R.id.cb_transporte);
    }

    public void onFilterClicked(View v) {
        Intent i = new Intent(this, MapActivity.class);

        i.putExtra("monumentos", cbMonumentos.isChecked());
        i.putExtra("museus", cbMuseus.isChecked());
        i.putExtra("arteUrbana", cbArteUrbana.isChecked());
        i.putExtra("zonaLazer", cbZonaLazer.isChecked());
        i.putExtra("zonaComercial", cbZonaComercial.isChecked());
        i.putExtra("zonaDesportiva", cbZonaDesportiva.isChecked());
        i.putExtra("zonaEstudantil", cbZonaEstudantil.isChecked());
        i.putExtra("transportes", cbTransportes.isChecked());

        startActivity(i);
        super.finish();
    }

    public void onExitClicked(View v) {
        super.finish();
    }
}

