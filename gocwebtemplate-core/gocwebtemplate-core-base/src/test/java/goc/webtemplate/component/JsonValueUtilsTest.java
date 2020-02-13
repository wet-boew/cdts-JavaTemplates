package goc.webtemplate.component;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import goc.webtemplate.FooterLink;
import goc.webtemplate.Link;
import goc.webtemplate.MenuItem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class JsonValueUtilsTest {
    
    @Test
    public void testGetNonEmptyLinkList_NonEmptyText() {
        ArrayList<Link> links = new ArrayList<Link>();
        ArrayList<Link> results;
        
        links.add(new Link("href1", "text1"));
        links.add(new Link("href2", ""));
        links.add(new Link("href3", "  "));
        
        results = JsonValueUtils.getNonEmptyLinkList(links);
        
        assertEquals(3, results.size()); 
        
        assertEquals("href1", results.get(0).getHref());
        assertEquals("text1", results.get(0).getText());
        assertEquals("href2", results.get(1).getHref());
        assertNull(results.get(1).getText(), "Link text should be null.");
        assertEquals("href3", results.get(2).getHref());
        assertNull(results.get(2).getText(), "Link text should be null.");
    }
    
    @Test
    public void testGetNonEmptyLinkList_SkipsNullHrefs() {
        ArrayList<Link> links = new ArrayList<Link>();
        ArrayList<Link> results;
        
        links.add(new Link("href1", "text1"));
        links.add(new Link(null, "text2"));
        links.add(new Link("", "text3"));
        links.add(new Link("href4", "text4"));
        
        results = JsonValueUtils.getNonEmptyLinkList(links);
        
        assertEquals(3, results.size()); //3 out of 4 included in results
        
        assertEquals("href1", results.get(0).getHref());
        assertEquals("text1", results.get(0).getText());
        assertEquals("", results.get(1).getHref());
        assertEquals("text3", results.get(1).getText());
        assertEquals("href4", results.get(2).getHref());
        assertEquals("text4", results.get(2).getText());
    }
    
    @Test
    public void testGetNonEmptyLinkList_LinkPolymorphic() {
        ArrayList<Link> links = new ArrayList<Link>();
        ArrayList<Link> results;
        
        links.add(new Link("href1", "text1"));
        links.add(new FooterLink("href2", "text2", true));
        links.add(new MenuItem("href3", "text3", false));
        
        results = JsonValueUtils.getNonEmptyLinkList(links);

        assertEquals(3, results.size());
        
        assertTrue(results.get(0) instanceof Link, "Object should be instanceof Link.");
        assertTrue(results.get(1) instanceof FooterLink, "Object should be instanceof FooterLink");
        assertEquals(true, ((FooterLink)results.get(1)).getNewWindow());
        assertTrue(results.get(2) instanceof MenuItem, "Object should be instanceof MenuItem");
        assertEquals(false, ((MenuItem)results.get(2)).isOpenInNewWindow());        
    }   

    @Test
    public void testGetNonEmptyLinkList_NullList() {
        assertNull(JsonValueUtils.getNonEmptyLinkList(null), "Return value should be null.");
    }

    @Test
    public void testGetNonEmptyLinkList_EmptyList() {
        assertNull(JsonValueUtils.getNonEmptyLinkList(new ArrayList<Link>()), "Return value should be null.");
    }

    @Test
    public void testGetNonEmptyFooterLinkList_NonEmptyText() {
        ArrayList<FooterLink> links = new ArrayList<FooterLink>();
        ArrayList<FooterLink> results;
        
        links.add(new FooterLink("href1", "text1", true));
        links.add(new FooterLink("href2", "", false));
        links.add(new FooterLink("href3", "  ", true));
        
        results = JsonValueUtils.getNonEmptyFooterLinkList(links);
        
        assertEquals(3, results.size()); 
        
        assertEquals("href1", results.get(0).getHref());
        assertEquals("text1", results.get(0).getText());
        assertEquals(true, results.get(0).getNewWindow());
        assertEquals("href2", results.get(1).getHref());
        assertNull(results.get(1).getText(), "Link text should be null.");
        assertEquals(false, results.get(1).getNewWindow());
        assertEquals("href3", results.get(2).getHref());
        assertNull(results.get(2).getText(), "Link text should be null.");
        assertEquals(true, results.get(2).getNewWindow());
    }

    @Test
    public void testGetNonEmptyFooterLinkList_NullList() {
        assertNull(JsonValueUtils.getNonEmptyFooterLinkList(null), "Return value should be null.");
    }

    @Test
    public void testGetNonEmptyFooterLinkList_EmptyList() {
        assertNull(JsonValueUtils.getNonEmptyFooterLinkList(new ArrayList<FooterLink>()), "Return value should be null.");
    }
}
