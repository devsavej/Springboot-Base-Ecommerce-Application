package com.savej.repository;

import com.savej.expections.UserException;
import com.savej.model.User;
import com.savej.model.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends JpaRepository<User,Integer> {

    public User findByUserId(Integer userId)throws UserException;
    public User findByName(String name);


    public User findByemail(String email);

    public User findByNameContainingIgnoreCase(String name)throws UserException;

    public User findByEmailContainingIgnoreCase(String email)throws UserException;

    @Query("select new com.savej.model.UserDTO(u.userId,u.name,u.email) from User u where u.userId=:userId")
    public UserDTO getUserDetailsById(@Param("userId") Integer userId);

    @Query("select u from User u where u.name= :name AND u.email=:email")
    public List<User> findByNameAndEmail(@Param("name") String name,
                                         @Param("email")String email);

    @Query("select u from User u where LOWER(u.name) like lower(CONCAT(:name,'%'))")
    public List<User>  findUserStartWith(@Param("name") String name);

    @Query("select u from User u where u.password=:password")
    public User findbypassword(@Param("password") String password);




}
