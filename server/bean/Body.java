class Body{

private String username;
private String password;
private Time time;
private int messageId;
private int messageLength;
private String text;

	private setHead(Head head){
		this.head = head;
	}
	private getHead(){
		return head;
	}
	private setBody(Body body){
		this.body = body;
	}
	private getBody(){
		return body;
	}
}