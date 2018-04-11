package ems.vtvpmc.procentu_skaiciuokle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    private EditText amountEditText;
    private TextView amountTextView;
    private TextView percentTextView;
    private SeekBar percentSeekBar;
    private TextView textViewPercent;
    private TextView textViewTotal;

    private static final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
    private static final NumberFormat percentFormat = NumberFormat.getPercentInstance();

    private double billAmount = 0.0;
    private double tipPercente = 0.25;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amountEditText = (EditText) findViewById(R.id.amountEditText);
        amountTextView = (TextView) findViewById(R.id.amountTextView);
        percentTextView = (TextView) findViewById(R.id.percentTextView);
        percentSeekBar = (SeekBar) findViewById(R.id.percentSeekBar);
        textViewPercent = (TextView) findViewById(R.id.textViewPercent);
        textViewTotal = (TextView) findViewById(R.id.textViewTotal);

        amountEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try {
                    billAmount = Double.parseDouble(charSequence.toString()) / 100.0;
                    amountTextView.setText(currencyFormat.format(billAmount));
                } catch (NumberFormatException ex) {
                    amountTextView.setText("");
                    billAmount = 0.0;
                }

                calculateTipAndTotalAmount();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        percentSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                tipPercente = progress / 100.0;
                calculateTipAndTotalAmount();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void calculateTipAndTotalAmount() {
        double tipAmount = billAmount * tipPercente;
        double totalAmount = billAmount + tipAmount;

        textViewPercent.setText(currencyFormat.format(tipAmount));
        textViewTotal.setText(currencyFormat.format(totalAmount));
        percentTextView.setText(percentFormat.format(tipPercente));
    }
}
