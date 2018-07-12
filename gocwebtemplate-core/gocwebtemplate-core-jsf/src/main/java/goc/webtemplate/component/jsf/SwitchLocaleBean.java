package goc.webtemplate.component.jsf;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import goc.webtemplate.component.BaseUtil;

public class SwitchLocaleBean {
	public void switchLang() {
		try {
			BaseUtil.doLocaleSwitch((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest(), 
									 (HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse());
		}
		catch (Exception ex) {}
	}
}
