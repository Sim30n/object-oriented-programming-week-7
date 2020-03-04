package com.example.firstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    Context context = null;

    TextView text;
    EditText mEdit;
    TextView mText;
    TextView tiedostosta;
    EditText editTiedostonimi;
    TextView textTiedostonimi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText myTextBox = (EditText) findViewById(R.id.editText1);
        myTextBox.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                TextView myOutputBox = (TextView) findViewById(R.id.tuloste);
                myOutputBox.setText(s);
            }
        });
        context = MainActivity.this;
        text = (TextView) findViewById(R.id.textView);
        tiedostosta = (TextView) findViewById(R.id.textViewTiedostosta);
        System.out.println(context.getFilesDir());
    }

    public void testFunction(View v){
        text.setText("Hello World!");
    }

    public void tulostaSyote(View v) {
        mEdit   = (EditText)findViewById(R.id.editText1);
        mText = (TextView)findViewById(R.id.textView1);
        mText.setText("Syötetty teksti: "+mEdit.getText().toString());
    }

    public void readFile(View v){
        try{
            editTiedostonimi = (EditText)findViewById(R.id.editTiedostoNimi);
            InputStream ins = context.openFileInput(editTiedostonimi.getText().toString());
            BufferedReader br = new BufferedReader(new InputStreamReader(ins));
            String s ="";
            while((s=br.readLine()) != null){
                tiedostosta.setText(s);
                System.out.println(s);
            }
            ins.close();
        } catch(IOException e) {
            Log.e("IOException", "Virhe syötteessä");
        } finally {
            System.out.println("Luettu");
        }

    }

    public void writeFile(View v){

        try{
            editTiedostonimi = (EditText)findViewById(R.id.editTiedostoNimi);
            OutputStreamWriter ows = new OutputStreamWriter(context.openFileOutput(editTiedostonimi.getText().toString(), Context.MODE_PRIVATE));
            String s = "";
            mEdit = (EditText)findViewById(R.id.editText1);
            s = (mEdit.getText().toString());
            ows.write(s);
            ows.close();
        } catch(IOException e) {
            Log.e("IOException", "Virhe syötteessä");
        } finally {
            System.out.println("Kirjoitettu");
        }
    }
}

