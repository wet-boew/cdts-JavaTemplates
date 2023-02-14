package goc.webtemplate.component.abstractcorebeantest;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import goc.webtemplate.CustomSearch;

/**
 * This contains tests for RenderSetup in relation with the Top attributes,
 * see the other RenderXXX classes for further tests.
 */
public class RenderTopTest {

    @Test
    public void testCustomSearchRenders() {
        AbstractCoreBeanImpl sut = new AbstractCoreBeanImpl();

        sut.setCustomSearch(new CustomSearch("#", "Custom Search Placeholder", null, "get"));

        assertTrue(sut.getRenderSetup().contains("\"customSearch\":[{\"action\":\"#\",\"placeholder\":\"Custom Search Placeholder\",\"method\":\"get\"}]"),
        		"RenderTop: CustomSearch not rendered as expected.");
    }

    @Test
    public void testCustomSearchDefaultNotRendered() {
        AbstractCoreBeanImpl sut = new AbstractCoreBeanImpl();

        assertTrue(!sut.getRenderSetup().contains("\"customSearch\":"),
        		"RenderTop: CustomSearch should not be rendered when not specified.");
    }

    @Test
    public void testGcToolsModalRendersInIntranet() {
        AbstractCoreBeanImpl sut = new AbstractCoreBeanImpl();

        sut.setTheme("gcintranet");
        sut.setGcToolsModal(true);

        assertTrue(sut.getRenderSetup().contains("\"GCToolsModal\":true"));
    }

    @Test
    public void testGcToolsModalThrowsExceptionInGcWeb() {
        AbstractCoreBeanImpl sut = new AbstractCoreBeanImpl();

        sut.setTheme("gcweb");
        sut.setGcToolsModal(true);

        assertThrows(UnsupportedOperationException.class, () -> {
            sut.getRenderSetup();
        });
    }

    @Test
    public void testHidePlaceholderMenuTrueRenders() {
        AbstractCoreBeanImpl sut = new AbstractCoreBeanImpl();

        sut.setHidePlaceholderMenu(true);

        assertTrue(sut.getRenderSetup().contains("\"hidePlaceholderMenu\":true"));
    }
}
