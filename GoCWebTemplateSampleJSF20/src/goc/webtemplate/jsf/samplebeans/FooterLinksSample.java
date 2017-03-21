package goc.webtemplate.jsf.samplebeans;

import goc.webtemplate.Link;
import goc.webtemplate.component.jsf.DefaultTemplateBean;

public class FooterLinksSample extends DefaultTemplateBean {

	@Override
	public void setAboutLinks() { 
		// About Links
		this.aboutLinks.add(new Link("https://www.facebook.com", "Facebook"));
		this.aboutLinks.add(new Link("http://www.lapresse.ca/", "LaPresse"));
	}

	@Override
	public void setContactLinks() { 
		// Contact Links
		this.contactLinks.add(new Link("http://travel.gc.ca/", "Travel"));
		this.contactLinks.add(new Link("http://healthycanadians.gc.ca/index-eng.php", "Health"));
		this.contactLinks.add(new Link("http://jobs-emplois.gc.ca/index-eng.htm", "Jobs"));
	}

	@Override
	public void setNewsLinks() { 
		// News Links
		this.newsLinks.add(new Link("http://www.cbc.ca/news/canada", "CBC"));
		this.newsLinks.add(new Link("http://www.cnn.com/", "CNN"));
	}
}
