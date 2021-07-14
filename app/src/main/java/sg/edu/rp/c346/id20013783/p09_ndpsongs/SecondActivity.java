package sg.edu.rp.c346.id20013783.p09_ndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {

    Button btnFiveStar;
    ListView lv;
    EditText etTheFilter;
    ArrayList<Song> songlist;
    ArrayAdapter<Song> aa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        setTitle(getTitle().toString() + "~" + "Show Song");
        btnFiveStar = findViewById(R.id.btnFiveStar);
        lv = findViewById(R.id.lv);
        etTheFilter = findViewById(R.id.searchFilter);

        DBHelper dbh = new DBHelper(this);
        songlist = dbh.getAllSongs();
        dbh.close();

        aa = new ArrayAdapter<Song>(this,
                android.R.layout.simple_list_item_1, songlist);
        lv.setAdapter(aa);

        etTheFilter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                (SecondActivity.this).aa.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Song song = songlist.get(position);
                Intent i = new Intent(SecondActivity.this,ThirdActivity.class);
                i.putExtra("song",song);
                startActivity(i);
            }
        });
        btnFiveStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(SecondActivity.this);
                songlist.clear();
                aa.notifyDataSetChanged();
            }
        });
    }
}