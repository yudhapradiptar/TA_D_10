package apap.tugasakhir.siruangan.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;
import java.util.Random;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @Override
    public String getUserRole() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String role = "";
        for(GrantedAuthority each : auth.getAuthorities()){
            role = each.getAuthority();
        }
        return role;

    public String generateNig(UserModel user, String tanggalLahir) throws ParseException {
        String NIG= "G";
        String[] tanggal = tanggalLahir.split("-");
        NIG += tanggal[2] + tanggal[1] + tanggal[0];
        Random rnds = new Random();
        String c = String.valueOf((char) (rnds.nextInt(26) + 'A'));
        String b = String.valueOf((char) (rnds.nextInt(26) + 'A'));
        String stringRandom= (c+b).toUpperCase();
        NIG=NIG.concat(stringRandom);
        Random rnd = new Random();
        int number = rnd.nextInt(999);
        String numberRandom= String.format("%03d", number);
        NIG=NIG.concat(numberRandom);
        NIG=NIG.concat(user.getIdUser());
        return NIG;
    }

    @Override
    public String generateNis(UserModel user, String tanggalLahir) throws ParseException {
        String NIS= "S";
        String[] tanggal = tanggalLahir.split("-");
        NIS += tanggal[2] + tanggal[1] + tanggal[0];
        Random rnds = new Random();
        String c = String.valueOf((char) (rnds.nextInt(26) + 'A'));
        String b = String.valueOf((char) (rnds.nextInt(26) + 'A'));
        String stringRandom= (c+b).toUpperCase();
        NIS=NIS.concat(stringRandom);
        Random rnd = new Random();
        int number = rnd.nextInt(999);
        String numberRandom= String.format("%03d", number);
        NIS=NIS.concat(numberRandom);
        NIS=NIS.concat(user.getIdUser());
        return NIS;
    }
}
