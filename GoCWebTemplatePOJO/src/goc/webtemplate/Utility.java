package goc.webtemplate;

public class Utility {
	public static boolean isNullOrEmpty(String param) {
		return param == null || param.trim().length() == 0;
    }
}
