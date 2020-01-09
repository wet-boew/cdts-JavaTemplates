package goc.webtemplate.component.abstractcorebeantest;

import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class RenderRefFooterTests {

    @Test
    public void renderWithoutSecureSite() {
        AbstractCoreBeanImpl sut = new AbstractCoreBeanImpl();
        
        assertTrue("RefFooter rendering: Not rendered as expected. (" + sut.getRenderRefFooter() + ")", 
                sut.getRenderRefFooter().contains("\"cdnEnv\":\"prod\",\"exitScript\":false,\"displayModal\":false"));
    }
    
    @Test
    public void renderWithSecureSite() {
        AbstractCoreBeanImpl sut = new AbstractCoreBeanImpl();
        
        sut.getLeavingSecureSiteWarning().setEnabled(true);
        
        assertTrue("RefFooter rendering: LeavingSecureSite not rendered as expected. (" + sut.getRenderRefFooter() + ")", 
                sut.getRenderRefFooter().contains("\"exitScript\":true,\"exitURL\":\"leave.action\",\"exitMsg\":\"\",\"displayModal\":true"));
    }
    
    @Test
    public void renderWithSecureSiteAndYesCancelMessages() {
        AbstractCoreBeanImpl sut = new AbstractCoreBeanImpl();
        
        sut.getLeavingSecureSiteWarning().setEnabled(true);
        sut.getLeavingSecureSiteWarning().setCancelMessage("Test Cancel Message");
        sut.getLeavingSecureSiteWarning().setYesMessage("Test Yes Message");
        
        assertTrue("RefFooter rendering: LeavingSecureSite not rendered as expected. (" + sut.getRenderRefFooter() + ")", 
                sut.getRenderRefFooter().contains("\"cancelMsg\":\"Test Cancel Message\",\"yesMsg\":\"Test Yes Message\""));
    }
    
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
