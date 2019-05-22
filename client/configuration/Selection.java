/**
 *	Config.java
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
import java.net.*;
import java.util.ArrayList;

import coreservlets.*;
import client.*;

@WebServlet("/selection")
public class Selection extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException
	{
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		out.println(ServletUtilities.headWithTitle("Car Configuration Application"));
		out.print("<body>Please select an Automobile<br>" 
                + "<form method=\"get\" action=\"configuration\">"
                + "<select name=\"auto\">");
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
		SelectCarOption sco = new SelectCarOption(oos, ois);
		ArrayList<String> list = sco.getModelList();
		if(list != null)
		{
			for(int i = 0; i < list.size(); i++)
				out.println("<option value=\""+list.get(i)+"\">"+list.get(i)+"</option>");
		}
        out.print("</select>"
                + "<input type=\"submit\" value=\"Submit\">"
                + "</form>"
                + "</body></html>");
        out.close();
	}
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException 
    {
        doGet(request, response);
    }
}
