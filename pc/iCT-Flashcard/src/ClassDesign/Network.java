/*


 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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


public class Network {

    /**
     * @param args the command line arguments
     */
    public DictWord searchWord(String inputWord) throws MalformedURLException, IOException {

        // TODO code application logic here
    	String urlString =  "https://ict-flashcard-server.herokuapp.com/api/dictionary/ev?lookup=" + inputWord;
        URL url = new URL(urlString);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept", "application/json");

        int responseCode = con.getResponseCode();
        System.out.println("nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String output;
        StringBuffer response = new StringBuffer();

        while ((output = in.readLine()) != null) {
            response.append(output);
        }
        in.close();

        //printing result from response
        System.out.println(response.toString());

        Gson gson = new Gson();
        DictWord data = gson.fromJson(response.toString(), DictWord.class); 
        WordNotFound newdata = gson.fromJson(response.toString(), WordNotFound.class);
        
        data.setMeaning(data.getMeaning().replaceAll("<br>", "\n"));
        
        if(newdata.getSuccess() == "false") {
        	data = new DictWord(); 
        	data.setSuccess("false");
        	data.setWord("");
        	data.setIpapron("");
        	data.setMeaning("");
        }
       
        
        return data;

    }
    
    public CheckLogin getLoginState(String username, String password) throws IOException {
		 
		 String urlParameters  = "username="+username+"&password="+password;
		 byte[] postData       = urlParameters.getBytes( StandardCharsets.UTF_8 );
		 int    postDataLength = postData.length;
		 String request        = "https://ict-flashcard-server.herokuapp.com/api/user/login";
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
	        CheckLogin check = gson.fromJson(result, CheckLogin.class); 
	        return check;
	        	
	 }
	 
	 public RegisterUser register(String user, String pw, String email, String fname) throws IOException {
		 String urlParameters  = "username="+user+"&password="+pw+"&email="+email+"&fullname="+fname;
		 byte[] postData       = urlParameters.getBytes( StandardCharsets.UTF_8 );
		 int    postDataLength = postData.length;
		 String request        = "https://ict-flashcard-server.herokuapp.com/api/user/register";
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
	        Gson gson = new Gson();
	        RegisterUser reg = gson.fromJson(result, RegisterUser.class);
	        
	        System.out.println(reg.getSuccess());
	        return reg;

	 }
	 
	    public UserInfor getUserInfor(String username, String token) throws IOException {
			 
			 String urlParameters  = "username="+username+"&token="+token;
			 byte[] postData       = urlParameters.getBytes( StandardCharsets.UTF_8 );
			 int    postDataLength = postData.length;
			 String request        = "https://ict-flashcard-server.herokuapp.com/api/user/getinfo";
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
		        UserInfor check = gson.fromJson(result, UserInfor.class); 
		        return check;
		        	
		 }
	 
public Success Logout(String username, String token) throws IOException {
		 
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
	        Success check = gson.fromJson(result, Success.class); 
	        return check;
	        	
	 }
	 
	 public void writeFlashcardData() throws IOException {
		 	String urlString =  "https://ict-flashcard-server.herokuapp.com/pinned-flashcard.json";
	        URL url = new URL(urlString);
	        HttpURLConnection con = (HttpURLConnection) url.openConnection();
	        con.setRequestMethod("GET");
	        con.setRequestProperty("Content-Type", "application/json");
	        con.setRequestProperty("Accept", "application/json");

	        int responseCode = con.getResponseCode();
	        System.out.println("nSending 'GET' request to URL : " + url);
	        System.out.println("Response Code : " + responseCode);

	        BufferedReader in = new BufferedReader(
	                new InputStreamReader(con.getInputStream()));
	        String output;
	        StringBuffer response = new StringBuffer();
	        
	        Gson gson = new Gson();
	        PrintWriter out = new PrintWriter("flashcard.txt");

	        while ((output = in.readLine()) != null) {
	            response.append(output);
	        }
	        String outString = response.toString();
	        outString.trim();
	        System.out.println(outString);
	        
//        	Flashcard[] data = gson.fromJson(output, Flashcard[].class); 
//        	
//        	for (int i = 0; i < data.length; i++) {
//        	out.println(data[i].getTitle());
//        	out.println(data[i].getIpapron());
//        	out.println(data[i].getContent());
//        	out.println(data[i].getImage());
//        	data[i].printInfor();
//        	}
        	
	        in.close();
	        out.close();
	 }
	 
//	 public UpdateInfor updateUserInfor(String username, String token, User newUserInfor) throws IOException {
//		 
//		 String urlParameters  = "username="+username+"&token="+token;
//		 byte[] postData       = urlParameters.getBytes( StandardCharsets.UTF_8 );
//		 int    postDataLength = postData.length;
//		 String request        = "https://ict-flashcard-server.herokuapp.com/api/user/getinfo";
//		 URL    url            = new URL( request );
//		 HttpURLConnection connection= (HttpURLConnection) url.openConnection();           
//		 connection.setDoOutput( true );
//		 connection.setInstanceFollowRedirects( false );
//		 connection.setRequestMethod( "POST" );
//		 connection.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded"); 
//		 connection.setRequestProperty( "charset", "utf-8");
//		 connection.setRequestProperty( "Content-Length", Integer.toString( postDataLength ));
//		 connection.setUseCaches( false );
//	         
//	        // Write data
//
//		DataOutputStream wr = new DataOutputStream( connection.getOutputStream());
//			   wr.write( postData );
//			
//	         
//	        // Read response
//	        StringBuilder responseSB = new StringBuilder();
//	        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//	          
//	        String line;
//	        while ( (line = br.readLine()) != null)
//	            responseSB.append(line);
//	                 
//	        // Close streams
//	        br.close();
//	        wr.close();
//	        String result = responseSB.toString();
//	        System.out.println(result);
//	        Gson gson = new Gson();
//	        UserInfor check = gson.fromJson(result, UserInfor.class); 
//	        return check;
//	        	
//	 }

}



