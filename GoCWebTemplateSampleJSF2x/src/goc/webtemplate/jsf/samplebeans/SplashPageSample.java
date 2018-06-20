package goc.webtemplate.jsf.samplebeans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import goc.webtemplate.SplashPageInfo;
import goc.webtemplate.component.jsf.DefaultTemplateCoreBean;

@Named("splashpagesamplebean")
@RequestScoped
public class SplashPageSample extends DefaultTemplateCoreBean {

    @Override
    public void onWebTemplateInitialize() {
        this.setSplashPageInfo(new SplashPageInfo(
                "/GoCWebTemplateSampleJSF2x/faces/index.xhtml",
                "/GoCWebTemplateSampleJSF2x/faces/index.xhtml",
                "http://www.canada.ca/en/transparency/terms.html",
                "http://www.canada.ca/fr/transparence/avis.html",
                "[My web asset]",
                "[Mon actif web]"
            ));
    }
}