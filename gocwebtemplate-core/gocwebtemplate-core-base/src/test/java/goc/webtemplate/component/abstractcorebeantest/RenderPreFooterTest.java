package goc.webtemplate.component.abstractcorebeantest;

import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.ArrayList;
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
}