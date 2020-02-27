package edu.gatech.seclass.wordfind6300;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Statistics extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        if (getIntent().hasExtra("edu.gatech.seclass.gameapp.SOMETHING")) {
            TextView tv = (TextView) findViewById(R.id.textView5);
            //TEST: the following is for test!!!!!
            String text = getIntent().getExtras().getString("edu.gatech.seclass.gameapp.SOMETHING");
            StatObject so = this.readFile();

            text = String.valueOf(so.letterWeights.get(so.letterWeights.size() - 1).get('B'));
            tv.setText("B's weight is "+ text);

        }
    }
    private StatObject readFile(){
        return ((StatObject)this.getApplication()).readFile();
    }
}
