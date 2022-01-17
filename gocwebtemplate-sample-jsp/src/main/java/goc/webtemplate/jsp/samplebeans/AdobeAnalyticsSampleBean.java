package goc.webtemplate.jsp.samplebeans;

import goc.webtemplate.WebAnalyticsInfo;
import goc.webtemplate.component.jsp.DefaultTemplateCoreBean;

public class AdobeAnalyticsSampleBean extends DefaultTemplateCoreBean {

    @Override
    public void onWebTemplateInitialize() {
        //Set to true too activate Adobe Analytics
        this.setWebAnalytics(new WebAnalyticsInfo(false, WebAnalyticsInfo.EnvironmentOption.STAGING, 1));
    }
}