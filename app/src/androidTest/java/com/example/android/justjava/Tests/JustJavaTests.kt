package com.example.android.justjava.Tests

import android.support.test.rule.ActivityTestRule

import com.example.android.justjava.MainActivity
import com.example.android.justjava.Models.JustJavaModel
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class JustJavaTests {

    private val justJavaModel = JustJavaModel()

    @get:Rule
    var mActivityRule = ActivityTestRule(
            MainActivity::class.java)

    @Before
    @Throws(Exception::class)
    fun setUp() {
        //Before Test case execution
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
        //After Test case Execution
    }

    @Test
    // check that all default elements are displayed on the page
    fun defaultElements_DisplayedOnStartup() {
        justJavaModel.verifyAllDefaultElements()
    }

    // click the Chocolate checkbox and verify it was clicked
    @Test
    fun clickChocAndVerify() {
        justJavaModel.clickChocolateCheckbox()
    }

    // click cream checkbox and verify it was clicked
    @Test
    fun clickCreamAndVerify() {
        justJavaModel.verifyCreamCheckbox()
    }

    @Test
    // click both checkboxes and verify they were checked
    fun clickBothCheckBoxesAndVerify() {
        justJavaModel.verifyBothChecked()
    }

    @Test
    // check both boxes, uncheck, verify they are unchecked
    fun clickBothThenUncheckAndVerify() {
        justJavaModel.verifyBothUnchecked()
    }

    // add name Kelly Powell and verify it's added
    @Test
    fun verifyAddedName() {
        justJavaModel.addNameAndVerify()
    }

    // add name greater than char limit on the field
    // verify that only the correct chars were added
    @Test
    fun testNameCharacterLimit() {
        justJavaModel.exceedCharLimitAndVerify()
    }

    // click the increase quantity button, verify count
    @Test
    fun clickPlusButtonAndVerify() {
        justJavaModel.verifyIncreasedCount()
    }

    // click decrease quantity button multiple times, verify count remains at 1, verify toast message
    @Test
    fun testMinCount() {
        justJavaModel.verifyMinCount()
    }

    // attempt to increase quantity to more than allowed, verify that only quantity 5 is chosen, verify toast message
    @Test
    fun testMaxCount() {
        justJavaModel.verifyMaxCount()
    }

    @Test
    fun submitTheOrder1() {
        justJavaModel.submitOrder1()
    }

    @Test
    fun submitTheOrder2() {
        justJavaModel.submitOrder2()
    }
}
