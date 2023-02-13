package goc.webtemplate.component.abstractcorebeantest;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import goc.webtemplate.ContextualFooter;
import goc.webtemplate.FooterLink;

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

        List<FooterLink> l = new ArrayList<FooterLink>();

        l.add(new FooterLink("google", "Link 1", false));
        l.add(new FooterLink("google", "Link 2", true));
        sut.setContextualFooter(new ContextualFooter("Contextual", l));

        assertTrue(sut.getRenderFooter().contains("\"contextualFooter\":{\"title\":\"Contextual\",\"links\":[{\"newWindow\":false,\"href\":\"google\",\"text\":\"Link 1\"},{\"newWindow\":true,\"href\":\"google\",\"text\":\"Link 2\"}]"),
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

        assertTrue(sut.getRenderFooter().contains("\"privacyLink\":{\"newWindow\":false,\"href\":\"google\",\"text\":\"\"},\"termsLink\":{\"newWindow\":false,\"href\":\"google\",\"text\":\"\"}"),
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

        assertTrue(sut.getRenderTransactionalFooter().contains("\"privacyLink\":[{\"newWindow\":false,\"href\":\"google\",\"text\":\"\"}],\"termsLink\":[{\"newWindow\":false,\"href\":\"google\",\"text\":\"\"}]"),
                "RenderTransactionalFooter: PrivacyLink/TermsLinks not rendered as expected.");
    }
}
