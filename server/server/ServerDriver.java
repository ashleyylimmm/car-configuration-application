/**
 *	ServerDriver.java
 *	Write a description of your file here
 *	
 *	Eclipse Neon.2 Release (4.6.2), macOS Sierra
 *	Java SE 8 [1.8.0_45]
 *	@author Ashley Lim
 *	@version June 2018
 */
package server;
import java.net.*;
import java.io.*;
public class ServerDriver {
	private ServerSocket serversocket;
	public ServerDriver()
	{
		try
		{
			serversocket = new ServerSocket(7135);
			System.out.println("Server listening on port "+7135);
		}
		catch(IOException e)
		{
			System.err.println("Could not listen on port: 7135.");
			System.exit(1);
		}
	}
	public void startServer()
	{
		DefaultSocketServer clientSocket = null;
		while(true)
		{
			try 
			{
				Socket s = serversocket.accept();
				clientSocket = new DefaultSocketServer(s);
				clientSocket.start();
			} 
			catch (IOException e) 
			{
	    		System.err.println("Accept failed.");
	    		e.printStackTrace();
			}
		}
	}
	public static void main(String args[])
	{
		ServerDriver sd = new ServerDriver();
		sd.startServer();
	}
}