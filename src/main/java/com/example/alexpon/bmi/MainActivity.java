package com.example.alexpon.bmi;

import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;


public class MainActivity extends ActionBarActivity {

    private EditText input_height;
    private EditText input_weight;
    private Button button_sub;
    private Button button_clr;
    private TextView show_bmi;
    private TextView show_sug;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setListener();
    }

    public void initView(){
        input_height = (EditText)findViewById(R.id.input_h);
        input_weight = (EditText)findViewById(R.id.input_w);
        button_sub = (Button)findViewById(R.id.submit);
        button_clr = (Button)findViewById(R.id.clear);
        show_bmi = (TextView)findViewById(R.id.bmi);
        show_sug = (TextView)findViewById(R.id.suggest);
    }

    public void setListener(){
        button_sub.setOnClickListener(clickListener);
        button_clr.setOnClickListener(clickListener);
    }

    private Button.OnClickListener clickListener = new Button.OnClickListener() {
        @Override
        public void onClick(View v){
            switch (v.getId()){
                case R.id.submit:
                    DecimalFormat df = new DecimalFormat("0.00");
                    try {
                        double height = Double.parseDouble(input_height.getText().toString()) / 100;
                        double weight = Double.parseDouble(input_weight.getText().toString());
                        double bmi = weight / (height * height);
                        show_bmi.setText(getText(R.string.show_result) + df.format(bmi));
                        if (bmi > 31.5) {
                            show_sug.setText(getText(R.string.too_heavy));
                        } else if (bmi > 25 && bmi <= 31.5) {
                            show_sug.setText(getText(R.string.heavy));
                        } else if (bmi <= 20) {
                            show_sug.setText(getText(R.string.thin));
                        } else {
                            show_sug.setText(getText(R.string.normal));
                        }
                    }
                    catch(Exception obj){
                       Toast alert = Toast.makeText(MainActivity.this, R.string.alert_no_input, Toast.LENGTH_SHORT);
                       alert.show();
                    }
                    break;
                case R.id.clear:
                    input_height.setText("");
                    input_weight.setText("");
                    show_bmi.setText("");
                    show_sug.setText("");
                    break;
            }
        }

    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
