package goc.webtemplate.component.jsonentities;

import java.io.Serializable;

/**
 * Objects of this class are meant to be serialized to a JSON object to be passed
 * as parameter to the 'wet.builder.preFooter' JavaScript function in the template
 * pages for the UnilingualError master/template.
 */
public class UnilingualErrorPreFooter implements Serializable, IPreFooter {
    private static final long serialVersionUID = 1L;

    private String  cdnEnv;
    /**
     * NOTE: All lowercase to respect the CDTS's naming
     */
    private boolean pagedetails;

    public UnilingualErrorPreFooter() {
    }

    public UnilingualErrorPreFooter(String cdnEnv, boolean pageDetails) {
        this.cdnEnv = cdnEnv;
        this.pagedetails = pageDetails;
    }

    public String getCdnEnv() {
        return cdnEnv;
    }

    public void setCdnEnv(String cdnEnv) {
        this.cdnEnv = cdnEnv;
    }

    public boolean isPagedetails() {
        return pagedetails;
    }

    public void setPagedetails(boolean pagedetails) {
        this.pagedetails = pagedetails;
    }
}
