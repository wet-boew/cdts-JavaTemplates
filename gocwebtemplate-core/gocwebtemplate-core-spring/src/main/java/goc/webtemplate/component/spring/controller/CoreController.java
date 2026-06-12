package goc.webtemplate.component.spring.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import goc.webtemplate.component.BaseUtil;

@Controller
public class CoreController {
	@GetMapping("/gocwebtemplate_switchlocale")
	public void SwitchLocale(HttpServletRequest request, HttpServletResponse response) throws Exception {
		BaseUtil.doLocaleSwitch(request, response);
	}	
}
