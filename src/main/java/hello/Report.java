package hello;

import org.springframework.data.annotation.Id;

public class Report {

	@Id private String id;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	public String getInfoCode() {
		return infoCode;
	}
	public void setInfoCode(String infoCode) {
		this.infoCode = infoCode;
	}
	public String getInsName() {
		return insName;
	}
	public void setInsName(String insName) {
		this.insName = insName;
	}
	public String getSecuFullCode() {
		return secuFullCode;
	}
	public void setSecuFullCode(String secuFullCode) {
		this.secuFullCode = secuFullCode;
	}
	public String getSecuName() {
		return secuName;
	}
	public void setSecuName(String secuName) {
		this.secuName = secuName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	private String author;


	private String datetime;
	private String infoCode;
	private String insName;
	private String secuFullCode;
	private String secuName;
	private String title;

}
