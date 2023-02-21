package org.jpmh.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.jpmh.dto.PersonDTO;
import org.jpmh.dto.ResponseDTO;
import org.jpmh.exception.InvalidSessionException;
import org.jpmh.factory.BOFactoryPerson;
import org.jpmh.factory.DTOFactoryPerson;
import org.jpmh.model.Person;
import org.jpmh.services.PersonService;
import org.jpmh.services.SecurityService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.ResourceBundle;

@Api(value = "Resources for Person",
     produces = MediaType.APPLICATION_JSON,
     consumes = MediaType.APPLICATION_JSON
    )

@Path("persons")
public class PersonResource {
    private final ResourceBundle messages = ResourceBundle.getBundle("/locale/MessageBundle");

    @EJB
    private PersonService personServices;
    @EJB
    private SecurityService securtyServices;

    /**
     * Add a new Person
     *
     * @param sessionToken
     * @param personDTO
     * @return The new person that was created
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Add a new Person", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Person added successfully", response = PersonDTO.class),
            @ApiResponse(code = 401, message = "Access not allowed", response = ResponseDTO.class),
            @ApiResponse(code = 500, message = "Unexpected Server Error", response = ResponseDTO.class)})
    public Response addPerson(@HeaderParam("Session-Token") String sessionToken, PersonDTO personDTO) {

        Response.ResponseBuilder builder;

        try {
            securtyServices.validateSessionToken(sessionToken);

            Person person = BOFactoryPerson.getPerson(personDTO);
            long idPerson = personServices.addPerson(person);

            Person personAdded = personServices.getPerson(idPerson);
            PersonDTO personAddedDTO = DTOFactoryPerson.getPersonDTO(personAdded);
            builder = Response.status(Response.Status.CREATED).entity(personAddedDTO);
        } catch (InvalidSessionException e) {
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setMessage(messages.getString("error.unauthorized"));
            builder = Response.status(Response.Status.UNAUTHORIZED).entity(responseDTO);
        } catch (Throwable e) {
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setMessage(messages.getString("error.unexpected"));
            builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(responseDTO);
        }

        return builder.build();
    }

    /**
     * @param id
     * @return The person whose id is indicated in the path parameter
     */
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Returns the person whose id is indicated in the path parameter", //
            httpMethod = "POST"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request successful, the person was returned", response = PersonDTO.class),
            @ApiResponse(code = 500, message = "Unexpected Server Error", response = ResponseDTO.class)
    })
    public Response getPerson(@PathParam("id") String id) {
        Response.ResponseBuilder builder;
        try {
            Person person = personServices.getPerson(Long.valueOf(id));
            PersonDTO personDTO = DTOFactoryPerson.getPersonDTO(person);
            builder = Response.status(Response.Status.OK).entity(personDTO);
        } catch (Throwable e) {
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setMessage(messages.getString("error.unexpected"));
            builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(responseDTO);
        }
        return builder.build();
    }

    /**
     * @return All people in the system
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Returns all people in the system",
                  httpMethod = "POST"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request successful, all people are returned", response = PersonDTO.class),
            @ApiResponse(code = 500, message = "Unexpected Server Error", response = ResponseDTO.class)
    })
    public Response getAllPersonexit
    () {
        Response.ResponseBuilder builder;
        try {
            List<Person> listPersons = personServices.getPersons();
            List<PersonDTO> listPersonsDTO = DTOFactoryPerson.getListPersonDTO(listPersons);
            builder = Response.status(Response.Status.OK).entity(listPersonsDTO);
        } catch (Throwable e) {
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setMessage(messages.getString("error.unexpected"));
            builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(responseDTO);
        }
        return builder.build();
    }

    /**
     *
     */
    @PUT
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Update the indicated person",
            httpMethod = "POST"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request successful, the person was modified", response = PersonDTO.class),
            @ApiResponse(code = 401, message = "Access not allowed", response = ResponseDTO.class),
            @ApiResponse(code = 500, message = "Unexpected Server Error", response = ResponseDTO.class)
    })
    public Response updatePersonPUT(@HeaderParam("Session-Token") String sessionToken, PersonDTO personDTO) {
        Response.ResponseBuilder builder;
        try {
            securtyServices.validateSessionToken(sessionToken);

            Person person = BOFactoryPerson.getPerson(personDTO);
            Person personUpdate = personServices.updatePerson(person);
            PersonDTO personUpdateDTO = DTOFactoryPerson.getPersonDTO(personUpdate);
            builder = Response.status(Response.Status.OK).entity(personUpdateDTO);
        } catch (InvalidSessionException e) {
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setMessage(messages.getString("error.unauthorized"));
            builder = Response.status(Response.Status.UNAUTHORIZED).entity(responseDTO);
        } catch (Throwable e) {
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setMessage(messages.getString("error.unexpected"));
            builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(responseDTO);
        }
        return builder.build();
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Remove the person",
            httpMethod = "POST"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request successful, the person was removed", response = PersonDTO.class),
            @ApiResponse(code = 401, message = "Access not allowed", response = ResponseDTO.class),
            @ApiResponse(code = 500, message = "Unexpected Server Error", response = ResponseDTO.class)
    })
    public Response deletePerson(@HeaderParam("Session-Token") String sessionToken, @PathParam("id") String id) {
        Response.ResponseBuilder builder;
        try {
            securtyServices.validateSessionToken(sessionToken);
            personServices.deletePerson(Long.valueOf(id));
            builder = Response.status(Response.Status.OK).entity(messages.getString("success.deleted"));
        } catch (InvalidSessionException e) {
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setMessage(messages.getString("error.unauthorized"));
            builder = Response.status(Response.Status.UNAUTHORIZED).entity(responseDTO);
        } catch (Throwable e) {
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setMessage(messages.getString("error.unexpected"));
            builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(responseDTO);
        }
        return builder.build();
    }
}