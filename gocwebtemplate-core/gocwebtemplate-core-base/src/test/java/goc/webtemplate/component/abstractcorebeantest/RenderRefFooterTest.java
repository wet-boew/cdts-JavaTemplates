package goc.webtemplate.component.abstractcorebeantest;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * This contains tests for RenderSetup in relation with the refFooter attributes (which are now in the "SetupBase" object),
 * see the other RenderXXX classes for further tests.
 */
public class RenderRefFooterTest {

    @Test
    public void testRenderWithoutSecureSite() {
        AbstractCoreBeanImpl sut = new AbstractCoreBeanImpl();

        assertTrue(!sut.getRenderSetup().contains("\"exitScript\":false,\"displayModal\":false"),
        		"RefFooter rendering: Not rendered as expected. (" + sut.getRenderSetup() + ")");
    }

    @Test
    public void testRenderWithSecureSite() {
        AbstractCoreBeanImpl sut = new AbstractCoreBeanImpl();

        sut.getLeavingSecureSiteWarning().setEnabled(true);

        assertTrue(sut.getRenderSetup().contains("\"exitSecureSite\":{\"exitScript\":true,\"displayModal\":true,\"displayModalForNewWindow\":true,\"exitMsg\":\"\""),
        		"RefFooter rendering: LeavingSecureSite not rendered as expected. (" + sut.getRenderSetup() + ")");
    }

    @Test
    public void testRenderWithSecureSiteAndYesCancelMessages() {
        AbstractCoreBeanImpl sut = new AbstractCoreBeanImpl();

        sut.getLeavingSecureSiteWarning().setEnabled(true);
        sut.getLeavingSecureSiteWarning().setCancelMessage("Test Cancel Message");
        sut.getLeavingSecureSiteWarning().setYesMessage("Test Yes Message");
        sut.getLeavingSecureSiteWarning().setTargetWarning("Test Target Warning");

        assertTrue(sut.getRenderSetup().contains("\"cancelMsg\":\"Test Cancel Message\",\"yesMsg\":\"Test Yes Message\",\"targetWarning\":\"Test Target Warning\""),
        		"RefFooter rendering: LeavingSecureSite not rendered as expected. (" + sut.getRenderSetup() + ")");
    }

    @Test
    public void testWebAnalyticsRenders() {
        AbstractCoreBeanImpl sut = new AbstractCoreBeanImpl();

        sut.getWebAnalytics().setActive(false);
        assertTrue(!sut.getRenderSetup().contains("\"webAnalytics\":"),
        		"RefFooter rendering: WebAnalytics not rendered as expected.");
    }

    @Test
    public void testWebAnalyticsOnlyIfSupportedInEnv() {
        AbstractCoreBeanImpl sut = new AbstractCoreBeanImpl();

        sut.getWebAnalytics().setActive(true);
        sut.setCDNEnvironment("PROD_SSL"); //this environment doesn't suppport analytics

        try {
            sut.getRenderSetup(); //will throw exception
            fail("Expected RuntimeException thrown.");
        }
        catch (IllegalArgumentException ex) {
            assertTrue(ex.getMessage().contains("WebAnalytics feature is not supported"),
            		"Unexpected exception message.");
        }
    }

    @Test
    public void testDisplayModalNewWinTrueWhenExitscriptDisabled() {
        AbstractCoreBeanImpl sut = new AbstractCoreBeanImpl();

        sut.getLeavingSecureSiteWarning().setEnabled(false);

        assertTrue(!sut.getRenderSetup().contains("\"displayModalForNewWindow\""),
        		"RefFooter rendering: LeavingSecureSite not rendered as expected. (" + sut.getRenderSetup() + ")");
    }

    @Test
    public void testDisplayModalNewWinFalse() {
        AbstractCoreBeanImpl sut = new AbstractCoreBeanImpl();

        sut.getLeavingSecureSiteWarning().setEnabled(true);
        sut.getLeavingSecureSiteWarning().setDisplayModalForNewWindow(false);

        assertTrue(sut.getRenderSetup().contains("\"displayModalForNewWindow\":false"),
        		"RefFooter rendering: LeavingSecureSite not rendered as expected. (" + sut.getRenderSetup() + ")");
    }

    @Test
    public void testMsgBoxHeader() {
        AbstractCoreBeanImpl sut = new AbstractCoreBeanImpl();

        sut.getLeavingSecureSiteWarning().setEnabled(true);
        sut.getLeavingSecureSiteWarning().setMsgBoxHeader("Warning, you are leaving a secure site!");

        assertTrue(sut.getRenderSetup().contains("\"msgBoxHeader\":\"Warning, you are leaving a secure site!\""),
        		"RefFooter rendering: LeavingSecureSite not rendered as expected. (" + sut.getRenderSetup() + ")");
    }

    @Test
    public void testIsApplicationFalse() {
        AbstractCoreBeanImpl sut = new AbstractCoreBeanImpl();

        assertTrue(sut.getRenderAppSetup().contains("\"mode\":\"app\""),
        		"RefFooter rendering: Not rendered as expected. (" + sut.getRenderAppSetup() + ")");
    }

    @Test
    public void testIsApplicationTrue() {
        AbstractCoreBeanImpl sut = new AbstractCoreBeanImpl();

        assertTrue(sut.getRenderAppSetup().contains("\"mode\":\"app\""),
        		"RefFooter rendering: Not rendered as expected. (" + sut.getRenderAppSetup() + ")");
    }
}
