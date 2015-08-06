import java.net.Socket;
import java.util.HashMap;

public class ChatManager{

	//名前は何がいいかわからない
	HashMap<String, Socket> chatMap;

	public ChatManager(){
		System.out.println(crlf + "//////////////////////////////////////////////////");
		System.out.println("////////  RUN CHAT MANGAER! d(・８・)b  ////////");
		System.out.println("//////////////////////////////////////////////////" + crlf);

		chatMap = new HashMap<String, Socket>();

	}


	//新規に作成されたチャット(ソケット)を受け取り、管理下に置く

	//ユーザーをカテゴリー分けするなら、Beans?作った方がいいかも
	public void setSocket(String userId, Socket socket){
		chatMap.put(userId, socket);
	}

	//切断されたチャット(ソケット)を受け取り、管理から外す
	public void removeSocket(String userId){
		chatMap.remove(userId);
	}

	//全件ソケット取得(ブロードキャスト用かな)
	public Socket[] getSocket(){
		Socket[] sockets = new Socket[chatMap.size()];
		int i = 0;
		//拡張for文（for-each)でループ
		for(HashMap.Entry<String, Socket> e : chatMap.entrySet()) {
			sockets[i] = e.getValue();
			i++;
		}
	}
	//特定ユーザーのソケット取得(複数対応にする？)
	public Socket getSocket(String userId){
		return chatMap.get(userId);
	}
}