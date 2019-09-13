package model;


import javax.persistence.*;

@Entity
@Table(name = "users", schema = "user_test")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "age")
    private int age;

    @Basic
    @Column(name = "login", unique = true)
    private String login;

    @Basic
    @Column(name = "password")
    private String password;

    @Basic
    @Column(name = "role")
    private String role = "user";


    public User(int id, String name, int age, String login, String password, String role) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public User() {

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
