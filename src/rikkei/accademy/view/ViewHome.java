package rikkei.accademy.view;

import rikkei.accademy.config.Config;
import rikkei.accademy.controller.UserController;
import rikkei.accademy.model.Role;
import rikkei.accademy.model.RoleName;
import rikkei.accademy.model.User;

import java.util.ArrayList;

public class ViewHome {
    UserController userController = new UserController();
    User currenUser = userController.grtCurrenUser();

    RoleName roleName = new ArrayList<>(currenUser.getRoles()).get(0).getRoleName();

    public ViewHome(){
       switch (roleName){
           case ADMIN:
               menuAdmin();
               break;
           case USER:
               menuUser();
               break;
       }
    }


    public void menuUser(){
        System.out.println("*****************WELCOME*************************");
        System.out.println("Hello: "  + currenUser.getName());
        System.out.println("1: Log Out");
        System.out.println("2: User Profile");


        int choice = Integer.parseInt(Config.scanner().nextLine());
        switch (choice){
            case 1:
                userController.logOut();
                new ViewMainMenu().menu();
               return;
            case 2:
                formProfile();
                break;
        }
        menuUser();
    }

    private void formProfile() {

    }

    public void menuAdmin(){
        System.out.println("*****************WELCOME*************************");
        System.out.println("Hello: " + roleName.name() );
        System.out.println("1: Log Out");
        System.out.println("2: User manage");
        System.out.println();

        int choice = Integer.parseInt(Config.scanner().nextLine());
        switch (choice){
            case 1:
                userController.logOut();
                new ViewMainMenu().menu();
                return;
            case 2:
                formUserManage();
                break;
        }
    }

    private void formUserManage() {
    }
}
