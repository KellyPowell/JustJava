/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;


import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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

    int numberOfCoffees = 0;
    Toast toast;

    /**
     * This method is called when the + button is clicked.  Changed to allow maximum 5 coffees.
     * If user tries to select > 5, display toast
     * If user taps valid selection, cancel any existing toast
     */
    public void increment(View view) {
        if (numberOfCoffees <= 4) {
            try {
                toast.cancel();
            }
            catch (Exception e){}

            numberOfCoffees += 1;
            display(numberOfCoffees);

        } else {
            toast = Toast.makeText(getApplicationContext(), "Max 5 coffees per order",
                    Toast.LENGTH_LONG);
            toast.show();
        }
    }

    /**
     * This method is called when the - button is clicked. Changed to not allow negative numbers.
     * * If user tries to select < 0, display toast
     * If user taps valid selection, cancel any existing toast
     */
    public void decrement(View view) {
        if (numberOfCoffees >= 1) {
            try {
                toast.cancel();
            }
            catch (Exception e){}
            numberOfCoffees -= 1;
            display(numberOfCoffees);

        } else {
            toast = Toast.makeText(getApplicationContext(), "Negative number not allowed",
                    Toast.LENGTH_LONG);
            toast.show();
        }
    }

    /**
     * This method is called when the order button is clicked. Updates the total price shown.
     */
    public void submitOrder(View view) {
        String priceMessage = "Total: $" + (numberOfCoffees * 5) + "\nThank you!";
        displayMessage(priceMessage);
    }

    /*
     * This method displays the given quantity on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_number_text_view);
        quantityTextView.setText("" + number);
    }

    /*
    This method displays the given quantity value on the screen.
     */
    private void displayPrice(int totalPrice) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(totalPrice));
    }

    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }


}