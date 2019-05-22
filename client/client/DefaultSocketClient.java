/**
 *	DefaultSocketClient.java
 *	Write a description of your file here
 *	
 *	Eclipse Neon.2 Release (4.6.2), macOS Sierra
 *	Java SE 8 [1.8.0_45]
 *	@author Ashley Lim
 *	@version June 2018
 */
package client;

import java.net.*;
import java.util.Scanner;
import model.Properties;

import java.io.*;
public class DefaultSocketClient extends Thread implements SocketClientInterface, SocketClientConstants {

	private ObjectInputStream ois;
	private ObjectOutputStream oos;
    private Socket sock;

    public DefaultSocketClient(Socket sock)
    {
    	this.sock = sock;
    }
    public void run()
    {
    	while(true)
    	{
    		if (openConnection())
    		{
    			handleSession();
    			closeSession();
	       }
    	}
    }
  
	public boolean openConnection() {
		try {
			sock = new Socket("localhost", 7135);
			oos = new ObjectOutputStream(sock.getOutputStream());
			ois = new ObjectInputStream(sock.getInputStream());
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public void handleSession() {
		String input = null;
		Scanner s = new Scanner(System.in);
		System.out.println("'upload' to upload a Properties file");
		System.out.println("'configure' to retrieve a list of models for configuration");
		System.out.println("'exit' to terminate program");
		System.out.println();
		System.out.print("Enter command: ");
		
		input = s.nextLine();
		try
		{
			oos.writeObject(input);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		switch(input){
		case "upload":
			CarModelOptionsIO cmio = new CarModelOptionsIO(oos, ois);
			cmio.upload();
			break;
		case "configure":
			SelectCarOption sco = new SelectCarOption(oos, ois);
			sco.configure();
			break;
		case "exit":
			System.out.println("Program terminated.");
			System.exit(1);
			break;
		default:
			System.out.println("Invalid input, try again!");
			System.out.println();
			break;
		}
	}

	@Override
	public void closeSession() {
		try
		{
			oos.close();
			ois.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
	}
      
}