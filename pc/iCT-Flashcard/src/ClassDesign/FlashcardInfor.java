package ClassDesign;

public class FlashcardInfor {
	private String success;
	private Flashcard[] data;
	public Flashcard[] getData() {
		return data;
	}
	public void setData(Flashcard[] data) {
		this.data = data;
	}
	public String getSuccess() {
		return success;
	}
	public void setSuccess(String success) {
		this.success = success;
	}

}
