package goc.webtemplate.component.abstractcorebeantest;

import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import goc.webtemplate.Constants;

public class RenderPreFooterTest {
	@Test
    public void testRenderShareSocialSites() {
        AbstractCoreBeanImpl sut = new AbstractCoreBeanImpl();
        
        ArrayList<Constants.SocialMediaSites> sites = new ArrayList<Constants.SocialMediaSites>();
        sites.add(Constants.SocialMediaSites.blogger);
        sites.add(Constants.SocialMediaSites.diigo);
        sut.setSharePageMediaSites(sites);
        
        assertTrue(sut.getRenderPreFooter().contains("\"showShare\":[\"blogger\",\"diigo\"]"),
                "PreFooter rendering: Not rendered as expected. (" + sut.getRenderPreFooter() + ")");
    }
}