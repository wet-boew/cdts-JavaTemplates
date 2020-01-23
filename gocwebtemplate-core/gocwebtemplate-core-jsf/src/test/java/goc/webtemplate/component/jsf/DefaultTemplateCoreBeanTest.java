package goc.webtemplate.component.jsf;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
