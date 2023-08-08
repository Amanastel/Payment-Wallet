package com.payment.service;



import com.payment.exceptions.UsersException;
import com.payment.model.Users;

import java.util.List;

public interface UserService {

    public Users registerCustomer(Users customer);

    public Users getUserDetailsByEmail(String email)throws UsersException;

    public List<Users> getAllUsersDetails()throws UsersException;

    public Users updateUserDetailsByEmail(String  email, Users users)throws UsersException;

    public Users deleteUserEmail(String email)throws UsersException;
    public List<Users> getAllUsersDetailsByRole(String role)throws UsersException;
}
