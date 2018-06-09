package ClassDesign;

public class Collection {
	private String id;
	private String photo;
	private String name;
	private String description;
	private String remember_score;
	private Flashcard flashcards;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRemember_score() {
		return remember_score;
	}
	public void setRemember_score(String remember_score) {
		this.remember_score = remember_score;
	}
	public String sgetDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Flashcard getFlashcards() {
		return flashcards;
	}
	public void setFlashcards(Flashcard flashcards) {
		this.flashcards = flashcards;
	}

}
