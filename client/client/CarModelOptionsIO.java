/**
 *	CarModelOptionsIO.java
 *	Write a description of your file here
 *	
 *	Eclipse Neon.2 Release (4.6.2), macOS Sierra
 *	Java SE 8 [1.8.0_45]
 *	@author Joshua Kuan
 *	@version Jun 6, 2017
 */
package client;

import java.io.*;
import java.util.Scanner;

import model.Properties;

public class CarModelOptionsIO {
	private ObjectOutputStream oos = null;
	private ObjectInputStream ois = null;
	public CarModelOptionsIO(ObjectOutputStream oos, ObjectInputStream ois)
	{
		this.oos = oos;
		this.ois = ois;
	}
	public void upload()
	{
		String serverResponse = null;
		Scanner s = new Scanner(System.in);
		FileInputStream fi = null;
		Properties p = new Properties();
		System.out.print("Enter file directory: ");
		try
		{
			File dirf = new File(s.nextLine());
			System.out.print("Enter the filename: ");
			File f = new File(dirf, s.nextLine());
			fi = new FileInputStream(f);
			p.load(fi);
			oos.writeObject(p);
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		try
		{
			serverResponse = (String)ois.readObject();
			System.out.println(serverResponse);
		}
		catch(IOException | ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}
}
