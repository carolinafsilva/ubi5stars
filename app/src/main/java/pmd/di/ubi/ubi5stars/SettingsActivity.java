package pmd.di.ubi.ubi5stars;

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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Locale;

public class SettingsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    FirebaseAuth mAuth;

    private SharedPreferences oSP;
    private SharedPreferences.Editor oSPE;
    private boolean initSpin = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        mAuth = FirebaseAuth.getInstance();

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
        else {
            String sys_lang = Locale.getDefault().getLanguage();
            if (sys_lang.equals("en") || sys_lang.equals("pt")) {
                int lang_pos = adapter.getPosition(sys_lang);
                spin.setSelection(lang_pos);
            }
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        if (initSpin)
            initSpin = false;
        else {
            String lang = parent.getItemAtPosition(position).toString();
            oSPE = oSP.edit();
            oSPE.putInt("lang", position);
            oSPE.apply();
            setLocale(lang);
            Toast.makeText(getApplicationContext(), getString(R.string.lang_toast), Toast.LENGTH_LONG).show();
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

    public void deleteUser(View v) {

        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            user.delete()
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "Account deleted", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        } else {
            Toast.makeText(getApplicationContext(), "No user logged in", Toast.LENGTH_LONG).show();
        }
    }

    // Idioma
    // Apagar dados/cache
    // Abrir página de créditos? Licensas, datas, agradecimentos?
}
