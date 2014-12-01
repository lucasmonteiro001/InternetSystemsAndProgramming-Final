package utilities;

/**
 * This class contains useful data to build html pages on servlets. 
 * @author Lucas
 *
 */
public class Html {
	private String content	= "text/html";
	private String title 	= "";
	private String docType 	= "<!doctype html public \"-//w3c//dtd html 4.0 transitional//en\">\n";
	private String beggining = "";
	private String end = "</body></html>";
	
	public String getContent() {
		return content;
	}

	public String getTitle() {
		return title;
	}

	public String getDocType() {
		return docType;
	}

	public String getBeg() {
		return beggining;
	}

	public String getEnd() {
		return end;
	}

	public Html(String title) {
		this.title = title;
		this.beggining = title + docType;
	}
}
