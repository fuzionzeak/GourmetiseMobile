package com.example.gourmetise;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

public class MainActivity extends AppCompatActivity {

    private Button btnIMPORT = null;


    GourmetiseDAO bdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // obtention des références sur les vues de l'activité
        btnIMPORT = (Button) findViewById(R.id.boutonImport);


        btnIMPORT.setOnClickListener(EcouteurBouton);
    }

    public View.OnClickListener EcouteurBouton = new View.OnClickListener() {
        @SuppressLint("Range")
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.boutonImport:
                    // Requête HTTP GET
                    String urlI = "https://10.0.2.2/ANTHONY/GOURMETISE%20PROJET/API/Boulangerie.php";
                    AsyncHttpClient requestI = new AsyncHttpClient();
                    requestI.get(urlI, new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                            super.onSuccess(statusCode, headers, response);
// Deserialisation du flux JSON
                            Log.i("json",response.toString());
                            bdd = new GourmetiseDAO(MainActivity.this);
                            bdd.supprimerTous();
                            for (int i = 0; i < response.length(); i++) {
                                try {
                                    String siren = response.getJSONObject(i).getString("sirenC");
                                    String nom = response.getJSONObject(i).getString("nomC");
                                    String rue = response.getJSONObject(i).getString("rueC");
                                    String ville = response.getJSONObject(i).getString("villeC");
                                    String codepostal = response.getJSONObject(i).getString("codepostalC");
                                    String descriptif = response.getJSONObject(i).getString("descriptifC");
                                    Boulangerie C = new Boulangerie();
                                    C.setSiren(siren);
                                    C.setNom(nom);
                                    C.setRue(rue);
                                    C.setVille(ville);
                                    C.setCode_postal(codepostal);
                                    C.setDescriptif(descriptif);
                                    Log.i("info", C.toString());
                                    bdd.ajouterBoulangerie(C);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            Toast.makeText(getApplicationContext(), "Imporation terminée", Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                            super.onFailure(statusCode, headers, responseString, throwable);
                            Log.i("Erreur", String.valueOf(statusCode) + "Erreur = " + responseString);
                            Toast.makeText(getApplicationContext(), "Echec de l'importation", Toast.LENGTH_LONG).show();
                        }
                    });
            }
        }
    };
}