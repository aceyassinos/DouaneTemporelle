package com.example.douanetemporelle;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // linkiw (link) code ll elements mt3 interface
        EditText etNom = findViewById(R.id.et_nom_mym);
        EditText etNaissance = findViewById(R.id.et_naissance_mym);
        EditText etDestination = findViewById(R.id.et_destination_mym);
        RadioGroup rgMotif = findViewById(R.id.rg_motif_mym);
        Button btnInspecter = findViewById(R.id.btn_inspecter_mym);

        // wktli nenzlou 3le button t3ml verify ll donnee w ba3dika tt3ada ll activity lli b3dha
        btnInspecter.setOnClickListener(v -> {
            String nom = etNom.getText().toString().trim();
            String naiss = etNaissance.getText().toString().trim();
            String dest = etDestination.getText().toString().trim();
            int idChoisi = rgMotif.getCheckedRadioButtonId();

            // verification ll slots lkkol lezmou ikounou m3abin
            if (nom.isEmpty() || naiss.isEmpty() || dest.isEmpty() || idChoisi == -1) {
                Toast.makeText(this, "Remplis tous les champs", Toast.LENGTH_SHORT).show();
                return;
            }

            int anneeNaissance = Integer.parseInt(naiss);
            int anneeDestination = Integer.parseInt(dest);

            // verification ll annnee dest lezm tkoun akber mn annee naissance
            if (anneeDestination <= anneeNaissance) {
                Toast.makeText(this, "L'année de destination doit être après la naissance",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            //  n9raw/read radio button (motif)
            String motif = ((RadioButton) findViewById(idChoisi)).getText().toString();

            // nt3adew mn mainactivity ll verifcationactivity w sending l donnees ll act2
            Intent intent = new Intent(MainActivity.this, VerificationActivity.class);
            String m = Utils.matricule;
            intent.putExtra(Utils.cle(m, Utils.nom), nom);
            intent.putExtra(Utils.cle(m, Utils.naissance), anneeNaissance);
            intent.putExtra(Utils.cle(m, Utils.destination), anneeDestination);
            intent.putExtra(Utils.cle(m, Utils.motif), motif);
            startActivity(intent);
        });
    }
}