package com.example.day10;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;
import java.util.Locale;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_layout);

        TextView resultTextView = findViewById(R.id.result_text);
        TextView explanationTextView = findViewById(R.id.explanation_text);

        // Mendapatkan total harga dan penjelasan dari intent
        double totalPrice = getIntent().getDoubleExtra("total_price", 0);
        String explanation = getIntent().getStringExtra("explanation");

        // Menampilkan total harga dan penjelasan
        resultTextView.setText(String.format("Total Price: Rp. %,.0f", totalPrice));
        explanationTextView.setText(explanation);
    }
}
