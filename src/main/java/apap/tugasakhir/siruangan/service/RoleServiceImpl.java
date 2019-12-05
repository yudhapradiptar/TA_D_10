package apap.tugasakhir.siruangan.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import apap.tugasakhir.siruangan.model.RoleModel;
import apap.tugasakhir.siruangan.repository.RoleDB;


@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDB roleDb;

    @Override
    public List<RoleModel> findAll() {
        return roleDb.findAll();
    }
}