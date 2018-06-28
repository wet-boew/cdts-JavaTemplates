package goc.webtemplate.component.jsf;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Hashtable;

import javax.faces.context.FacesContext;

import goc.webtemplate.Constants;

public class MasterApplicationBean {
	private Hashtable<String, String> staticFileCache = new Hashtable<String, String>();
	
	public String getStaticFile(String filePath, String wetTheme, String fileName) {
		String info = "";
		String cacheKey = Constants.CACHE_KEY_STATICFILES_PREFIX + "." + fileName;
		
		try {
			info = this.staticFileCache.get(cacheKey);
			if (info != null) return info;
			
			synchronized(this) {
				info = (String)this.staticFileCache.get(cacheKey);
				if (info != null) return info;
				
				if (filePath.startsWith("/")) {
					InputStreamReader isrdr = null;
					try { 
						isrdr = new InputStreamReader(FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream(filePath + "/" + wetTheme + "/" + fileName), "UTF-8");
						StringBuilder sb = new StringBuilder();
						int ch = isrdr.read();
						while (ch >= 0) {
							sb.append((char)ch);
							ch = isrdr.read();
						}
						info = sb.toString();
						this.staticFileCache.put(cacheKey, info);
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
						this.staticFileCache.put(cacheKey, info);
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
		} catch (Exception ex) {}
		
		return info;
	}
}
