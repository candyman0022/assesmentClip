package com.backend;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.backend.enums.TransactionType;
import org.junit.*;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.UUID;


public class AppTest 
{

    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    private static final String EOL = System.getProperty("line.separator");
    private PrintStream console;
    private ByteArrayOutputStream bytes;

    @Before
    public void setUp() {
        bytes   = new ByteArrayOutputStream();
        console = System.out;
        System.setOut(new PrintStream(bytes));
    }

    @After
    public void tearDown() {
        System.setOut(console);
    }


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
        exit.expectSystemExitWithStatus(1);
        App.main();
    }

    @Test
    public void testAbortWhenTooManyArgumentsSupplied() {
        exit.expectSystemExitWithStatus(1);
        App.main("a", "b", "c" , "d");
        assertEquals(App.MSG_TOO_MANY_ARGUMENTS + EOL +
                App.USAGE + EOL, bytes.toString());
    }

    @Test
    public void testFirstParamIsNotNumericFail() {
        exit.expectSystemExitWithStatus(1);
        App.main("notNumeric ","add","{ \"amount\": 1.23, \"description\": \"Joes Tacos\", \"date\":\"2018-11-30\", \"user_id\": 345 }");
    }

    @Test
    public void testFirstParamIsNumericPass(){
        exit.expectSystemExitWithStatus(0);
        App.main("345","add","{ \"amount\": 1.23, \"description\": \"Joes Tacos\", \"date\":\"2018-12-30\", \"user_id\": 345 }");
    }

    @Test
    public void testValidCommandLineOperationForAdd() {
        Assert.assertEquals(App.setTransactionType("add"), TransactionType.ADD);
    }

    @Test
    public void testValidCommandLineOperationForShow() {
        Assert.assertEquals(App.setTransactionType(UUID.randomUUID().toString()), TransactionType.SHOW);
    }
    @Test
    public void testValidCommandLineOperationForList() {
        Assert.assertEquals(App.setTransactionType("list"), TransactionType.LIST);
    }
    @Test
    public void testValidCommandLineOperationForSum() {
        Assert.assertEquals(App.setTransactionType("sum"), TransactionType.SUM);
    }

    @Test
    public void testShowTransaction() {
        exit.expectSystemExitWithStatus(0);
        App.main("345" ,"101d1d7f-46aa-4737-a1c2-6faf83c9016b");
    }

}
