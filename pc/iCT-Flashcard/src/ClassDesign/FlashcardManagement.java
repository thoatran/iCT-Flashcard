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

public class FlashcardManagement {
	public Success createFlashcard(String username, String token, String collection_id, String word, String pronunciation, String meaning, String image, int order) throws IOException {
		String urlParameters  = "username="+username+"&token="+token+"&collection_id="+collection_id+"&word="+word+"&pronunciation="+pronunciation+"&meaning="+meaning+"&image="+image+"&order="+order;
		 byte[] postData       = urlParameters.getBytes( StandardCharsets.UTF_8 );
		 int    postDataLength = postData.length;
		 String request        = "https://ict-flashcard-server.herokuapp.com/api/fashcard/create";
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
	
	public Success updateFlashcard(String username, String token, String flashcard_id, String word, String pronunciation, String meaning, String image, int order) throws IOException {
		String urlParameters  = "username="+username+"&token="+token+"&flashcard_id="+flashcard_id+"&word="+word+"&pronunciation="+pronunciation+"&meaning="+meaning+"&image="+image+"&order="+order;
		 byte[] postData       = urlParameters.getBytes( StandardCharsets.UTF_8 );
		 int    postDataLength = postData.length;
		 String request        = "https://ict-flashcard-server.herokuapp.com/api/fashcard/update";
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
	
	public Success deleteFlashcard(String username, String token, String flashcard_id) throws IOException {
		String urlParameters  = "username="+username+"&token="+token+"&flashcard_id="+flashcard_id;
		 byte[] postData       = urlParameters.getBytes( StandardCharsets.UTF_8 );
		 int    postDataLength = postData.length;
		 String request        = "https://ict-flashcard-server.herokuapp.com/api/fashcard/delete";
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
	
	public FlashcardInfor getFlashcardInfor(String username, String token, String flashcard_id) throws IOException {
		String urlParameters  = "username="+username+"&token="+token+"&flashcard_id="+flashcard_id;
		 byte[] postData       = urlParameters.getBytes( StandardCharsets.UTF_8 );
		 int    postDataLength = postData.length;
		 String request        = "https://ict-flashcard-server.herokuapp.com/api/fashcard/get";
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
	        FlashcardInfor check = gson.fromJson(result, FlashcardInfor.class); 
	        return check;
	}
	
	public FlashcardInfor getAllFlashcardInfor(String username, String token, String collection_id) throws IOException {
		String urlParameters  = "username="+username+"&token="+token+"&collection_id="+collection_id;
		 byte[] postData       = urlParameters.getBytes( StandardCharsets.UTF_8 );
		 int    postDataLength = postData.length;
		 String request        = "https://ict-flashcard-server.herokuapp.com/api/fashcard/getall";
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
	        FlashcardInfor check = gson.fromJson(result, FlashcardInfor.class); 
	        return check;
	}

}
