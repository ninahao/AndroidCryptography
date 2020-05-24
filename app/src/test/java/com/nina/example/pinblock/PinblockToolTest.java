package com.nina.example.pinblock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

public class PinblockToolTest {
    @Test
    public void test() {
        String pin = "1234";
        String pan = "123456789012";
        String encode = PinblockTool.format0Encode(pin, pan);
        String decode = PinblockTool.format0decode(encode, pan);
        assertEquals(pin, new String(decode));
    }
}