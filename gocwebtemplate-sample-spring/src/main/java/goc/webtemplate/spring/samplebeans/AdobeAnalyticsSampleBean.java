package goc.webtemplate.spring.samplebeans;

import org.springframework.stereotype.Component;

import goc.webtemplate.WebAnalyticsInfo;
import goc.webtemplate.component.spring.DefaultTemplateCoreBean;

@Component(value = "adobeanalyticssamplebean")
public class AdobeAnalyticsSampleBean extends DefaultTemplateCoreBean {

    @Override
    public void onWebTemplateInitialize() {
        //Set to true too activate Adobe Analytics
    	this.setWebAnalytics(new WebAnalyticsInfo(false, WebAnalyticsInfo.EnvironmentOption.STAGING, 1));
    }
}
