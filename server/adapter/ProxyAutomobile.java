/**
 *	ProxyAutomobile.java
 *	Write a description of your file here
 *	
 *	Eclipse Neon.2 Release (4.6.2), macOS Sierra
 *	Java SE 8 [1.8.0_45]
 *	@author Ashley Lim
 *	@version June 2018
 */
package adapter;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import model.*;
import exception.*;
import scale.*;
import util.FileIO;
import server.BuildCarModelOptions;
public abstract class ProxyAutomobile {
	private static LinkedHashMap<String, Automobile> autoMap = new LinkedHashMap<String, Automobile>();
	public void acceptProperties(ObjectInputStream ois)
	{
		BuildCarModelOptions cmo = new BuildCarModelOptions();
		cmo.acceptProperties(ois);
	}
	public void addAuto(Automobile auto)
	{
		StringBuilder key = new StringBuilder();
		key.append(auto.getYear());
		key.append(auto.getMake());
		key.append(auto.getModel());
		auto.rebuildChoiceLink();
		autoMap.put(key.toString(), auto);
	}
	public void buildAuto(Object o, String type) {
		FileIO fio = new FileIO();
		Automobile temp = null;
		switch(type)
		{
		case "Property":
			Properties p = (Properties)o;
			temp = new Automobile();
			temp.setAutomobileName(p.getProperty("CarName"));
			temp.setYear(p.getProperty("CarYear"));
			temp.setModel(p.getProperty("CarModel"));
			temp.setMake(p.getProperty("CarMake"));
			temp.setAutomobilePrice(Float.parseFloat(p.getProperty("CarPrice")));
			int counter = 1;
			char letter = 'a';
			
			String buffer = p.getProperty("Option"+counter);
			ArrayList<String> optionName = new ArrayList<String>();
			ArrayList<String> optionPrice = new ArrayList<String>();
			while(buffer != null)
			{
				temp.addOptionSet(p.getProperty("Option"+counter));
				int count = 1;
				while(p.getProperty("OptionValue"+counter+letter) != null && p.getProperty("OptionValue"+counter+letter+"Price")!= null)
				{
					optionName.add(p.getProperty("OptionValue"+counter+letter));
					optionPrice.add(p.getProperty("OptionValue"+counter+letter+"Price"));
					count++;
					letter++;
				}				
				
				String[] opsName = optionName.toArray(new String[optionName.size()]);
				String[] opsPrice = optionPrice.toArray(new String[optionName.size()]);
				temp.initOptions(count-1, counter-1);
				temp.setOptionName(opsName, counter-1);
				temp.setOptionPrice(opsPrice, counter-1);
				optionName.clear();
				optionPrice.clear();
				counter++;
				letter = 'a';
				buffer = p.getProperty("Option"+counter);
			}
			break;
		case "Auto":
			temp = fio.readFile((File)o);
			break;
		}
		addAuto(temp);
	}
	public synchronized void editAuto(String key, String[] args, int processNum)
	{
		if(autoMap.get(key) != null)
		{
			EditOptions eo = new EditOptions(key, args, processNum);
			eo.start();
		}
	}
	public void fix(AutomobileError e, String key)
	{
		AutoException ae = new AutoException(e);
		if(autoMap.get(key) != null)
			ae.fix(autoMap.get(key));
	}
	public Automobile get(String key)
	{
		return autoMap.get(key);
	}
	public ArrayList<String> getModelList()
	{
		ArrayList<String> temp = new ArrayList<String>();
		temp.addAll(autoMap.keySet());
		return temp;
	}
	public String printAuto(String key) {
		if(autoMap.get(key) != null)
			return autoMap.get(key).toString();
		else return null;
	}
	public void updateOptionPrice(String key, String optionName, float newPrice) {
		if(autoMap.get(key) != null)
			autoMap.get(key).updateOptionPrice(optionName, newPrice);		
	}
	public void updateOptionSetName(String key, String oldName, String newName) {
		if(autoMap.get(key) != null)
			autoMap.get(key).updateOptionSetName(oldName, newName);		
	}
	public void updateOptionChoice(String key, String opSetName, String optionName)
	{
		if(autoMap.get(key) != null)
		{
			autoMap.get(key).setOptionChoice(opSetName, optionName);
			autoMap.get(key).rebuildChoiceLink();
		}
	}
	public String printAll()
	{
		Iterator<Automobile> list = autoMap.values().iterator();
		StringBuilder sb = new StringBuilder();
		while(list.hasNext())
			sb.append(list.next().toString());
		return sb.toString();
	}
	public void sendAuto(ObjectOutputStream oos, String key)
	{
		if(autoMap.get(key) != null)
		{
			try
			{
				oos.writeObject(autoMap.get(key));
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}
	}
}
