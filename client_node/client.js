'use strict';

var net = require('net');
var readlineSync = require('readline-sync');
var FX = require('./FXprotocolModule.js');
var config = require('./config.json')
var message = require('./message.json')


var socket;



/* このプログラムはコネクションセクションから始まります */
connection();


// コネクションセクション-------------------------------------//

function connection(){
	socket = createSocket(login);	//コネクション確立後、コールバックでログインセクションに入ります。
}

// ログインセクション-------------------------------------//



function login(){

	var type = config.type.login;

	// var username = readlineSync.question('username: ');
	// var password = readlineSync.question('password: ', {hideEchoBack: true});

	//メッセージオブジェクト	
	var message = {
		"head": {
			"version": config.app.version,
			"type":type
		},
		"body": {
			"username": username,
			"password": password
		}
	}
	FX.toBinary(message)

	console.log("TODO ログイン実装");
	chat();
}

// チャットセクション-------------------------------------//

function chat(){

	while(true){
		var type = config.type.broadcast;	//TODOメッセージタイプの切り替え
		var	body = readlineSync.question('> ');

		if(body === "exit"){
			//ソケットの状態をsocket.syncで見るのが正しいか分からない
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

	// if(!socket.sync)){
		console.log("^C to quit.....");
	// }
}


/* action */

function createSocket(callback){

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
				
				// 引数はlogin()
				callback();
			}
		);
	socket.on('error', function(){
		console.log(message.notFoundSocket);
	});
	socket.on('close', function(){
		console.log(message.socketClosed);
	});

	//受信イベント
	socket.on('data', function(buffer){
		console.log(FX.toJSON(buffer));
		console.log(FX.toJSON(buffer).body.toString());
	});

	return socket;
}
