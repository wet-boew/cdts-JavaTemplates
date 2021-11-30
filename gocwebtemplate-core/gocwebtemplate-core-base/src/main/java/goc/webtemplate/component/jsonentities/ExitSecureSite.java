package goc.webtemplate.component.jsonentities;

import java.io.Serializable;

import goc.webtemplate.LeavingSecureSiteWarning;

@SuppressWarnings("serial")
public class ExitSecureSite implements Serializable {
    private boolean exitScript;
    private boolean displayModal;
    private boolean displayModalForNewWindow = true;
    private String msgBoxHeader;
    private String exitMsg;
    private String exitURL;
    private String exitDomains;
    private String cancelMsg;
    private String yesMsg;
    private String targetWarning;

    public ExitSecureSite() {}

    public ExitSecureSite(boolean exitScript, boolean displayModal, boolean displayModalForNewWindow,
            String msgBoxHeader, String exitMsg, String exitURL, String exitDomains, String cancelMsg, String yesMsg,
            String targetWarning) {
        this.exitScript = exitScript;
        this.displayModal = displayModal;
        this.displayModalForNewWindow = displayModalForNewWindow;
        this.msgBoxHeader = msgBoxHeader;
        this.exitMsg = exitMsg;
        this.exitURL = exitURL;
        this.exitDomains = exitDomains;
        this.cancelMsg = cancelMsg;
        this.yesMsg = yesMsg;
        this.targetWarning = targetWarning;
    }
    
    public ExitSecureSite(LeavingSecureSiteWarning lssw) {
        this.exitScript = lssw.isEnabled();
        this.displayModal = lssw.getDisplayModalWindow();
        this.displayModalForNewWindow = lssw.getDisplayModalForNewWindow();
        this.msgBoxHeader = lssw.getMsgBoxHeader();
        this.exitMsg = lssw.getMessage();
        this.exitURL = lssw.getRedirectUrl();
        this.exitDomains = lssw.getExcludedDomains();
        this.cancelMsg = lssw.getCancelMessage();
        this.yesMsg = lssw.getYesMessage();
        this.targetWarning = lssw.getTargetWarning();
    }

    public boolean isExitScript() {
        return exitScript;
    }
    public void setExitScript(boolean exitScript) {
        this.exitScript = exitScript;
    }
    public boolean isDisplayModal() {
        return displayModal;
    }
    public void setDisplayModal(boolean displayModal) {
        this.displayModal = displayModal;
    }
    public String getMsgBoxHeader() {
        return msgBoxHeader;
    }
    public void setMsgBoxHeader(String msgBoxHeader) {
        this.msgBoxHeader = msgBoxHeader;
    }
    public String getExitURL() {
        return exitURL;
    }
    public void setExitURL(String exitURL) {
        this.exitURL = exitURL;
    }
    public String getExitMsg() {
        return exitMsg;
    }
    public void setExitMsg(String exitMsg) {
        this.exitMsg = exitMsg;
    }
    public String getCancelMsg() {
        return cancelMsg;
    }
    public void setCancelMsg(String cancelMsg) {
        this.cancelMsg = cancelMsg;
    }
    public String getYesMsg() {
        return yesMsg;
    }
    public void setYesMsg(String yesMsg) {
        this.yesMsg = yesMsg;
    }
    public String getExitDomains() {
        return exitDomains;
    }
    public void setExitDomains(String exitDomains) {
        this.exitDomains = exitDomains;
    }
    public String getTargetWarning() {
        return targetWarning;
    }
    public void setTargetWarning(String targetWarning) {
        this.targetWarning = targetWarning;
    }
    public boolean isDisplayModalForNewWindow() {
        return displayModalForNewWindow;
    }
    public void setDisplayModalForNewWindow(boolean displayModalForNewWindow) {
        this.displayModalForNewWindow = displayModalForNewWindow;
    }
}