package goc.webtemplate.component.abstractcorebeantest;

import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import goc.webtemplate.Constants;
import goc.webtemplate.FeedbackLink;

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
        elements.add("<meta name=\"dcterms.creator\" content=\"[Department name / Nom du département]\">");
        sut.setHtmlHeaderElements(elements);

        FeedbackLink feedbackLink = new FeedbackLink();
        feedbackLink.setEnabled(true);
        feedbackLink.setText("Contact");
        feedbackLink.setTextFr("Contactez-nous");
        feedbackLink.setUrl("http://www.google.ca"); 
        feedbackLink.setUrlFr("http://www.google.ca/?hl=fr");
        feedbackLink.setTheme("Theme");
        feedbackLink.setSection("Section");
        sut.setFeedbackLink(feedbackLink);

        String x = sut.getRenderSetup();
        assertTrue(sut.getRenderSetup().contains("\"showFeedback\":{\"enabled\":true,\"href\":\"http://www.google.ca\",\"text\":\"Contact\",\"theme\":\"Theme\",\"section\":\"Section\"}"),
                "PreFooter rendering: Not rendered as expected. (" + sut.getRenderSetup() + ")");
    }
}