package com.example.android.justjava.Tests;

import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.rule.ActivityTestRule;

import com.example.android.justjava.MainActivity;
import com.example.android.justjava.Models.JustJavaModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;



public class JustJavaTests {

    private JustJavaModel justJavaModel = new JustJavaModel();

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    public IntentsTestRule<MainActivity> intentsTestRule =
            new IntentsTestRule<>(MainActivity.class);

    @Before
    public void setUp() throws Exception {
        //Before Test case execution
    }

    @After
    public void tearDown() throws Exception {
        //After Test case Execution
    }


    @Test

    // check that all default elements are displayed on the page
    public void defaultElements_DisplayedOnStartup() {
        justJavaModel.verifyAllDefaultElements();
    }

    // click the Chocolate checkbox and verify it was clicked
    @Test
    public void clickChocAndVerify() {
        justJavaModel.clickChocolateCheckbox();
    }

    // click cream checkbox and verify it was clicked
    @Test
    public void clickCreamAndVerify() {
        justJavaModel.verifyCreamCheckbox();
    }

    @Test
    // click both checkboxes and verify they were checked
    public void clickBothCheckBoxesAndVerify() {
        justJavaModel.verifyBothChecked();
    }

    @Test
    // check both boxes, uncheck, verify they are unchecked
    public void clickBothThenUncheckAndVerify() {
        justJavaModel.verifyBothUnchecked();
    }

    // add name Kelly Powell and verify it's added
    @Test
    public void verifyAddedName() {
        justJavaModel.addNameAndVerify();
    }

    // add name greater than char limit on the field
    // verify that only the correct chars were added
    @Test
    public void testNameCharacterLimit() {
        justJavaModel.exceedCharLimitAndVerify();
    }

    // click the increase quantity button, verify count
    @Test
    public void clickPlusButtonAndVerify() {
        justJavaModel.verifyIncreasedCount();
    }

    // click decrease quantity button multiple times, verify count remains at 1, verify toast message
    @Test
    public void testMinCount() {
        justJavaModel.verifyMinCount();
    }

    // attempt to increase quantity to more than allowed, verify that only quantity 5 is chosen, verify toast message
    @Test
    public void testMaxCount() {
        justJavaModel.verifyMaxCount();

    }

    @Test
    public void submitTheOrder1() {
        justJavaModel.submitOrder1();
    }

    @Test
    public void submitTheOrder2() {
        justJavaModel.submitOrder2();
    }

}
