package goc.webtemplate.spring.samplebeans;

import org.springframework.stereotype.Component;

import goc.webtemplate.SplashPageInfo;
import goc.webtemplate.component.spring.DefaultTemplateCoreBean;

@Component(value = "splashpagesamplebean")
public class SplashPageSampleBean extends DefaultTemplateCoreBean {

    @Override
    public void onWebTemplateInitialize() {
        this.setSplashPageInfo(new SplashPageInfo(
                "index",
                "index",
                "http://www.canada.ca/en/transparency/terms.html",
                "http://www.canada.ca/fr/transparence/avis.html",
                "[My web asset]",
                "[Mon actif web]"
            ));
    }
}
