package goc.webtemplate.component.abstractcorebeantest;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import goc.webtemplate.CustomSearch;

public class RenderTransactionalTopTests {
    @Test
    public void customSearchRenders() {
        AbstractCoreBeanImpl sut = new AbstractCoreBeanImpl();

        sut.setCustomSearch(new CustomSearch("#", "Custom Search Placeholder", null, "get"));
        
        assertTrue("getRenderTransactionalTop: CustomSearch not rendered as expected.", 
                sut.getRenderTransactionalTop().contains("\"customSearch\":[{\"action\":\"#\",\"placeholder\":\"Custom Search Placeholder\",\"method\":\"get\"}]"));
    }
    
    @Test
    public void customSearchDefaultNotRendered() {
        AbstractCoreBeanImpl sut = new AbstractCoreBeanImpl();
        
        assertTrue("getRenderTransactionalTop: CustomSearch should not be rendered when not specified.", 
                !sut.getRenderTransactionalTop().contains("\"customSearch\":"));
    }
}
