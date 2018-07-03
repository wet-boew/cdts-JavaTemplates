package goc.webtemplate.jsp.samplebeans;

import java.util.ArrayList;

import goc.webtemplate.Breadcrumb;

import goc.webtemplate.component.jsp.DefaultTemplateCoreBean;

public class BreadcrumbsSampleBean extends DefaultTemplateCoreBean {

    @Override
    public void onWebTemplateInitialize() {
        ArrayList<Breadcrumb>   bcs = new ArrayList<Breadcrumb>();
        
        bcs.add(new Breadcrumb("http://www.canada.ca/en/index.html", "Home", ""));
        bcs.add(new Breadcrumb("http://www.esdc.gc.ca/en/jobs/opportunities/index.page", "Jobs", ""));
        bcs.add(new Breadcrumb("http://www.esdc.gc.ca/en/jobs/opportunities/youth_students.page", "Opportunities", ""));
        bcs.add(new Breadcrumb("", "FSWEP", "Federal Student Work Experience Program"));
        
        this.setBreadcrumbs(bcs);
    }
}
