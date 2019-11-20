package apap.tugasakhir.siruangan.service;

import apap.tugasakhir.siruangan.model.UserModel;

public interface UserService {
    UserModel addUser(UserModel user);
    public String encrypt(String password);
    UserModel getUserByUsername (String username);
}
