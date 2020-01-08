package goc.webtemplate.component.abstractcorebeantest;

import java.util.ResourceBundle;

import goc.webtemplate.component.AbstractCoreBean;

/**
 *  A dummy implementation of AbstractCoreBean used for testing
 */
public class AbstractCoreBeanImpl extends AbstractCoreBean {

    @Override
    public ResourceBundle getResourceBundle() {
        return ResourceBundle.getBundle("goc.webtemplate.global.config.cdn"); //loaded from src/test/resources
    }

    @Override
    public String getResourceBundleString(String resourceBundleName, String resourceBundleKey) {
        try {
            return (java.util.ResourceBundle.getBundle("goc.webtemplate.global.config." + resourceBundleName)).getString(resourceBundleKey);
        }
        catch (Exception ex) {
            return "";
        }
    }

    @Override
    public String getTwoLetterCultureLanguage() {
        return "en";
    }

    @Override
    protected String getDefaultLanguageLinkUrl() {
        return "language.action";  //a dummy value
    }

    @Override
    protected String getDefaultLeaveSecureSiteRedirectUrl() {
        return "leave.action"; //a dummy value
    }

    @Override
    public void onWebTemplateInitialize() {
    }
}
