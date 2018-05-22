/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.mosaidzeno.justjava;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    int quantity = 1;
    int price;
    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

        createOrderSummary();
    }

    /**
     * Calculates the price of the order.
     *
     * @param quantity is the number of cups of coffee ordered
     */
    private int calculatePrice(int quantity,int xprice) {
        price = quantity * xprice;
        return  price;
    }
    public void display(int number) {

        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
    public void displayprice(int number) {
     TextView priceTextView = findViewById(R.id.orderSummaryTextView);
      priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));

    }

    /**
     * This method displays the given text on the screen.
     */

    public  void createOrderSummary(){
        TextView priceTextView = findViewById(R.id.orderSummaryTextView);
        String CName = "Customer Name";
        display(quantity);
        calculatePrice(quantity,10);
        priceTextView.setText(CName + "\n" +"quantity : " + quantity +
                                      "\n" + "Total is : " + price +
                                      "\nThanks You !! ");
    }

    /**
     *  adding button in runtime

     Button myButton = new Button(this);
     myButton.setText("created");

     LinearLayout ll = findViewById(R.id.layout);
     //        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
     ll.addView(myButton);
     */

    public void increment(View view) {

        quantity = quantity + 1;
        display(quantity);
    }

    public void decrement(View view) {

        quantity =quantity - 1;
        display(quantity);
    }
}