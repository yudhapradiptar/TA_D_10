package apap.tugasakhir.siruangan.service;

import apap.tugasakhir.siruangan.model.UserModel;

public interface UserService {

    UserModel getUserByUsername(String username);

    UserModel addUser(UserModel user);
    public String encrypt(String password);

}
