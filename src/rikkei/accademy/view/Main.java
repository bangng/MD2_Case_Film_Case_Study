package rikkei.accademy.view;

import rikkei.accademy.config.Config;
import rikkei.accademy.controller.UserController;
import rikkei.accademy.model.User;

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
