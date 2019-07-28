package mbg.model;

import java.io.Serializable;

public class Bitter implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Bitter.id
     *
     * @mbggenerated Sun Jul 28 13:33:14 CST 2019
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Bitter.username
     *
     * @mbggenerated Sun Jul 28 13:33:14 CST 2019
     */
    private String username;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Bitter.password
     *
     * @mbggenerated Sun Jul 28 13:33:14 CST 2019
     */
    private String password;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Bitter.first_name
     *
     * @mbggenerated Sun Jul 28 13:33:14 CST 2019
     */
    private String firstName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Bitter.last_name
     *
     * @mbggenerated Sun Jul 28 13:33:14 CST 2019
     */
    private String lastName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Bitter.email
     *
     * @mbggenerated Sun Jul 28 13:33:14 CST 2019
     */
    private String email;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table Bitter
     *
     * @mbggenerated Sun Jul 28 13:33:14 CST 2019
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Bitter.id
     *
     * @return the value of Bitter.id
     *
     * @mbggenerated Sun Jul 28 13:33:14 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Bitter.id
     *
     * @param id the value for Bitter.id
     *
     * @mbggenerated Sun Jul 28 13:33:14 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Bitter.username
     *
     * @return the value of Bitter.username
     *
     * @mbggenerated Sun Jul 28 13:33:14 CST 2019
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Bitter.username
     *
     * @param username the value for Bitter.username
     *
     * @mbggenerated Sun Jul 28 13:33:14 CST 2019
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Bitter.password
     *
     * @return the value of Bitter.password
     *
     * @mbggenerated Sun Jul 28 13:33:14 CST 2019
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Bitter.password
     *
     * @param password the value for Bitter.password
     *
     * @mbggenerated Sun Jul 28 13:33:14 CST 2019
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Bitter.first_name
     *
     * @return the value of Bitter.first_name
     *
     * @mbggenerated Sun Jul 28 13:33:14 CST 2019
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Bitter.first_name
     *
     * @param firstName the value for Bitter.first_name
     *
     * @mbggenerated Sun Jul 28 13:33:14 CST 2019
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName == null ? null : firstName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Bitter.last_name
     *
     * @return the value of Bitter.last_name
     *
     * @mbggenerated Sun Jul 28 13:33:14 CST 2019
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Bitter.last_name
     *
     * @param lastName the value for Bitter.last_name
     *
     * @mbggenerated Sun Jul 28 13:33:14 CST 2019
     */
    public void setLastName(String lastName) {
        this.lastName = lastName == null ? null : lastName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Bitter.email
     *
     * @return the value of Bitter.email
     *
     * @mbggenerated Sun Jul 28 13:33:14 CST 2019
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Bitter.email
     *
     * @param email the value for Bitter.email
     *
     * @mbggenerated Sun Jul 28 13:33:14 CST 2019
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Bitter
     *
     * @mbggenerated Sun Jul 28 13:33:14 CST 2019
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", username=").append(username);
        sb.append(", password=").append(password);
        sb.append(", firstName=").append(firstName);
        sb.append(", lastName=").append(lastName);
        sb.append(", email=").append(email);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}