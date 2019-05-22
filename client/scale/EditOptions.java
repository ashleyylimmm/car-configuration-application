/**
 *	EditOptions.java
 *	Write a description of your file here
 *	
 *	Eclipse Neon.2 Release (4.6.2), macOS Sierra
 *	Java SE 8 [1.8.0_45]
 *	@author Ashley Lim
 *	@version June 2018
 */
package scale;
import model.*;
import adapter.ProxyLink;
import adapter.BuildAuto;
public class EditOptions extends Thread{
	private ProxyLink pLink = new BuildAuto();
	private String[] args;
	private int processNumber;
	private Automobile auto;

	public EditOptions(String key, String[] args, int pNum)
	{
		processNumber = pNum;
		this.args = args;
		auto = pLink.get(key);
	}
	public void editModelName()
	{
		auto.setAutomobileName(args[0]);
	}
	public void editModelOptionName()
	{
		int opSetIndex = auto.findOptionSet(args[0]);
		if(opSetIndex >= 0 && opSetIndex < auto.getOptionSet().size())
		{
			int opIndex = auto.findOption(args[1], opSetIndex);
			if(opIndex >= 0 && opIndex < auto.getOptionArrLength(opSetIndex))
			{
				auto.setOptionName(args[2], opSetIndex, opIndex);
			}
		}
	}
	
	public void editModelOptionSetName()
	{
		int opSetIndex = auto.findOptionSet(args[0]);
		if(opSetIndex >= 0 && opSetIndex <= auto.getOptionSet().size())
		{
			auto.setOptionSetName(args[1], opSetIndex);
		}
	}
	
	public void ops()
	{
		switch(processNumber)
		{
		case 1:
			editModelName();
			break;
		case 2:
			editModelOptionSetName();
			break;
		case 3:
			editModelOptionName();
			break;
		}
	}
	
	@Override
	public void run()
	{
		ops();
	}
}

/*
false
false
Old model name: Toyota Camry
Old model name: Toyota Camry
New model name: !!!
New model name: ???
*/



/* public interface Editable {
		public void edit(int ops, String[] x);
	}
*/