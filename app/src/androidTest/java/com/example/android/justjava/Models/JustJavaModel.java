package com.example.android.justjava.Models;

import android.content.Intent;
import android.support.test.espresso.ViewInteraction;

import com.example.android.justjava.Helpers.Matchers.ToastMatcher;
import com.example.android.justjava.R;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static android.support.test.espresso.matcher.ViewMatchers.isChecked;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isNotChecked;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.example.android.justjava.TestData.TestStrings.testName;

public class JustJavaModel {

    private ViewInteraction nameEditText = onView(withId(R.id.name_field));
    private ViewInteraction toppingsHeader = onView(withId(R.id.toppings));
    private ViewInteraction creamCheckBox = onView(withId(R.id.whipped_cream_checkbox));
    private ViewInteraction chocolateCheckBox = onView(withId(R.id.chocolate_checkbox));
    private ViewInteraction quantityHeader = onView(withId(R.id.quantity));
    private ViewInteraction minusButton = onView(withId(R.id.minusButton));
    private ViewInteraction quantityDisplay = onView(withId(R.id.quantity_number_text_view));
    private ViewInteraction plusButton = onView(withId(R.id.plusButton));
    private ViewInteraction summaryHeader = onView(withId(R.id.orderSummaryHeader));
    private ViewInteraction totalAmount = onView(withId(R.id.order_summary_text_view));
    private ViewInteraction orderButton = onView(withId(R.id.orderButton));

    // add name testName to the name textEdit field
    public void addNameToEditText() {
        nameEditText.perform(replaceText(testName));
    }

    // verify correct name was added to the field
    public void verifyNameAdded() {
        nameEditText.check(matches(withText(testName)));
    }

    // add name testName to field and verify
    public void addNameAndVerify() {
        addNameToEditText();
        verifyNameAdded();
    }

    // add a name greater than character limit set in activity_main.xml
    public void exceedCharLimit() {
        nameEditText.perform(replaceText("12345678901234567890123456789012345678901234567890asdf;lkjasdfl;kjasdf"));
    }

    // verify that only the correct characters were added(did not exceed char limit)
    public void verifyCharLimit() {
        nameEditText.check((matches(withText("123456789012345678901234567890"))));
    }

    // attempt to add long name, verify only max chars added
    public void exceedCharLimitAndVerify() {
        exceedCharLimit();
        verifyCharLimit();
    }


    public void addNameToEditText(String name) {
        nameEditText.perform(replaceText(name));
    }

    // check the whipped cream checkbox
    public void clickWhippedCreamCheckbox() {
        creamCheckBox.perform(click());
    }

    // check the chocolate checkbox
    public void clickChocolateCheckbox() {
        chocolateCheckBox.perform(click());

    }

    // verify chocolate checkbox has been checked
    public void verifyChocCheckbox() {
        clickChocolateCheckbox();
        chocolateCheckBox.check(matches(isChecked()));
    }

    // verify cream checkbox has been checked
    public void verifyCreamCheckbox() {
        clickWhippedCreamCheckbox();
        creamCheckBox.check(matches(isChecked()));
    }

    // verify both boxes can be checked
    public void verifyBothChecked() {
        clickChocolateCheckbox();
        clickWhippedCreamCheckbox();
        creamCheckBox.check(matches(isChecked()));
        chocolateCheckBox.check(matches(isChecked()));

    }

    // verify cream checkbox has been UNchecked
    public void verifyCreamUnchecked() {
        clickWhippedCreamCheckbox();
        clickWhippedCreamCheckbox();
        creamCheckBox.check(matches(isNotChecked()));
    }

    // verify chocolate checkbox has been UNchecked
    public void verifyChocolateUnchecked() {
        clickChocolateCheckbox();
        clickChocolateCheckbox();
        chocolateCheckBox.check(matches(isNotChecked()));
    }

    // verify BOTH boxes checked, unchecked, remain unchecked
    public void verifyBothUnchecked() {
        clickWhippedCreamCheckbox();
        clickChocolateCheckbox();
        clickWhippedCreamCheckbox();
        clickChocolateCheckbox();
        chocolateCheckBox.check(matches(isNotChecked()));
        creamCheckBox.check(matches(isNotChecked()));
    }

    // click minus button
    public void clickMinusButton() {
        minusButton.perform(click());
    }

    // verify that count cannot be less than 1, verify toast message
    public void verifyMinCount() {
        closeSoftKeyboard();
        clickMinusButton();
        clickMinusButton();
        clickMinusButton();
        clickMinusButton();
        onView(withText(R.string.min_count_toast)).inRoot(new ToastMatcher()).check(matches(withText("You cannot have less than 1 coffee")));
        quantityDisplay.check(matches(withText("1")));
    }


    // click plus button
    public void clickPlusButton() {
        plusButton.perform(click());
    }

    // check that count has increased after clicking plus button
    public void verifyIncreasedCount() {
        clickPlusButton();
        quantityDisplay.check(matches(withText("2")));
    }

    // check that count does not exceed 5, verify toast message
    public void verifyMaxCount() {
        closeSoftKeyboard();
        clickPlusButton();
        clickPlusButton();
        clickPlusButton();
        clickPlusButton();
        clickPlusButton();
        clickPlusButton();
        onView((withText(R.string.max_count_toast))).inRoot(new ToastMatcher()).check(matches(withText("Max 5 coffees per order")));
        quantityDisplay.check(matches(withText("5")));
    }

    // verify that all expected UI elements are on the screen
    public void verifyAllDefaultElements() {

        closeSoftKeyboard();
        nameEditText.check(matches(isDisplayed()));
        toppingsHeader.check(matches(isDisplayed()));
        creamCheckBox.check(matches(isDisplayed()));
        chocolateCheckBox.check(matches(isDisplayed()));
        quantityHeader.check(matches(isDisplayed()));
        minusButton.check(matches(isDisplayed()));
        quantityDisplay.check(matches(isDisplayed()));
        plusButton.check(matches(isDisplayed()));
        summaryHeader.check(matches(isDisplayed()));
        orderButton.check(matches(isDisplayed()));
        totalAmount.check(matches(isDisplayed()));
    }

    // clicks the order button
    public void clickOrderButton() {
        orderButton.perform(click());
    }

/*    //check email intent
    public void testEmailIntent() {
        intended(hasAction(Intent.ACTION_SEND));
    }
*/

    // add name, check chocolate box, increase quantity to 2, submit order
    public void submitOrder1() {
        addNameToEditText("Kelly");
        closeSoftKeyboard();
        clickChocolateCheckbox();
        clickPlusButton();          // how to verify intent and its contents?
        clickOrderButton();
        //testEmailIntent();
        totalAmount.check(matches(withText("Name: Kelly\nAdd whipped" +
                " cream? false\nAdd chocolate? true\nQuantity: 2\nTotal: $14\nThank you!")));

    }


    // add long name, check chocolate box, check cream box,  increase quantity to 6, decrease to 4, submit order
    public void submitOrder2() {
        exceedCharLimit();
        closeSoftKeyboard();
        clickWhippedCreamCheckbox();
        clickPlusButton();          // how to verify intent and its contents?
        clickPlusButton();
        clickPlusButton();
        clickPlusButton();
        clickPlusButton();
        onView((withText(R.string.max_count_toast))).inRoot(new ToastMatcher()).check(matches(withText("Max 5 coffees per order")));
        clickMinusButton();
        clickOrderButton();
        //testEmailIntent();
        totalAmount.check(matches(withText("Name: 123456789012345678901234567890\nAdd whipped" +
                " cream? true\nAdd chocolate? false\nQuantity: 4\nTotal: $24\nThank you!")));

    }

}
