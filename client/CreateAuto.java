/**
 *	CreateAuto.java
 *	Write a description of your file here
 *	
 *	Eclipse Neon.2 Release (4.6.2), macOS Sierra
 *	Java SE 8 [1.8.0_45]
 *	@author Ashley Lim
 *	@version June 2018
 */
package adapter;
import java.util.ArrayList;
public interface CreateAuto {
	public void buildAuto(Object o, String type);
	public String printAuto(String key);
	public ArrayList<String> getModelList();
}
