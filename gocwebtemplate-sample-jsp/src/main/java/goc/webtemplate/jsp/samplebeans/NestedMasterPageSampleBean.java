package goc.webtemplate.jsp.samplebeans;

public class NestedMasterPageSampleBean extends NestedMasterPageTemplateBean {
    
    @Override 
    public void onWebTemplateInitialize() {
        super.onWebTemplateInitialize(); //call our parent first, we'll override the values we need
        
        this.setHeaderTitle("Nested Master Page Sample");
    }
}
