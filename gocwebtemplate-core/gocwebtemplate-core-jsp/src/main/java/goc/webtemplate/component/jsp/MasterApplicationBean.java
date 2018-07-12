package goc.webtemplate.component.jsp;

import goc.webtemplate.Constants;

import java.io.FileInputStream;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;

public class MasterApplicationBean {
	private HttpServletRequest req = null;
	
	public void setRequest(HttpServletRequest req) { this.req = req; }
	public HttpServletRequest getRequest() { return this.req; }
	
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
					try { 
						isrdr = new InputStreamReader(this.req.getSession().getServletContext().getResourceAsStream(filePath + "/" + wetTheme + "/" + fileName), "UTF-8");
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
