package rikkei.accademy.service.user;

import rikkei.accademy.config.Config;
import rikkei.accademy.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserServiceIMPL implements IUserService{
    static String PATH_USER = "D:\\IdeaProjects\\MD2_Register_Role_2\\src\\rikkei\\accademy\\database\\user.txt";
    static List<User> userList = new Config<User>().readFile(PATH_USER);


    @Override
    public List<User> findAll() {
        new Config<User>().writeFile(PATH_USER, userList);
        return userList;
    }

    @Override
    public void save(User user) {
        userList.add(user);
        new Config<User>().writeFile(PATH_USER, userList);
    }

    @Override
    public boolean existedByUserName(String username) {
        for (int i = 0; i < userList.size(); i++) {
            if (username.equals(userList.get(i).getUsername())){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean existedByMail(String mail) {
        for (int i = 0; i < userList.size(); i++) {
            if (mail.equals(userList.get(i).getEmail())){
                return  true;
            }
        }
        return false;
    }

    @Override
    public boolean checkLogin(String username, String password) {
        for (int i = 0; i < userList.size(); i++) {
            if (username.equals(userList.get(i).getUsername())&&password.equals(userList.get(i).getPassword())){
                return true;
            }
        }
        return false;
    }

    @Override
    public User findByUserName(String username) {
        for (int i = 0; i < userList.size(); i++) {
            if (username.equals(userList.get(i).getUsername())){
                return userList.get(i);
            }
        }
        return null;
    }

    @Override
    public User getCurrenUser() {
        if (new Config<User>().readFile(Config.PATH_USER_PRINCIPAL) != null){
            User user = new Config<User>().readFile(Config.PATH_USER_PRINCIPAL).get(0);
            return user;
        }
        return null;

    }

}
