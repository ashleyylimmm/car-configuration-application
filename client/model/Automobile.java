/**
 *	Automotive.java
 *	Automotive class contains methods and properties for which a general Automotive should contain and be able to operate with
 *	This class also contains wrapper methods for most (all, soonTM) protected methods in OptionSet and Option
 *	Eclipse Neon.2 Release (4.6.2), macOS Sierra
 *	Java SE 8 [1.8.0_45]
 *	@author Ashley Lim
 *	@version June 2018
 */
package model;
import java.io.Serializable;
import java.util.ArrayList;
import exception.AutoException;
import exception.AutomobileError;
public class Automobile implements Serializable {
	private String name;
	private String year;
	private String make;
	private String model;
	private float basePrice;
	private ArrayList<OptionSet> optionSet = new ArrayList<OptionSet>();
	private ArrayList<OptionSet.Option> choice = new ArrayList<OptionSet.Option>();

	public Automobile()
	{
		
	}
	public Automobile(float price)
	{
		setAutomobilePrice(price);
	}
	public Automobile(String name)
	{
		setAutomobileName(name);
	}
	public Automobile(String[] nm_yr_mk_mdl)
	{
		setAutomobileName(nm_yr_mk_mdl[0]);
		setYear(nm_yr_mk_mdl[1]);
		setMake(nm_yr_mk_mdl[2]);
		setModel(nm_yr_mk_mdl[3]);		
	}
	public Automobile(String name, float price, int options)
	{
		setAutomobileName(name);
		setAutomobilePrice(price);
		initOptionSet(options);
	}
	public Automobile(String name, float price, ArrayList<OptionSet> optionSet)
	{
		setAutomobileName(name);
		setAutomobilePrice(price);
		setOptionSet(optionSet);
	}

	public String getAutomobileName()
	{
		return name;
	}
	public float getAutomobilePrice()
	{
		return basePrice;
	}
	public String getMake()
	{
		return make;
	}

    public String getModel()
    {
    	return model;
    }
	public OptionSet.Option getOption(int opSetIndex, int optionIndex)
	{
		if(opSetIndex >= 0 && opSetIndex < optionSet.size())
			return optionSet.get(opSetIndex).getOption(optionIndex);
		return null;
	}
	public String getOptionName(int opSetIndex, int optionIndex)
	{
		if(opSetIndex >= 0 && opSetIndex < optionSet.size())
			return optionSet.get(opSetIndex).getOptionName(optionIndex);
		return null;
	}
	public float getOptionPrice(int opSetIndex, int optionIndex)
	{
		if(opSetIndex >= 0 && opSetIndex < optionSet.size())
			return optionSet.get(opSetIndex).getOptionPrice(optionIndex);
		return -1;
	}
	public ArrayList<OptionSet.Option> getOptions(int index)
	{
		// Options not visible? invalid?
		if(index >= 0 && index < optionSet.size())
			if(optionSet.get(index) != null)
				return optionSet.get(index).getOptions();
		return null;
	}
	
	public int getOptionArrLength(int opSetIndex)
	{
		if(opSetIndex >= 0 && opSetIndex < optionSet.size())
			return optionSet.get(opSetIndex).getOptionArrLength();
		else 
			return -1;
	}
	public String getOptionChoice(String opSetName)
	{
		for(int i = 0; i < optionSet.size(); i++)
			if(optionSet.get(i).getName().equals(opSetName))
					return optionSet.get(i).getOptionChoice().getName();
		return null;
	}
	public float getOptionChoicePrice(String opSetName)
	{
		for(int i = 0; i < optionSet.size(); i++)
			if(optionSet.get(i).getName().equals(opSetName))
					return optionSet.get(i).getOptionChoice().getPrice();
		return 0;
	}
	public ArrayList<OptionSet> getOptionSet()
	{
		return optionSet;
	}
	public String getOptionSetName(int index)
	{
		if(index >= 0 && index < optionSet.size())
			if(optionSet.get(index) != null)
				return optionSet.get(index).getName();
		return null;
	}
	public float getTotalPrice()
	{
		float sum = 0;
		sum += basePrice;
		rebuildChoiceLink();
		for(int i = 0; i < choice.size(); i++)
		{
			sum += choice.get(i).getPrice();
		}
		return sum;
	}
	public String getYear()
	{
		return year;
	}
	public void setAllOptions(ArrayList<OptionSet.Option> o, int index)
	{
		if(index < optionSet.size())
		{
			optionSet.get(index).setAllOptions(o);
		}
	}
	public void setAutomobileName(String name)
	{
		try{
		if(name == null || name.equals(""))
			throw new AutoException(AutomobileError.IllegalModelName);
		else
			this.name = name;
		}
		catch(AutoException e)
		{
			e.fix(this);
		}
	}
	public void setAutomobilePrice(float price)
	{
		try {
			if(price <= 0)
				throw new AutoException(AutomobileError.IllegalModelPrice);
			else
				this.basePrice = price;
		}
		catch(AutoException e)
		{
			e.fix(this);
		}
		
	}
	public void setMake(String make)
	{
		this.make = make;
	}
	public void setModel(String model)
	{
		this.model = model;
	}
	public void setOptionChoice(String opSetName, String opName)
	{
		for(int i = 0; i < optionSet.size(); i++)
		{
			if(optionSet.get(i).getName().equals(opSetName))
			{				
				optionSet.get(i).setOptionChoice(opName);
				break;
			}
		}
	}
	public void setOptionName(String name, int opSetIndex, int optionIndex)
	{
		if(opSetIndex >= 0 && opSetIndex < optionSet.size())
		{
			try
			{
				optionSet.get(opSetIndex).setOptionName(name, optionIndex);
			}
			catch(AutoException e)
			{
				e.fix(this);
			}
		}
			
	}
	public void setOptionName(String[] strArray, int index)
	{
		if(index >= 0 && index < optionSet.size())
		{
			try
    		{
				optionSet.get(index).setOptionName(strArray);
    		}
			catch(AutoException e)
			{
				e.fix(this);
			}
		}
			
	}
	public void setOptionPrice(float price, int opSetIndex, int optionIndex)
	{
		if(opSetIndex >= 0 && opSetIndex < optionSet.size())
			optionSet.get(opSetIndex).setOptionPrice(price, optionIndex);
	}
	public void setOptionPrice(String[] strArray, int index)
	{
		if(index < optionSet.size())
			optionSet.get(index).setOptionPrice(strArray);
	}
	public void setOptionSet(ArrayList<OptionSet> o)
	{
		optionSet = o;
	}
	public void setOptionSetName(String name, int index)
	{
		try
		{
			optionSet.get(index).setName(name);
		}
		catch(AutoException e)
		{
			e.fix(this);
		}
	}
	public void setYear(String year)
	{
		this.year = year;
	}
	
	public void addOptionSet(String name)
	{
		optionSet.add(new OptionSet(name));
	}
	public int findOption(float price, int opSetIndex)
	{
		if(opSetIndex >= 0 && opSetIndex < optionSet.size())
			return optionSet.get(opSetIndex).findOption(price);
		return -1;
	}
	public int findOption(String name, int opSetIndex)
	{
		if(opSetIndex >= 0 && opSetIndex < optionSet.size())
			return optionSet.get(opSetIndex).findOption(name);
		return -1;
	}
	public int findOptionSet(String name)
	{
		for(int i = 0; i < optionSet.size(); i++)
		{
			if(optionSet.get(i) != null && optionSet.get(i).getName().equals(name))
				return i;
		}
		return -1;
	}
	public void initOptions(int size, int index)
	{
		if(size < 0)
			size = 0;
		if(index < optionSet.size())
			optionSet.get(index).initOptions(size);
	}
	public void initOptionSet(int size)
	{
		if(size < 0)
			size = 0;
		for(int i = 0; i < size; i++)
			optionSet.add(new OptionSet());
	}
	public void rebuildChoiceLink()
	{
		choice.clear();
		for(int i = 0; i < optionSet.size(); i++)
		{
			if(optionSet.get(i).getOptionChoice() != null)
				choice.add(optionSet.get(i).getOptionChoice());
		}
	}
	public boolean removeOption(int opSetIndex, OptionSet.Option o)
	{
		if(opSetIndex >= 0 && opSetIndex < optionSet.size())
			return optionSet.get(opSetIndex).remove(o);
		return false;
	}
	public boolean removeOptionSet(int index)
	{
		if(index >= 0 && index < optionSet.size())
		{
			optionSet.set(index, null);
			return true;
		}
		return false;
	}
	public void resetDefaultOptionChoice()
	{
		for(int i = 0; i < optionSet.size(); i++)
			optionSet.get(i).resetDefaultOptionChoice();
	}
	public void updateOptionSetName(String oldName, String newName)
	{
		int index = findOptionSet(oldName);
		if(index >= 0 && index < optionSet.size())
		{
			try
			{
				optionSet.get(index).setName(newName);
			}
			catch(AutoException e)
			{
				e.fix(this);
			}
		}
	}
	public void updateOptionPrice(String optionName, float newPrice)
	{
		for(int i = 0; i < optionSet.size(); i++)
		{
			if(optionSet.get(i).updateOptionPrice(optionName, newPrice))
				break;
		}
	}
	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder("");
		sb.append(name);
		sb.append("\n");
		sb.append(year);
		sb.append(" // ");
		sb.append(make);
		sb.append(" // ");
		sb.append(model);
		sb.append("\n");
		sb.append("Base Price: $");
		sb.append(basePrice);
		sb.append("\n ");
		for(OptionSet os : optionSet)
		{
			sb.append(os.toString());
		}
		sb.append("Selected Option Choices: \n");
		
		for(OptionSet.Option o : choice)
		{
			sb.append(o.toString());
			sb.append("\n");
		}
		sb.append("Total Price: $");
		sb.append(getTotalPrice());
		return sb.toString();
	}
}
