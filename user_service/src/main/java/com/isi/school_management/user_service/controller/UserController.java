package com.isi.school_management.user_service.controller;

import org.springframework.http.HttpStatus;

import com.isi.school_management.user_service.entity.UserEntity;
import com.isi.school_management.user_service.exception.ResourceNotFoundException;
import com.isi.school_management.user_service.dto.UserDto;
import com.isi.school_management.user_service.service.implement.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(path="/creerUser")
    public ResponseEntity<UserDto> creatUser(@RequestBody  UserDto userDto){
        UserDto creatDto =userService.creatUser(userDto);
        return new ResponseEntity<UserDto>(creatDto, HttpStatus.CREATED);
    }
    @GetMapping(path = "/list-all-users")
    public ResponseEntity<List<UserDto>> listAllusers(){
      List<UserDto> userDto = userService.getAllUsers();
      return new ResponseEntity<List<UserDto>>(userDto,HttpStatus.OK);

    }
    @GetMapping("/{id}")
    public  ResponseEntity<UserDto> ListerUserId(@PathVariable long id){
        UserDto userDto= userService.getUserById(id);
        if (userDto == null) {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }
        return  new ResponseEntity<UserDto>(userDto,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<UserDto> UpdateUser(@PathVariable long id,@RequestBody  UserDto userDto) {
        UserDto existingUser = userService.getUserById(id);
        if (existingUser == null) {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }


        UserDto updatedUserDto = userService.updateUser(userDto, id); // Je transmets bien le DTO
        return  new ResponseEntity<>(updatedUserDto,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> DeleteUser(@PathVariable long id) {
        UserDto userDto = userService.getUserById(id);
        if (userDto == null) {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}



