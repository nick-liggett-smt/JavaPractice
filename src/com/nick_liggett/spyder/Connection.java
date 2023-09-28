package com.nick_liggett.spyder;

import java.io.*;
import javax.net.ssl.*;

public class Connection {

	public SSLSocket createSocket(String host, int port) throws IOException {
		SSLSocketFactory sslSocketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
		return (SSLSocket) sslSocketFactory.createSocket(host, port);
	}
}