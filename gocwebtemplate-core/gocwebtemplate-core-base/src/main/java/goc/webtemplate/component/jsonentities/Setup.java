package goc.webtemplate.component.jsonentities;

import java.io.Serializable;

import java.util.List;

import com.google.gson.annotations.SerializedName;

/**
 * Objects of this class are meant to be serialized to a JSON object to be
 * passed as parameter to the 'wet.builder.setup' JavaScript function in the
 * template pages.
 */
public class Setup implements Serializable {
    private static final long serialVersionUID = 1L;

    public static enum Mode {
        @SerializedName("common")
        COMMON,
        @SerializedName("app")
        APP,
        @SerializedName("server")
        SERVER,
        @SerializedName("splash")
        SPLASH
    }

    private String cdnEnv;
    private Mode mode;
    private SetupBase base;
    private ITop top;
    private IPreFooter preFooter;
    private IFooter footer;
    private SecMenu secmenu; //all lowercase to fit with CDTS parameter
    private Splash splash;
    private List<String> onCDTSPageFinalized;

    public Setup() {
    }

    public Setup(String cdnEnv, Mode mode, SetupBase base, ITop top, IPreFooter preFooter, IFooter footer, SecMenu secmenu,
            Splash splash, List<String> onCDTSPageFinalized) {
        this.cdnEnv = cdnEnv;
        this.mode = mode;
        this.base = base;
        this.top = top;
        this.preFooter = preFooter;
        this.footer = footer;
        this.secmenu = secmenu;
        this.splash = splash;
        this.onCDTSPageFinalized = onCDTSPageFinalized;
    }

    public String getCdnEnv() {
        return cdnEnv;
    }

    public void setCdnEnv(String cdnEnv) {
        this.cdnEnv = cdnEnv;
    }

    public Mode getMode() {
        return this.mode;
    }

    public void setMode(Mode value) {
        this.mode = value;
    }

    public SetupBase getBase() {
        return this.base;
    }

    public void setBase(SetupBase value) {
        this.base = value;
    }

    public ITop getTop() {
        return top;
    }

    public void setTop(ITop top) {
        this.top = top;
    }

    public IPreFooter getPreFooter() {
        return preFooter;
    }

    public void setPreFooter(IPreFooter preFooter) {
        this.preFooter = preFooter;
    }

    public IFooter getFooter() {
        return footer;
    }

    public void setFooter(IFooter footer) {
        this.footer = footer;
    }

    public SecMenu getSecmenu() {
        return secmenu;
    }

    public void setSecmenu(SecMenu secmenu) {
        this.secmenu = secmenu;
    }

    public Splash getSplash() {
        return splash;
    }

    public void setSplash(Splash splash) {
        this.splash = splash;
    }

    public List<String> getOnCDTSPageFinalized() {
        return onCDTSPageFinalized;
    }

    public void setOnCDTSPageFinalized(List<String> onCDTSPageFinalized) {
        this.onCDTSPageFinalized = onCDTSPageFinalized;
    }
}
