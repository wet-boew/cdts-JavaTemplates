package goc.webtemplate.component.spring;

import java.io.FileInputStream;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import goc.webtemplate.Constants;

@Component(value = "applicationscopebean")
public class MasterApplicationBean {

	@Autowired private HttpServletRequest req = null;
	
	public String getStaticFile(String filePath, String wetTheme, String fileName) {
		if (this.req == null) return "";
		
		String info = "";
		String cacheKey = Constants.CACHE_KEY_STATICFILES_PREFIX + "." + wetTheme + "." + fileName;
		
		try {
			info = (this.req.getSession().getServletContext().getAttribute(cacheKey) == null ? null : (String)this.req.getSession().getServletContext().getAttribute(cacheKey));
			if (info != null) return info;
			
			synchronized(this) {
				info = (this.req.getSession().getServletContext().getAttribute(cacheKey) == null ? null : (String)this.req.getSession().getServletContext().getAttribute(cacheKey));
				if (info != null) return info;
				
				if (filePath.startsWith("/")) {
					InputStreamReader isrdr = null;
					java.io.InputStream is = null;
					try { 
						is = this.getClass().getResourceAsStream(filePath + "/" + wetTheme + "/" + fileName);
						if (is == null) is = this.req.getSession().getServletContext().getResourceAsStream(filePath + "/" + wetTheme + "/" + fileName);
						if (is == null) is = this.getClass().getResourceAsStream(Constants.STATIC_FALLBACK_FILES_INTERNAL_PATH + "/" + wetTheme + "/" + fileName);
						isrdr = new InputStreamReader(is, "UTF-8");
						StringBuilder sb = new StringBuilder();
						int ch = isrdr.read();
						while (ch >= 0) {
							sb.append((char)ch);
							ch = isrdr.read();
						}
						info = sb.toString();
						this.req.getSession().getServletContext().setAttribute(cacheKey, info);
					} catch(Exception ex) {
					} finally {
						if (isrdr != null) {
							try {
								isrdr.close();
							} catch (Exception ex) {}
						}
					}
				} else { 
					InputStreamReader isrdr = null;
					try { 
						isrdr = new InputStreamReader(new FileInputStream(filePath + "/" + wetTheme + "/" + fileName), "UTF-8");
						StringBuilder sb = new StringBuilder();
						int ch = isrdr.read();
						while (ch >= 0) {
							sb.append((char)ch);
							ch = isrdr.read();
						}
						info = sb.toString();
						this.req.getSession().getServletContext().setAttribute(cacheKey, info);
					} catch(Exception ex) {
					} finally {
						if (isrdr != null) {
							try {
								isrdr.close();
							} catch (Exception ex) {}
						}
					}
				}
			}
		} catch (Exception ex) { }
		
		return info;
	}
}