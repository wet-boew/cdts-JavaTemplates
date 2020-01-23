package goc.webtemplate;

import java.util.HashMap;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.google.gson.Gson;

public class CustomSearchTest {

    private static Gson gson = new com.google.gson.GsonBuilder()
            .setFieldNamingPolicy(com.google.gson.FieldNamingPolicy.IDENTITY)
            .create();
    
    @Test
    public void testJSONSerialization() {
        final String expectedStartValue = "{\"action\":\"action1\",\"placeholder\":\"placeholder1\",\"id\":\"id1\",\"method\":\"method1\",\"hiddenInput\":[";
        final String expectedEndValue = "]}";
        final String hiddenInput1Value = "{\"name\":\"name1\",\"value\":\"val1\"}";
        final String hiddenInput2Value = "{\"name\":\"name2\",\"value\":\"val2\"}";
        
        CustomSearch sut = new CustomSearch("action1", "placeholder1", "id1", "method1");
        
        HashMap<String, String> map = new HashMap<>();
        
        map.put("name1", "val1");
        map.put("name2", "val2");
        sut.setHiddenInput(map);
       
        String json = gson.toJson(sut); 
        
        //NOTE: Since there is no guarantee for the order of items in an HashMap,
        //      we can't just do a "is json string equal" assert, we'll do:
        assertTrue(json.startsWith(expectedStartValue), 
        		"Unexpected JSON string: '" + json + "'.  Expected to start with: '" + expectedStartValue + "'");
        assertTrue(json.endsWith(expectedEndValue), 
        		"Unexpected JSON string: '" + json + "'.  Expected to end with: '" + expectedEndValue + "'");
        
        assertTrue(json.contains(hiddenInput1Value), 
        		"Unexpected JSON string: '" + json + "'.  Expected to contain: '" + hiddenInput1Value + "'");
        
        assertTrue(json.contains(hiddenInput2Value),
        		"Unexpected JSON string: '" + json + "'.  Expected to contain: '" + hiddenInput2Value + "'");
    }
}
