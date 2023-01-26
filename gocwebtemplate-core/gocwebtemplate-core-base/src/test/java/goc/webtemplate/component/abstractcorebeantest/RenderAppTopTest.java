package goc.webtemplate.component.abstractcorebeantest;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import goc.webtemplate.InfoBanner;
import goc.webtemplate.Link;
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

    @Test
    public void testInfoBanner() {
        AbstractCoreBeanImpl sut = new AbstractCoreBeanImpl();

        InfoBanner banner = new InfoBanner("Main Text", new Link("google", "Link"), new Link("yahoo", "Button"));
        sut.setInfoBanner(banner);

        assertTrue(sut.getRenderAppTop().contains("\"infoBanner\":{\"mainHTML\":\"Main Text\",\"link\":{\"href\":\"google\",\"text\":\"Link\"},\"button\":{\"href\":\"yahoo\",\"text\":\"Button\"}"),
        		"\"RenderTop: InfoBanner not rendered as expected.\"");
    }

}
