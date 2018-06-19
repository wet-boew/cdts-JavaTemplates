package goc.webtemplate.jsf.samplebeans;

import goc.webtemplate.SplashPageInfo;
import goc.webtemplate.component.jsf.DefaultTemplateCoreBean;

public class SplashPageSample extends DefaultTemplateCoreBean {

    @Override
    public void onWebTemplateInitialize() {
        this.setSplashPageInfo(new SplashPageInfo(
                "/GoCWebTemplateSampleJSF20/faces/index.xhtml",
                "/GoCWebTemplateSampleJSF20/faces/index.xhtml",
                "http://www.canada.ca/en/transparency/terms.html",
                "http://www.canada.ca/fr/transparence/avis.html",
                "[My web asset]",
                "[Mon actif web]"
            ));
    }
}