package goc.webtemplate.spring.samplebeans;

import goc.webtemplate.component.spring.DefaultTemplateCoreBean;

public class NestedMasterPageSampleBean extends DefaultTemplateCoreBean {

    @Override
    public void onWebTemplateInitialize() {
        this.setDateModified(new java.util.Date());
    }
}
