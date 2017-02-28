package id.blobob.android.kalkulator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private float number1 = 0;
    private float number2 = 0;
    public enum Operator {
        NULL, PLUS, MIN, MUL, DIV
    }
    public Operator op = Operator.NULL;
    private boolean isComma = false;
    private double power = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayNumber(0);
    }

    public void displayNumber (float number) {
        TextView textview = (TextView) findViewById(R.id.display);
        textview.setText("" + number);
    }

    public void clear (View view) {
        number1 = 0;
        number2 = 0;
        op = Operator.NULL;
        power = -1;
        isComma = false;
        displayNumber(number1);
    }

    public void updateNumber (View view) {
        Button btn = (Button) view;
        float numTemp = Float.parseFloat(btn.getText().toString());
        if (isComma) {
            number1 = number1 + (numTemp * ((float) Math.pow(10, power)));
            if ((numTemp == 0) && (power != -1)) {
                TextView textview = (TextView) findViewById(R.id.display);
                textview.setText("" + number1 + "0");
            } else {
                displayNumber(number1);
            }
            power--;
        } else {
            number1 = (number1 * 10) + numTemp;
            displayNumber(number1);
        }
    }

    public void putComma (View view) {
        isComma = true;
    }

    public void equal (View view) {
        switch (op) {
            case PLUS : {
                number2 = number2 + number1;
                displayNumber(number2);
                break;
            }
            case MIN : {
                number2 = number2 - number1;
                displayNumber(number2);
                break;
            }
            case MUL : {
                number2 = number2 * number1;
                displayNumber(number2);
                break;
            }
            case DIV : {
                number2 = number2 / number1;
                displayNumber(number2);
                break;
            }
            default : {
                displayNumber(number1);
            }
        }
    }

    public void operate (View view) {
        String operator = ((Button) view).getText().toString();
        if (op != Operator.NULL) {
            switch (op) {
                case PLUS : {
                    number2 = number2 + number1;
                    displayNumber(number2);
                    break;
                }
                case MIN : {
                    number2 = number2 - number1;
                    displayNumber(number2);
                    break;
                }
                case MUL : {
                    number2 = number2 * number1;
                    displayNumber(number2);
                    break;
                }
                case DIV : {
                    number2 = number2 / number1;
                    displayNumber(number2);
                    break;
                }
            }
        } else {
            number2 = number1;
        }
        switch (operator) {
            case "+" : {
                op = Operator.PLUS;
                break;
            }
            case "-" : {
                op = Operator.MIN;
                break;
            }
            case "x" : {
                op = Operator.MUL;
                break;
            }
            case "/" : {
                op = Operator.DIV;
                break;
            }
        }
        number1 = 0;
        isComma = false;
        power = -1;
    }
}
