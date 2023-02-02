package goc.webtemplate.component.jsonentities;

import goc.webtemplate.FooterLink;

public class FooterLinkContext {

    private boolean showFooter;
    private FooterLink footerLink;

    public FooterLinkContext() {}

    public FooterLinkContext(boolean showFooter, FooterLink footerLink) {
        this.showFooter = showFooter;
        this.footerLink = footerLink;
    }

    public boolean isShowFooter() {
        return showFooter;
    }

    public void setShowFooter(boolean showFooter) {
        this.showFooter = showFooter;
    }

    public FooterLink getFooterLink() {
        return footerLink;
    }

    public void setFooterLink(FooterLink footerLink) {
        this.footerLink = footerLink;
    }
}
