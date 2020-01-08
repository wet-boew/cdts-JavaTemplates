package goc.webtemplate.component.abstractcorebeantest;

import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class RenderRefFooterTests {

    @Test
    public void webAnalyticsRenders() {
        AbstractCoreBeanImpl sut = new AbstractCoreBeanImpl();
        
        sut.getWebAnalytics().setActive(false);
        assertTrue("RefFooter rendering: WebAnalytics not rendered as expected.", 
                sut.getRenderRefFooter().contains("\"webAnalytics\":false"));
    }
    
    @Test
    public void webAnalyticsOnlyIfSupportedInEnv() {
        AbstractCoreBeanImpl sut = new AbstractCoreBeanImpl();
        
        sut.getWebAnalytics().setActive(true);
        sut.setCDNEnvironment("PROD_SSL"); //this environment doesn't suppport analytics
        
        try {
            sut.getRenderRefFooter(); //will throw exception
            fail("Expected RuntimeException thrown.");
        }
        catch (IllegalArgumentException ex) {
            assertTrue("Unexpected exception message.", ex.getMessage().contains("WebAnalytics feature is not supported"));
        }
    }
}
