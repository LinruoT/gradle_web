package bitter.mybatis.domain;

import bitter.mybatis.EmpStatus;
import bitter.mybatis.domain.Department;

import java.io.Serializable;

public class Employee implements Serializable {
    private Long id;
    private String firstName;
    private String lastName;
    private int age;
    private String gender;
    private String email;
    private Department dept;
    private EmpStatus empStatus=EmpStatus.LOGOUT;


    public Employee() {
    }

    public Employee(Long id, String firstName, String lastName, int age, String gender, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String isGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDept(Department dept) {
        this.dept = dept;
    }

    public Department getDept() {
        return dept;
    }

    public EmpStatus getEmpStatus() {
        return empStatus;
    }

    public void setEmpStatus(EmpStatus empStatus) {
        this.empStatus = empStatus;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", email='" + email + '\'' +
                ", dept='" + dept + '\'' +
                ", dept='" + dept + '\'' +
                ", status='" + empStatus + '\'' +
                '}';
    }
}
