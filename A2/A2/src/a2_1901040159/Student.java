package a2_1901040159;

import java.time.LocalDate;

public class Student {
    private String id;
    private String name;
    private String dob;
    private LocalDate currentDate = LocalDate.now();
    private String address;
    private String email;

    private boolean check = true;

    private static int year = 2019;
    public Student(String name, String dob, String address, String email) {
        generateStudentID();
        this.name = name;
        this.dob = dob;
        this.address = address;
        this.email = email;
    }

    public Student() {
    }

    public void generateStudentID(){
        this.id = "s" + year;
        year++;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public void setYear(int y){
        year = y;
    }

    public int getYear(){
        return year;
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

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", dob=" + dob +
                ", currentDate=" + currentDate +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
