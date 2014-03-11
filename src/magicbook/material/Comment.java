package magicbook.material;

public class Comment {

	private String _userName;
	private String _commentText;
	
	/** Constructor for the comment class.
	 *  User can leave their name.
	 * @param userName Name the user entered
	 * @param commentText comment the user wrote
	 */
	public Comment(String userName,String commentText){
		_userName=userName;
		_commentText=commentText;
	
	}
	
	/**Constructor for the comment class without the user entering a name.
	 * 
	 * @param commentText comment the user wrote
	 */
	public Comment(String commentText){
		_commentText=commentText;
	}
	
	/**Returns the comment text.
	 * @return
	 */
	public String getText(){
		return _commentText;
	}
	
	/**Returns the name of the user who has written or given the comment
	 * @return
	 */
	public String getUsername(){
		return _userName;
	}
}
