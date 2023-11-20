package goc.webtemplate.component.abstractcorebeantest;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import goc.webtemplate.Constants;

public class SRITest {

    @Test
    public void testNoSRIForUnknownVersion() {
        AbstractCoreBeanImpl sut = new AbstractCoreBeanImpl();

        sut.setUseSRI(true);
        sut.setTheme("gcweb");
        sut.setTemplateVersion("v999_999_999");

        assertTrue(sut.getCssPathAttributes().contains("v999_999_999"));
        assertTrue(!sut.getCssPathAttributes().contains("integrity"));
        assertTrue(!sut.getWetJsPathAttributes().contains("integrity"));
    }

    @Test
    public void testHasSRIForGCWebDefaultVersion() {
        AbstractCoreBeanImpl sut = new AbstractCoreBeanImpl();

        sut.setUseSRI(true);
        sut.setTheme("gcweb");

        assertTrue(sut.getCssPathAttributes().contains(Constants.CDTS_DEFAULT_VERSION));
        assertTrue(sut.getCssPathAttributes().contains("integrity"));
        assertTrue(sut.getWetJsPathAttributes().contains("integrity"));
    }

    @Test
    public void testHasSRIForGCIntranetDefaultVersion() {
        AbstractCoreBeanImpl sut = new AbstractCoreBeanImpl();

        sut.setUseSRI(true);
        sut.setTheme("gcintranet");

        assertTrue(sut.getCssPathAttributes().contains(Constants.CDTS_DEFAULT_VERSION));
        assertTrue(sut.getCssPathAttributes().contains("integrity"));
        assertTrue(sut.getWetJsPathAttributes().contains("integrity"));
    }

    @Test
    public void testNoSRIWhenDisabled() {
        AbstractCoreBeanImpl sut = new AbstractCoreBeanImpl();

        sut.setUseSRI(false);
        sut.setTheme("gcweb");

        assertTrue(!sut.getCssPathAttributes().contains("integrity"));
        assertTrue(!sut.getWetJsPathAttributes().contains("integrity"));
    }
}
