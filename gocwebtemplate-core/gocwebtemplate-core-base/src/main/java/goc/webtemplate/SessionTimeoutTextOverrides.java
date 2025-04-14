package goc.webtemplate;

public class SessionTimeoutTextOverrides {
    private String buttonContinue;
    private String buttonEnd;
    private String buttonSignIn;
    private String timeoutEnd;
    private String timeoutAlready;

    public SessionTimeoutTextOverrides() {}

    public SessionTimeoutTextOverrides(String buttonContinue, String buttonEnd, String buttonSignIn, String timeoutEnd, String timeoutAlready)
    {
        this.buttonContinue = buttonContinue;
        this.buttonEnd = buttonEnd;
        this.buttonSignIn = buttonSignIn;
        this.timeoutEnd = timeoutEnd;
        this.timeoutAlready = timeoutAlready;
    }

    public String getButtonContinue() {
        return buttonContinue;
    }

    public void setButtonContinue(String buttonContinue) {
        this.buttonContinue = buttonContinue;
    }

    public String getButtonEnd() {
        return buttonEnd;
    }

    public void setButtonEnd(String buttonEnd) {
        this.buttonEnd = buttonEnd;
    }

    public String getButtonSignIn() {
        return buttonSignIn;
    }

    public void setButtonSignIn(String buttonSignIn) {
        this.buttonSignIn = buttonSignIn;
    }

    public String getTimeoutEnd() {
        return timeoutEnd;
    }

    public void setTimeoutEnd(String timeoutEnd) {
        this.timeoutEnd = timeoutEnd;
    }

    public String getTimeoutAlready() {
        return timeoutAlready;
    }

    public void setTimeoutAlready(String timeoutAlready) {
        this.timeoutAlready = timeoutAlready;
    }
}