/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match the package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    private static final String THANK_YOU = "\nThank you!";
    private static final String TOTAL_$ = "\nTotal: $";
    private static final String QUANTITY = "\nQuantity: ";
    private static final String ADD_CHOCOLATE = "\nAdd chocolate? ";
    private static final String ADD_WHIPPED_CREAM = "\nAdd whipped cream? ";
    private static final String NAME = "Name: ";
    private static final String JUST_JAVA_ORDER_FOR = "Just Java order for ";
    private static final String YOU_CANNOT_HAVE_LESS_THAN_1_COFFEE = "You cannot have less than 1 coffee";
    private static final String MAX_5_COFFEES_PER_ORDER = "Max 5 coffees per order";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private int numberOfCoffees = 1;
    //String name;
    private Toast toast;
    //boolean hasWhippedCream;
    //boolean hasChocolate;

    /**
     * This method is called when the + button is clicked.  Changed to allow maximum 5 coffees.
     * If user tries to select > 5, display toast
     * If user taps valid selection, cancel any existing toast
     */
    public void increment(View view) {
        if (numberOfCoffees <= 4) {
            if (toast != null) {
                toast.cancel();
            }

            numberOfCoffees += 1;
            displayQuantity(numberOfCoffees);

        } else {
            toast = Toast.makeText(getApplicationContext(), MAX_5_COFFEES_PER_ORDER,
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
        if (numberOfCoffees > 1) {
            if (toast != null) {
                toast.cancel();
            }
            numberOfCoffees -= 1;
            displayQuantity(numberOfCoffees);

        } else {
            toast = Toast.makeText(getApplicationContext(), YOU_CANNOT_HAVE_LESS_THAN_1_COFFEE,
                    Toast.LENGTH_LONG);
            toast.show();
        }
    }

    /**
     * This method is called when the order button is clicked. Updates the total price shown.
     */
    public void submitOrder(View view) {

        // get user's name from EditText field
        EditText nameField = findViewById(R.id.name_field);
        String name = nameField.getText().toString();

        // figure out if user wants whipped cream
        CheckBox whippedCreamCheckBox = findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();
        // Log.v("MainActivity", "Has whipped cream?" + hasWhippedCream);

        //figure out if user wants chocolate
        CheckBox chocolateCheckBox = findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheckBox.isChecked();
        // Log.v("MainActivity", "Has chocolate?" + hasChocolate);

        int totalPrice = calculatePrice(hasWhippedCream, hasChocolate);
        String summary = createOrderSummary(name, totalPrice, hasWhippedCream, hasChocolate);
        //String priceMessage = "Total: $" + totalPrice + "\nThank you!";

        displayMessage(summary);  //no need to display summary in the app, we're emailing instead

/*
        Intent intent = new Intent(Intent.ACTION_SENDTO);  // creates new object instance called intent
        intent.setData(Uri.parse("mailto:kpmbconnect@gmail.com")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, JUST_JAVA_ORDER_FOR + name);  // populate our subject line
        intent.putExtra(Intent.EXTRA_TEXT, summary);  // put our summary into the body of the email

        if (intent.resolveActivity(getPackageManager()) != null) {  // make sure we don't crash if no email app on device
            startActivity(intent);
        }
        */
    }

    /**
     * @param nameEntered            is the user's name entered on the EditText field at the top of the layout
     * @param priceAmount     is the total price of the order
     * @param addWhippedCream is whether or not the user wants whipped cream
     * @param addChocolate    is whether or not user wants chocolate
     * @return text summary
     */

    private String createOrderSummary(String nameEntered, int priceAmount, boolean addWhippedCream, boolean addChocolate) {
        String orderSummary = NAME + nameEntered;
        orderSummary += ADD_WHIPPED_CREAM + addWhippedCream;
        orderSummary += ADD_CHOCOLATE + addChocolate;
        orderSummary += QUANTITY + numberOfCoffees;
        orderSummary += TOTAL_$ + priceAmount;
        orderSummary += THANK_YOU;
        return orderSummary;
    }

    private int calculatePrice(boolean addWhippedCream, boolean addChocolate) {
        // Price of 1 cup of coffee
        int basePrice = 5;
        if (addWhippedCream) {
            basePrice += 1;
        }
        if (addChocolate) {
            basePrice += 2;
        }
        return numberOfCoffees * basePrice;
    }


    /*
     * This method displays the given quantity on the screen.
     */
    private void displayQuantity(int howMany) {
        TextView quantityTextView = findViewById(R.id.quantity_number_text_view);
        quantityTextView.setText("" + howMany);
    }


    //This method displays the given quantity value on the screen.

    private void displayPrice(int totalPrice) {
        TextView priceTextView = findViewById(R.id.order_summary_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(totalPrice));
    }


   private void displayMessage(String message) {
       TextView priceTextView = findViewById(R.id.order_summary_text_view);
       priceTextView.setText(message);
   }


}