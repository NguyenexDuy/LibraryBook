package edu.huflit.cnpm_th_quanandduy.model;

public class User {
    private String tenUser;
    private String taiKhoan;
    private String matKhau;
    private String sdtUser;
    private String idUser;

    public User(String idUser,String tenUser, String taiKhoan, String matKhau, String sdtUser) {
        this.idUser=idUser;
        this.tenUser = tenUser;
        this.taiKhoan = taiKhoan;
        this.matKhau = matKhau;
        this.sdtUser = sdtUser;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getTenUser() {
        return tenUser;
    }

    public void setTenUser(String tenUser) {
        this.tenUser = tenUser;
    }

    public String getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getSdtUser() {
        return sdtUser;
    }

    public void setSdtUser(String sdtUser) {
        this.sdtUser = sdtUser;
    }

}
