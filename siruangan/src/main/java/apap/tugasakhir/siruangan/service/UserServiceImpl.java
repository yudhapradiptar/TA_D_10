package apap.tugasakhir.siruangan.service;

<<<<<<< HEAD
import apap.tugasakhir.siruangan.model.UserModel;
import apap.tugasakhir.siruangan.repository.UserDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    UserDB userDb;

    @Override
    public UserModel getUserById(String idUser){return userDb.findById(idUser).get();}

    @Override
    public UserModel getUserByUsername(String username) {return userDb.findByUsername(username);}
=======
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import apap.tugasakhir.siruangan.model.UserModel;
import apap.tugasakhir.siruangan.repository.UserDB;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDB userDb;

    @Override
    public UserModel addUser(UserModel user){
        String pass = encrypt(user.getPassword());
        user.setPassword(pass);
        return userDb.save(user);
    }

    @Override
    public String encrypt(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        return hashedPassword;
    }
    
    @Override
    public UserModel getUserByUsername(String username) {
        return userDb.findByUsername(username);
    }
>>>>>>> dbd5c1e69d9e1e88852dbb1ecff69e90fe720b60
}
