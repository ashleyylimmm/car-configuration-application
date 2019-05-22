/**
 *	SelectCarOption.java
 *	Write a description of your file here
 *	
 *	Eclipse Neon.2 Release (4.6.2), macOS Sierra
 *	Java SE 8 [1.8.0_45]
 *	@author Ashley Lim
 *	@version June 2018
 */
package client;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import model.Automobile;

public class SelectCarOption {
	private ObjectOutputStream oos = null;
	private ObjectInputStream ois = null;
	public SelectCarOption(ObjectOutputStream oos, ObjectInputStream ois)
	{
		this.oos = oos;
		this.ois = ois;			
	}
	public void configure()
	{
		try
		{
    		String input;
    		Scanner s = new Scanner(System.in);
    		System.out.println("List of models: ");
    		ArrayList<String> listModels = (ArrayList<String>) ois.readObject();
    		if(listModels.isEmpty())
    		{
    			System.out.println("There are no models to configure!");
    			return;
    		}
    		for(String str : listModels)
    			System.out.println(str);
    		System.out.println();
    		System.out.print("Enter model to configure: ");
    		input = s.nextLine();
    		oos.writeObject(input);
    		Automobile auto = (Automobile) ois.readObject();
    		System.out.println(auto);
    		for(int i = 0; i < auto.getOptionSet().size(); i++)
    		{
    			System.out.print("For " +auto.getOptionSetName(i)+", pick an Option: ");
    			auto.setOptionChoice(auto.getOptionSetName(i), s.nextLine());
    		}
    		auto.rebuildChoiceLink();
    		for(int i = 0; i < auto.getOptionSet().size(); i++)
    		{
    			System.out.println(auto.getOptionChoice(auto.getOptionSetName(i)) +" : " +auto.getOptionChoicePrice(auto.getOptionSetName(i))); 
    		}
    		System.out.println("Final price: $" + auto.getTotalPrice());
    		System.out.println();
		}
    	catch(IOException | ClassNotFoundException e)
    	{
    		e.printStackTrace();
    	}
	}
	public ArrayList<String> getModelList()
	{
		Object o = null;
		try
		{
			oos.writeObject("list");
			o = ois.readObject();
			if(o instanceof ArrayList<?>)
				return (ArrayList<String>)o;
		}
		catch(ClassNotFoundException | IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
