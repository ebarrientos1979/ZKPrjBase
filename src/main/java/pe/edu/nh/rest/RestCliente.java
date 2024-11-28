package pe.edu.nh.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import pe.edu.nh.dto.ClienteDTO;
import pe.edu.nh.dto.FormularioDTO;
import pe.edu.nh.dto.Respuesta;
import pe.edu.nh.model.gif.*;

public class RestCliente {
	private String url= "http://localhost:8065/v1/cliente/";
	HttpURLConnection connection;
	
	public void conectarREST() throws MalformedURLException, IOException {
		this.connection =  (HttpURLConnection) new URL(this.url).openConnection();
	}
	
	public Root getImages(String campo) throws MalformedURLException, IOException{
		Root root = new Root();
		this.url = "https://api.giphy.com/v1/gifs/search?limit=30&api_key=qeasCWE1IA3NB4yvdR55kXnAuH69Engk&q=" + campo;
		
		this.conectarREST();
		
		this.connection.setRequestMethod("GET");
				
		
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
			root = gson.fromJson(response.toString(), 
					new TypeToken<Root>(){}.getType());
			System.out.println(root.getData().size());
		}else {
			System.out.println(responseCode);
			System.out.println(this.url);
			System.out.println("ERROR AL LLAMAR AL METODO GET EN GIFS");			
		}
		
		return root;
		
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
	
	public ClienteDTO grabarCliente(ClienteDTO cliente) throws MalformedURLException, IOException {
		ClienteDTO clienteRespuesta;
		this.url += "save";		
		this.conectarREST();
		
		this.connection.setRequestMethod("POST");
		this.connection.setRequestProperty("Accept", "application/json");
		this.connection.setRequestProperty("Content-Type",  "application/json");
		this.connection.setDoOutput(true);
		
		Gson gson = new Gson();
		String jsonInputString = gson.toJson(cliente);	
	
		try (OutputStream os = this.connection.getOutputStream()){
			byte[] input =  jsonInputString.getBytes("utf-8");
			os.write(input,0, input.length);
		}
		
		int responseCode = this.connection.getResponseCode();
				
		if(responseCode == HttpURLConnection.HTTP_OK) {
			try(BufferedReader br = new BufferedReader(
					new InputStreamReader(this.connection.getInputStream(), "utf-8"))){
				StringBuilder response = new StringBuilder();
				String responseLine;
				//Mientras haya bits en el buffer de lectura
				while((responseLine = br.readLine()) != null) {
					response.append(responseLine.trim());
				}
				
				return gson.fromJson(response.toString(),
							new TypeToken<ClienteDTO>() {}.getType()
						);
			}			
			
		}else {
			System.out.println("ERROR AL LLAMAR AL METODO POST " + responseCode );			
		}
		
		return null;
	}
	
	public Respuesta eliminarCliente(ClienteDTO cliente) throws MalformedURLException, IOException {
		ClienteDTO clienteRespuesta;
		this.url += "delete";		
		this.conectarREST();
		
		this.connection.setRequestMethod("DELETE");
		this.connection.setRequestProperty("Accept", "application/json");
		this.connection.setRequestProperty("Content-Type",  "application/json");
		this.connection.setDoOutput(true);
		
		Gson gson = new Gson();
		String jsonInputString = gson.toJson(cliente);	
		

		try (OutputStream os = this.connection.getOutputStream()){
			byte[] input =  jsonInputString.getBytes("utf-8");
			os.write(input,0, input.length);
		}
		
		int responseCode = this.connection.getResponseCode();
				
		if(responseCode == HttpURLConnection.HTTP_OK) {
			try(BufferedReader br = new BufferedReader(
					new InputStreamReader(this.connection.getInputStream(), "utf-8"))){
				StringBuilder response = new StringBuilder();
				String responseLine;
				//Mientras haya bits en el buffer de lectura
				while((responseLine = br.readLine()) != null) {
					response.append(responseLine.trim());
				}
				
				return gson.fromJson(response.toString(),
							new TypeToken<Respuesta>() {}.getType()
						);
			}			
			
		}else {
			System.out.println("ERROR AL LLAMAR AL METODO POST " + responseCode );			
		}
		
		return null;
	}
	
	
	public List<FormularioDTO> getFormulario(int idFormulario) throws MalformedURLException, IOException{
		List<FormularioDTO> formularios = new ArrayList<FormularioDTO>();
		this.url = "http://localhost:8065/v1/formulario/getDatos?idFormulario=" + String.valueOf(idFormulario);		
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
			formularios = gson.fromJson(response.toString(), 
					new TypeToken<List<FormularioDTO>>(){}.getType());
			
		}else {
			System.out.println("ERROR AL LLAMAR AL METODO GET");			
		}		
		return formularios;		
	}
}
