package apap.tugasakhir.siruangan.service;

import java.util.List;

import org.springframework.stereotype.Service;

import apap.tugasakhir.siruangan.model.RoleModel;


@Service
public interface RoleService {
   List<RoleModel> findAll();
}