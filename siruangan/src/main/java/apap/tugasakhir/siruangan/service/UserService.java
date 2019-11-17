package apap.tugasakhir.siruangan.service;

import apap.tugasakhir.siruangan.model.UserModel;

public interface UserService {
    UserModel getUserById(String idUser);

    UserModel getUserByUsername(String username);
}
