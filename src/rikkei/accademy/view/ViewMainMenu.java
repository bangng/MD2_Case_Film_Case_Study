package rikkei.accademy.view;

import rikkei.accademy.config.Config;
import rikkei.accademy.controller.UserController;
import rikkei.accademy.dto.request.SinUpDTO;
import rikkei.accademy.dto.response.ResponseMessenger;
import rikkei.accademy.model.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ViewMainMenu {
    UserController userController = new UserController();
    List<User> userList = userController.getUserList();
    public void menu(){
        System.out.println("**********MeNu************");
        System.out.println("1: Show user list");
        System.out.println("2: Register");
        System.out.println("3: Login");

        int choice = Integer.parseInt(Config.scanner().nextLine());
        switch (choice){
            case 1:
                formShowListUser();
                break;
            case 2:
                fromRegister();
                break;
            case 3:
                formLogin();
                break;
            default:
                System.out.println("Invalid choice");
        }
        menu();
    }

    private void formLogin() {
        System.out.println("*********************FORM LOGIN*********************");
//        UserName
        String username;
        while (true){
            System.out.println("Enter username");
            username = Config.scanner().nextLine();
            if (username.matches("[a-zA-Z]{1,30}")){
                break;
            }else {
                System.out.println("Invalid username, try again!!");
            }
        }
//        Password
        String password;
        while (true){
            System.out.println("Enter password");
            password = Config.scanner().nextLine();
            if (password.matches("[a-zA-Z0-9]{1,40}")){
                break;
            }else {
                System.out.println("Invalid password, try again!!");
            }
        }
        ResponseMessenger messenger = userController.login(new SinUpDTO(username,password));
        if (messenger.getMessenger().equals("Login-Failed!!!")){
            System.out.println("Login-Failed!!! Please check your username");
            formLogin();
        }else {
            messenger.getMessenger().equals("Login-Success");
            System.out.println("Login-Success ");
            new ViewHome();
        }
    }


    private void fromRegister() {
        System.out.println("****************REGISTER***************");
//        id
        int id;
        if (userList.isEmpty()){
            id = 1;
        }else {
            id = userList.get(userList.size() - 1).getId() + 1;
        }
//        name
        String name;
        while (true){
            System.out.println("Enter name");
            name = Config.scanner().nextLine();
            if (name.matches("[A-Z][a-zA-Z]+")){
                break;
            }else {
                System.out.println("Invalid name, try again!!");
            }

        }
//        username
        String username;
        while (true){
            System.out.println("Enter username");
            username = Config.scanner().nextLine();
            if (username.matches("[a-zA-Z]{1,30}")){
                break;
            }else {
                System.out.println("Invalid username, try again!!");
            }
        }
//        email
        String email;
        while (true){
            System.out.println("Enter email");
            email = Config.scanner().nextLine();
            if (email.matches("^(.+)@(.+)$")){
                break;
            }else {
                System.out.println("Invalid email, try again!!");
            }
        }
//        password
        String password;
        while (true){
            System.out.println("Enter password");
            password = Config.scanner().nextLine();
            if (password.matches("[a-zA-Z0-9]{1,40}")){
                break;
            }else {
                System.out.println("Invalid password, try again!!");
            }
        }
//        Role
        System.out.println("Enter Role");
        String role = Config.scanner().nextLine();
        Set<String> strRole = new HashSet<>();
        strRole.add(role);
        SinUpDTO sinUpDTO = new SinUpDTO(id,name,username,email,password,strRole);
        ResponseMessenger check_existed = userController.register(sinUpDTO);
        if (check_existed.getMessenger().equals("Username_Existed")){
            System.out.println("Username the existed, try again!!!");
            fromRegister();
        }else if (check_existed.getMessenger().equals("Email_Existed!!!")){
            System.out.println("Email the existed, try again!!!");
            fromRegister();
        }else if (check_existed.getMessenger().equals("Create success")){
            System.out.println("checkList: " + userController.getUserList());
        }





    }

    private void formShowListUser() {
        System.out.printf("%-15s%s%n", "Username","Role");
        for (User user: userList) {
            System.out.printf("%-15s%s%n", user.getUsername(), user.getRoles());
        }
    }
}
