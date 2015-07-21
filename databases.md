# FXChat database仕様


- database名
	- fxchat
- tables
	- account
	- group?
	- とりあえず
- account table
	- user_name
		- int
	- user_id
		- varchar(20)
	- password(暗号化はとりあえずいいや)
		- varchar(20)
	
	```
	create table (user_id int, user_name varchar(20), password varchar(20));
	```