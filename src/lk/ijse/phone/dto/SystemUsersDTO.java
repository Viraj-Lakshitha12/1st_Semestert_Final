package lk.ijse.phone.dto;

public class SystemUsersDTO {
    private String id;
    private String name;
    private String email;
    private String rank;

    public SystemUsersDTO(String id, String name, String email, String rank, String password) {
        this.setId(id);
        this.setName(name);
        this.setEmail(email);
        this.setRank(rank);
        this.setPassword(password);
    }

    public SystemUsersDTO() {
    }

    private String  password;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
