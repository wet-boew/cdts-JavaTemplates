package goc.webtemplate;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Breadcrumb implements Serializable {
    private String href = "";
    private String title = "";
    private String acronym = "";
    
    public Breadcrumb() {
    	this("", "", "");
    }
    
    public Breadcrumb(String href, String title, String acronym) {
		this.href = href;
		this.title = title;
		this.acronym = acronym;
    }
    
    public void setHref(String href) { this.href = href; }
    public String getHref() { return this.href; }
    
    public void setTitle(String title) { this.title = title; }
    public String getTitle() { return this.title; }
    
    public void setAcronym(String acronym) { this.acronym = acronym; }
    public String getAcronym() { return this.acronym; }
}
