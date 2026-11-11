package com.example.douanetemporelle;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

public class VerificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_verification);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // linkiw (link) code ll elements mt3 interface
        TextView tvNom = findViewById(R.id.tv_nom_kn);
        TextView tvAge = findViewById(R.id.tv_age_kn);
        TextView tvMotif = findViewById(R.id.tv_motif_kn);
        Button btnApprouver = findViewById(R.id.btn_approuver_kn);
        Button btnRefuser = findViewById(R.id.btn_refuser_kn);

        // n9raw el donnees elli jaw mel MainActivity b nafs les cles
        Intent in = getIntent();
        String m = Utils.matricule;
        String nom = in.getStringExtra(Utils.cle(m, Utils.nom));
        int naissance = in.getIntExtra(Utils.cle(m, Utils.naissance), 0);
        int destination = in.getIntExtra(Utils.cle(m, Utils.destination), 0);
        String motif = in.getStringExtra(Utils.cle(m, Utils.motif));

        // 7sab el age temporel (farq bin el destination w el naissance)
        int ageTemporel = destination - naissance;

        // na3mrou el textviews (el valeurs bark)
        tvNom.setText(nom);
        tvAge.setText(ageTemporel + " ans");
        tvMotif.setText(motif);

        // nhsbou risque level hasb reason mta3 safar w adding colors hasb risque
        TextView tvRisque = findViewById(R.id.tv_risque_kn);
        if (motif != null && motif.toLowerCase().contains("modification")) {
            tvRisque.setText("RISQUE DE PARADOXE : ÉLEVÉ");
            tvRisque.setTextColor(Color.parseColor("#E74C3C"));
        } else if (motif != null && motif.toLowerCase().contains("fuite")) {
            tvRisque.setText("RISQUE DE PARADOXE : MOYEN");
            tvRisque.setTextColor(Color.parseColor("#F39C12"));
        } else {
            tvRisque.setText("RISQUE DE PARADOXE : FAIBLE");
            tvRisque.setTextColor(Color.parseColor("#2ECC71"));
        }

        // ken el motif "Modification du passé" w el farq 3adad premier il bouton approuver twali disabled
        if (motif != null && motif.toLowerCase().contains("modification")
                && estPremier(ageTemporel)) {
            btnApprouver.setEnabled(false);
            // n5aliw el bouton yban disabled
            btnApprouver.setAlpha(0.4f);
        }

        btnApprouver.setOnClickListener(v -> lancerVerdict(true, nom));
        btnRefuser.setOnClickListener(v -> lancerVerdict(false, nom));
    }

    // nab3thou el verdict (boolean) w el esm lel ResultActivity
    private void lancerVerdict(boolean approuve, String nom) {
        Intent i = new Intent(VerificationActivity.this, ResultActivity.class);
        i.putExtra(Utils.cle(Utils.matricule, Utils.verdict), approuve);
        i.putExtra(Utils.cle(Utils.matricule, Utils.nom), nom);
        startActivity(i);
    }

    // methode tchouf ken 3dad premier walla le
    private boolean estPremier(int n) {
        if (n < 2) return false;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
}