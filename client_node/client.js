'use strict';

var remote = require('remote');
var net = remote.require('net');
var FX = remote.require('./FXprotocolModule.js');
var config = remote.require('./config.json')
var message = remote.require('./message.json')


var socket = createSocket();



function send(){

	// テキストボックスの文字を取得
	var body = $("#message").val();
	// テキストボックスを空欄にする
	$("#message").val("");

    // 選択されているtype属性値を取り出す
	var type = $("#type").val();

	// 取得したtypeを1byte形式に変換
	switch(type){
		case "broadcast":
			type = config.type.broadcast;
			break;
		case "multicast":
			type = config.type.multicast;
			break;
	}

	//　メッセージオブジェクト
	var message = {
		"head": {
			"version": config.app.version,
			"type":type
		},
		"body": body
	}

	FX.send(socket, FX.toBinary(message));

}

function receive (message) {
	var body = FX.toJSON(message).body.toString();

	//受信メッセージリストに追加する
	$("#msg_list").prepend("<div class='msg'>" + body + "</div>");
}    


/* action */

function createSocket(){

	var socket = net.createConnection(
			{
				port: config.connection.port,
				host: config.connection.host
			},
			function(){
				console.log(message.createConnection);
				console.log(
					"///////////////////////////" + 
					"\nremoteAddress: " + socket.remoteAddress +
					"\nremotePort: " + socket.remotePort + 
					"\nlocalAddress: " + socket.localAddress + 
					"\nlocalPort: " + socket.localPort +
					"\n///////////////////////////"
				);
				

			}
		);
	socket.on('error', function(){
		console.log(message.notFoundSocket);
	});
	socket.on('close', function(){
		console.log(message.socketClosed);
	});

	//受信イベント
	socket.on('data', function(message){
		receive(message);
	});

	return socket;
}
