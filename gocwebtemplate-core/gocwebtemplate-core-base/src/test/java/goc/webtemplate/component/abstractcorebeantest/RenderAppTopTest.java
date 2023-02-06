package goc.webtemplate.component.abstractcorebeantest;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import goc.webtemplate.HeaderLink;
import goc.webtemplate.HeaderMenu;
import goc.webtemplate.InfoBanner;
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

        InfoBanner banner = new InfoBanner("Main Text", new HeaderLink("google", "Link"), new HeaderLink("yahoo", "Button"));
        sut.setInfoBanner(banner);

        assertTrue(sut.getRenderAppTop().contains("\"infoBanner\":{\"mainHTML\":\"Main Text\",\"link\":{\"newWindow\":false,\"href\":\"google\",\"text\":\"Link\"},\"button\":{\"newWindow\":false,\"href\":\"yahoo\",\"text\":\"Button\"}"),
        		"\"RenderTop: InfoBanner not rendered as expected.\"");
    }

    @Test
    public void testHeaderMenu() {
        AbstractCoreBeanImpl sut = new AbstractCoreBeanImpl();

        List<HeaderLink> list = new ArrayList<HeaderLink>();
        list.add(new HeaderLink("google", "Link 1"));
        list.add(new HeaderLink("google", "Link 2", true));

        HeaderMenu menu = new HeaderMenu("Main Text", list, new Link("google", "Logout Now"));
        sut.setHeaderMenu(menu);
        String x = sut.getRenderAppTop();
        assertTrue(sut.getRenderAppTop().contains("\"headerMenu\":{\"text\":\"Main Text\",\"links\":[{\"newWindow\":false,\"href\":\"google\",\"text\":\"Link 1\"},{\"newWindow\":true,\"href\":\"google\",\"text\":\"Link 2\"}],\"logoutLink\":{\"href\":\"google\",\"text\":\"Logout Now\"}}"),
        		"\"RenderTop: HeaderMenu not rendered as expected.\"");
    }
}
