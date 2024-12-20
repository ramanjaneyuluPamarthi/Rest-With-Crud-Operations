package com.nit.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nit.entity.UserEntity;
import com.nit.repository.UserRepository;

@RestController
@RequestMapping("/rest")
//@CrossOrigin("*")             //It is used to bind our project to frontend react like libraries
public class WelcomeNoteWithRestApi {
 
	@Autowired
	private UserRepository userRepo;
	
	@GetMapping("/welcome")             //1st approach to communicate with REST Api's
	public String welcomeMsgRest() {
		String msg = "Welcome To Rest...";
		return msg;
	}
	
	@PostMapping(value="/wish",consumes = {"application/xml","application/json"},
			produces = {"application/xml"}
			)              //2nd Approach to for binding method with REST APi
	public ResponseEntity<String> getWishMsg(){
		String msg = "How Are You...";
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}
	
	@PostMapping("/data")      //Here request body is used to convert text data to json format             
	public String insertData(@RequestBody UserEntity userEntity) {
		userRepo.save(userEntity);        //Here iam saving data into db variables should be
		return "Records Inserted...";        //in POSTMAN in the json format then send it 
	}                                             //inserts data into db
	
	@GetMapping("/getdata")
	public String getData() {
		List<UserEntity> all = userRepo.findAll();
		return all.toString();
	}
	
	@GetMapping("/getdata/{id}")               //Path Param
	public String byId(@PathVariable(name = "id") int rt) {
	 UserEntity all = userRepo.findById(rt).get();
	 if(all!=null) {
		return all.toString();
	 }
	 return "Not Found";
	}
	
	@DeleteMapping("/getdelete/{id}")
	public String deleteId(@PathVariable Integer id) {
		userRepo.deleteById(id);
		
		return " Record Deleted...";
	}
}
