package goc.webtemplate.component.abstractcorebeantest;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import goc.webtemplate.CustomSearch;

public class RenderTopTests {

    @Test
    public void customSearchRenders() {
        AbstractCoreBeanImpl sut = new AbstractCoreBeanImpl();

        sut.setCustomSearch(new CustomSearch("#", "Custom Search Placeholder", null, "get"));
        
        assertTrue(sut.getRenderTop().contains("\"customSearch\":[{\"action\":\"#\",\"placeholder\":\"Custom Search Placeholder\",\"method\":\"get\"}]"),
        		"RenderTop: CustomSearch not rendered as expected.");
    }
    
    @Test
    public void customSearchDefaultNotRendered() {
        AbstractCoreBeanImpl sut = new AbstractCoreBeanImpl();
        
        assertTrue(!sut.getRenderTop().contains("\"customSearch\":"),
        		"RenderTop: CustomSearch should not be rendered when not specified.");
    }
}
