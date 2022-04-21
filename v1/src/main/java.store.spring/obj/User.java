package obj;

import jakarta.persistence.*;

@Entity // Указывает, что данный бин (класс) является сущностью.
@Table(name = "users")
public class User {
    private static final String SEQ_NAME = "user_seq";

    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = SEQ_NAME)
    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME, allocationSize = 1)
    private int id = 0;
    private String username;
    private String lastname;
    private String firstname;
    private String password;
    private boolean archive; // Есть ли пользователь в архиве

    @OneToOne(cascade = CascadeType.REMOVE)
    private Bucket bucket;


    public User(String username, String lastname, String firstname, String password) {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
