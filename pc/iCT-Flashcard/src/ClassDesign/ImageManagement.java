package ClassDesign;

import com.google.gson.Gson;

import FrameDesign.DictWord;
import FrameDesign.WordNotFound;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class ImageManagement {
	
	public Success deleteImage(String username, String token, String imageUrl) throws IOException {
		String urlParameters  = "username="+username+"&token="+token+"&imageUrl="+imageUrl;
		 byte[] postData       = urlParameters.getBytes( StandardCharsets.UTF_8 );
		 int    postDataLength = postData.length;
		 String request        = "https://ict-flashcard-server.herokuapp.com/api/user/logout";
		 URL    url            = new URL( request );
		 HttpURLConnection connection= (HttpURLConnection) url.openConnection();           
		 connection.setDoOutput( true );
		 connection.setInstanceFollowRedirects( false );
		 connection.setRequestMethod( "POST" );
		 connection.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded"); 
		 connection.setRequestProperty( "charset", "utf-8");
		 connection.setRequestProperty( "Content-Length", Integer.toString( postDataLength ));
		 connection.setUseCaches( false );
	         
	        // Write data

		DataOutputStream wr = new DataOutputStream( connection.getOutputStream());
			   wr.write( postData );
			
	         
	        // Read response
	        StringBuilder responseSB = new StringBuilder();
	        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	          
	        String line;
	        while ( (line = br.readLine()) != null)
	            responseSB.append(line);
	                 
	        // Close streams
	        br.close();
	        wr.close();
	        String result = responseSB.toString();
	        System.out.println(result);
	        Gson gson = new Gson();
	        Success check = gson.fromJson(result, Success.class); 
	        return check;
	}
	
	public Success updateImage(String username, String token, String image) throws IOException {
		String urlParameters  = "username="+username+"&token="+token+"&image="+image;
		 byte[] postData       = urlParameters.getBytes( StandardCharsets.UTF_8 );
		 int    postDataLength = postData.length;
		 String request        = "https://ict-flashcard-server.herokuapp.com/api/user/logout";
		 URL    url            = new URL( request );
		 HttpURLConnection connection= (HttpURLConnection) url.openConnection();           
		 connection.setDoOutput( true );
		 connection.setInstanceFollowRedirects( false );
		 connection.setRequestMethod( "POST" );
		 connection.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded"); 
		 connection.setRequestProperty( "charset", "utf-8");
		 connection.setRequestProperty( "Content-Length", Integer.toString( postDataLength ));
		 connection.setUseCaches( false );
	         
	        // Write data

		DataOutputStream wr = new DataOutputStream( connection.getOutputStream());
			   wr.write( postData );
			
	         
	        // Read response
	        StringBuilder responseSB = new StringBuilder();
	        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	          
	        String line;
	        while ( (line = br.readLine()) != null)
	            responseSB.append(line);
	                 
	        // Close streams
	        br.close();
	        wr.close();
	        String result = responseSB.toString();
	        System.out.println(result);
	        Gson gson = new Gson();
	        Success check = gson.fromJson(result, Success.class); 
	        return check;
	}

}
