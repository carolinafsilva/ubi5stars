package pmd.di.ubi.ubi5stars;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class SearchActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_search);

        ListView listV=(ListView)findViewById(R.id.list);   // Colocar titulo na listView

        Spinner spinner = findViewById(R.id.spinner1);  // Spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.classes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {   // Implement Query to database and show results
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}
