package jp.suntech.c21009.bmicalculatorc009;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButtonListener listener = new ButtonListener();

        Button btConfirm = findViewById(R.id.btCalculate);
        btConfirm.setOnClickListener(listener);

        Button btClear = findViewById(R.id.btClear);
        btClear.setOnClickListener(listener);
    }

    private class ButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            EditText age = findViewById(R.id.etAge);
            EditText height = findViewById(R.id.etHeight);
            EditText weight = findViewById(R.id.etWeight);

            String SAge = age.getText().toString();
            String SHeight = height.getText().toString();
            String SWeight = weight.getText().toString();

            Float Age = Float.parseFloat(SAge);
            Float Height = Float.parseFloat(SHeight);
            Float Weight = Float.parseFloat(SWeight);
            Float BMI, sta;

            TextView resulttext = findViewById(R.id.tvResultText);
            TextView result = findViewById(R.id.tvResult);
            TextView suitabletext = findViewById(R.id.tvSuitableText);
            TextView suitable = findViewById(R.id.tvSuitable);
            TextView suitableunit = findViewById(R.id.tvSuitableUnit);

            int id = view.getId();

            switch (id) {
                case R.id.btCalculate:
                    resulttext.setText("あなたの肥満度は");
                    suitabletext.setText("あなたの適正体重は");
                    suitableunit.setText("kg");

                    if (Age >= 16) {
                        BMI = Weight / ((Height / 100) * (Height / 100));

                        if (BMI < 18.5) {
                            result.setText("低体重");
                            result.setTextColor(Color.BLUE);
                        } else if (BMI < 25.0) {
                            result.setText("普通体重");
                            result.setTextColor(Color.BLACK);
                        } else if (BMI < 30.0) {
                            result.setText("肥満(1度)");
                            result.setTextColor(Color.RED);
                        } else if (BMI < 35.0) {
                            result.setText("肥満(2度)");
                            result.setTextColor(Color.RED);
                        } else if (BMI < 40.0) {
                            result.setText("肥満(3度)");
                            result.setTextColor(Color.RED);
                        } else {
                            result.setText("肥満(4度)");
                            result.setTextColor(Color.RED);
                        }

                        sta = 22 * ((Height / 100) * (Height / 100));
                        suitable.setText(String.format("%.1f", sta));
                        break;
                    } else if (Age < 6) {
                        BMI = ((Weight * 1000) / (Height * Height)) * 10;

                        if (Age < 1) {
                            if (BMI < 14.5) {
                                result.setText("低体重");
                                result.setTextColor(Color.BLUE);
                            } else if (BMI < 20.0) {
                                result.setText("普通体重");
                                result.setTextColor(Color.BLACK);
                            } else {
                                result.setText("肥満");
                                result.setTextColor(Color.RED);
                            }
                            sta = (17 * ((Height * Height) / 10)) / 1000;
                        } else if (Age < 2) {
                            if (BMI < 14.0) {
                                result.setText("低体重");
                                result.setTextColor(Color.BLUE);
                            } else if (BMI < 19.0) {
                                result.setText("普通体重");
                                result.setTextColor(Color.BLACK);
                            } else {
                                result.setText("肥満");
                                result.setTextColor(Color.RED);
                            }
                            sta = (16 * ((Height * Height) / 10)) / 1000;
                        } else if (Age < 4) {
                            if (BMI < 13.5) {
                                result.setText("低体重");
                                result.setTextColor(Color.BLUE);
                            } else if (BMI < 18.0) {
                                result.setText("普通体重");
                                result.setTextColor(Color.BLACK);
                            } else {
                                result.setText("肥満");
                                result.setTextColor(Color.RED);
                            }
                            sta = (15 * ((Height * Height) / 10)) / 1000;
                        } else {
                            if (BMI < 13.0) {
                                result.setText("低体重");
                                result.setTextColor(Color.BLUE);
                            } else if (BMI < 18.5) {
                                result.setText("普通体重");
                                result.setTextColor(Color.BLACK);
                            } else {
                                result.setText("肥満");
                                result.setTextColor(Color.RED);
                            }
                            sta = (16 * ((Height * Height) / 10)) / 1000;
                        }

                        suitable.setText(String.format("%.2f", sta));

                        OrderConfirmDialogFragment dialogFragment = new OrderConfirmDialogFragment();
                        Bundle args = new Bundle();
                        args.putString("Method","カウプ");
                        dialogFragment.setArguments(args);
                        dialogFragment.show(getSupportFragmentManager(), "OrderConfirmDialogFragment");
                        break;
                    } else {
                        BMI = (Weight / ((Height / 100) * (Height / 100) * (Height / 100))) * 10;

                        if (BMI < 100.0) {
                            result.setText("低体重");
                            result.setTextColor(Color.BLUE);
                        } else if (BMI < 160.0) {
                            result.setText("普通体重");
                            result.setTextColor(Color.BLACK);
                        } else {
                            result.setText("肥満");
                            result.setTextColor(Color.RED);
                        }

                        sta = 130 * (((Height / 100) * (Height / 100) * (Height / 100)) / 10);
                        suitable.setText(String.format("%.1f", sta));

                        OrderConfirmDialogFragment dialogFragment = new OrderConfirmDialogFragment();
                        Bundle args = new Bundle();
                        args.putString("Method","ローレル");
                        dialogFragment.setArguments(args);
                        dialogFragment.show(getSupportFragmentManager(), "OrderConfirmDialogFragment");
                        break;
                    }

                case R.id.btClear:
                    age.setText("");
                    height.setText("");
                    weight.setText("");
                    resulttext.setText("");
                    result.setText("");
                    suitabletext.setText("");
                    suitable.setText("");
                    suitableunit.setText("");
                    break;
            }
        }
    }
}