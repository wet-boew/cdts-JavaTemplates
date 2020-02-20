package goc.webtemplate.component.abstractcorebeantest;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import goc.webtemplate.CustomSearch;

public class RenderTopTest {

    @Test
    public void testCustomSearchRenders() {
        AbstractCoreBeanImpl sut = new AbstractCoreBeanImpl();

        sut.setCustomSearch(new CustomSearch("#", "Custom Search Placeholder", null, "get"));
        
        assertTrue(sut.getRenderTop().contains("\"customSearch\":[{\"action\":\"#\",\"placeholder\":\"Custom Search Placeholder\",\"method\":\"get\"}]"),
        		"RenderTop: CustomSearch not rendered as expected.");
    }
    
    @Test
    public void testCustomSearchDefaultNotRendered() {
        AbstractCoreBeanImpl sut = new AbstractCoreBeanImpl();
        
        assertTrue(!sut.getRenderTop().contains("\"customSearch\":"),
        		"RenderTop: CustomSearch should not be rendered when not specified.");
    }
    
    @Test
    public void testGcToolsModalRendersInIntranet() {
        AbstractCoreBeanImpl sut = new AbstractCoreBeanImpl();
        
        sut.setTheme("gcintranet");
        sut.setGcToolsModal(true);
        
        assertTrue(sut.getRenderTop().contains("\"GCToolsModal\":true"));
    }
    
    @Test
    public void testGcToolsModalThrowsExceptionInGcWeb() {
        AbstractCoreBeanImpl sut = new AbstractCoreBeanImpl();
        
        sut.setTheme("gcweb");
        sut.setGcToolsModal(true);
        
        assertThrows(UnsupportedOperationException.class, () -> { 
            sut.getRenderTop(); 
        });
    }
}
