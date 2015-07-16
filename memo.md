
##Socketクラス

クライアントソケットを実装する

###コンストラクタ

- new Socket(args[0], ECHO_PORT);

```
public Socket(InetAddress address,
              int port)
       throws IOException
ストリームソケットを作成し、指定された IP アドレスの指定されたポート番号に接続します。
```

###getRemoteSocketAddress()

```
public SocketAddress getRemoteSocketAddress()
このソケットが接続されている端点のアドレスを返します。ソケットが接続されていない場合は null を返します。
```

###getInputStream()

###getOutputStream()


###close()

##ServerSocketクラス

このクラスはサーバーソケットを実装します。サーバーソケットは、ネットワーク経由で要求が送られてくるのを待ちます。これは、その要求に基づいていくつかの操作を実行します。その後、場合によっては要求元に結果を返します。

###コンストラクタ
- new ServerSocket(ECHO_PORT);

```
public ServerSocket(int port)
             throws IOException
指定されたポートにバインドされたサーバーソケットを作成します。ポート 0 を指定すると、空いているポート上でソケットが作成されます。
```

###getLocalPort()

```
このソケットが接続を待機中のポートを返します。
```

###accept()

```
accept

public Socket accept()
              throws IOException
このソケットに対する接続要求を待機し、それを受け取ります。このメソッドは接続が行われるまでブロックされます。  

戻り値:
新しいソケット
```


###serverSocket.accept().getRemoteSocketAddress()

###close();

##InputStreamReaderクラス

###コンストラクタ
- new InputStreamReader(socket.getInputStream())

##BufferedReaderクラス


###コンストラクタ

- new BufferedReader(new InputStreamReader(socket.getInputStream()));
- new BufferedReader(new InputStreamReader(System.in));

###readLine()

##PrintWritterクラス

###コンストラクタ

- new PrintWriter(socket.getOutputStream(), true);


