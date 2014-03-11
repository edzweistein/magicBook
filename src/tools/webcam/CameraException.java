package tools.webcam;

public class CameraException extends Exception {

	
	private static final long serialVersionUID = 1L;
	private String _text;

	public CameraException(String text) {
		_text = text;
	}

	public String getText() {
		return _text;
	}
}
