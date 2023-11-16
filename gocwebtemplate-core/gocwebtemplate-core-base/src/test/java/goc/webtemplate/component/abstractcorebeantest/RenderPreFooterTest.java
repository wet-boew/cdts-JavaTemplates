package goc.webtemplate.component.abstractcorebeantest;

import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import goc.webtemplate.Constants;

/**
 * This contains tests for RenderSetup in relation with the preFooter attributes,
 * see the other RenderXXX classes for further tests.
 */
public class RenderPreFooterTest {
	@Test
    public void testRenderShareSocialSites() {
        AbstractCoreBeanImpl sut = new AbstractCoreBeanImpl();

        ArrayList<Constants.SocialMediaSites> sites = new ArrayList<Constants.SocialMediaSites>();
        sites.add(Constants.SocialMediaSites.blogger);
        sites.add(Constants.SocialMediaSites.diigo);
        sut.setSharePageMediaSites(sites);

        assertTrue(sut.getRenderSetup().contains("\"showShare\":[\"blogger\",\"diigo\"]"),
                "PreFooter rendering: Not rendered as expected. (" + sut.getRenderSetup() + ")");
    }

    @Test
    public void testRenderFeedback() {
        AbstractCoreBeanImpl sut = new AbstractCoreBeanImpl();

        ArrayList<String> elements = new ArrayList<String>();
        elements.add("<meta content=\"width = device - width, initial - scale = 1\" name=\"viewport\">");
        elements.add("<meta name=\"dcterms.creator\" content=\"[Department name / Nom du dÃ©partement]\">");
        sut.setHtmlHeaderElements(elements);

        sut.setShowFeedbackLink(true);
        sut.setFeedbackText("Contact");
        sut.setFeedbackUrl("http://www.google.ca");
        sut.setFeedbackTheme("Theme");
        sut.setFeedbackSection("Section");

        String x = sut.getRenderSetup();
        assertTrue(sut.getRenderSetup().contains("\"showFeedback\":{\"enabled\":true,\"href\":\"http://www.google.ca\",\"text\":\"Contact\",\"theme\":\"Theme\",\"section\":\"Section\"}"),
                "PreFooter rendering: Not rendered as expected. (" + sut.getRenderSetup() + ")");
    }
}