package selenium;

import dao.ConstantesSistema;

public class FindChromeDriver {

	String path = System.getProperty("user.dir");
	String folderSeparator = System.getProperty("file.separator");

	String relativePath = 
			folderSeparator + "src" + 
			folderSeparator + "main" + 
			folderSeparator + "webapp" +
			folderSeparator + "lib";
	
	public String getChormeDriverLocation() {
		return path  + relativePath + folderSeparator+ ConstantesSistema.CHROME_DRIVER;
	}
}
