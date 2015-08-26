class Message{

	private byte version;	//1byte
	private byte type;		//1byte

	private <T> body;



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