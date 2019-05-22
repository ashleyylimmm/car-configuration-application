/**
 *	DefaultSocketClient.java
 *	Write a description of your file here
 *	
 *	Eclipse Neon.2 Release (4.6.2), macOS Sierra
 *	Java SE 8 [1.8.0_45]
 *	@author Ashley Lim
 *	@version June 2018
 */
package server;
import java.net.*;
import java.util.ArrayList;
import java.io.*;
public class DefaultSocketServer extends Thread implements SocketClientInterface, SocketClientConstants {

    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private Socket sock;

    public DefaultSocketServer(Socket sock)
    {
    	this.sock = sock;
    	
    }
	@Override
	public void closeSession() {
		try
		{
			ois.close();
			oos.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		System.out.println("Connection closed");
	}
	@Override
	public void handleSession() {
		System.out.println(sock.getInetAddress()+":"+sock.getLocalPort() +" -Session being handled");
		String clientInput = null;
		BuildCarModelOptions cmo = new BuildCarModelOptions();
		try
		{
			clientInput = (String) ois.readObject();
		
    		switch(clientInput)
    		{
    		case "upload":
    			System.out.println("Client wants to upload file");
    			cmo.acceptProperties(ois);
    			System.out.println("Properties uploaded and added to autoMap");
    			
    			oos.writeObject("Properties file received by server");
    			break;
    		case "configure":
    		{
    			System.out.println("Client wants to configure auto");
    			System.out.println("List of autos: ");
    			ArrayList<String> modelList = cmo.getModelList();
    			if(modelList.isEmpty())
    			{
    				System.out.println("Model List is empty!");
    				break;
    			}
    			for(String s : modelList)
    				System.out.println(s);
    			oos.writeObject(cmo.getModelList());
    			clientInput = (String)ois.readObject();
    			System.out.println("Client wants to configure: " +clientInput);
    			cmo.sendAuto(oos, clientInput);
    			break;
    		}
    		case "list":
    			System.out.println("Client wants a list of autos");
    			ArrayList<String> modelList = cmo.getModelList();
    			if(modelList.isEmpty())
    			{
    				System.out.println("Model List is empty!");
    				break;
    			}
    			oos.writeObject(cmo.getModelList());
    			break;
    		case "send":
    			System.out.print("Client wants an auto with key: ");
    			clientInput = (String)ois.readObject();
    			System.out.println(clientInput);
    			cmo.sendAuto(oos, clientInput);
    			break;
    		}
    		System.out.println();
		}
		catch(ClassNotFoundException | IOException e)
		{
			e.printStackTrace();
		}
	}
	@Override
	public boolean openConnection() {
		try
		{
			oos = new ObjectOutputStream(sock.getOutputStream());
			ois = new ObjectInputStream(sock.getInputStream());
		}
		catch(IOException e)
		{
			e.printStackTrace();
			return false;
		}
		System.out.println("Connected to " +sock.getInetAddress() +":" +sock.getLocalPort());
		return true;
	}

	public void run(){
	       if (openConnection()){
	          handleSession();
	          closeSession();
	       }
	       else
	       {
	    	   System.out.println("Failed to open session");
	       }
		}

}