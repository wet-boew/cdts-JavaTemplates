package goc.webtemplate;

import org.apache.commons.lang.StringUtils;

public class Utility {
	public static boolean isNullOrEmpty(String param) {
		//return param == null || param.trim().length() == 0;
	    return StringUtils.isBlank(param);
    }
}
