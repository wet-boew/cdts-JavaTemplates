package goc.webtemplate.jsp.samplebeans;

import goc.webtemplate.SplashPageInfo;
import goc.webtemplate.component.jsp.DefaultTemplateCoreBean;

public class SplashPageSampleBean extends DefaultTemplateCoreBean {

    @Override
    public void onWebTemplateInitialize() {
        this.setSplashPageInfo(new SplashPageInfo(
                    "index.action",
                    "index.action",
                    "http://www.canada.ca/en/transparency/terms.html",
                    "http://www.canada.ca/fr/transparence/avis.html",
                    "[My web asset]",
                    "[Mon actif web]"
                ));
    }
}
