package com.example.sensorprueba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.speech.RecognizerIntent;
import java.util.ArrayList;
import java.util.Locale;

import java.util.List;

public class MainActivity extends AppCompatActivity{

    /* primer ejemplo ?????
    private static final int SPEECH_REQUEST_CODE = 0;

    //el intent para comenzar el reconocimiento del discurso
    private void displaySpeechRecognizer(){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);

        //se inicia el activity
        startActivityForResult(intent, SPEECH_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == SPEECH_REQUEST_CODE && resultCode == RESULT_OK){
            List<String> results = data.getStringArrayListExtra(
                    RecognizerIntent.EXTRA_RESULTS);
            String textohablado = results.get(0);
            //algo hace con el texo hablado ???
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    */

    private TextView textView;
    private ImageButton imageButton;
    private final int RCODE = 28;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.intro);
        imageButton = findViewById(R.id.francisco);
        imageButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                capturarVoz();
            }
        });
    }

    private void capturarVoz()
    {
        Intent intent = new Intent(RecognizerIntent
                .ACTION_RECOGNIZE_SPEECH);

        intent.putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
        );
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,
                Locale.getDefault());

        if (intent.resolveActivity(getPackageManager()) != null)
        {
            startActivityForResult(intent, RCODE);
        } else
        {
            Log.e("ERROR","Su dispositivo no admite entrada de voz");
        }
    }

    @Override
    protected  void onActivityResult(int requestCode,
                                     int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RCODE && resultCode == RESULT_OK && data != null)
        {
            ArrayList<String> result = data.getStringArrayListExtra(
                    RecognizerIntent.EXTRA_RESULTS);
            textView.setText(result.get(0));
        }

    }


}