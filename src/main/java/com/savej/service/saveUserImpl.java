package com.savej.service;

import com.savej.expections.UserException;
import com.savej.model.Profile;
import com.savej.model.User;
import com.savej.model.UserDTO;
import com.savej.repository.ProfileDao;
import com.savej.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Service
public class saveUserImpl implements userService{

    @Autowired
    private UserDao uDao;

    @Autowired
    private ProfileDao pDao;

    @Override
    public User saveUser(User user) {

        return uDao.save(user);

    }

    @Override
    public User findById(Integer id) {
        return uDao.findById(id).orElseThrow(()->new UserException("user not found with id:"+id));
    }

    @Override
    public List<User> getAllUser(PageRequest pageRequest) {
         return uDao.findAll(pageRequest).getContent();
    }


    @Override
    public User deleteUserById(Integer userid) throws UserException {
        Optional<User> opt = uDao.findById(userid);

        if(opt.isPresent()){
            User user=opt.get();
            uDao.delete(user);
            return user;
        }else {
            throw new UserException("User Not Found With Id:"+userid);
        }

    }

    @Override
    public User updateUserById(User user) throws UserException {
        Optional<User> opt=uDao.findById(user.getUserId());

        if (opt.isPresent()){
            User user1=opt.get();
            uDao.save(user);
            return user;
        }else {
            throw new UserException("User Not Found With Id:"+user.getUserId());
        }
    }

    @Override
    public User updateUser(Integer userid, String name) throws UserException {
        Optional<User> opt=uDao.findById(userid);

        if (opt.isPresent()){
            User user=opt.get();
            user.setName(name);
            return uDao.save(user);
        }else {
            throw new UserException("User Not Found With id:"+userid);
        }
    }

    @Override
    public User findByName(String name) throws UserException {
        User user= uDao.findByName(name);

        if (user!=null){
            return user;
        }else {
            throw new UserException("User Not Found With Name:"+name);
        }
    }

    @Override
    public User findByemail(String email) throws UserException {
        User user=uDao.findByemail(email);

        if (user!=null){
            return user;
        }else{
            throw new UserException("User Not Found With Email:"+email);
        }
    }







}
