/**
 *	BuildCarModelOptions.java
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

import adapter.BuildAuto;
import adapter.CreateAuto;
import model.Properties;
public class BuildCarModelOptions implements AutoServer{
	private CreateAuto ca = new BuildAuto();
	public void acceptProperties(ObjectInputStream ois)
	{
		try
		{
			Properties p = (Properties)ois.readObject();
			ca.buildAuto(p, "Property");
		}
		catch(ClassNotFoundException | IOException e)
		{
			e.printStackTrace();
		}
	}
	public ArrayList<String> getModelList() {
		return ca.getModelList();
	}
	public void sendAuto(ObjectOutputStream oos, String key)
	{
		BuildAuto ba = new BuildAuto();
		ba.sendAuto(oos, key);
	}
}
