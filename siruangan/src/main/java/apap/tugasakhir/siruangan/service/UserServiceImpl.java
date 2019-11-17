package apap.tugasakhir.siruangan.service;

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
}
