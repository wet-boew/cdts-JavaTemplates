package goc.webtemplate.component.abstractcorebeantest;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import goc.webtemplate.SplashPageInfo;

public class RenderSplashTest {

	@Test
	public void testSplash() {
		AbstractCoreBeanImpl sut = new AbstractCoreBeanImpl();

		SplashPageInfo splash = new SplashPageInfo(
                "index",
                "index",
                "http://www.canada.ca/en/transparency/terms.html",
                "http://www.canada.ca/fr/transparence/avis.html",
                "[My web asset]",
                "[Mon actif web]",
                "French"
        );
		sut.setSplashPageInfo(splash);

		assertTrue(sut.getRenderSplashSetup().contains("\"splash\":{\"indexEng\":\"index\",\"indexFra\":\"index\",\"termsEng\":\"http://www.canada.ca/en/transparency/terms.html\",\"termsFra\":\"http://www.canada.ca/fr/transparence/avis.html\",\"nameEng\":\"[My web asset]\",\"nameFra\":\"[Mon actif web]\",\"languagePrecedence\":\"French\"}"),
                "\"RenderSplash: Splash not rendered as expected.\"");
	}
}
