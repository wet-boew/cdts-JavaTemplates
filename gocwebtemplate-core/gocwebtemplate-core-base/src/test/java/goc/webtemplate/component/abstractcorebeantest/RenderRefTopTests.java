package goc.webtemplate.component.abstractcorebeantest;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import goc.webtemplate.WebAnalyticsInfo;

public class RenderRefTopTests {

    @Test
    public void webAnalyticsRenders() {
        AbstractCoreBeanImpl sut = new AbstractCoreBeanImpl();
        
        sut.setWebAnalytics(new WebAnalyticsInfo(true, WebAnalyticsInfo.EnvironmentOption.STAGING, 1));
        
        assertTrue("RefFooter rendering: WebAnalytics not rendered as expected (" + sut.getRenderRefTop() + ").", 
                sut.getRenderRefTop().contains("\"webAnalytics\":[{\"environment\":\"staging\",\"version\":1}]"));
    }
    
    @Test
    public void webAnalyticsFromConfig() {
        AbstractCoreBeanImpl sut = new AbstractCoreBeanImpl();
        
        //(Test test cdn.properties files default WebAnalytics to true)
        assertTrue("RefFooter rendering: WebAnalytics not rendered as expected (" + sut.getRenderRefTop() + ").", 
                sut.getRenderRefTop().contains("\"webAnalytics\":"));
    }
}
