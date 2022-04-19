package obj;

import jakarta.persistence.*;

@Entity // Указывает, что данный бин (класс) является сущностью.
@Table(name = "users")
public class User {
    @Id
    private int id = 0;
    private String username;
    private String lastname;
    private String firstname;
    private int password;

    @OneToOne(cascade = CascadeType.REMOVE)
    private Bucket bucket;


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
