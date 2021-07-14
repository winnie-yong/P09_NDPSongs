package sg.edu.rp.c346.id20013783.p09_ndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class ThirdActivity extends AppCompatActivity {

    TextView tvID;
    EditText etSongTitle , etSinger , etYearRelease;
    RadioButton rb1,rb2,rb3,rb4,rb5;
    RadioGroup rgUpdate;
    Button btnUpdate , btnDelete , btnCancel;
    Song songData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        tvID = findViewById(R.id.songID);
        etSongTitle = findViewById(R.id.etUpdateTitle);
        etSinger = findViewById(R.id.etUpdateSinger);
        etYearRelease = findViewById(R.id.etUpdateYear);
        rb1 = findViewById(R.id.radio1);
        rb2 = findViewById(R.id.radio2);
        rb3 = findViewById(R.id.radio3);
        rb4 = findViewById(R.id.radio4);
        rb5 = findViewById(R.id.radio5);
        rgUpdate = findViewById(R.id.updateRadiogroup);
        btnUpdate = findViewById(R.id.TbtnUpdate);
        btnDelete = findViewById(R.id.TbtnDelete);
        btnCancel = findViewById(R.id.btnCancel);

        Intent i = getIntent();
        songData = (Song) i.getSerializableExtra("song");

        tvID.setText("ID: " + songData.get_id());
        etSongTitle.setText(songData.getTitle());
        etSinger.setText(songData.getSingers());
        etYearRelease.setText(songData.getYear()+"");
        switch (songData.getStar()){
            case 5:
                rb5.setChecked(true);
                break;
            case 4:
                rb4.setChecked(true);
                break;
            case 3:
                rb3.setChecked(true);
                break;
            case 2:
                rb2.setChecked(true);
                break;
            case 1:
                rb1.setChecked(true);
                break;
        }
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ThirdActivity.this);
                songData.setTitle(etSongTitle.getText().toString());
                songData.setSinger(etSinger.getText().toString());
                int year = 0;
                try {
                    year = Integer.valueOf(etYearRelease.getText().toString());
                } catch (Exception e) {
                    Toast.makeText(ThirdActivity.this, "Invalid year", Toast.LENGTH_LONG).show();
                    return;
                }
                songData.setYear(year);

                int selectedRB = rgUpdate.getCheckedRadioButtonId();
                RadioButton rb = findViewById(selectedRB);
                songData.setStars(Integer.parseInt(rb.getText().toString()));
                int result = dbh.updateSong(songData);
                if(result>0){
                    Intent i = new Intent(ThirdActivity.this,SecondActivity.class);
                    startActivity(i);
                    Toast.makeText(ThirdActivity.this,"Song updated!",Toast.LENGTH_LONG).show();
                    setResult(RESULT_OK);
                    finish();
                }
                else{
                    Toast.makeText(ThirdActivity.this,"Update failed",Toast.LENGTH_LONG).show();
                }
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ThirdActivity.this);
                dbh.deleteSong(songData.get_id());
                Intent i = new Intent(ThirdActivity.this,SecondActivity.class);
                startActivity(i);
                Toast.makeText(ThirdActivity.this,"Song is deleted",Toast.LENGTH_LONG).show();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ThirdActivity.this,SecondActivity.class);
                startActivity(i);
            }
        });
    }
}