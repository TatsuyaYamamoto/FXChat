import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.net.Socket;

class TransmissionThread extends Thread{

	private Socket socket;

	public TransmissionThread(Socket socket){
		this.socket = socket;
	}
	
	public void run(){

		try{
			BufferedReader keyIn = new BufferedReader(new InputStreamReader(System.in));
			PrintWriter output = new PrintWriter(socket.getOutputStream(), true);


			while(!socket.isClosed()){
				System.out.println("[transmission is in readiness]");
				String keyIn_text = keyIn.readLine();

				//exitで終わります
				if("exit".equals(keyIn_text)){
					System.out.println("帰っちゃうの...？!（・８・）ｻﾐｼｲｯ‼︎");
					System.out.println("Please enter \"no\"!");
					String exit_answer = keyIn.readLine();

					if("no".equals(exit_answer)){
						System.out.println("YATTA!YATTA!");
						//noならループが続く
					}else{
						//それ以外は終了
						break;
					}
				}else{
					//サーバへ文字列を送信
					output.println(keyIn_text);
				}
			}

		}catch(IOException e){
			// e.printStackTrace();
		}finally{
			try{
				if(!socket.isClosed()){
					socket.close();
					System.out.println("[transmission]close socket...(ｼｮﾝﾎﾞﾘ");
				}
			}catch(IOException e){
				// e.printStackTrace();
			}
		}
	}
}