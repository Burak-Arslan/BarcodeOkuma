package com.example.burakarslan.barkodokuma;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class QRokuma extends AppCompatActivity {

    private Button btnScanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcodeokuma);

        btnScanner = (Button) findViewById(R.id.btnScan);

        final Activity activity = this;

        btnScanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                IntentIntegrator integrator = new IntentIntegrator(activity);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                integrator.setPrompt("Barkodu Hizalayınız");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(true);
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);


        if (intentResult != null) {

            if (intentResult.getContents() == null) {

                Toast.makeText(this, "Taramadan çıkıldı", Toast.LENGTH_SHORT).show();

            } else {

                Toast.makeText(this, intentResult.getContents(), Toast.LENGTH_SHORT).show();
            }
        } else {

            super.onActivityResult(requestCode, resultCode, data);

        }
    }
}
