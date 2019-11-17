package apap.tugasakhir.siruangan.service;

import apap.tugasakhir.siruangan.model.UserModel;

public interface UserService {
<<<<<<< HEAD
    UserModel getUserById(String idUser);

    UserModel getUserByUsername(String username);
=======
    UserModel addUser(UserModel user);
    public String encrypt(String password);
    UserModel getUserByUsername (String username);
>>>>>>> dbd5c1e69d9e1e88852dbb1ecff69e90fe720b60
}
