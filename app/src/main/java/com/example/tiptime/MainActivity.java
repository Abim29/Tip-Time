package com.example.tiptime;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.example.tiptime.databinding.ActivityMainBinding;

import java.text.NumberFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        displayTip(0.0);

        binding.calculateButton.setOnClickListener(v -> calculateTip());

        binding.costOfServiceEditText.setOnKeyListener((v, keyCode, event) -> handleKeyEvent(v, keyCode));
    }

    private void calculateTip() {
        String costTextField = binding.costOfServiceEditText.getText().toString();
        try{
            double cost = Double.parseDouble(costTextField);
            double tipPercentage;
            int selectedTipId = binding.tipOptions.getCheckedRadioButtonId();
            if (selectedTipId == R.id.option_twenty_percent) {
                tipPercentage = 0.20;
            } else if (selectedTipId == R.id.option_eighteen_percent) {
                tipPercentage = 0.18;
            } else {
                tipPercentage = 0.15;
            }
            double tip = tipPercentage * cost;
            boolean isRoundUp = binding.roundUpSwitch.isChecked();
            if(isRoundUp) {
                tip = Math.ceil(tip);
            }
            displayTip(tip);
        } catch (NumberFormatException e) {
            Toast.makeText(getApplicationContext(), "Please enter the cost!", Toast.LENGTH_SHORT).show();
            displayTip(0.0);
        }
    }

    private void displayTip(double tip) {
        String formattedTip = NumberFormat.getCurrencyInstance(new Locale("in", "Id")).format(tip);
        binding.tipResult.setText(getString(R.string.tip_amount, formattedTip));
    }

    private Boolean handleKeyEvent(View v, int keyCode) {
        if(keyCode == KeyEvent.KEYCODE_ENTER) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
            return true;
        }
        return false;
    }
}