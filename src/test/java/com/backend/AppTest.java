package com.backend;

import static org.junit.Assert.assertTrue;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;


public class AppTest 
{

    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void systemExitWithArbitraryStatusCode() {
        exit.expectSystemExit();
        App.main();
    }

    @Test
    public void systemExitWithSelectedStatusCode0() {
        exit.expectSystemExitWithStatus(0);
        App.main();
    }

}
