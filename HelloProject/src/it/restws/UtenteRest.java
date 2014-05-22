package it.restws;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.xml.bind.JAXBElement;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

import DAOs.UtenteDAO;
import entities.Utente;

@Consumes(MediaType.APPLICATION_JSON)
@Path("UtenteRestService")
public class UtenteRest {
	
	private Logger log = Logger.getLogger(UtenteRest.class);
	
	private UtenteDAO utenteDAO = new UtenteDAO();
	
	

	// @GET here defines, this method will method will process HTTP GET
	// requests.
	@GET
	// @Path here defines method level path. Identifies the URI path that a
	// resource class method will serve requests for.
	@Path("/nome/{i}")
	// @Produces here defines the media type(s) that the methods
	// of a resource class can produce.
	@Produces(MediaType.TEXT_XML)
	// @PathParam injects the value of URI parameter that defined in @Path
	// expression, into the method.
	
	public String userNome(@PathParam("i") String i) {




	String nome = i;
	return "<User>" + "<Name>" + nome + "</Name>" + "</User>";
	}




	@GET 
	@Path("/cognome/{j}") 
	@Produces(MediaType.TEXT_XML)
	public String userCognome(@PathParam("j") String j) {




	String cognome = j;
	return "<User>" + "<Age>" + cognome + "</Age>" + "</User>";
	}
	
	@GET 
	@Path("/idpersona/{j}") 
	@Produces(MediaType.APPLICATION_JSON)
	public String FullName(@PathParam("j") int j) {
		
		Utente u = utenteDAO.findById(j);
		
		ObjectMapper mapper = new ObjectMapper();
		 
		// display to console
		try {
			return mapper.writeValueAsString(u);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		//return "<User>" + "<Name>" + u.getNome() + "</Name>" +  "<Cognome>" + u.getCognome() + "</Cognome>" + "</User>";
		
	}
	
	@GET 
	@Path("/all/") 
	@Produces(MediaType.TEXT_XML)
	public String allUser() {
		
		List<Utente> u;
		try {
			u = utenteDAO.findAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
		int i;
		String response = "<Users>";
		
		for (Utente user: u){
			response += "<User>" + "<Name>" + user.getNome()+ "</Name>" +  "<Cognome>" + user.getCognome() + "</Cognome>" + "</User>";
		}
		response += "</Users>";
		return response;
		
	}
	
	@PUT
	@Path("/adduser/")
	@Consumes(MediaType.APPLICATION_XML)
	public Response insert(JAXBElement<Utente> userWrapped){
		boolean result;
		String risp;
		result = utenteDAO.save(userWrapped.getValue());
		log.debug("Method: insert, dopo save. Result = "+ result);
		if(result){
			return Response.status(Status.OK).build();
		}
		else{
			return Response.status(Status.NOT_MODIFIED).build();
		}
	}
	
	@PUT
	@Path("/adduserJSON/")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response insertJSON(String userString){
		ObjectMapper mapper = new ObjectMapper();
		Utente user = new Utente();
		try {
			user = mapper.readValue(userString, Utente.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boolean result;
		String risp;
		result = utenteDAO.save(user);
		log.debug("Method: insert JSON, dopo save. Result = "+ result);
		if(result){
			return Response.status(Status.OK).build();
		}
		else{
			return Response.status(Status.NOT_MODIFIED).build();
		}
			
	}
	
}
