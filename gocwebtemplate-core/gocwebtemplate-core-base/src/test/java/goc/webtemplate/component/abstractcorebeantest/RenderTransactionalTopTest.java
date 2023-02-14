package goc.webtemplate.component.abstractcorebeantest;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import goc.webtemplate.CustomSearch;

/**
 * This contains tests for RenderTransactionalSetup in relation with the Top attributes,
 * see the other RenderXXX classes for further tests.
 */
public class RenderTransactionalTopTest {
    @Test
    public void testCustomSearchRenders() {
        AbstractCoreBeanImpl sut = new AbstractCoreBeanImpl();

        sut.setCustomSearch(new CustomSearch("#", "Custom Search Placeholder", null, "get"));

        assertTrue(sut.getRenderTransactionalSetup().contains("\"customSearch\":[{\"action\":\"#\",\"placeholder\":\"Custom Search Placeholder\",\"method\":\"get\"}]"),
        		"getRenderTransactionalTop: CustomSearch not rendered as expected.");
    }

    @Test
    public void testCustomSearchDefaultNotRendered() {
        AbstractCoreBeanImpl sut = new AbstractCoreBeanImpl();

        assertTrue(!sut.getRenderTransactionalSetup().contains("\"customSearch\":"),
        		"getRenderTransactionalTop: CustomSearch should not be rendered when not specified.");
    }

    @Test
    public void testGcToolsModalRendersInIntranet() {
        AbstractCoreBeanImpl sut = new AbstractCoreBeanImpl();

        sut.setTheme("gcintranet");
        sut.setGcToolsModal(true);

        assertTrue(sut.getRenderTransactionalSetup().contains("\"GCToolsModal\":true"));
    }

    @Test
    public void testGcToolsModalThrowsExceptionInGcWeb() {
        AbstractCoreBeanImpl sut = new AbstractCoreBeanImpl();

        sut.setTheme("gcweb");
        sut.setGcToolsModal(true);

        assertThrows(UnsupportedOperationException.class, () -> {
            sut.getRenderTransactionalSetup();
        });
    }
}
