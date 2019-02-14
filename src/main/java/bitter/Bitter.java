package bitter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

//用户类Bitter
public class Bitter {
    private long id;
    @NotNull
    @Size(min = 3,max = 25,message = "{username.size}")
    private String username;
    @NotNull
    @Size(min = 2,max = 25,message = "{firstName.size}")
    private String firstName;
    @NotNull
    @Size(min = 2,max = 25,message = "{lastName.size}")
    private String lastName;
    @NotNull
    @Size(min = 5,max = 25,message = "{password.size}")
    private String password;
    @NotNull
    @Size(min = 5,max = 25,message = "{email.valid}")
    private String email;
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
}
