package com.ndelifa.javahitungumur;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private EditText txt_nama, edtTanggalLahir;
    private Button btnHitungUmur;
    private TextView txtHasilUmur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inisialisasi komponen
        txt_nama = findViewById(R.id.txt_nama);
        edtTanggalLahir = findViewById(R.id.edtTanggalLahir);
        btnHitungUmur = findViewById(R.id.btnHitungUmur);
        txtHasilUmur = findViewById(R.id.txtHasilUmur);

        btnHitungUmur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hitungUmur();
            }
        });
    }

    private void hitungUmur() {
        String nama = txt_nama.getText().toString();
        String tanggalLahirStr = edtTanggalLahir.getText().toString();

        if (!nama.isEmpty() && !tanggalLahirStr.isEmpty()) {
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                Date tanggalLahir = dateFormat.parse(tanggalLahirStr);
                int umur = hitungUmur(tanggalLahir);
                txtHasilUmur.setText("Halo " + nama + ", umur Anda: " + umur + " tahun");
            } catch (Exception e) {
                txtHasilUmur.setText("Format tanggal salah!");
            }
        } else {
            txtHasilUmur.setText("Isi semua kolom!");
        }
    }

    private int hitungUmur(Date tanggalLahir) {
        Calendar today = Calendar.getInstance();
        Calendar dob = Calendar.getInstance();
        dob.setTime(tanggalLahir);

        int umur = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)) {
            umur--;
        }

        return umur;
    }
}
