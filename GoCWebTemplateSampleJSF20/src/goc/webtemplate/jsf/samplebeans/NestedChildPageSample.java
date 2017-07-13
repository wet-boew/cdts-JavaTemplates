package goc.webtemplate.jsf.samplebeans;

public class NestedChildPageSample extends NestedMasterPageSample {
    
    @Override 
    public void onWebTemplateInitialize() {
        super.onWebTemplateInitialize(); //call our parent first, we'll override the values we need
        
        this.setHeaderTitle("Nested Master Page Sample");
    }
}
