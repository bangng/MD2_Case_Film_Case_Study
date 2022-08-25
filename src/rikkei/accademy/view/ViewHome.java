package rikkei.accademy.view;

import rikkei.accademy.config.Config;
import rikkei.accademy.controller.UserController;
import rikkei.accademy.model.Role;
import rikkei.accademy.model.RoleName;
import rikkei.accademy.model.User;

import java.util.ArrayList;
import java.util.List;

public class ViewHome {
    UserController userController = new UserController();
    User currenUser = userController.grtCurrenUser();
    List<User> userList = userController.getUserList();

    RoleName roleName = new ArrayList<>(currenUser.getRoles()).get(0).getRoleName();

    public ViewHome(){
       switch (roleName){
           case ADMIN:
               menuAdmin();
               break;
           case PM:
               menuPm();
               break;
           case USER:
               menuUser();
               break;


       }
    }

    private void menuPm() {
        System.out.println("*****************WELCOME*************************");
        System.out.println("Hello: " + roleName + " : " + currenUser.getName() );
        System.out.println("1: Log Out");

        System.out.println("2: User manage");
        System.out.println("3: Show list user");

        int choice = Integer.parseInt(Config.scanner().nextLine());
        switch (choice){
            case 1:
                userController.logOut();
                new ViewMainMenu().menu();
                return;
            case 2:
                formUserManage();
                break;
            case 3:
                formShowListUser();
                break;
        }
        menuPm();

    }


    public void menuUser(){
        System.out.println("*****************WELCOME*************************");
        System.out.println("Hello: "  + currenUser.getName());
        System.out.println("1: Log Out");
        System.out.println("2: Your Profile");


        int choice = Integer.parseInt(Config.scanner().nextLine());
        switch (choice){
            case 1:
                userController.logOut();
                new ViewMainMenu().menu();
               return;
            case 2:
                new ViewChangeProFile().menuProFile();
                break;
        }
        menuUser();
    }



    public void menuAdmin(){
        System.out.println("*****************WELCOME*************************");
        System.out.println("Hello: " + roleName + " : " + currenUser.getName() );
        System.out.println("1: Log Out");

        System.out.println("2: User manage");
        System.out.println("3: Show list user");

        int choice = Integer.parseInt(Config.scanner().nextLine());
        switch (choice){
            case 1:
                userController.logOut();
                new ViewMainMenu().menu();
                return;
            case 2:
                formUserManage();
                break;
            case 3:
                formShowListUser();
                break;
        }
        menuAdmin();
    }
    private void formShowListUser() {
        System.out.printf("%-15s%-15s%-15s%-25s%-15s%s%n","id","name", "Username","email", "password","Role");
        for (User user: userList) {
            System.out.printf("%-15s%-15s%-15s%-25s%-15s%s%n",user.getId(),user.getName(), user.getUsername(), user.getEmail(), user.getPassword(),user.getRoles() );
        }
    }

    private void formUserManage() {
       new ViewProfile().menu();

    }
}

