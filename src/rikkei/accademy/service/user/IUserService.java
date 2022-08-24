package rikkei.accademy.service.user;

import rikkei.accademy.model.User;
import rikkei.accademy.service.IGenericService;

public interface IUserService extends IGenericService<User> {
    boolean existedByUserName(String username);
    boolean existedByMail(String mail);
    boolean checkLogin(String username, String password );
    User findByUserName(String username);
    User getCurrenUser();



}
