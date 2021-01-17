package bitter.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

//用户类Bitter
@Entity
public class Bitter {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Size(min = 3,max = 25,message = "{username.size}")
    @Column(name="username")
    private String username;

    @NotNull
    @Size(min = 1,max = 25,message = "{firstName.size}")
    @Column(name="first_name")
    private String firstName;

    @NotNull
    @Size(min = 1,max = 25,message = "{lastName.size}")
    @Column(name="last_name")
    private String lastName;

    @NotNull
    @Size(min = 5,max = 250,message = "{password.size}")
    @Column(name="password")
    private String password;

    @NotNull
    @Size(min = 5,max = 25,message = "{email.valid}")
    @Column(name="email")
    private String email;

    @OneToOne
    @JoinColumn(name = "icon")
    private Picture icon;

//    // 关注的人
//    @OneToMany
//    @JoinTable(name = "Bitter_relation",
//            joinColumns = { @JoinColumn(name = "Bitter_id", referencedColumnName = "id") },
//            inverseJoinColumns = {
//                    @JoinColumn(name = "Follow_id", referencedColumnName = "id") })
//    private List<Bitter> follows;
//
//    // 粉丝
//    @OneToMany
//    @JoinTable(name = "Bitter_relation",
//            joinColumns = { @JoinColumn(name = "Follow_id", referencedColumnName = "id") },
//            inverseJoinColumns = {
//                    @JoinColumn(name = "Bitter_id", referencedColumnName = "id") })
//    private List<Bitter> fans;
//
//    @Column(name="follow_count")
//    private int followCount;
//
//    @Column(name="fans_count")
//    private int fansCount;

    public Bitter() {}
    public Bitter(Long id,String username,String password,String firstName,String lastName,String email) {
        this.id=id;
        this.username=username;
        this.password=password;
        this.email=email;
        this.firstName=firstName;
        this.lastName=lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setIcon(Picture icon) {
        this.icon = icon;
    }

    public Picture getIcon() {
        return icon;
    }

//    public List<Bitter> getFollows() {
//        return follows;
//    }
//
//    public void setFollows(List<Bitter> followBitters) {
//        this.follows = followBitters;
//    }
//
//    public List<Bitter> getFans() {
//        return fans;
//    }
//
//    public void setFans(List<Bitter> fans) {
//        this.fans = fans;
//    }
}
