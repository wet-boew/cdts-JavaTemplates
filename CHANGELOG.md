# JavaTemplates Change Log

[Download and/or Installation instructions](https://gccode.ssc-spc.gc.ca/iitb-dgiit/sds/GOCWebTemplates/JavaTemplates/wikis/Documentation/Installation)

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
