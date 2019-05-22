/**
 *	SocketClientInterface.java
 *	Write a description of your file here
 *	
 *	Eclipse Neon.2 Release (4.6.2), macOS Sierra
 *	Java SE 8 [1.8.0_45]
 *	@author Ashley Lim
 *	@version June 2018
 */
package server;

public interface SocketClientInterface {
	public boolean openConnection();
    public void handleSession();
    public void closeSession();
}
