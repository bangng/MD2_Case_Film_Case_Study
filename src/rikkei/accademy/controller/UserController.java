package rikkei.accademy.controller;

import rikkei.accademy.config.Config;
import rikkei.accademy.dto.request.SinUpDTO;
import rikkei.accademy.dto.response.ResponseMessenger;
import rikkei.accademy.model.Role;
import rikkei.accademy.model.RoleName;
import rikkei.accademy.model.User;
import rikkei.accademy.service.role.IRoleService;
import rikkei.accademy.service.role.RoleServiceIMPL;
import rikkei.accademy.service.user.IUserService;
import rikkei.accademy.service.user.UserServiceIMPL;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserController {

    IRoleService roleService = new RoleServiceIMPL();
    IUserService userService = new UserServiceIMPL();

    public List<User> getUserList() {
        return userService.findAll();
    }

    public ResponseMessenger register(SinUpDTO sinUpDTO) {
        if (userService.existedByUserName(sinUpDTO.getUsername())) {
            return new ResponseMessenger("Username_Existed!!!");
        }
        if (userService.existedByMail(sinUpDTO.getEmail())){
            return new ResponseMessenger("Email_Existed!!!");
        }
        Set<String> strRoles = sinUpDTO.getRoles();
        Set<Role> roles = new HashSet<>();
        for (String role: strRoles) {
            switch (role){
                case "pm":
                    roles.add(roleService.findByRoleName(RoleName.PM));
                        break;
                case "admin":
                    roles.add(roleService.findByRoleName(RoleName.ADMIN));
                    break;
                case "user":
                    roles.add(roleService.findByRoleName(RoleName.USER));
                    break;
                default:
                    return new ResponseMessenger("Invalid role");

            }
        }


       User user = new User(
               sinUpDTO.getId(),
               sinUpDTO.getName(),
               sinUpDTO.getUsername(),
               sinUpDTO.getEmail(),
               sinUpDTO.getPassword(),
               roles
       );
       userService.save(user);
       getUserList();
       return new ResponseMessenger("Create success");

    }
    public ResponseMessenger login(SinUpDTO sinUpDTO){
        if (userService.checkLogin(sinUpDTO.getUsername(),sinUpDTO.getPassword())){
            User user = userService.findByUserName(sinUpDTO.getUsername());
            List<User> userLogin = new ArrayList<>();
            userLogin.add(user);
            new Config<User>().writeFile(Config.PATH_USER_PRINCIPAL,userLogin);
            return new ResponseMessenger("Login-Success");
        }else {
            return new ResponseMessenger("Login-Failed!!!");
        }
    }
    public User grtCurrenUser(){
        return userService.getCurrenUser();
    }

    public void logOut(){
        new Config<User>().writeFile(Config.PATH_USER_PRINCIPAL,null);
    }

}
