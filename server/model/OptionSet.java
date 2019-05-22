/**
 *	OptionSet.java
 *	OptionSet class contains an inner class Option
 *	
 *	Eclipse Neon.2 Release (4.6.2), macOS Sierra
 *	Java SE 8 [1.8.0_45]
 *	@author Ashley Lim
 *	@version June 2018
 */
package model;
import java.io.Serializable;
import java.util.ArrayList;
import exception.AutomobileError;
import exception.AutoException;
public class OptionSet implements Serializable{
	protected class Option implements Serializable{
		private String name;
		private float price;
		protected Option()
		{
			
		}
		protected Option(float price)
		{
			setPrice(price);
		}
		protected Option(String name) throws AutoException
		{
			setName(name);
		}
		protected Option(String name, float price) throws AutoException
		{
			setName(name);
			setPrice(price);
		}

		protected String getName() {
			return name;
		}
		protected float getPrice() {
			return price;
		}
		protected void setName(String name) throws AutoException {
			if(name == null || name.equals(""))
				throw new AutoException(AutomobileError.IllegalOptionName);
			else 
				this.name = name;
		}

		protected void setPrice(float price) {
			this.price = price;
		}

		@Override
		public boolean equals(Object obj)
		{
			if(obj instanceof Option)
			{
				Option o = (Option) obj;
				return (this.price == o.price && this.name.equals(o.name));
			}
			return false;
		}
		@Override
		public String toString()
		{
			StringBuilder sb = new StringBuilder("");
			sb.append(name);
			sb.append(" : $");
			sb.append(price);
			return sb.toString();
		}
	}

	// instance variables
	private String name;
	private ArrayList<Option> options = new ArrayList<Option>();
	private Option choice;
		
	// constructors
	protected OptionSet()
	{
		
	}
	protected OptionSet(int numOptions)
	{
		initOptions(numOptions);
	}
	protected OptionSet(String name)
	{
		this.name = name;
	}
	protected OptionSet(String name, int numOptions)
	{
		this.name = name;
		initOptions(numOptions);
	}

	// has no equals method
	
	// instance methods
	protected String getName() {
		return name;
	}
	protected int getOptionArrLength()
	{
		return options.size();
	}
	protected Option getOption(int index) {
		// invalid?
		return options.get(index);
	}
	protected Option getOptionChoice()
	{
		return choice;
	}
	protected String getOptionName(int index)
	{
		Option o = getOption(index);
		if(o != null)
			return o.getName();
		else return "";
	}
	protected String getOptionName(Option o)
	{
		// no wrapper method in Automotive yet
		return o.getName();
	}
	protected float getOptionPrice(int index)
	{
		Option o = getOption(index);
		if(o != null)
			return o.getPrice();
		else return -1;
	}
	protected float getOptionPrice(Option o)
	{
		// no wrapper method in Automotive yet
		return o.getPrice();
	}
	protected ArrayList<Option> getOptions() {
		return options;
	}

	protected void initOptions(int size)
	{
		if(size < 0)
			size = 0;
		for(int i = 0; i < size; i++)
			options.add(new Option());
	}


	protected void setAllOptions(ArrayList<Option> options)
	{
		this.options = options;
	}
	
	protected void setName(String name) throws AutoException
	{
		if(name == null || name.equals(""))
			throw new AutoException(AutomobileError.IllegalOptionSetName);
		else
			this.name = name;

	}
	protected boolean setOption(Option o)
	{
		// not implemented in Automotive yet
		for(int i = 0; i < options.size(); i++)
		{
			if(options.get(i) == null)
			{
				options.set(i, o);
				return true;
			}
		}
		return false;
	}
	protected boolean setOption(Option o, int index)
	{
		// not implemented in Automotive 
		if(index >= 0 && index < options.size())
		{
			options.set(index, o);
			return true;
		}
		return false;
	}
	protected void setOptionChoice(String optionName)
	{
		for(int i = 0; i < options.size(); i++)
		{
			if(options.get(i).getName().equals(optionName))
			{
				choice = options.get(i);
				break;
			}
		}
	}
	protected void setOptionName(String name, int index) throws AutoException
	{
		if(index >= 0 && index < options.size())
			options.get(index).setName(name);
	}
	protected void setOptionName(String[] names) throws AutoException
	{
		if(names.length <= options.size())
			for(int i = 0; i < names.length; i++)
				options.get(i).setName(names[i]);
		else
			for(int i = 0; i < options.size(); i++)
				options.get(i).setName(names[i]);
		
	}
	protected void setOptionPrice(float price, int index)
	{
		if(index >= 0 && index < options.size())
			options.get(index).setPrice(price);
	}
	protected void setOptionPrice(String[] prices) 
	{
		if(prices.length <= options.size())
		{
			for(int i = 0; i < prices.length; i++)
				options.get(i).setPrice(Integer.parseInt(prices[i]));
		}
		else
			for(int i = 0; i < options.size(); i++)
				options.get(i).setPrice(Integer.parseInt(prices[i]));
	}
	protected int findOption(float price)
	{
		for(int i = 0; i < options.size(); i++)
			if(options.get(i).getPrice() == price)
				return i;
		return -1;
	}
	protected int findOption(Option o)
	{ 
		for(int i = 0; i < options.size(); i++)
			if(options.contains(o))
				return i;
		return -1;
	}

	protected int findOption(String name)
	{
		for(int i = 0; i < options.size(); i++)
			if(options.get(i).getName().equals(name))
				return i;
		return -1;
	}
	protected boolean updateOptionPrice(String optionName, float newPrice)
	{
		for(int i = 0; i < options.size(); i++)
		{
			if(options.get(i).getName().equals(optionName))
			{
				options.get(i).setPrice(newPrice);
				return true;
			}
		}
		return false;
	}
	protected boolean remove(Option o)
	{
		if(options.contains(o))
			return options.remove(o);
		else return false;
	}
	protected void resetDefaultOptionChoice()
	{
		if(options.get(0) != null)
			choice = options.get(0);
	}
	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder("");
		sb.append(name);
		sb.append("\n");
		for(Option o : options)
		{
			sb.append("  -");
			sb.append(o.toString());
			sb.append("\n");
		}
		sb.append("\n");
		return sb.toString();
	}
}
