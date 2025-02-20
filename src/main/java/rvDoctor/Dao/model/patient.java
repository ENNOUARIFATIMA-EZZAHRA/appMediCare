package rvDoctor.Dao.model;

public class patient {
    private int id;
    private String username;
    private String email;
    private String tel;


    public patient(int id, String username, String email, String tel) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.tel = tel;
    }
    public patient(String username, String email, String tel) {
        this.username = username;
        this.email = email;
        this.tel = tel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }



}