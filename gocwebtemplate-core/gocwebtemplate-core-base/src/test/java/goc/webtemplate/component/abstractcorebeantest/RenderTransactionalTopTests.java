package goc.webtemplate.component.abstractcorebeantest;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import goc.webtemplate.CustomSearch;

public class RenderTransactionalTopTests {
    @Test
    public void customSearchRenders() {
        AbstractCoreBeanImpl sut = new AbstractCoreBeanImpl();

        sut.setCustomSearch(new CustomSearch("#", "Custom Search Placeholder", null, "get"));
        
        assertTrue(sut.getRenderTransactionalTop().contains("\"customSearch\":[{\"action\":\"#\",\"placeholder\":\"Custom Search Placeholder\",\"method\":\"get\"}]"),
        		"getRenderTransactionalTop: CustomSearch not rendered as expected.");
    }
    
    @Test
    public void customSearchDefaultNotRendered() {
        AbstractCoreBeanImpl sut = new AbstractCoreBeanImpl();
        
        assertTrue(!sut.getRenderTransactionalTop().contains("\"customSearch\":"),
        		"getRenderTransactionalTop: CustomSearch should not be rendered when not specified.");
    }
}
