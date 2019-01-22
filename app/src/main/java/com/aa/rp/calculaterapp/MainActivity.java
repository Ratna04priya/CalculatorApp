package com.aa.rp.calculaterapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText result;
    private EditText newno;
    private TextView op;

    private static final String STATE_PENDING_OP = "PendingOp";
    private static final String STATE_OP1 = "Op1";

    // Vairables to hold operands
    private Double op1 = null;
    private Double op2 = null;
    private String pendingop = "=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = (EditText) findViewById(R.id.result);
        newno = (EditText) findViewById(R.id.newNo);
        op = (TextView) findViewById(R.id.operation);

        Button button0 = (Button) findViewById(R.id.button0);
        Button button1 = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);
        Button button4 = (Button) findViewById(R.id.button4);
        Button button5 = (Button) findViewById(R.id.button5);
        Button button6 = (Button) findViewById(R.id.button6);
        Button button7 = (Button) findViewById(R.id.button7);
        Button button8 = (Button) findViewById(R.id.button8);
        Button button9 = (Button) findViewById(R.id.button9);
        Button buttond = (Button) findViewById(R.id.buttonD);
        Button buttonm = (Button) findViewById(R.id.buttonM);
        Button buttona = (Button) findViewById(R.id.buttonA);
        Button buttons = (Button) findViewById(R.id.buttonS);
        Button buttone = (Button) findViewById(R.id.buttonE);
        Button buttonc = (Button) findViewById(R.id.buttonC);
        Button buttondot = (Button) findViewById(R.id.buttonDot);
        Button buttonneg = (Button) findViewById(R.id.buttonNeg);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button) v;
                newno.append(b.getText().toString());
            }
        };
        button0.setOnClickListener(listener);
        button1.setOnClickListener(listener);
        button2.setOnClickListener(listener);
        button3.setOnClickListener(listener);
        button4.setOnClickListener(listener);
        button5.setOnClickListener(listener);
        button6.setOnClickListener(listener);
        button7.setOnClickListener(listener);
        button8.setOnClickListener(listener);
        button9.setOnClickListener(listener);
        buttondot.setOnClickListener(listener);


        buttonneg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = newno.getText().toString();
                if (value.length() == 0){
                    newno.setText("-");
                }
                else{
                    try{
                        Double doublevalue =  Double.valueOf(value);
                        doublevalue *= (-1);
                        newno.setText(doublevalue.toString());
                    } catch (NumberFormatException e){
                        //new number was "-" or ".", then clear it
                        newno.setText("");
                    }
                }

            }
        });

        buttonc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 op1 = null;
                 op2 = null;
                 pendingop = "=";
                 result.setText("");
                 newno.setText("");
                 op.setText("");


            }
        });


        View.OnClickListener opListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Button b = (Button) v;
                String operation = b.getText().toString();
                String value = newno.getText().toString();
                try{
                    Double doublevalue = Double.valueOf(value);
                    performOperation(doublevalue,operation);
                }catch (NumberFormatException e){
                    newno.setText("");

                }
                pendingop = operation;
                op.setText(pendingop);
            }
        };

        buttone.setOnClickListener(opListener);
        buttond.setOnClickListener(opListener);
        buttonm.setOnClickListener(opListener);
        buttona.setOnClickListener(opListener);
        buttons.setOnClickListener(opListener);


    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {

        super.onRestoreInstanceState(savedInstanceState);

        pendingop = savedInstanceState.getString(STATE_PENDING_OP);
        op1 = savedInstanceState.getDouble(STATE_OP1);
        op.setText(pendingop);
    }



    @Override
    protected void onSaveInstanceState(Bundle outState) {

        outState.putString(STATE_PENDING_OP, pendingop);
        if (op1 != null){
            outState.putDouble(STATE_OP1,op1);
        }
        super.onSaveInstanceState(outState);

    }



    private void performOperation(Double value, String  operation) {
       if (null == op1) {

       op1 = value;
       }
       else {
           op2 = value;

           if(pendingop.equals("=")){
               pendingop = operation;
           }
           switch (pendingop){
               case "=":
                   op1 = op2;
                   break;
               case "/":
                   if(op2 == 0){
                       op1 = 0.0;
                   }
                   else{
                       op1 /=op2;
                   }
                   break;
               case "*":
                   op1 *=op2;
                   break;
               case "+":
                   op1 +=op2;
                   break;
               case "-":
                   op1 -=op2;
                   break;
           }


       }

       result.setText(op1.toString());
       newno.setText("");

    }
}
