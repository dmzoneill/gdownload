package Gdownload;

import java.io.*;
import java.net.*;

/** Fetches the HTML content of a web page (or HTTP header) as a String. */
public class WebPageFetcher {

	private boolean Done = false;
	private boolean Error = false;
	private String ErrorMessage = "";
	private String webPage = "";
	private String googleStream = "";
	private String docid;
	
	public WebPageFetcher(String googleVideo)
	{
		this.Done = false;
		this.Error = false;
		URL url;
		InputStream is = null;
		DataInputStream dis;
		String line;
		String page = "";		
		
		if(!googleVideo.contains("docid"))
		{
			this.Error = true;
			this.ErrorMessage = "Invalid google link";
			return;
			
		}
		
		String[] Query = googleVideo.split("docid=");
		
		int indexOfAmp = Query[1].indexOf('&');
		
		if(indexOfAmp>-1)
		{			
			String[] temp = Query[1].split("&");
			this.docid = temp[0];
		}
		else
		{
			this.docid = Query[1];
		}
		
		

		try {			
		    url = new URL("http://www.googlevideodownload.com/display.php?id="+this.docid+"&source=GOG&r=1");
		    is = url.openStream();  // throws an IOException
		    dis = new DataInputStream(new BufferedInputStream(is));

		    while ((line = dis.readLine()) != null) {
		    	page = page + line;
		    }
		    this.webPage = page;
		    this.Done = true;
		    
		} catch (MalformedURLException mue) {
		     mue.printStackTrace();
		     this.ErrorMessage = "Invalid url (malformed)";
		     this.Error = true;
		} catch (IOException ioe) {
		     ioe.printStackTrace();
		     this.Error = true;
		     this.ErrorMessage = "Unable to read website";
		} finally {
		    try {
		        is.close();
		    } catch (IOException ioe) {
		    	this.ErrorMessage = "Connection broken";
		    }
		}
	}
	
	public String getVideoTitle()
	{
		String[] temp = this.webPage.split("</title>");
		String[] temp2 = temp[0].split("<title>");		
		String[] titleBits = temp2[1].split("-");
		String title = new String();
		
		if(titleBits.length>2)
		{
			titleBits[0] = titleBits[0].replaceAll("[^A-Za-z0-9]", " ");
			titleBits[1] = titleBits[1].replaceAll("[^A-Za-z0-9]", " ");
			title = titleBits[0] + " - " + titleBits[1];
		}
		else
		{
			title = titleBits[0].replaceAll("[^A-Za-z0-9]", " ");
		}
		return title;
	}
	
	public String getGoogleVideoUrl()
	{
		String[] temp = this.webPage.split("<div class=link>");		
		String[] temp2 = temp[2].split("</div>");
		String[] temp3 = temp2[0].split("\"");
		this.googleStream = temp3[1];		
		return this.googleStream;
		
	}
	
	public String getDocid()
	{
		return this.docid;
	}
	
	public String getErrorMessage()
	{
		return this.ErrorMessage;
	}
	
	public boolean getStatus()
	{
		return this.Error;
	}
	
} 
