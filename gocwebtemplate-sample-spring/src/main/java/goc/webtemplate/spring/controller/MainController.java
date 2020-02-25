package goc.webtemplate.spring.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import goc.webtemplate.component.BaseUtil;

@Controller
public class MainController {

	@GetMapping("/AddJSandCSSFilesSample")
	public String AddJsAndCssFilesSample() {
		return "AddJSandCSSFilesSample";
	}
	
	@GetMapping("/ApplicationSample")
	public String ApplicationSample() {
		return "ApplicationSample";
	}
	
	@GetMapping("/BreadcrumbSample")
	public String BreadcrumbSample() {
		return "BreadcrumbSample";
	}
	
	@GetMapping("/BaseSettingsSample")
	public String BaseSettingsSample() {
		return "BaseSettingsSample";
	}
	
	@GetMapping("/ErrorBilingualSample")
	public String ErrorBilingualSample() {
		return "ErrorBilingualSample";
	}
	
	@GetMapping("/ErrorSample")
	public String ErrorSample() {
		return "ErrorSample";
	}
	
	@GetMapping("/ExtendedBasePageSample")
	public String ExtendedBasePageSample() {
		return "ExtendedBasePageSample";
	}
	
	@GetMapping("/FeedbackAndShareThisPageSample")
	public String FeedbackAndShareThisPageSample() {
		return "FeedbackAndShareThisPageSample";
	}
	
	@GetMapping("/FooterLinksSample")
	public String FooterLinksSample() {
		return "FooterLinksSample";
	}
	
	@GetMapping("/IntranetSample")
	public String IntranetSample() {
		return "IntranetSample";
	}
	
	@GetMapping("/LeavingSecureSiteSample")
	public String LeavingSecureSiteSample() {
		return "LeavingSecureSiteSample";
	}
	
	@GetMapping("/LeftSideMenuSample")
	public String LeftSideMenuSample() {
		return "LeftSideMenuSample";
	}
	
	@GetMapping("/NestedMasterPageSample")
	public String NestedMasterPageSample() {
		return "NestedMasterPageSample";
	}
	
	@GetMapping("/SplashPageSample")
	public String SplashPageSample() {
		return "SplashPageSample";
	}
	
	@GetMapping("/SessionTimeoutSample")
	public String SessionTimeoutSample() {
		return "SessionTimeoutSample";
	}
	
	@GetMapping("/SessionTimeoutValidity")
	public String SessionTimeoutValidity() {
		return "SessionTimeoutValidity";
	}
	
	@GetMapping("/SessionTimeoutLogout")
	public void SessionTimeoutLogout(HttpServletRequest request, HttpServletResponse response) throws Exception { 
		HttpSession session = request.getSession(false);
		if (session != null) session.invalidate();
		response.sendRedirect("AddJSandCSSFilesSample");
	}
	
	@GetMapping("/TransactionalSample")
	public String TransactionalSample() {
		return "TransactionalSample";
	}
}
