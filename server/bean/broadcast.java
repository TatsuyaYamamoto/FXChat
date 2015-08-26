class Message{

	private Head head;// 1byte
	private Body body;// 1byte

	private byte version;	//1byte
	private byte type;		//1byte


	private byte[] id;// 16bytes
	private byte[] username;// 32bytes
	private byte[] password;// 32bytes
	private byte status;// 1byte
	private byte[] messageLength// 2bytes
	private byte[] text;// >2^16bytes


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