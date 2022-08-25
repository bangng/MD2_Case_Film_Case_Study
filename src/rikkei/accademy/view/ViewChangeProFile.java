package rikkei.accademy.view;

import rikkei.accademy.config.Config;
import rikkei.accademy.controller.UserController;
import rikkei.accademy.dto.request.SinUpDTO;
import rikkei.accademy.model.User;
import rikkei.accademy.service.user.IUserService;
import rikkei.accademy.service.user.UserServiceIMPL;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

public class ViewChangeProFile {
    IUserService userService = new UserServiceIMPL();
    UserController userController = new UserController();
    List<User> userList = userController.getUserList();
    User currentUser = userController.grtCurrenUser();
    
    public void menuProFile(){
        System.out.println("**************Change Profile****************");
        System.out.println("Hello: "+currentUser .getName() + currentUser.getRoles());
        System.out.println("1: Change name");
        System.out.println("2: Change email");
        int choice = Config.scanner().nextInt();
        switch (choice){
            case 1:
                this.formChangeName();
                break;
            case 2:
                this.formChangeEmail();
                break;
        }
    }

    private void formChangeEmail() {
        User user = userController.grtCurrenUser();
        String email;
        boolean validateEmail;
        while (true) {
            System.out.println("Enter email");
            email = Config.scanner().nextLine();
            validateEmail = Pattern.matches("^[A-Za-z]+[A-Za-z0-9._]*@[A-Za-z]+(\\.[a-z]+)$", email);
            if (validateEmail) {
                break;
            } else {
                System.out.println("the name failed! please try again!");

            }
        }
        User user1 = new User(user.getId(),user.getName(),user.getUsername(),email,user.getPassword(),user.getRoles());
        if (userController.existByEmail(email)){
            System.out.println("Email_Existed!!!");
        }else {
            userController.upDateProFile(user1);
            System.out.println("Change Email Success!!!!");
        }
        userController.getUserList();


    }

    private void formChangeName() {
       User user = userController.grtCurrenUser();
        String name;
        boolean validateName;
        while (true) {
            System.out.println("Enter name");
            name = Config.scanner().nextLine();
            validateName = Pattern.matches("[A-Z][a-zA-Z[\\s]]{1,40}", name);
            if (validateName) {
                break;
            } else {
                System.out.println("the name failed! please try again!");

            }


        }
        User user1 = new User(user.getId(),name,user.getUsername(),user.getEmail(),user.getPassword(),user.getRoles());
        userController.upDateProFile(user1);
        System.out.println("Change name success!");
        userController.getUserList();

    }
}
