package goc.webtemplate.component.abstractcorebeantest;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CSSPathTest {
	// **********************
	// ***** GetCssPath *****
	// **********************
    @Test
    public void testBasicGCWeb() {
        AbstractCoreBeanImpl sut = new AbstractCoreBeanImpl();

        sut.setTheme("gcweb");

        assertTrue(sut.getCssPath().endsWith("/cdts-styles.css"));
    }

    @Test
    public void testBasicGCIntranet() {
        AbstractCoreBeanImpl sut = new AbstractCoreBeanImpl();

        sut.setTheme("gcintranet");

        assertTrue(sut.getCssPath().endsWith("/cdts-styles.css"));
    }

    @Test
    public void testBasicGCIntranetESDC() {
        AbstractCoreBeanImpl sut = new AbstractCoreBeanImpl();

        sut.setTheme("gcintranet");
        sut.setSubTheme("eSdC"); //test case insensitivity

        assertTrue(sut.getCssPath().endsWith("/cdts-esdc-styles.css"));
    }

    @Test
    public void testBasicGCIntranetECCC() {
        AbstractCoreBeanImpl sut = new AbstractCoreBeanImpl();

        sut.setTheme("gcintranet");
        sut.setSubTheme("eCcC"); //test case insensitivity

        assertTrue(sut.getCssPath().endsWith("/cdts-eccc-styles.css"));
    }

    @Test
    public void testBasicUnknownTheme() {
        AbstractCoreBeanImpl sut = new AbstractCoreBeanImpl();

        sut.setTheme("whatever");

        assertTrue(sut.getCssPath().endsWith("/cdts-styles.css"));
    }

    @Test
    public void testBasicGCIntranetUnknownSubtheme() {
        AbstractCoreBeanImpl sut = new AbstractCoreBeanImpl();

        sut.setTheme("gcintranet");
        sut.setSubTheme("whatever");

        assertTrue(sut.getCssPath().endsWith("/cdts-styles.css"));
    }

	// *************************
    // ***** GetAppCssPath *****
	// *************************

    @Test
    public void testAppGCWeb() {
        AbstractCoreBeanImpl sut = new AbstractCoreBeanImpl();

        sut.setTheme("gcweb");

        assertTrue(sut.getAppCssPath().endsWith("/cdts-app-styles.css"));
    }

    @Test
    public void testAppGCIntranet() {
        AbstractCoreBeanImpl sut = new AbstractCoreBeanImpl();

        sut.setTheme("gcintranet");

        assertTrue(sut.getAppCssPath().endsWith("/cdts-styles.css"));
    }

    @Test
    public void testAppGCIntranetESDC() {
        AbstractCoreBeanImpl sut = new AbstractCoreBeanImpl();

        sut.setTheme("gcintranet");
        sut.setSubTheme("eSdC"); //test case insensitivity

        assertTrue(sut.getAppCssPath().endsWith("/cdts-esdc-styles.css"));
    }

    @Test
    public void testAppGCIntranetECCC() {
        AbstractCoreBeanImpl sut = new AbstractCoreBeanImpl();

        sut.setTheme("gcintranet");
        sut.setSubTheme("eCcC"); //test case insensitivity

        assertTrue(sut.getAppCssPath().endsWith("/cdts-eccc-styles.css"));
    }

    @Test
    public void testAppUnknownTheme() {
        AbstractCoreBeanImpl sut = new AbstractCoreBeanImpl();

        sut.setTheme("whatever");

        assertTrue(sut.getAppCssPath().endsWith("/cdts-styles.css"));
    }

    @Test
    public void testAppGCIntranetUnknownSubtheme() {
        AbstractCoreBeanImpl sut = new AbstractCoreBeanImpl();

        sut.setTheme("gcintranet");
        sut.setSubTheme("whatever");

        assertTrue(sut.getAppCssPath().endsWith("/cdts-styles.css"));
    }

	// *************************
    // ***** GetAppCssPath *****
	// *************************

    @Test
    public void testSplashGCWeb() {
        AbstractCoreBeanImpl sut = new AbstractCoreBeanImpl();

        sut.setTheme("gcweb");

        assertTrue(sut.getSplashCssPath().endsWith("/cdts-splash-styles.css"));
    }

    @Test
    public void testSplashGCIntranet() {
        AbstractCoreBeanImpl sut = new AbstractCoreBeanImpl();

        sut.setTheme("gcintranet");

        assertTrue(sut.getSplashCssPath().endsWith("/cdts-splash-styles.css"));
    }
}
