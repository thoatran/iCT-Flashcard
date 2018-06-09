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


public class CollectionManagement {
	public Success createCol(String username, String token, String photoUrl, String name, String description) throws IOException {
		String urlParameters  = "username="+username+"&token="+token+"&photo="+photoUrl+"&name="+name+"&description="+description;
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
	
	public Success updateCol(String username, String token, String colection_id, String photoUrl, String name, String description, String remember_score) throws IOException {
		String urlParameters  = "username="+username+"&token="+token+"&colection_id="+colection_id+"&photo="+photoUrl+"&name="+name+"&description="+description+"&remember_score="+remember_score;
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
	
	public Success deleteCol(String username, String token, String colection_id) throws IOException {
		String urlParameters  = "username="+username+"&token="+token+"&colection_id="+colection_id;
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
	
	public CollectionInfor getColInfo(String username, String token, String colection_id) throws IOException {
		String urlParameters  = "username="+username+"&token="+token+"&colection_id="+colection_id;
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
	        CollectionInfor check = gson.fromJson(result, CollectionInfor.class); 
	        return check;
	}
	
	public CollectionInfor getAllColInfo(String username, String token) throws IOException {
		String urlParameters  = "username="+username+"&token="+token;
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
	        CollectionInfor check = gson.fromJson(result, CollectionInfor.class); 
	        return check;
	}
	

}
