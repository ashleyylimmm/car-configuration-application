/**
 *	FileIO.java
 *	Contains methods which can serialize/deserialize and construct an Automotive object.
 *	
 *	Eclipse Neon.2 Release (4.6.2), macOS Sierra
 *	Java SE 8 [1.8.0_45]
 *	@author Ashley Lim
 *	@version June 2018
 */
package util;
import model.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Scanner;
public class FileIO {

	public Automobile deserializeAuto(String filename)
	{
		try
		{
			FileInputStream fi = new FileInputStream(filename);
			ObjectInputStream in = new ObjectInputStream(fi);
			Object o = in.readObject();
			in.close();
			if(o instanceof Automobile)
				return (Automobile) o;
		}
		catch(IOException e)
		{
			System.err.println(e);
		}
		catch(ClassNotFoundException e)
		{
			System.err.println(e);
		}
		return null;
	}
	public void parseProperties(FileInputStream in, Properties p)
	{
		Scanner s = new Scanner(in);
		Hashtable<String, String> tempHt = new Hashtable<String, String>();
		tempHt.put("CarName", s.nextLine().substring(8));
		tempHt.put("CarYear", s.nextLine().substring(8));
		tempHt.put("CarModel", s.nextLine().substring(9));
		tempHt.put("CarMake", s.nextLine().substring(8));
		tempHt.put("CarPrice", s.nextLine().substring(10));
		int counter = 1;
		char letter = 'a';
		String buffer = s.nextLine();
		while(s.hasNextLine())
		{
			tempHt.put("Option"+counter, buffer.substring(buffer.indexOf("=")+1));
			buffer = s.nextLine();
			while(s.hasNextLine() && !buffer.substring(0,buffer.indexOf("=")).equals("Option"+ (counter+1)))
			{
				String[] np = buffer.split(" // ");
				tempHt.put("OptionValue"+counter+letter, np[0].substring(buffer.indexOf("=")+1));
				tempHt.put("OptionValue"+counter+letter+"Price", np[1]);
				buffer = s.nextLine();
				letter++;
			}
			if(!s.hasNextLine())
			{
				String[] np = buffer.split(" // ");
				tempHt.put("OptionValue"+counter+letter, np[0].substring(buffer.indexOf("=")+1));
				tempHt.put("OptionValue"+counter+letter+"Price", np[1]);
			}
			letter = 'a';
			counter++;
		}
		p.setHashtable(tempHt);
	}
	
	public Automobile readFile(File f)
	{
		Automobile auto = null;
		try
		{
			FileReader file = new FileReader(f);
			BufferedReader buffer = new BufferedReader(file);
			auto = new Automobile(buffer.readLine().split(" - "));
			auto.setAutomobilePrice(Float.parseFloat(buffer.readLine()));
			buffer.readLine(); // empty line
			auto.initOptionSet(Integer.parseInt(buffer.readLine()));
			buffer.readLine(); // empty line
			for(int i = 0; i < auto.getOptionSet().size(); i++)
			{
				auto.setOptionSetName(buffer.readLine(), i);

				String[] options = buffer.readLine().toString().split(", ");
				auto.initOptions(options.length, i);
				auto.setOptionName(options, i);

				String[] optionPrice = buffer.readLine().toString().split(" ");
				auto.setOptionPrice(optionPrice, i);
				buffer.readLine(); // empty line
			}
			buffer.close();
		}
		catch(FileNotFoundException e)
		{
			System.err.println(e.toString());
		}
		catch(IOException e)
		{
			System.err.println(e.toString());
		}
		catch(NumberFormatException e)
		{
			System.err.println(e.toString());
		}
		return auto;
	} // end method

	public void serializeAuto(Automobile auto, String filename)
	{
		try
		{
			FileOutputStream fo = new FileOutputStream(filename);
			ObjectOutputStream out = new ObjectOutputStream(fo);
			out.writeObject(auto);
			out.close();
			fo.close();
			System.out.println("Serialized data is saved in " +filename);
		}
		catch(IOException e)
		{
			System.err.println(e);
		}
	}

	public void appendToFile(String filename, String data)
	{
		try
		{
			File f = new File(filename);
			FileWriter fw = new FileWriter(f, true);
			BufferedWriter bw = new BufferedWriter(fw);
			String timeStamp = new SimpleDateFormat().format( new Date() );
			StringBuilder sb = new StringBuilder(timeStamp);
			sb.append(" ");
			sb.append(data);
			sb.append("\n");
			bw.write(sb.toString());
			bw.close();
		}
		catch(IOException e)
		{
			System.err.println(e);
		}
	}
} // end class
