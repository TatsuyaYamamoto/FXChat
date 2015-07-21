/**
Server用モジュール
FX prptocolに従って送信文字列をバイナリデータに変換するクラス
*/

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;

class FXprotocolModuleServer{
	private FXprotocolModuleServer(){
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
}