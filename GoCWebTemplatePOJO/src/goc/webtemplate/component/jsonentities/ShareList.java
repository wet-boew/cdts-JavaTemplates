package goc.webtemplate.component.jsonentities;

import java.io.Serializable;

import java.util.ArrayList;

import goc.webtemplate.Constants;

public class ShareList implements Serializable {
    private static final long serialVersionUID = 1L;

    private boolean                                 shown;
    private ArrayList<Constants.SocialMediaSites>   sites;
    
    public ShareList() {
    }
    
    public ShareList(boolean shown, ArrayList<Constants.SocialMediaSites> sites) {
        this.shown = shown;
        this.sites = sites;
    }

    public boolean isShown() {
        return shown;
    }

    public void setShown(boolean shown) {
        this.shown = shown;
    }

    public ArrayList<Constants.SocialMediaSites> getSites() {
        return sites;
    }

    public void setSites(ArrayList<Constants.SocialMediaSites> sites) {
        this.sites = sites;
    }
}
