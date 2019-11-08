package apap.tugasakhir.siruangan.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name="user")
public class UserModel implements Serializable{
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String idUser;

    @NotNull
    @Size(max=200)
    @Column(name="username", nullable = false)
    private String username;

    @NotNull
    @Lob
    @Size(max=200)
    @Column(name="password", nullable = false)
    private String password;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idRole", referencedColumnName = "idRole", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private RoleModel role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PengadaanFasilitasModel> listPengadaanFasilitas;

    @OneToMany(mappedBy = "userPeminjam", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PeminjamanRuanganModel> listPeminjamanRuanganDiajukan;
    
    @OneToMany(mappedBy = "userPenyetuju", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PeminjamanRuanganModel> listPeminjamanRuanganDisetujui;
    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }
    /**
     * @param role the role to set
     */
    public void setRole(RoleModel role) {
        this.role = role;
    }
    /**
     * @return the role
     */
    public RoleModel getRole() {
        return role;
    }
    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }
    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }
    /**
     * @param idUser the idUser to set
     */
    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }
    /**
     * @return the idUser
     */
    public String getIdUser() {
        return idUser;
    }

    public List<PengadaanFasilitasModel> getListPengadaanFasilitas() {
        return listPengadaanFasilitas;
    }

    public void setListPengadaanFasilitas(List<PengadaanFasilitasModel> listPengadaanFasilitas) {
        this.listPengadaanFasilitas = listPengadaanFasilitas;
    }

    public List<PeminjamanRuanganModel> getListPeminjamanRuanganDiajukan() {
        return this.listPeminjamanRuanganDiajukan;
    }

    public void setListPeminjamanRuanganDiajukan(List<PeminjamanRuanganModel> listPeminjamanRuanganDiajukan) {
        this.listPeminjamanRuanganDiajukan = listPeminjamanRuanganDiajukan;
    }

    public List<PeminjamanRuanganModel> getListPeminjamanRuanganDisetujui() {
        return this.listPeminjamanRuanganDisetujui;
    }

    public void setListPeminjamanRuanganDisetujui(List<PeminjamanRuanganModel> listPeminjamanRuanganDisetujui) {
        this.listPeminjamanRuanganDisetujui = listPeminjamanRuanganDisetujui;
    }
}