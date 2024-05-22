
package com.example.wage4;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText editTextHourlyWage;
    private EditText editTextRegularHours;
    private TextView textViewWeeklyWage;
    private TextView textViewMonthlyWage;
    private TextView textViewYearlyWage;
    private Button buttonCalculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        editTextHourlyWage = findViewById(R.id.editTextHourlyWage);
        editTextRegularHours = findViewById(R.id.editTextRegularHours);
        textViewWeeklyWage = findViewById(R.id.textViewWeeklyWage);
        textViewMonthlyWage = findViewById(R.id.textViewMonthlyWage);
        textViewYearlyWage = findViewById(R.id.textViewYearlyWage);
        buttonCalculate = findViewById(R.id.buttonCalculate);



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateWages();
            }
        });
    }

    private void calculateWages(){
        String hourlyWageStr = editTextHourlyWage.getText().toString();
        String totalHoursStr = editTextRegularHours.getText().toString();

        if (hourlyWageStr.isEmpty() || totalHoursStr.isEmpty()) {
            Toast.makeText(this, "Please enter valid numbers", Toast.LENGTH_SHORT).show();
            return;
        }

        double hourlyWage = Double.parseDouble(hourlyWageStr);
        double totalHours = Double.parseDouble(totalHoursStr);

        double weeklyWage = hourlyWage * totalHours;
        double monthlyWage = (weeklyWage * 52)/12;
        double yearlyWage = weeklyWage * 52;

        textViewWeeklyWage.setText("Weekly Wage: ₱" + String.format("%.2f", weeklyWage));
        textViewMonthlyWage.setText("Monthly Wage: ₱" + String.format("%.2f", monthlyWage));
        textViewYearlyWage.setText("Yearly Wage: ₱" + String.format("%.2f", yearlyWage));
    }
}