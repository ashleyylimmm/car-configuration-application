/**
 *	AutoException.java
 *	Write a description of your file here
 *	
 *	Eclipse Neon.2 Release (4.6.2), macOS Sierra
 *	Java SE 8 [1.8.0_45]
 *	@author Ashley Lim
 *	@version June 2018
 */
package exception;
import util.FileIO;
import model.Automobile;
public class AutoException extends Exception{

	private AutomobileError error; 
	public AutoException(AutomobileError e)
	{
		super(e.getErrorMessage());
		this.error = e;
	}

	public void fix(Automobile auto)
	{
		switch(error)
		{
			case IllegalModelName:
			{
    			Fix100 fixer = new Fix100();
    			fixer.fix1(auto);
    			logException();
    			break;
			}
			case IllegalModelPrice:
			{
				Fix100 fixer = new Fix100();
				fixer.fix2(auto);
				logException();
				break;
			}
			case IllegalOptionSetName:
			{
				Fix100 fixer = new Fix100();
				fixer.fix3(auto);
				logException();
				break;
			}
			case IllegalOptionName:
			{
				Fix100 fixer = new Fix100();
				fixer.fix4(auto);
				logException();
				break;
			}
		}
	}
	
	public void logException()
	{
		FileIO fio = new FileIO();
		String filename = "log.txt";
		fio.appendToFile(filename, getMessage());
		System.err.println(getMessage());
		System.err.println("Logged in " +filename);
	}
}
