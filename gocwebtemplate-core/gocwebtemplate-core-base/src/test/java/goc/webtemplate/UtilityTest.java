package goc.webtemplate;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class UtilityTest
{
    @Test
    public void testIsNull_TrueWithNull()
    {
        assertTrue(Utility.isNullOrEmpty(null));
    }
    
    @Test
    public void testIsNull_TrueWithBlank()
    {
        assertTrue(Utility.isNullOrEmpty(""));
    }
    
    @Test
    public void testIsNull_TrueWithWhiteSpace()
    {
        assertTrue(Utility.isNullOrEmpty(" "));
        assertTrue(Utility.isNullOrEmpty(" \t\n "));
    }
    
    @Test
    public void testIsNull_FalseWithValue()
    {
        assertFalse(Utility.isNullOrEmpty(" \tHello There\n"));
    }
}
