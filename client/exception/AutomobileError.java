/**
 *	ErrorMessage.java
 *	Write a description of your file here
 *	
 *	Eclipse Neon.2 Release (4.6.2), macOS Sierra
 *	Java SE 8 [1.8.0_45]
 *	@author Ashley Lim
 *	@version June 2018
 */
package exception;

public enum AutomobileError {
	
	IllegalModelName ("IllegalModelName - Model does not have valid name"), 
	IllegalModelPrice ("IllegalModelPrice - Model does not have valid price"),
	IllegalOptionSetName ("IllegalOptionSetName - OptionSet does not have valid name"),
	IllegalOptionName ("IllegalOptionName - Option does not have valid name");
	
	private String message;
	private AutomobileError(String msg)
	{
		this.message = msg;
	}
	public String getErrorMessage()
	{
		return message;
	}
}
