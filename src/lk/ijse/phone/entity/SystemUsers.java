package lk.ijse.phone.entity;

public class SystemUsers {
    private String id;
    private String name;
    private String email;
    private String userRank;
    private String password;

    public SystemUsers() {
    }

    public SystemUsers(String id, String name, String email, String userRank, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.userRank = userRank;
        this.password = password;
    }

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

    public String getUserRank() {
        return userRank;
    }

    public void setUserRank(String userRank) {
        this.userRank = userRank;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "SystemUsers{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", userRank='" + userRank + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
