package com.galvanize;

import org.junit.Test;

public class HelloWorldTest {

    @Test
    public void helloWorldShouldWork() {
        HelloWorld greeting = new HelloWorld();

        assertEquals("Hello world!", greeting.greet());
    }

}
