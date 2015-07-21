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
		//headerを削除
		byte[] body_bytes = new byte[buffer.length - 2];
		for(int i = 0; i < body_bytes.length; i++){
			body_bytes[i] = buffer[i+2];
		}
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		bout.write(body_bytes, 0, body_bytes.length);
		// 受信したバイト列をunicodeに変換
		String body_String;
		try{
			body_String = new String(bout.toByteArray(), "UTF-8");
		}catch(UnsupportedEncodingException e){
			// e.printStackTrace();
			body_String = "CONVERT ERROR";
		}
		return body_String;
	}

	public static byte[] convert(String body_String){


		//typeはブロードキャスト固定
		byte type_byte = 3;

		byte[] body_bytes = body_String.getBytes();

		//サーバーへ送るバイト列を作成。version, type, bodyを結合する
		//ログイン時のbyte列の要素数はheader 2bytes
		byte[] message = new byte[1 + 1 + body_bytes.length];
		message[0] = VERSION;
		message[1] = type_byte;
		for(int i = 0; i < body_bytes.length; i++){
			message[i+2] = body_bytes[i];
		}


		return message;
	}

	public static byte[] login(String user_name, char [] password){

		//typeはログイン固定
		byte type_byte = 1;

		//user_nameをバイト列に変換
		byte[] user_name_bytes = user_name.getBytes();

		//passwordをバイト列に変換
		byte[] password_bytes = String.valueOf(password).getBytes();

		//サーバーへ送るバイト列を作成。version, type, bodyを結合する
		//ログイン時のbyte列の要素数はheader 2bytes, username 32bytes, password 32bytes
		byte[] message = new byte[1 + 1 + 32 + 32];
		message[0] = VERSION;
		message[1] = type_byte;
		for(int i = 0; i < user_name_bytes.length; i++){
			message[i+1] = user_name_bytes[i];
		}
		for(int i = 0; i < user_name_bytes.length; i++){
			message[i+33] = password_bytes[i];
		}

		return message;
	}

	public byte[] logout(){

		//typeはログアウト固定
		byte type_byte = 2;

		byte[] message = new byte[2];
		message[0] = VERSION;
		message[1] = type_byte;

		return message;
	}
}