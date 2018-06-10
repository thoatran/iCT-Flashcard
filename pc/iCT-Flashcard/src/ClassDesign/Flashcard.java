package ClassDesign;

public class Flashcard {
	private String collection_id;
	private String id;
	private String word;
	private String pronunciation;
	private String meaning;
	private String image;
	private int order;
	public String getCollection_id() {
		return collection_id;
	}
	public void setCollection_id(String collection_id) {
		this.collection_id = collection_id;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPronunciation() {
		return pronunciation;
	}
	public void setPronunciation(String pronunciation) {
		this.pronunciation = pronunciation;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getMeaning() {
		return meaning;
	}
	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}


}
