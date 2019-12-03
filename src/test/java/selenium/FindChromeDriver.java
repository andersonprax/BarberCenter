package selenium;

import dao.ConstantesSistema;
/**
 * Classe reponsável por localizar o endereço do driver do 
 * google chrome em seu PC
 * @author pedro.silva
 *
 */
public class FindChromeDriver {

	//Aqui pego o diretorio do projeto
	String path = System.getProperty("user.dir");
	//Aqui pelo o serparador que uma "/"
	String folderSeparator = System.getProperty("file.separator");

	//Aqui é o caminho onde o driver se encontra dentro do projeto
	String relativePath = 
			folderSeparator + "src" + 
			folderSeparator + "main" + 
			folderSeparator + "webapp" +
			folderSeparator + "lib";
	/**
	 * Metodo que retorna a String com caminho do arquivo do driver do google Chrome
	 * @return String
	 */
	public String getChormeDriverLocation() {
		return path  + relativePath + folderSeparator+ ConstantesSistema.CHROME_DRIVER;
	}
}
