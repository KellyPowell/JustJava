package com.example.android.justjava.Models

import android.support.test.espresso.ViewInteraction

import com.example.android.justjava.Helpers.Matchers.ToastMatcher
import com.example.android.justjava.R

import android.support.test.espresso.Espresso.closeSoftKeyboard
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.replaceText
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isChecked
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.isNotChecked
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import com.example.android.justjava.TestData.TestStrings.firstNameOnly
import com.example.android.justjava.TestData.TestStrings.longName
import com.example.android.justjava.TestData.TestStrings.order1
import com.example.android.justjava.TestData.TestStrings.order2
import com.example.android.justjava.TestData.TestStrings.shortenedName
import com.example.android.justjava.TestData.TestStrings.testName
import org.hamcrest.Matchers.containsString

class JustJavaModel {
    private val nameEditText = onView(withId(R.id.name_field))
    private val toppingsHeader = onView(withId(R.id.toppings))
    private val creamCheckBox = onView(withId(R.id.whipped_cream_checkbox))
    private val chocolateCheckBox = onView(withId(R.id.chocolate_checkbox))
    private val quantityHeader = onView(withId(R.id.quantity))
    private val minusButton = onView(withId(R.id.minusButton))
    private val quantityDisplay = onView(withId(R.id.quantity_number_text_view))
    private val plusButton = onView(withId(R.id.plusButton))
    private val summaryHeader = onView(withId(R.id.orderSummaryHeader))
    private val totalAmount = onView(withId(R.id.order_summary_text_view))
    private val orderButton = onView(withId(R.id.orderButton))

    // add name testName to the name textEdit field
    private fun addNameToEditText() {
        nameEditText.perform(replaceText(testName))
    }

    // verify correct name was added to the field
    private fun verifyNameAdded() {
        nameEditText.check(matches(withText(testName)))
    }

    // add name testName to field and verify
    fun addNameAndVerify() {
        addNameToEditText()
        verifyNameAdded()
    }

    // add a name greater than character limit set in activity_main.xml
    private fun exceedCharLimit() {
        nameEditText.perform(replaceText(longName))
    }

    // verify that only the correct characters were added(did not exceed char limit)
    private fun verifyCharLimit() {
        nameEditText.check(matches(withText(shortenedName)))
    }

    // attempt to add long name, verify only max chars added
    fun exceedCharLimitAndVerify() {
        exceedCharLimit()
        verifyCharLimit()
    }


    private fun addNameToEditText(name: String) {
        nameEditText.perform(replaceText(name))
    }

    // check the whipped cream checkbox
    private fun clickWhippedCreamCheckbox() {
        creamCheckBox.perform(click())
    }

    // check the chocolate checkbox
    fun clickChocolateCheckbox() {
        chocolateCheckBox.perform(click())

    }

    // verify cream checkbox has been checked
    fun verifyCreamCheckbox() {
        clickWhippedCreamCheckbox()
        creamCheckBox.check(matches(isChecked()))
    }

    // verify both boxes can be checked
    fun verifyBothChecked() {
        clickChocolateCheckbox()
        clickWhippedCreamCheckbox()
        creamCheckBox.check(matches(isChecked()))
        chocolateCheckBox.check(matches(isChecked()))

    }

    // verify BOTH boxes checked, unchecked, remain unchecked
    fun verifyBothUnchecked() {
        clickWhippedCreamCheckbox()
        clickChocolateCheckbox()
        clickWhippedCreamCheckbox()
        clickChocolateCheckbox()
        chocolateCheckBox.check(matches(isNotChecked()))
        creamCheckBox.check(matches(isNotChecked()))
    }

    // click minus button
    private fun clickMinusButton() {
        minusButton.perform(click())
    }

    // verify that count cannot be less than 1, verify toast message
    fun verifyMinCount() {
        closeSoftKeyboard()
        clickMinusButton()
        clickMinusButton()
        clickMinusButton()
        clickMinusButton()
        onView(withText(R.string.min_count_toast)).inRoot(ToastMatcher()).check(matches(withText(YOU_CANNOT_HAVE_LESS_THAN_1_COFFEE)))
        quantityDisplay.check(matches(withText("1")))
    }


    // click plus button
    private fun clickPlusButton() {
        plusButton.perform(click())
    }

    // check that count has increased after clicking plus button
    fun verifyIncreasedCount() {
        clickPlusButton()
        quantityDisplay.check(matches(withText("2")))
    }

    // check that count does not exceed 5, verify toast message
    fun verifyMaxCount() {
        closeSoftKeyboard()
        clickPlusButton()
        clickPlusButton()
        clickPlusButton()
        clickPlusButton()
        clickPlusButton()
        clickPlusButton()
        onView(withText(R.string.max_count_toast)).inRoot(ToastMatcher()).check(matches(withText(MAX_5_COFFEES_PER_ORDER)))
        quantityDisplay.check(matches(withText("5")))
    }

    // verify that all expected UI elements are on the screen
    fun verifyAllDefaultElements() {

        closeSoftKeyboard()
        nameEditText.check(matches(isDisplayed()))
        toppingsHeader.check(matches(isDisplayed()))
        creamCheckBox.check(matches(isDisplayed()))
        chocolateCheckBox.check(matches(isDisplayed()))
        quantityHeader.check(matches(isDisplayed()))
        minusButton.check(matches(isDisplayed()))
        quantityDisplay.check(matches(isDisplayed()))
        plusButton.check(matches(isDisplayed()))
        summaryHeader.check(matches(isDisplayed()))
        orderButton.check(matches(isDisplayed()))
        totalAmount.check(matches(isDisplayed()))
    }

    // clicks the order button
    private fun clickOrderButton() {
        orderButton.perform(click())
    }


    // add name, check chocolate box, increase quantity to 2, submit order
    fun submitOrder1() {
        addNameToEditText(firstNameOnly)
        closeSoftKeyboard()
        clickChocolateCheckbox()
        clickPlusButton()          // how to verify intent and its contents?
        clickOrderButton()
        //testEmailIntent();
        totalAmount.check(matches(withText(order1)))
        totalAmount.check(matches(withText(containsString(`TOTAL_$14`))))

    }

    // add long name, check chocolate box, check cream box,  increase quantity to 6, decrease to 4, submit order
    fun submitOrder2() {
        exceedCharLimit()
        closeSoftKeyboard()
        clickWhippedCreamCheckbox()
        clickPlusButton()
        clickPlusButton()
        clickPlusButton()
        clickPlusButton()
        clickPlusButton()
        onView(withText(R.string.max_count_toast)).inRoot(ToastMatcher()).check(matches(withText(MAX_5_COFFEES_PER_ORDER)))
        clickMinusButton()
        clickOrderButton()
        //testEmailIntent();
        totalAmount.check(matches(withText(order2)))

    }

    companion object {

        private val MAX_5_COFFEES_PER_ORDER = "Max 5 coffees per order"
        private val `TOTAL_$14` = "Total: $14"
        private val YOU_CANNOT_HAVE_LESS_THAN_1_COFFEE = "You cannot have less than 1 coffee"
    }

}
