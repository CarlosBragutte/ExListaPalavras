package com.example.ex03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {
private Button btFalar, btOuvir;
private EditText edTexto;
private TextToSpeech textoFalar;
private ListView listaPalavras;
private Locale locale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textoFalar=new TextToSpeech(MainActivity.this, MainActivity.this);
        edTexto=(EditText)findViewById(R.id.edtTexto);
        btFalar=(Button)findViewById(R.id.btnFalar);
        btOuvir=(Button)findViewById(R.id.btnOuvir);
        listaPalavras=(ListView)findViewById(R.id.lstPalavras);
        botoes();

    }
    private void botoes(){
        btFalar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String texto =edTexto.getText().toString();
                textoFalar.speak(texto, TextToSpeech.QUEUE_FLUSH,Bundle.EMPTY, "1");

            }
        });

        btOuvir.setOnClickListener();
        protected intent getRecognizerIntent(){
            Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Fale Aqui");
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, 'pt-BR');
            intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS,10);
            return intent;


        }

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode==RESULT_OK){
            ArrayList<String> palavras=data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                    listaPalavras.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple));

        }
    }
    public void onInit(int status) {
        textoFalar.setLanguage(locale.JAPANESE);
    }
}
