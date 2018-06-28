package goc.webtemplate.component.jsf;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DefaultTemplateCoreBeanTest
{
    @Test
    public void testCDNEnvironment_loadedFromProps()
    {
        DefaultTemplateCoreBean sut = new DefaultTemplateCoreBean();
        
        //(default is Akamai)
        assertEquals("Akamai", sut.getCDNEnvironment());
    }    
}
