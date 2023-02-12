package lk.ijse.phone.util;

public class GetSystemUserDetails {
    private static String id;
    private static String name;
    private static String email;
    private static String rank;

    public GetSystemUserDetails(String id, String name, String email, String rank, String password) {
        this.setId(id);
        this.setName(name);
        this.setEmail(email);
        this.setRank(rank);
        this.setPassword(password);
    }

    public GetSystemUserDetails() {
    }

    private static String  password;


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
