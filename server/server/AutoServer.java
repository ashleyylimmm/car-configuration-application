/**
 *	AutoServer.java
 *	Write a description of your file here
 *	
 *	Eclipse Neon.2 Release (4.6.2), macOS Sierra
 *	Java SE 8 [1.8.0_45]
 *	@author Ashley Lim
 *	@version June 2018
 */
package server;
import java.io.*;
import java.util.ArrayList;
public interface AutoServer {
	public void acceptProperties(ObjectInputStream ois);
	public ArrayList<String> getModelList();
	public void sendAuto(ObjectOutputStream oos, String key);
}
