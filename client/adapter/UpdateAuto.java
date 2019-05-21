/**
 *	UpdateAuto.java
 *	Write a description of your file here
 *	
 *	Eclipse Neon.2 Release (4.6.2), macOS Sierra
 *	Java SE 8 [1.8.0_45]
 *	@author Ashley Lim
 *	@version June 2018
 */
package adapter;

public interface UpdateAuto {
	public void updateOptionSetName(String key, String oldName, String newName);
	public void updateOptionPrice(String key, String optionName, float newPrice);
	public void updateOptionChoice(String key, String opSetName, String optionName);
}
