package goc.webtemplate.component.abstractcorebeantest;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * This contains tests for RenderSetup in relation with the top-most attributes,
 * see the other RenderXXX classes for further tests.
 *
 */
public class RenderSetupTest {
    @Test
    public void testRenderSetup() {
        AbstractCoreBeanImpl sut = new AbstractCoreBeanImpl();

        String json = sut.getRenderSetup();

        //cdnEnv must be present
        assertTrue(json.contains("\"cdnEnv\":\""));
        //mode should NOT be present (we rely on CDTS's default in "normal" setup)
        assertTrue(!json.contains("\"mode\":\""));
    }

    /**
     * Tests that apostrophes (') are escaped
     */
    @Test
    public void testRenderSetupApos() {
        AbstractCoreBeanImpl sut = new AbstractCoreBeanImpl();

        sut.setScreenIdentifier("This is an 'identifier'");

        String json = sut.getRenderSetup();
        assertTrue(json.contains("\"screenIdentifier\":\"This is an \\u0027identifier\\u0027\""));
    }

    @Test
    public void testRenderAppSetup() {
        AbstractCoreBeanImpl sut = new AbstractCoreBeanImpl();

        String json = sut.getRenderAppSetup();

        //cdnEnv must be present
        assertTrue(json.contains("\"cdnEnv\":\""));
        //mode should be "app"
        assertTrue(json.contains("\"mode\":\"app\""));
    }

    @Test
    public void testRenderServerSetup() {
        AbstractCoreBeanImpl sut = new AbstractCoreBeanImpl();

        String json = sut.getRenderServerSetup();

        //cdnEnv must be present
        assertTrue(json.contains("\"cdnEnv\":\""));
        //mode should be "server"
        assertTrue(json.contains("\"mode\":\"server\""));
    }

    @Test
    public void testRenderSplashSetup() {
        AbstractCoreBeanImpl sut = new AbstractCoreBeanImpl();

        String json = sut.getRenderSplashSetup();

        //cdnEnv must be present
        assertTrue(json.contains("\"cdnEnv\":\""));
        //mode should be "splash"
        assertTrue(json.contains("\"mode\":\"splash\""));
        //splash must be present
        assertTrue(json.contains("\"splash\":{"));
    }

    //TODO: Update other tests
    //TODO: Get rid of RefTop and RefFooter (if no longer used)... and maybe getLocalPath() and other unused functions that were used to build these objects.

    //TODO: Change log
    //TODO: Remove some Deprecated?
    //TODO: Test with huge data
}
