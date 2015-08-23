'use strict';
var config = require('./config.json')

module.exports.toBinary = function (message_json) {

	//バージョンヘッダとメッセージタイプヘッダ
	// configファイル内の基数から10進数に変換し、buffer(各1byte)に代入します。
	var version_binary	= new Buffer(1);
	version_binary.fill(parseInt(message_json.head.version, config.app.base));

	var type_binary	= new Buffer(1);
	type_binary.fill(parseInt(message_json.head.type, config.type.base));


	//ボディ
	var body_binary;

	if(message_json.head.type == config.type.login){	//ログイン時のみbody内にオブジェクトがあります

		var username = new Buffer(32).fill(' ');
		var password = new Buffer(32).fill(' ');

		body_binary = username.write(message_json.body.username) 
						+ password.write(message_json.body.password);
	}else{
		body_binary = new Buffer(message_json.body)
	}

	//バイナリのメッセージデータを作成
	var message_binary = version_binary + type_binary + body_binary;

    return message_binary;  
};

module.exports.toJSON = function (message_binary) {

	var message_json = {
		"head": {
			"version": message_binary[0],
			"type": message_binary[1]
		},
		"body": message_binary.slice(2)
	}

    return message_json;  
};

module.exports.send = function (socket, message_binary) {
    socket.write(message_binary,[], [])
};

