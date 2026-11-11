package com.example.douanetemporelle;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_result);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // linkiw (link) code ll elements mt3 interface
        View fond = findViewById(R.id.main);
        TextView tvIcone = findViewById(R.id.tv_icone_deb);
        TextView tvVerdict = findViewById(R.id.tv_verdict_deb);
        TextView tvMessage = findViewById(R.id.tv_message_deb);
        Button btnNouveau = findViewById(R.id.btn_nouveau_deb);

        // n9raw el donnees elli jaw mel VerificationActivity b nafs les cles
        Intent in = getIntent();
        String m = Utils.matricule;
        boolean approuve = in.getBooleanExtra(Utils.cle(m, Utils.verdict), false);
        String nom = in.getStringExtra(Utils.cle(m, Utils.nom));

        // el fond yetbaddel 7asb el verdict : vert ken approuve w rouge ken refuse
        if (approuve) {
            fond.setBackgroundColor(Color.parseColor("#2ECC71"));
            tvIcone.setText("✔");
            tvVerdict.setText("SAUT APPROUVÉ");
            tvMessage.setText("Bon voyage " + nom + " !");
        } else {
            fond.setBackgroundColor(Color.parseColor("#E74C3C"));
            tvIcone.setText("✘");
            tvVerdict.setText("SAUT REFUSÉ");
            tvMessage.setText(nom + " est en état d'arrestation temporelle");
        }

        // nrj3ou ll MainActivity w nsakrou el activities el 9dom
        btnNouveau.setOnClickListener(v -> {
            Intent i = new Intent(ResultActivity.this, MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
            finish();
        });
    }
}