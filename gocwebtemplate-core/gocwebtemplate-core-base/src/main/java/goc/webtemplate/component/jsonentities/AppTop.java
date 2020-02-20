package goc.webtemplate.component.jsonentities;

import java.io.Serializable;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import goc.webtemplate.Breadcrumb;
import goc.webtemplate.CustomSearch;
import goc.webtemplate.IntranetTitle;
import goc.webtemplate.LanguageLink;
import goc.webtemplate.Link;

/**
 * Objects of this class are meant to be serialized to a JSON object to be passed
 * as parameter to the 'wet.builder.appTop' JavaScript function in the template
 * pages. 
 * 
 * NOTE: For v4.0.27+ we have to render AppTop differently depending on the theme,
 *       GCIntranet has extra properties. So, see also AppTopGCIntranet at the 
 *       bottom of this file.         
 */
public class AppTop implements Serializable { 
    private static final long serialVersionUID = 1L;
    
    private String              cdnEnv;
    private String              subTheme;
    private String              localPath;
    
    private List<Link>          appName;
    private String              menuPath;
    private List<SecMenuItem>   menuLinks;
    private List<LanguageLink>  lngLinks;
    
    /**
     * This is a List but should only have one item in it.
     */
    private List<Link>          signIn;
    /**
     * This is a List but should only have one item in it.
     */
    private List<Link>          signOut;
    /**
     * This is a List but should only have one item in it.
     */
    private List<Link>          appSettings;
    private boolean             search;
    private List<Breadcrumb>    breadcrumbs;
    private boolean             showPreContent;
    private List<CustomSearch>  customSearch;
    /**
     * Must be true if there is a left section/left side menu, otherwise must be false.
     */
    private boolean             topSecMenu;

    public AppTop()
    {
    }

    public AppTop(String cdnEnv, String subTheme, String localPath, List<Link> appName, String menuPath,
            List<SecMenuItem> menuLinks, List<LanguageLink> lngLinks, List<Link> signIn, List<Link> signOut, List<Link> appSettings,
            boolean search, List<Breadcrumb> breadcrumbs, boolean showPreContent, List<CustomSearch> customSearch, boolean topSecMenu) {
        this.cdnEnv = cdnEnv;
        this.subTheme = subTheme;
        this.localPath = localPath;
        this.appName = appName;
        this.menuPath = menuPath;
        this.menuLinks = menuLinks;
        this.lngLinks = lngLinks;
        this.signIn = signIn;
        this.signOut = signOut;
        this.appSettings = appSettings;
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

    public List<Link> getAppName() {
        return this.appName;
    }
    
    public void setAppName(List<Link> value) {
        this.appName = value;
    }
    
    public String getMenuPath() {
        return menuPath;
    }

    public void setMenuPath(String menuPath) {
        this.menuPath = menuPath;
    }

    public List<SecMenuItem> getMenuLinks() {
        return menuLinks;
    }

    public void setMenuLinks(List<SecMenuItem> menuLinks) {
        this.menuLinks = menuLinks;
    }

    public List<LanguageLink> getLngLinks() {
        return lngLinks;
    }

    public void setLngLinks(List<LanguageLink> lngLinks) {
        this.lngLinks = lngLinks;
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

    public List<Link> getAppSettings() {
        return appSettings;
    }

    public void setAppSettings(List<Link> appSettings) {
        this.appSettings = appSettings;
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
    
    public List<CustomSearch> getCustomSearch() {
        return this.customSearch;
    }
    
    public void setCustomSearch(List<CustomSearch> customSearch) {
        this.customSearch = customSearch;
    }

    public boolean isTopSecMenu() {
        return topSecMenu;
    }

    public void setTopSecMenu(boolean topSecMenu) {
        this.topSecMenu = topSecMenu;
    }

    
    /**
     * For v4.0.27+ we have to render AppTop differently depending on the theme,
     * GCIntranet has extra properties.
     * 
     * This is the GCIntranet-specific implementation
     */
    public static class AppTopGCIntranet extends AppTop
    {
        private static final long serialVersionUID = 1L;
        
        private List<IntranetTitle>          intranetTitle;
        
        @SerializedName("GCToolsModal")
        private boolean                      gcToolsModal;
        
        public AppTopGCIntranet()
        {
        }

        public AppTopGCIntranet(String cdnEnv, String subTheme, String localPath, List<Link> appName, String menuPath,
                List<SecMenuItem> menuLinks, List<LanguageLink> lngLinks, List<Link> signIn, List<Link> signOut, List<Link> appSettings,
                boolean search, List<Breadcrumb> breadcrumbs, boolean showPreContent, List<CustomSearch> customSearch, boolean topSecMenu, 
                List<IntranetTitle> intranetTitle, boolean gcToolsModal) {
            
            super(cdnEnv, subTheme, localPath, appName, menuPath,
                    menuLinks, lngLinks, signIn, signOut, appSettings,
                    search, breadcrumbs, showPreContent, customSearch, topSecMenu);
            
            this.intranetTitle = intranetTitle;
            this.gcToolsModal = gcToolsModal;
        }
        
        public List<IntranetTitle> getIntranetTitle() {
            return intranetTitle;
        }

        public void setIntranetTitle(List<IntranetTitle> intranetTitle) {
            this.intranetTitle = intranetTitle;
        }
        
        public boolean getGcToolsModal() {
            return gcToolsModal;
        }
        
        public void setGcToolsModal(boolean value) {
            this.gcToolsModal = value;
        }
    }    
}
