package goc.webtemplate.component.abstractcorebeantest;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import goc.webtemplate.ContextualFooter;
import goc.webtemplate.FooterLink;
import goc.webtemplate.Link;

public class RenderFooterTest {

    @Test
    public void testHideMainCorporateFooter() {
        AbstractCoreBeanImpl sut = new AbstractCoreBeanImpl();

        sut.setHideMainFooter(true);
        sut.setHideCorporateFooter(true);

        assertTrue(sut.getRenderFooter().contains("\"hideFooterMain\":true,\"hideFooterCorporate\":true"),
                "RenderFooter: HideMainFooter/HideCorporateFooter not rendered as expected.");
    }

    @Test
    public void testShowContextualFooter() {
        AbstractCoreBeanImpl sut = new AbstractCoreBeanImpl();

        ContextualFooter f = new ContextualFooter();
        List<Link> l = new ArrayList<Link>();

        l.add(new Link("google", "Link 1"));
        l.add(new Link("google", "Link 2", true));
        sut.setContextualFooter(new ContextualFooter("Contextual", l));

        assertTrue(sut.getRenderFooter().contains("\"contextualFooter\":{\"title\":\"Contextual\",\"links\":[{\"href\":\"google\",\"text\":\"Link 1\",\"newWindow\":false},{\"href\":\"google\",\"text\":\"Link 2\",\"newWindow\":true}]"),
                "RenderFooter: ContextualFooter not rendered as expected.");
    }

    /**
     * This is testing that the footer (when showFooter = true) renders the privacyLink and the termsLink as a single object
     */
    @Test
    public void testModifyTermsPrivacyLink() {
        AbstractCoreBeanImpl sut = new AbstractCoreBeanImpl();

        sut.setPrivacyLink(new FooterLink("google"));
        sut.setTermsConditionsLink(new FooterLink("google"));

        assertTrue(sut.getRenderFooter().contains("\"privacyLink\":{\"href\":\"google\",\"newWindow\":false},\"termsLink\":{\"href\":\"google\",\"newWindow\":false}"),
                "RenderFooter: PrivacyLink/TermsLinks not rendered as expected.");
    }

    /**
     * This is testing that the transactional footer (when showFooter = false) renders the privacyLink and the termsLink as an array
     */
    @Test
    public void testModifyTermsProvacyLinkTransactionalFooter() {
        AbstractCoreBeanImpl sut = new AbstractCoreBeanImpl();

        sut.setPrivacyLink(new FooterLink("google"));
        sut.setTermsConditionsLink(new FooterLink("google"));

        assertTrue(sut.getRenderTransactionalFooter().contains("\"privacyLink\":[{\"href\":\"google\",\"newWindow\":false}],\"termsLink\":[{\"href\":\"google\",\"newWindow\":false}]"),
                "RenderTransactionalFooter: PrivacyLink/TermsLinks not rendered as expected.");
    }
}
