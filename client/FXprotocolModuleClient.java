/**
Client用モジュール
FX prptocolに従って送信文字列をバイナリデータに変換するクラス
*/

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;

class FXprotocolModuleClient{

	//固定値：プロトコルのバージョン(取り急ぎ)
	private static byte VERSION = 1;

	private FXprotocolModuleClient(){
		//インスタンス作成禁止
	}


	public static String convert (byte [] buffer, int messageSize){
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		bout.write(buffer, 0, messageSize);

		// 受信したバイト列をunicodeに変換
		String text_String;
		try{
			text_String = new String(bout.toByteArray(), "UTF-8");
		}catch(UnsupportedEncodingException e){
			// e.printStackTrace();
			text_String = "CONVERT ERROR";
		}
		return text_String;
	}

	public static byte[] convert(String body_String){
		byte[] body_bytes = body_String.getBytes();
		return body_bytes;
	}

	// public static byte[] convert(String type_String, String body_String){

	// 	//typeをbyte列に変換
	// 	byte type_byte = 0;
	// 	switch(type_String){
	// 		case "login":
	// 			type_byte = 1;
	// 			break;
	// 		case "logout":
	// 			type_byte = 2;
	// 			break;
	// 		case "broadcast":
	// 			type_byte = 3;
	// 			break;
	// 	}
	// 	//入力された文字列をバイト列に変換
	// 	byte[] body_bytes = body_String.getBytes();

	// 	//サーバーへ送るバイト列を作成
	// 	byte[] message = new byte[body_bytes.length + 2];
	// 	message[0] = VERSION;
	// 	message[1] = type_byte;
	// 	for(int i = 0; i < body_bytes.length; i++){
	// 		message[i + 2] = body_bytes[i];
	// 	}

	// 	return message;
	// }

	// public byte[] login(String user_name, char [] password){

	// 	//typeはログイン固定
	// 	byte type_byte = 1;

	// 	//user_nameをバイト列に変換
	// 	byte[] user_name_bytes = user_name.getBytes();

	// 	//passwordをバイト列に変換
	// 	byte[] password_bytes;
	// 	for(int i = 0; i < password.length; i++){

	// 	}
	// 	//サーバーへ送るバイト列を作成。version, type, bodyを結合する
	// 	byte[] message;

	// 	return message;
	// }

	public byte[] logout(){

		//typeはログアウト固定
		byte type_byte = 2;

		byte[] message = new byte[2];
		message[0] = VERSION;
		message[1] = type_byte;

		return message;
	}
}