package com.example.gourmetise;

import  androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

public class MainActivity extends AppCompatActivity {
    Spinner spinnerBoulangerie = null;
    private ArrayList<String> NomBoulangerie = new ArrayList<String>();
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
                    Log.i("info", "ok");
                    // Requête HTTP GET
                    String urlI = "http://10.0.2.2/ANTHONY/GOURMETISEPROJET/API/Boulangerie.php";
                    AsyncHttpClient requestI = new AsyncHttpClient();
                    requestI.get(urlI, new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                            super.onSuccess(statusCode, headers, response);
                            Log.i("info", "onsuccess ok");
// Deserialisation du flux JSON
                            Log.i("json",response.toString());
                            bdd = new GourmetiseDAO(MainActivity.this);
                            bdd.supprimerTous();
                            for (int i = 0; i < response.length(); i++) {
                                try {
                                    String siren = response.getJSONObject(i).getString("siren");
                                    String nom = response.getJSONObject(i).getString("raison_sociale");
                                    String rue = response.getJSONObject(i).getString("rue");
                                    String ville = response.getJSONObject(i).getString("ville");
                                    String codepostal = response.getJSONObject(i).getString("code_postal");
                                    String descriptif = response.getJSONObject(i).getString("descriptif");
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
                            /* public void chargerSpinner() {
                                bdd = new GourmetiseDAO(MainActivity.this); // Correction de l'usage de `this`
                                Cursor curseurTous = bdd.LesBoulangeries(); // Correction de la méthode appelée
                                NomBoulangerie.clear();
                                while (curseurTous.moveToNext()) {
                                    String nom = curseurTous.getString(curseurTous.getColumnIndex("nom")); // Utilisation de "nom"
                                    NomBoulangerie.add(nom);
                                }
                                curseurTous.close(); // Ne pas oublier de fermer le curseur
                                spinnerBoulangerie.setAdapter(new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item, NomBoulangerie));
                            } */
                        }


                        @Override
                        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                            super.onFailure(statusCode, headers, responseString, throwable);
                            Log.i("info", " on failure ok");
                            Log.i("Erreur", String.valueOf(statusCode) + "Erreur = " + responseString);
                            Toast.makeText(getApplicationContext(), "Echec de l'importation", Toast.LENGTH_LONG).show();
                        }
                    });
            }
        }
    };
}