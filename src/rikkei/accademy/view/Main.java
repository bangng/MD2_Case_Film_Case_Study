package rikkei.accademy.view;

import rikkei.accademy.controller.UserController;
import rikkei.accademy.model.User;
import rikkei.accademy.view.viewuser.ViewHome;
import rikkei.accademy.view.viewuser.ViewMainMenu;

public class Main {
    UserController userController = new UserController();

    public Main(){
        User currenUser = userController.grtCurrenUser();
        if (currenUser == null){
            new ViewMainMenu().menu();
        }else {
            new ViewHome();
        }

    }

    public static void main(String[] args) {

      new Main();
    }
}
