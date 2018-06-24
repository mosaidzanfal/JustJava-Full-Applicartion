@@ -9,12 +9,19 @@
package com.example.mosaidzeno.justjava;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

@ -24,14 +31,23 @@ import java.text.NumberFormat;
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity ;
    int price;
    boolean hasWhippedCream;
    boolean hasChecolat;
    CheckBox hasWC ;
    CheckBox HasCheco ;
    String OrderSummary;


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
@ -46,54 +62,94 @@ public class MainActivity extends AppCompatActivity {
     * @param quantity is the number of cups of coffee ordered
     */
    private int calculatePrice(int quantity,int xprice) {
        hasWC = findViewById(R.id.Haswhippedcream);
        HasCheco = findViewById(R.id.chocolate_checkbox);
        hasWhippedCream = hasWC.isChecked();
        hasChecolat = HasCheco.isChecked();

        if (hasWhippedCream){
            xprice = xprice + 1;
        }

        if ((hasChecolat)){
            xprice = xprice + 2;
        }
        price = quantity * xprice;
        return  price;
    }
    public void display(int number) {
    public void displayQuantity(int number) {

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
        hasWC = findViewById(R.id.Haswhippedcream);
        HasCheco = findViewById(R.id.chocolate_checkbox);
        hasWhippedCream = hasWC.isChecked();
        hasChecolat = HasCheco.isChecked();

        TextView priceTextView = findViewById(R.id.orderSummaryTextView);
        String CName = "Customer Name";
        display(quantity);
        calculatePrice(quantity,10);
        priceTextView.setText(CName + "\n" +"quantity : " + quantity +
                                      "\n" + "Total is : " + price +
                                      "\nThanks You !! ");
        EditText UserName  = findViewById(R.id.TxtUserName);

        displayQuantity(quantity);
        calculatePrice(quantity,5);

        OrderSummary = UserName.getText() +"\nWihppedCream : "+ hasWhippedCream +"\nChocolate : " + hasChecolat +
                "\nquantity : " + quantity +
                "\nTotal is : " + price +
                "\nThanks You !! ";
        priceTextView.setText(OrderSummary);
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "This the Coffe Order Summary for : " + UserName.getText());
        intent.putExtra(Intent.EXTRA_TEXT,OrderSummary);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

        priceTextView.setText(OrderSummary);
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
    public void increment(View view){

        if (quantity == 100) {
            // Show an error message as a toast
            Toast.makeText(this, "You cannot have more than 100 coffees", Toast.LENGTH_SHORT).show();
            // Exit this method early because there's nothing left to do
            return;
        }
        quantity = quantity + 1;
        display(quantity);
        displayQuantity(quantity);


    }

    public void decrement(View view) {

        quantity =quantity - 1;
        display(quantity);
        if (quantity == 1) {
            // Show an error message as a toast
            Toast.makeText(this, "You cannot have less than 1 coffee", Toast.LENGTH_SHORT).show();
            // Exit this method early because there's nothing left to do
            return;
        }
        quantity = quantity - 1;
        displayQuantity(quantity);


    }
}
