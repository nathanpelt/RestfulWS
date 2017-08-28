package com.projects.nathan;

import javax.annotation.PostConstruct;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import com.projects.nathan.Client;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


@Produces({"application/json", "application/xml"})
@Consumes({"application/json", "application/xml"})
public class CrudOperations {
	
ApplicationContext context;
JdbcMethods x;
	
	
@PostConstruct
public void init() {
	context = new ClassPathXmlApplicationContext("jdbc-connection.xml");
	x = (JdbcMethods) context.getBean("JdbcMethods");
	System.out.println("Finished init");
	
}
	
// Create

@POST
@Path("/client/")
public Response create(Client client) {

	x.createClient(client);
	((ConfigurableApplicationContext)context).close();
	return Response.ok().build();
		
	
}


// Read

@GET
@Path("/client/{id}")
public Response read(@PathParam("id") int id) {
	
    ((ConfigurableApplicationContext)context).close();
    return Response.status(200).entity(x.readClient(id)).build();
	
} 

//Update	 

@PUT
@Path("/client/")
public Response update(Client client) {

	x.updateClient(client);
	((ConfigurableApplicationContext)context).close();
	return Response.ok().build();
	
}

//Delete

@DELETE
@Path("/client/{id}")
public Response delete(@PathParam("id") int id) {
	
	x.deleteClient(id);
	return Response.ok().build();
	
}



}


