# JavaTemplates Change Log

[Download and/or Installation instructions](https://github.com/wet-boew/cdts-JavaTemplates/wiki/Installation)

## v4.0.4

- [CDTS](https://github.com/wet-boew/cdts-sgdc/) v5.0.4 & [wet-boew](https://github.com/wet-boew/wet-boew) v4.0.85
- Adding an optional signInUrl property for Session Timeout allowing users to specify the sign-in page url when the session has expired
- Adding an optional textOverrides property for Session Timeout allowing users to override specific text elements
- Updated static fallback files

## v4.0.3

- [CDTS](https://github.com/wet-boew/cdts-sgdc/) v5.0.2 & [wet-boew](https://github.com/wet-boew/wet-boew) v4.0.81
- Updated static fallback files

## v4.0.2

- [CDTS](https://github.com/wet-boew/cdts-sgdc/) v5.0.1 & [wet-boew](https://github.com/wet-boew/wet-boew) v4.0.79
- Updated static fallback files
- Bug Fixes

## v4.0.1

- Now targeting Struts version 2.5.33

## v4.0.0

- **SECURITY FIX** Removal of default redirect handlers for "leaving secure site" feature. Leaving secure site feature now relies solely on WET functionality. Unless these redirect handlers were explicitely referenced by client application there should be no impact. (Spring version: removal of endpoint "/gocwebtemplate_leavesecuresiteredirect"; JSP version: removal of action "leavesecuresiteredirect.action")
- Replacing the 'Report a problem button' with the new Page Feedback tool. The Page Feedback tool is optional and will be hidden unless explicitly enabled and other conditions are met. Please visit the sample pages for more information.
- [CDTS](https://github.com/wet-boew/cdts-sgdc/) v5.0.0 & [wet-boew](https://github.com/wet-boew/wet-boew) v4.0.70.1
- Updated static fallback files
- Bug fixes

## v3.0.0

- **IMPORTANT** ALL LAYOUT DEFINITIONS UPDATED - All inline scripts and occurences of `document.write` were removed. 
    - Spring Version: This change should be transparent unless custom layouts were created in client applications. 
    - Struts/JSP Version: All tiles templates must be updated from sample project (src/main/webapp/templates/*.
- **IMPORTTANT** Functions that were deprecated long ago were removed in this version.
- [CDTS](https://github.com/wet-boew/cdts-sgdc/) v4.1.0 & [wet-boew](https://github.com/wet-boew/wet-boew) v4.0.63
- Updated static fallback files
- Bug Fixes

## v2.6.0

- **IMPORTANT** The GCWeb site footer has been updated to reflect the changes introduced in WET footer version 4. These changes will be applied automatically. For more information, please visit the WET documentation: https://wet-boew.github.io/GCWeb/sites/footers/footers-en.html
- [CDTS](https://github.com/wet-boew/cdts-sgdc/) v4.0.47 & [wet-boew](https://github.com/wet-boew/wet-boew) v4.0.56.5
- Updated static fallback files
- Bug Fixes

## v2.5.1

- Minor refactoring: Functions now take parameters (and return value) using interfaces `java.util.List` and `java.util.Map` instead of concrete classes `java.util.ArrayList` and `java.util.HashMap` for more flexibility.

## v2.5.0

- Added an optional parameter hidePlaceholderMenu to the GCIntranet theme. When set to true, this parameter is used to hide the placeholder menu while a custom menu is being loaded.
- [CDTS](https://gccode.ssc-spc.gc.ca/iitb-dgiit/nw-ws/sgdc-cdts) v4.0.46 & [wet-boew](https://github.com/wet-boew/wet-boew) v4.0.55
- Updated static fallback files
- Bug Fixes

## v2.4.0

- Added an acronym parameter to the custom menu allowing users to provide a description for an abbreviation.
- [CDTS](https://gccode.ssc-spc.gc.ca/iitb-dgiit/nw-ws/sgdc-cdts) v4.0.45 & [wet-boew](https://github.com/wet-boew/wet-boew) v4.0.50.1
- Updated static fallback files
- Bug Fixes

## v2.3.2

- Now targeting Struts version 2.5.30

## v2.3.1

- Now targeting Struts version 2.5.29
- Now targeting Spring Boot version 2.6.6
- Bug Fixes

## v2.3.0

- Now targeting Struts version 2.5.27
- Now targeting Spring Boot version 2.6.1
- Forcing log4j transitive dependency to point to version 2.16.0 because of vulnerability in previous versions
- [CDTS](https://gccode.ssc-spc.gc.ca/iitb-dgiit/nw-ws/sgdc-cdts) v4.0.44 & [wet-boew](https://github.com/wet-boew/wet-boew) v4.0.44.5
- Updated static fallback files
- Bug Fixes

## v2.2.0

- Added a targetWarning parameter for the exitScript allowing users to be warned that link will open in a new window
- Added a displayModalForNewWindow parameter for the exitScript allowing users to not show a modal if link opens in a new window
- Added a footerPath parameter allowing users to provide a custom footer link for the GCIntranet template
- Updated static fallback files
- [CDTS](https://gccode.ssc-spc.gc.ca/iitb-dgiit/nw-ws/sgdc-cdts) v4.0.43 & [wet-boew](https://github.com/wet-boew/wet-boew) v4.0.43

## v2.1.1

- Added attached artifact "uberjar" to gocwebtemplate-core-spring project (see pom.xml for contents).

## v2.1.0

- Adds support for Adobe Analytics Version 3
- Temporarily re-introduced document.write to fix a regression (issue will be address in upcoming release)
- Updated static fallback files
- [CDTS](https://gccode.ssc-spc.gc.ca/iitb-dgiit/nw-ws/sgdc-cdts) v4.0.39 & [wet-boew](https://github.com/wet-boew/wet-boew) v4.0.39

## v2.0.1

- Bug fixes (#120)

## v2.0.0

* **IMPORTANT** The JSF variant of the Java Template is no longer supported and has been removed.
* **IMPORTANT** Introducing a new variant of the Java Template that uses Spring Boot/Thymeleaf (can either be packaged as JAR and run as a standalone application, or packaged as WAR and run in a web container).
* **IMPORTANT** Minimum supported version of WebLogic is now 12.2.1.3
* **IMPORTANT** (JSP project) Update of Struts version from 2.3.37 to 2.5.22
* [CDTS](https://gccode.ssc-spc.gc.ca/iitb-dgiit/nw-ws/sgdc-cdts) v4.0.32 & [wet-boew](https://github.com/wet-boew/wet-boew) v4.0.32
* Added new *GCToolsModal* control
* Updated static fallback files
* Bug fixes
