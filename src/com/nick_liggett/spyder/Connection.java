package com.nick_liggett.spyder;

import java.io.*;
import javax.net.ssl.*;

public class Connection {

	public void createConnection(String host, int port) throws IOException {
		// Create an SSL socket factory
		SSLSocketFactory sslSocketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();

		try (SSLSocket socket = (SSLSocket) sslSocketFactory.createSocket(host, port);
				PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
				BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

			// Construct and send HTTPS request
			String request = "GET / HTTP/1.1\r\n" + "Host: " + host + "\r\n" + "Connection: close\r\n" + "\r\n";
			writer.print(request);
			writer.flush();
			System.out.println("Request: \r\n" + request);
			// Read and log the response
			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public SSLSocket createSocket(String host, int port) throws IOException {
		SSLSocketFactory sslSocketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
		return (SSLSocket) sslSocketFactory.createSocket(host, port);
	}
}