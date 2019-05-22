/**
 *	Fix100.java
 *	Write a description of your file here
 *	
 *	Eclipse Neon.2 Release (4.6.2), macOS Sierra
 *	Java SE 8 [1.8.0_45]
 *	@author Ashley Lim
 *	@version June 2018
 */
package exception;
import java.util.Scanner;
import model.Automobile;
public class Fix100 {
	private Scanner s = new Scanner(System.in);
	public void fix1(Automobile auto) // handles IllegalModelName
	{
		System.out.println("Seen: " +auto.getAutomobileName());
		System.out.println("Expected: Not null");
		System.out.print("Enter a valid Model Name: ");
		auto.setAutomobileName(s.nextLine());
	}
	public void fix2(Automobile auto) // handles IllegalModelPrice
	{
		System.out.println("Expected: > 0");
		System.out.print("Enter a valid Model Price: ");
		auto.setAutomobilePrice(s.nextFloat());
	}
	public void fix3(Automobile auto) // handles IllegalOptionSetName
	{
		for(int i = 0; i < auto.getOptionSet().size(); i++)
		{
			if(auto.getOptionSetName(i) == null || auto.getOptionSetName(i).equals(""))
			{
				System.out.println("Seen: " +auto.getOptionSetName(i));
				System.out.print("Enter a valid OptionSet Name: ");
				auto.setOptionSetName(s.nextLine(), i);
				break;
			}
		}
	}
	public void fix4(Automobile auto) // handles IllegalOptionName
	{
		boolean fixed = false;
		for(int opSetIndex = 0; opSetIndex < auto.getOptionSet().size(); opSetIndex++)
		{
			if(auto.getOptionArrLength(opSetIndex) != -1)
			{
				for(int opIndex = 0; opIndex < auto.getOptionArrLength(opSetIndex); opIndex++)
				{
					
					if(auto.getOptionName(opSetIndex, opIndex) == null || auto.getOptionName(opSetIndex, opIndex).equals(""))
					{
						System.out.println("For " +auto.getOptionSetName(opSetIndex));
						System.out.print("Enter a valid Option Name: ");
						auto.setOptionName(s.nextLine(), opSetIndex, opIndex);
						fixed = true;
					}
				}
			}
			if(fixed)
				break;
		}
	}
}
