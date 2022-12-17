package io.springboot.main.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.springboot.main.Exception.ResourceNotFoundException;
import io.springboot.main.Model.Employees;
import io.springboot.main.Repository.EmployeesRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController  //a combination of @Controller and @ResponseBody annotation.
@RequestMapping("/api/v1/")
public class EmployeesController {

	    @Autowired                           
		private EmployeesRepository employeesRepository;
	    
	    
	    //get all employees
	    @GetMapping("/employees")
	    public List<Employees> getAllEmployees(){
			return employeesRepository.findAll();
	    	
	    }
	    
	    //create employee 
	    @PostMapping("/employees")
	    public Employees createEmployee(@RequestBody Employees employee) {
	    	return employeesRepository.save(employee);    
	    	}
	    
	    //get employee  by id 
	    @GetMapping("/employees/{id}")
	    public ResponseEntity<Employees>getEmployeesById(@PathVariable Long id) {
	    	
	    	Employees employees = employeesRepository.findById(id)
	    			.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with  id : " +id));
	    	                                                          // if record not found in database, we throw this exception
	    	
	    	return ResponseEntity.ok(employees);
	    	
	    }
	    
	    // update employee 
	    @PutMapping("/employees/{id}")
	   public ResponseEntity<Employees>updateEmployees(@PathVariable Long id, @RequestBody Employees employeeDetails){
	    	
	    	Employees employees = employeesRepository.findById(id)
	    			.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with  id : " +id));
		   
	    	employees.setFirstName(employeeDetails.getFirstName());
	    	employees.setLastName(employeeDetails.getLastName());
	    	employees.setEmailId(employeeDetails.getEmailId());
	    	
	    	Employees updatedEmployees = employeesRepository.save(employees);
	    	return ResponseEntity.ok(updatedEmployees);
	   }
	    
	    //delete employee 
	    @DeleteMapping("/employees/{id}")
	    public ResponseEntity<Map<String, Boolean>>deleteEmplloyee(@PathVariable Long id){
	    
	    	Employees employees = employeesRepository.findById(id)
	    			.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with  id : " +id));
		   
	    	employeesRepository.delete(employees);
	    	Map<String , Boolean>response = new HashMap<>();
	    	response.put("deleted",Boolean.TRUE);
	    	return ResponseEntity.ok(response);
	    }
	
	
	

}
//Application Programming Interface API
//REST API just returns data in form of JSON or XML. This difference is also obvious in the @Controller and @RestController annotation.


//The key difference is that you do not need to use @ResponseBody on each and every handler method once you annotate the class with @RestController.

//@Controller create a Map of Model Object and find a view while @RestController simply return object and object data directly written into http response as JSON orXML.
//@RequestMapping is used at the class level while @GetMapping is used to connect the methods.
//Spring currently supports five types of inbuilt annotations for handling different types of incoming HTTP request methods which are GET, POST, PUT, DELETE, and PATCH. These annotations are:
//@GetMapping - shortcut for @RequestMapping(method = RequestMethod.GET)
//@PostMapping - shortcut for @RequestMapping(method = RequestMethod.POST)
//@PutMapping - shortcut for @RequestMapping(method = RequestMethod.PUT)
//@DeleteMapping - shortcut for @RequestMapping(method =RequestMethod.DELETE)
//@PatchMapping - shortcut for @RequestMapping(method = RequestMethod.PATCH)
//The GET HTTP request is used to get a single or multiple resources and @GetMapping annotation for mapping HTTP GET requests onto specific handler methods.
//The POST HTTP method is used to create a resource and @PostMapping annotation for mapping HTTP POST requests onto specific handler methods.
//The PUT HTTP method is used to update the resource and @PutMapping annotation for mapping HTTP PUT requests onto specific handler methods.
//The DELETE HTTP method is used to delete the resource and @DeleteMapping annotation for mapping HTTP DELETE requests onto specific handler methods.
//The PATCH HTTP method is used when you want to apply a partial update to the resource and @PatchMapping annotation for mapping HTTP PATCH requests onto specific handler methods.
//Consider we want to update the Employee resources partially (only firstName field) in a database.



//Autowiring feature of spring framework enables you to inject the object dependency implicitly. 
//The @Autowired annotation in spring automatically injects the dependent beans into the associated references of a POJO class. This annotation will inject the dependent beans by matching the data-type (i.e. Works internally as Autowiring byType). Developers can apply the @Autowired annotation to the following:

//@Autowired on property
//@Autowired on the setter method
//@Autowired on constructor
