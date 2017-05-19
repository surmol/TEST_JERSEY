package rinicom.unity.server.services;

import org.hibernate.HibernateException;
import org.hibernate.mapping.Set;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import rinicom.unity.server.database.EmployeeFacade;
import rinicom.unity.server.database.entities.Employee;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;



@Path("/Employee")
public class EmployeeService {


    private EmployeeFacade EmployeeRepository = new EmployeeFacade();

    @OPTIONS
    @Path("/all")
    public Response preflightGet() {
        return Response.status(200)
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, OPTIONS,HEAD")
				.header("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept")
				.build();
	}

    @GET
    @Path("/all")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getEmployees() {
        try {
            JSONObject jsonResp = new JSONObject();

            List<Employee> EmployeeList = EmployeeRepository.getEmployees();
            if (EmployeeList == null) {
                String jsonReply = jsonResp.toJSONString();
                return Response.status(Response.Status.NO_CONTENT).entity(jsonReply).header("Access-Control-Allow-Origin", "*").build();
            }

            JSONArray arrayObj = new JSONArray();

            for (Employee employee : EmployeeList) {
                JSONObject employeeJson = new JSONObject();
                employeeJson.put("id", employee.getId());
                employeeJson.put("age", employee.getAge());
                employeeJson.put("name", employee.getName());




                arrayObj.add(employeeJson);
            }

            String jsonReply = arrayObj.toJSONString();
          
            return Response.status(Response.Status.OK).entity(jsonReply).header("Access-Control-Allow-Origin", "*").build();
        } catch (HibernateException e) {
            JSONObject jsonRespExcep = new JSONObject();
            jsonRespExcep.put("error", e.toString());
            return Response.status(Response.Status.CONFLICT).entity(jsonRespExcep.toJSONString()).header("Access-Control-Allow-Origin", "*").build();
        }
    }


}





