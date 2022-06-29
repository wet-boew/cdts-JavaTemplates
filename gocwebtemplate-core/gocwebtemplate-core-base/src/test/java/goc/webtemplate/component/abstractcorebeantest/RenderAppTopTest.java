package goc.webtemplate.component.abstractcorebeantest;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import goc.webtemplate.MenuItem;

public class RenderAppTopTest {

    @Test
    public void testCustomMenuItem() {
        AbstractCoreBeanImpl sut = new AbstractCoreBeanImpl();

        ArrayList<MenuItem> menuLinks = new ArrayList<MenuItem>();
        MenuItem item = new MenuItem();
        item.setText("Custom Menu Link");
        item.setHref("https//google.ca");
        item.setAcronym("acronym");
        menuLinks.add(item);
        sut.setMenuLinks(menuLinks);

        assertTrue(sut.getRenderAppTop().contains("\"menuLinks\":[{\"href\":\"https//google.ca\",\"text\":\"Custom Menu Link\",\"acronym\":\"acronym\""),
        		"RenderTop: Custom MenuItem not rendered as expected.");
    }

}
