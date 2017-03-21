package goc.webtemplate.jsp.samplebeans;

import goc.webtemplate.Breadcrumb;
import goc.webtemplate.component.jsp.DefaultTemplateBean;

public class BreadcrumbsSampleBean extends DefaultTemplateBean {

	@Override
	public void setBreadcrumbsList() {
		this.breadCrumbsList.add(new Breadcrumb("http://www.canada.ca/en/index.html", "Home", ""));
		this.breadCrumbsList.add(new Breadcrumb("http://www.esdc.gc.ca/en/jobs/opportunities/index.page", "Jobs", ""));
		this.breadCrumbsList.add(new Breadcrumb("http://www.esdc.gc.ca/en/jobs/opportunities/youth_students.page", "Opportunities", ""));
		this.breadCrumbsList.add(new Breadcrumb("", "FSWEP", "Federal Student Work Experience Program"));
	}
}
