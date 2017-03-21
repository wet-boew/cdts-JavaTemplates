package goc.webtemplate.jsf.samplebeans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named("nestedchildpagesamplebean")
@RequestScoped
public class NestedChildPageSample extends NestedMasterPageSample {
	@Override
	public void setHeaderTitle() {
		this.headerTitle = "Nested Master Page Sample";
	}
}
