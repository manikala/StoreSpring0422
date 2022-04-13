package obj;


public class User {
    private int id = 0;
    private String username;
    private String lastname;
    private String firstname;
    private int password;


    public User(String username, String lastname, String firstname, int password) {
        this.username = username;
        this.lastname = lastname;
        this.firstname = firstname;
        this.password = password;
        id = id +1;
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

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }
}
