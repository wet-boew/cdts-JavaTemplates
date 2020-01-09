package goc.webtemplate.component.jsonentities;

import java.io.Serializable;
import java.util.List;

import goc.webtemplate.Breadcrumb;
import goc.webtemplate.CustomSearch;
import goc.webtemplate.IntranetTitle;
import goc.webtemplate.LanguageLink;

/**
 * Objects of this class are meant to be serialized to a JSON object to be passed
 * as parameter to the 'wet.builder.top' JavaScript function in the template
 * pages. 
 */
public class Top implements Serializable {
    private static final long serialVersionUID = 1L;

    private String              cdnEnv;
    private String              subTheme;
    private List<IntranetTitle> intranetTitle;
    private boolean             search;
    private List<LanguageLink>  lngLinks;
    private boolean             showPreContent;
    private List<Breadcrumb>    breadcrumbs;
    private String              localPath;
    private boolean             siteMenu;
    /**
     * Must be true if there is a left section/left side menu, otherwise must be false.
     */
    private boolean             topSecMenu;
    private List<CustomSearch>  customSearch;
    
    public Top() {
    }

    public Top(String cdnEnv, String subTheme, List<IntranetTitle> intranetTitle, boolean search, List<LanguageLink> lngLinks,
            boolean showPreContent, List<Breadcrumb> breadcrumbs, String localPath, boolean siteMenu,
            boolean topSecMenu, List<CustomSearch> customSearch) {
        this.cdnEnv = cdnEnv;
        this.subTheme = subTheme;
        this.intranetTitle = intranetTitle;
        this.search = search;
        this.lngLinks = lngLinks;
        this.showPreContent = showPreContent;
        this.breadcrumbs = breadcrumbs;
        this.localPath = localPath;
        this.siteMenu = siteMenu;
        this.topSecMenu = topSecMenu;
        this.customSearch = customSearch;
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

    public List<IntranetTitle> getIntranetTitle() {
        return intranetTitle;
    }

    public void setIntranetTitle(List<IntranetTitle> intranetTitle) {
        this.intranetTitle = intranetTitle;
    }

    public boolean isSearch() {
        return search;
    }

    public void setSearch(boolean search) {
        this.search = search;
    }

    public List<LanguageLink> getLngLinks() {
        return lngLinks;
    }

    public void setLngLinks(List<LanguageLink> lngLinks) {
        this.lngLinks = lngLinks;
    }

    public boolean isShowPreContent() {
        return showPreContent;
    }

    public void setShowPreContent(boolean showPreContent) {
        this.showPreContent = showPreContent;
    }

    public List<Breadcrumb> getBreadcrumbs() {
        return breadcrumbs;
    }

    public void setBreadcrumbs(List<Breadcrumb> breadcrumbs) {
        this.breadcrumbs = breadcrumbs;
    }

    public String getLocalPath() {
        return localPath;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }

    public boolean isSiteMenu() {
        return siteMenu;
    }

    public void setSiteMenu(boolean siteMenu) {
        this.siteMenu = siteMenu;
    }

    public boolean isTopSecMenu() {
        return topSecMenu;
    }

    public void setTopSecMenu(boolean topSecMenu) {
        this.topSecMenu = topSecMenu;
    }

    public List<CustomSearch> getCustomSearch() {
        return customSearch;
    }

    public void setCustomSearch(List<CustomSearch> customSearch) {
        this.customSearch = customSearch;
    }    
}
