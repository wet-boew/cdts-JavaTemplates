package goc.webtemplate.component.jsonentities;

import java.io.Serializable;

import java.util.List;

import goc.webtemplate.Breadcrumb;
import goc.webtemplate.LanguageLink;
import goc.webtemplate.Link;

/**
 * Objects of this class are meant to be serialized to a JSON object to be passed
 * as parameter to the 'wet.builder.appTop' JavaScript function in the template
 * pages. 
 */
public class AppTop implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String              cdnEnv;
    private String              subTheme;
    private String              localPath;
    private String              appName;
    private String              appUrl;
    private List<Link>          intranetTitle;
    
    private String              menuPath;
    private List<LanguageLink>  lngLinks;
    private boolean             siteMenu;
    
    private boolean             secure;
    /**
     * This is a List but should only have one item in it.
     */
    private List<Link>          signIn;
    /**
     * This is a List but should only have one item in it.
     */
    private List<Link>          signOut;
    private boolean             search;
    private List<Breadcrumb>    breadcrumbs;
    private boolean             showPreContent;
    private String              customSearch;
    /**
     * Must be true if there is a left section/left side menu, otherwise must be false.
     */
    private boolean             topSecMenu;

    public AppTop()
    {
    }

    public AppTop(String cdnEnv, String subTheme, String localPath, String appName, String appUrl, List<Link> intranetTitle, String menuPath,
            List<LanguageLink> lngLinks, boolean siteMenu, boolean secure, List<Link> signIn, List<Link> signOut,
            boolean search, List<Breadcrumb> breadcrumbs, boolean showPreContent, String customSearch, boolean topSecMenu) {
        this.cdnEnv = cdnEnv;
        this.subTheme = subTheme;
        this.localPath = localPath;
        this.appName = appName;
        this.appUrl = appUrl;
        this.intranetTitle = intranetTitle;
        this.menuPath = menuPath;
        this.lngLinks = lngLinks;
        this.siteMenu = siteMenu;
        this.secure = secure;
        this.signIn = signIn;
        this.signOut = signOut;
        this.search = search;
        this.breadcrumbs = breadcrumbs;
        this.showPreContent = showPreContent;
        this.customSearch = customSearch;
        this.topSecMenu = topSecMenu;
    }

    public String getCdnEnv() {
        return cdnEnv;
    }

    public void setCdnEnv(String cdnEnv) {
        this.cdnEnv = cdnEnv;
    }

    public String getSubTheme() {
        return subTheme;
    }

    public void setSubTheme(String subTheme) {
        this.subTheme = subTheme;
    }

    public String getLocalPath() {
        return localPath;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }
    
    public String getAppUrl() {
        return appUrl;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }

    public List<Link> getIntranetTitle() {
        return intranetTitle;
    }

    public void setIntranetTitle(List<Link> intranetTitle) {
        this.intranetTitle = intranetTitle;
    }

    public String getMenuPath() {
        return menuPath;
    }

    public void setMenuPath(String menuPath) {
        this.menuPath = menuPath;
    }

    public List<LanguageLink> getLngLinks() {
        return lngLinks;
    }

    public void setLngLinks(List<LanguageLink> lngLinks) {
        this.lngLinks = lngLinks;
    }

    public boolean isSiteMenu() {
        return siteMenu;
    }

    public void setSiteMenu(boolean siteMenu) {
        this.siteMenu = siteMenu;
    }

    public boolean isSecure() {
        return secure;
    }

    public void setSecure(boolean secure) {
        this.secure = secure;
    }

    public List<Link> getSignIn() {
        return signIn;
    }

    public void setSignIn(List<Link> signIn) {
        this.signIn = signIn;
    }

    public List<Link> getSignOut() {
        return signOut;
    }

    public void setSignOut(List<Link> signOut) {
        this.signOut = signOut;
    }

    public boolean isSearch() {
        return search;
    }

    public void setSearch(boolean search) {
        this.search = search;
    }

    public List<Breadcrumb> getBreadcrumbs() {
        return breadcrumbs;
    }

    public void setBreadcrumbs(List<Breadcrumb> breadcrumbs) {
        this.breadcrumbs = breadcrumbs;
    }

    public boolean isShowPreContent() {
        return showPreContent;
    }

    public void setShowPreContent(boolean showPreContent) {
        this.showPreContent = showPreContent;
    }
    
    public String getCustomSearch() {
        return this.customSearch;
    }
    
    public void setCustomSearch(String customSearch) {
        this.customSearch = customSearch;
    }

    public boolean isTopSecMenu() {
        return topSecMenu;
    }

    public void setTopSecMenu(boolean topSecMenu) {
        this.topSecMenu = topSecMenu;
    }
}
