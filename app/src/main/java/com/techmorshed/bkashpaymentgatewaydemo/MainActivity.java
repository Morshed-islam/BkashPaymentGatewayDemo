package com.techmorshed.bkashpaymentgatewaydemo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.techmorshed.bkashpaymentgatewaydemo.model.Checkout;

public class MainActivity extends AppCompatActivity {


    private Button buttonCheckOut;
    private EditText amount;
    private RadioButton sale, auth;
    private RadioButton version1_1, version1_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final Context context = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        amount = (EditText) findViewById(R.id.checkout_amount);
        sale = (RadioButton) findViewById(R.id.intent_sale);
        auth = (RadioButton) findViewById(R.id.intent_auth);
        version1_1 =(RadioButton) findViewById(R.id.one_one);
        version1_2 = (RadioButton) findViewById(R.id.one_two);

        sale.setChecked(true);
        version1_1.setChecked(true);

        buttonCheckOut = (Button) findViewById(R.id.buttonUrlCheckout);


        buttonCheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!amount.getText().toString().isEmpty()) {
                    Checkout checkout = new Checkout();
                    checkout.setAmount(amount.getText().toString());
                    if(version1_1.isChecked()==true){
                        checkout.setVersion("one");
                    }
                    else{
                        checkout.setVersion("two");
                    }
                    if (sale.isChecked() == true) {
                        checkout.setIntent("sale");
                    } else {
                        checkout.setIntent("authorization");
                    }
                    Intent intent = new Intent(context, WebViewCheckoutActivity.class);
                    intent.putExtra("values", checkout);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Please enter the field values", Toast.LENGTH_SHORT).show();
                }
            }
        });





    }
}
