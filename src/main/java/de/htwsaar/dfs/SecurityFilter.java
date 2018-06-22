package de.htwsaar.dfs;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;
import java.util.StringTokenizer;
import java.util.function.Predicate;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.internal.util.Base64;

import de.htwsaar.dfs.can_network.Bootstrap;
import de.htwsaar.dfs.model.User;
import de.htwsaar.dfs.service.UserService;

/**
 * SecurityFilter class.
 * this class filters all access to Images resources. 
 * @author Aude
 *
 */
@Provider
public class SecurityFilter implements ContainerRequestFilter {

	//Authorization header raw
	private static final String AUTHENTICATION_HEADER_KEY = "Authorization";
	//type of Authorization
	private static final String AUTHENTICATION_HEADER_PREFIX = "Basic";
	//Resource to secure
	private static final String SECURED_URL_PREFIX = "images";
	
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		
		//specify that the URI path musts contain "images" before process to secure
		if(requestContext.getUriInfo().getPath().contains(SECURED_URL_PREFIX)) {
			//get authentication header of the request
			List<String> authHeader = requestContext.getHeaders().get(AUTHENTICATION_HEADER_KEY);
			
			//authenticate the user only when the authentication header has informations 
			if(authHeader != null && authHeader.size() > 0) {
				//get the header
				String authToken = authHeader.get(0);
				authToken = authToken.replaceFirst(AUTHENTICATION_HEADER_PREFIX, "");
				authToken = authToken.trim();
				//decode the header
				String decodedString = Base64.decodeAsString(authToken);
				StringTokenizer tokenizer = new StringTokenizer(decodedString, ":");
				//read the header
				String username = tokenizer.nextToken();
				String password = tokenizer.nextToken();
				
				//proceed authentication
				if(Bootstrap.authenticateUser(username, password))
					return;
			}
			//Response to unauthorized user
			Response unAuthStatus = Response.status(Response.Status.UNAUTHORIZED)
					.entity(" This user cannot access the resource.").build();
			requestContext.abortWith(unAuthStatus);
		}
		
	}
	

}
