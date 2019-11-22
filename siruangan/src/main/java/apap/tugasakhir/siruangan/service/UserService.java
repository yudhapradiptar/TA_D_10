package apap.tugasakhir.siruangan.service;

import java.text.ParseException;

import apap.tugasakhir.siruangan.model.UserModel;

public interface UserService {
    UserModel addUser(UserModel user);
    public String encrypt(String password);
    UserModel getUserByUsername (String username);
    public String generateNig(UserModel user, String tanggalLahir) throws ParseException;
    public String generateNis(UserModel user, String tanggalLahir) throws ParseException;
    UserModel getCurrentLoggedInUser();

}


