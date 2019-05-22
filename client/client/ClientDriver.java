/**
 *	ClientDriver.java
 *	Write a description of your file here
 *	
 *	Eclipse Neon.2 Release (4.6.2), macOS Sierra
 *	Java SE 8 [1.8.0_45]
 *	@author Ashley Lim
 *	@version June 2018
 */
package client;
import java.io.*;
import java.net.*;

public class ClientDriver {
	public ClientDriver()
	{
		Socket sock = null;
		try {
			sock = new Socket("localhost", 7135);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		DefaultSocketClient client = new DefaultSocketClient(sock); 
		client.start();
	}
}
