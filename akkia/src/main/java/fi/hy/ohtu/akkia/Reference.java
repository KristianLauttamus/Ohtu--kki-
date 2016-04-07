/**
 * 
 */
package fi.hy.ohtu.akkia;

/**
 * @author Esa Potkonen
 *
 */
public class Reference {
	// Mandatory
	private String ReferenceKey;
	private String author;
	private String title;
	private String publisher;
	private String year;

	// Not mandatory
	private String volume;
	private String series;
	private String address;
	private String edition;
	private String month;
	private String note;
	private String ISBNkey;
	public String getReferenceKey() {
		return ReferenceKey;
	}
	public void setReferenceKey(String referenceKey) {
		ReferenceKey = referenceKey;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getVolume() {
		return volume;
	}
	public void setVolume(String volume) {
		this.volume = volume;
	}
	public String getSeries() {
		return series;
	}
	public void setSeries(String series) {
		this.series = series;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEdition() {
		return edition;
	}
	public void setEdition(String edition) {
		this.edition = edition;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getISBNkey() {
		return ISBNkey;
	}
	public void setISBNkey(String iSBNkey) {
		ISBNkey = iSBNkey;
	}
	
}
