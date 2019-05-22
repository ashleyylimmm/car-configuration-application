/**
 *	Configuration.java
 *	Write a description of your file here
 *	
 *	Eclipse Neon.2 Release (4.6.2), macOS Sierra
 *	Java SE 8 [1.8.0_45]
 *	@author Ashley Lim
 *	@version June 2018
 */
package configuration;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

import coreservlets.*;
import model.*;

@WebServlet("/configuration")
public class Configuration extends HttpServlet{
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		ServletOutputStream out = response.getOutputStream();
		String modelName = request.getParameter("auto");
        if(modelName == null)
        {
            out.print("Nothing to be configured.");
            return;
        }
        Socket sock = null;
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		try {
			sock = new Socket("localhost", 7135);
			oos = new ObjectOutputStream(sock.getOutputStream());
			ois = new ObjectInputStream(sock.getInputStream());
		}
		catch(UnknownHostException e)
		{
			e.printStackTrace();
		}
		oos.writeObject("send");
		oos.writeObject(modelName);
		Automobile auto = null;
		try
		{ 
			Object o = ois.readObject();
			if(o instanceof Automobile)
				auto = (Automobile)o;
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		out.println(ServletUtilities.headWithTitle("Car Configuration Application"));
		out.print("<html>"
                + "<head><title>Automobile Configuration</title></head><body>"
                + "<center>"
                + "<form method=\"post\" action=\"configuration\">"
                + "<table border=\"1\" style=\"width:50%\">");
		out.println("<tr><td>Make/Model</td><td>" + auto.getMake() + " " + auto.getModel() + " (" +auto.getAutomobileName()+ ")</td></tr>");
		out.println("<tr><td>Base Price</td><td>$" + auto.getAutomobilePrice() +"</td></tr>");
		
		for(int i = 0; i < auto.getOptionSet().size(); i++)
		{
			out.println("<tr>"
                    + "<td>" + auto.getOptionSetName(i) + "</td>"
                    + "<td><select name=\""+auto.getOptionSetName(i)+"\">");
			for(int j = 0; j < auto.getOptions(i).size(); j++)
			{
				out.println("<option value=\""+auto.getOptionName(i, j)+"\">"
                        +auto.getOptionName(i, j)+" ($"+auto.getOptionPrice(i, j)+")"
                        +"</option>");
			}
            out.println("</select></td>");
		}

        out.print("</table>"
                + "<input type=\"hidden\" name=\"auto\" value=\""+ auto.getYear()+auto.getMake()+auto.getModel() +"\">"
                + "<input type=\"submit\" value=\"Submit\">"
                + "</form></center>"
                + "</body></html>");  
        out.close();
	}
	@Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException 
    {
    	String modelName = request.getParameter("auto");
        Socket sock = null;
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		try {
			sock = new Socket("localhost", 7135);
			oos = new ObjectOutputStream(sock.getOutputStream());
			ois = new ObjectInputStream(sock.getInputStream());
		}
		catch(UnknownHostException e)
		{
			e.printStackTrace();
		}
		oos.writeObject("configure");
		try
		{
			ois.readObject(); // buffer throws away modelList 
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		oos.writeObject(modelName);
		Automobile auto = null;
		try
		{ 
			Object o = ois.readObject();
			if(o instanceof Automobile)
				auto = (Automobile)o;
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
    	for(int i = 0; i < auto.getOptionSet().size(); i++)
    	{
    		String optionName = request.getParameter(auto.getOptionSetName(i));
    		auto.setOptionChoice(auto.getOptionSetName(i), optionName);
    		auto.rebuildChoiceLink();
    	}

        request.setAttribute("auto", auto);
        request.getRequestDispatcher("/result.jsp").forward(request, response);
    }
}
