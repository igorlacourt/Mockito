/*
 * Copyright 2018, Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.SimpleCalc;

import android.content.Context;
import android.test.suitebuilder.annotation.SmallTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

/**
 * JUnit4 unit tests for the calculator logic. These are local unit tests; no device needed
 */
//@RunWith(JUnit4.class)
@RunWith(MockitoJUnitRunner.class)
@SmallTest
public class CalculatorTest {

    private Calculator mCalculator;

    /**
     * Set up the environment for testing
     */
    @Before
    public void setUp() {
        mCalculator = new Calculator();
    }

    /**
     * Test for simple addition
     */
    @Test
    public void addTwoNumbers() {
        double resultAdd = mCalculator.add(1d, 1d);
        assertThat(resultAdd, is(equalTo(2d)));
    }

    @Test
    public void addTwoNumbersNegative(){
        double result = mCalculator.add(-2d, -2d);
        assertThat(result, is(equalTo(-4d)));
    }

    @Test
    public void addTwoNumbersFloats() {
        double resultAdd = mCalculator.add(1.111f, 1.111d);
        assertThat(resultAdd, is(closeTo(2.222d, 0.01)));
    }



    //As we don't have access to Context in our JUnit test classes, we need to mock it
    @Mock
    Context mMockContext;
    private static final String TEST_STRING = "Hello, Mockito!";

    @Test
    public void readStringFromContext(){
        //Returns the TEST_STRING when getString(R.string.hello_world) is called
        /*Se houver algum método dentro do objeto a ser 'mocado' que será necessário ao longo da operação alvo do teste,
        * deve-se simular sua saída.*/
        when(mMockContext.getString(R.string.text_hello_mockito)).thenReturn(TEST_STRING);

        //Creates an object of the ClassUnderTest with the mock context
        ClassUnderTest objectUnderTest = new ClassUnderTest(mMockContext);

        //Stores the return value of getHelloWorldString() in result
        String result = objectUnderTest.getHelloWorldString();

        //Asserts that result is the value of TEST_STRING
        assertThat(result, is(TEST_STRING));

    }

    @Test

    public void testMockMethod(){

        List mockList = Mockito.mock(ArrayList.class);

        mockList.add("hello world");

        //verifica se 'hello world' foi inserido na lista.
        Mockito.verify(mockList).add("hello world");

        assertEquals(0, mockList.size());

    }

    @Test
    public void testSpyMethod(){

        List spyList = Mockito.spy(new ArrayList());

        spyList.add("hello world");

        Mockito.verify(spyList).add("hello world");

        assertEquals(1, spyList.size());

    }

    @Test
    public void testThenReturn(){
        //Create a mock object of the class Calculator
        Calculator mockCalculator = Mockito.mock(Calculator.class);

        //O valor retornado precisa ser double nessas funções!
        //Return the value of 30 when the add method is called with the arguments 10 and 20
        Mockito.when(mockCalculator.add(10, 20)).thenReturn(30d);

        //Asserts that the return value of add method with arguments 10 and 20 is 30
        assertThat(mockCalculator.add(10, 20), is(equalTo(30d)));

    }

    @Test
    public void testDoReturn(){
        //Create a spy object of the class Calculator
        Calculator mockCalculator = Mockito.spy(new Calculator());

        //O valor retornado precisa ser double nessas funções!
        //Return the value of 30 when the add method is called on the spied object with the arguments 10 and 20
        Mockito.doReturn(30d).when(mockCalculator).add(10, 20);

        //Asserts that the return value of add method with arguments 10 and 20 is 30
        assertThat(mockCalculator.add(10, 20), is(equalTo(30d)));

    }
}