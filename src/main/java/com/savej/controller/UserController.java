package com.savej.controller;

import com.savej.expections.UserException;
import com.savej.model.*;
import com.savej.repository.ProfileDao;
import com.savej.repository.UserDao;
import com.savej.service.userService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private userService uService;

    @Autowired
    private UserDao udao;

//    @Autowired
//   private Product pdao;



    @PostMapping("/user")
    public User saveUserHandler( @Valid @RequestBody User user) {
        return uService.saveUser(user);
    }


    @GetMapping("getuser/{id}")
    public ResponseEntity<User> getUserByIdHandler(@PathVariable("id") Integer id) {
        User user= uService.findById(id);
        return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
    }

    @GetMapping("/allusers")
    public List<User> getAllUserHandler(@RequestParam (required = false,defaultValue = "1") int pageNo,
                                        @RequestParam(required = false,defaultValue = "5") int pageSize,
                                         @RequestParam String sortBy,
                                        @RequestParam String sortDir) {

        Sort sort=null;

        if (sortDir.equalsIgnoreCase("ASC")){
            sort=Sort.by(sortBy).ascending();
        }else {
            sort=Sort.by(sortBy).descending();
        }
         return uService.getAllUser(PageRequest.of(pageNo,pageSize,sort));

    }

    @GetMapping("/byname/{name}")
    public ResponseEntity<User> findByNameHander(@PathVariable("name") String name){
        User user=uService.findByName(name);
        return new ResponseEntity<>(user,HttpStatus.FOUND);
    }

    @GetMapping("/byemail/{email}")
    public ResponseEntity<User> findByEmailHandler(@PathVariable("email") String email){

        User user=uService.findByemail(email);
        return new ResponseEntity<>(user,HttpStatus.CREATED);

    }

    @DeleteMapping("/user/{id}")
    public User deleteByIdHandler(@PathVariable("id") Integer id) {

        return uService.deleteUserById(id);
    }

    @PutMapping("/user")
    public User updateByIdHandler( @RequestBody User user) {

        return uService.updateUserById(user);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<User> updateuserHandler(@PathVariable("id") Integer id,@RequestParam("name") String name){

        User user=uService.updateUser(id,name);

        return new ResponseEntity<>(user,HttpStatus.OK);

    }

    @GetMapping("/testapi/{userId}")
    public UserDTO testApiHandler(@PathVariable("userId") Integer userId) {

        UserDTO result=udao.getUserDetailsById(userId);

        if (result!=null){
            return result;
        }else {
            throw new UserException("User Not Found With id:"+userId);
        }
    }

    @GetMapping("getbyname/{name}")
    public User findByNameContainingIgnoreCaseHandler(@PathVariable("name") String name){

        User user=udao.findByNameContainingIgnoreCase(name);

        if (user!=null){
            return user;
        }else {
            throw new UserException("User Not Found With Id:"+name);
        }

    }


    @GetMapping("getbyemail/{email}")
    public User findByEmailContainingIgnoreCaseHandler(@PathVariable("email") String email){
        User user=udao.findByEmailContainingIgnoreCase(email);

        if (user!=null){
            return user;
        }else {
            throw new UserException("User Not Found With Email:"+email);
        }
    }

    @GetMapping("getbynameandemail/{name}/{email}")
    public List<User> findByNameAndEmailHandler(@PathVariable("name") String name,
                                         @PathVariable("email") String email){
        List<User> user=udao.findByNameAndEmail(name,email);

        if (user.size()>0){
            return user;
        }else {
            throw new UserException("User Not Found With Name And Email:"+name+email);
        }
    }

    @GetMapping("getbynamestart/{name}")
    public List<User> findUserStartWithHandler(@PathVariable("name") String name){
       List<User> user= udao.findUserStartWith(name);

       if (user.size()>0){
           return user;
       }else {
           throw new UserException("User Not Found With Name Starts With:"+name);
       }
    }

    @GetMapping("getbypassword/{password}")
    public User findbyPasswordHandler(@PathVariable("password") String password){
        User user=udao.findbypassword(password);

        if (user!=null){
            return user;
        }else {
            throw new UserException("User Not Found With Password:"+password);
        }
    }







}

