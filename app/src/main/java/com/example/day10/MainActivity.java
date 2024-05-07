package com.example.day10;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final double DISCOUNT_THRESHOLD_1 = 10000000;
    private static final double DISCOUNT_THRESHOLD_2 = 5000000;
    private static final double DISCOUNT_RATE_1 = 0.07;
    private static final double DISCOUNT_RATE_2 = 0.05;

    private double pajeroPrice = 2400000;
    private double alphardPrice = 1550000;
    private double innovaPrice = 850000;
    private double brioPrice = 550000;
    private double insideCityPrice = 135000;
    private double outsideCityPrice = 250000;

    private EditText daysInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        daysInput = findViewById(R.id.days_input);

        Button calculateButton = findViewById(R.id.calculate_button);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Hitung total harga
                double totalPrice = calculateTotalPrice();

                // Hitung jumlah hari sewa
                int days = Integer.parseInt(daysInput.getText().toString());

                // Hitung diskon
                double discount = calculateDiscount(totalPrice);

                // Hitung total setelah diskon
                double totalPriceAfterDiscount = totalPrice - discount;

                // Buat teks penjelasan
                String explanation = generateExplanation(totalPrice, days, discount, totalPriceAfterDiscount);

                // Alirkan ke halaman hasil dengan total harga dan penjelasan sebagai data tambahan
                Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                intent.putExtra("total_price", totalPrice);
                intent.putExtra("explanation", explanation);
                startActivity(intent);
            }
        });
    }

    private double calculateTotalPrice() {
        int days = Integer.parseInt(daysInput.getText().toString());
        double totalPrice = 0;

        totalPrice += calculateItemTotal(pajeroPrice, R.id.pajero_checkbox);
        totalPrice += calculateItemTotal(alphardPrice, R.id.alphard_checkbox);
        totalPrice += calculateItemTotal(innovaPrice, R.id.innova_checkbox);
        totalPrice += calculateItemTotal(brioPrice, R.id.brio_checkbox);
        totalPrice += calculateItemTotal(insideCityPrice, R.id.inside_city_checkbox);
        totalPrice += calculateItemTotal(outsideCityPrice, R.id.outside_city_checkbox);

        return totalPrice * days;
    }

    private double calculateItemTotal(double itemPrice, int checkBoxId) {
        CheckBox checkBox = findViewById(checkBoxId);
        if (checkBox.isChecked()) {
            return itemPrice;
        }
        return 0;
    }

    private double calculateDiscount(double totalPrice) {
        if (totalPrice >= DISCOUNT_THRESHOLD_1) {
            return totalPrice * DISCOUNT_RATE_1;
        } else if (totalPrice >= DISCOUNT_THRESHOLD_2) {
            return totalPrice * DISCOUNT_RATE_2;
        } else {
            return 0;
        }
    }

    private String generateExplanation(double totalPrice, int days, double discount, double totalPriceAfterDiscount) {
        StringBuilder explanationBuilder = new StringBuilder();
        explanationBuilder.append("Number of days: ").append(days).append("\n\n");

        explanationBuilder.append("Discount:\n");
        if (totalPrice >= DISCOUNT_THRESHOLD_1) {
            explanationBuilder.append("- 7%\n");
        } else if (totalPrice >= DISCOUNT_THRESHOLD_2) {
            explanationBuilder.append("- 5%\n");
        } else {
            explanationBuilder.append("- No discount\n");
        }

        explanationBuilder.append("\nTotal Price before discount: Rp. ").append(NumberFormat.getInstance().format(totalPrice)).append("\n");
        explanationBuilder.append("Total Discount: Rp. ").append(NumberFormat.getInstance().format(discount)).append("\n");
        explanationBuilder.append("Total Price after discount: Rp. ").append(NumberFormat.getInstance().format(totalPriceAfterDiscount));

        return explanationBuilder.toString();
    }
}



