package pe.edu.nh.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import pe.edu.nh.dto.ClienteDTO;

public class RestCliente {
	private String url= "http://localhost:8065/v1/cliente/";
	HttpURLConnection connection;
	
	public void conectarREST() throws MalformedURLException, IOException {
		this.connection =  (HttpURLConnection) new URL(this.url).openConnection();
	}
	
	public List<ClienteDTO> ListarClientes() throws MalformedURLException, IOException{
		List<ClienteDTO> clientes = new ArrayList<ClienteDTO>();
		this.url += "listar";		
		this.conectarREST();
		
		this.connection.setRequestMethod("GET");
		this.connection.setRequestProperty("Accept", "application/json");
		
		int responseCode = this.connection.getResponseCode();
				
		if(responseCode == HttpURLConnection.HTTP_OK) {
			BufferedReader in = new BufferedReader( new InputStreamReader( 
					this.connection.getInputStream() ));
						
			String inputLine;
			StringBuilder response = new StringBuilder();
			
			while((inputLine = in.readLine()) != null) {				
				response.append(inputLine);
			}
			
			in.close();
			
			Gson gson = new Gson();
			clientes = gson.fromJson(response.toString(), 
					new TypeToken<List<ClienteDTO>>(){}.getType());
			
		}else {
			System.out.println("ERROR AL LLAMAR AL METODO GET");			
		}
		
		return clientes;
		
	}
}
