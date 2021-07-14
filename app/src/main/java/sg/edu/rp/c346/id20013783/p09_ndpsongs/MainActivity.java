package sg.edu.rp.c346.id20013783.p09_ndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import sg.edu.rp.c346.id20013783.p09_ndpsongs.R;

public class MainActivity extends AppCompatActivity {

    EditText etSongTitle , etSinger , etYearRelease;
    RadioGroup rg;
    Button btnInsert , btnShowList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etSongTitle = findViewById(R.id.etSongTitle);
        etSinger = findViewById(R.id.etSinger);
        etYearRelease = findViewById(R.id.etYear);
        rg = findViewById(R.id.radioGroup);
        btnInsert = findViewById(R.id.btnInsert);
        btnShowList = findViewById(R.id.btnShow);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newSongTitle = etSongTitle.getText().toString();
                String newSinger = etSinger.getText().toString();
                String newYear = etYearRelease.getText().toString().trim();
                int year = 0;
                try {
                    year = Integer.valueOf(newYear);
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Invalid year", Toast.LENGTH_LONG).show();
                    return;
                }
                DBHelper dbh = new DBHelper(MainActivity.this);

                int stars = getStars();
                dbh.insertSong(newSongTitle,newSinger,newYear,stars);
                dbh.close();
                Toast.makeText(MainActivity.this,"Inserted",Toast.LENGTH_LONG);

                etSongTitle.setText("");
                etSinger.setText("");
                etYearRelease.setText("");
            }
        });
        btnShowList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                startActivity(intent);
            }
        });
    }

    private int getStars(){
        int stars = 1;
        if (rg.getCheckedRadioButtonId() == R.id.oneStar){
            stars=1;
        }
        else if(rg.getCheckedRadioButtonId() == R.id.twoStar){
            stars=2;
        }
        else if(rg.getCheckedRadioButtonId() == R.id.threeStar){
            stars=3;
        }
        else if(rg.getCheckedRadioButtonId() == R.id.fourStar){
            stars=4;
        }
        else if(rg.getCheckedRadioButtonId() == R.id.twoStar){
            stars=5;
        }return stars;
    }


}