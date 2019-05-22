/**
 *	Properties.java
 *	Write a description of your file here
 *	
 *	Eclipse Neon.2 Release (4.6.2), macOS Sierra
 *	Java SE 8 [1.8.0_45]
 *	@author Ashley Lim
 *	@version June 2018
 */
package model;
import java.util.Hashtable;
import java.io.*;
import util.FileIO;
public class Properties implements Serializable{
	private Hashtable<String, String> ht = new Hashtable<String, String>();

	public Hashtable<String, String> getHashtable()
	{
		return ht;
	}
	public void load(FileInputStream in)
	{
		FileIO fio = new FileIO();
		fio.parseProperties(in, this);
	}
	public String getProperty(String key)
	{
		return ht.get(key);
	}
	public void setHashtable(Hashtable<String, String> ht)
	{
		this.ht = ht;
	}
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		for(String keys : ht.keySet())
		{
			System.out.print(keys + " ");
			System.out.println(ht.get(keys));
		}
		return sb.toString();
	}
}
