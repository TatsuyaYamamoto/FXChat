- database
	- fine
- table
	- user

## user table

- username
- password




### 手順

```mysql
create database fine character set utf8
```

```mysql
create table fine.user (username varchar(32), password varchar(32))
```

```mysql
insert into fine.user values ('yamamoto', 'yamamoto');
insert into fine.user values ('hirade', 'hirade');
insert into fine.user values ('masu', 'masu');
```

