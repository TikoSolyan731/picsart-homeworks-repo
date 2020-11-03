package student;

public class Student {
    private String firstName;
    private String lastName;
    private int age;
    private char gender;
    private String university;
    private String department = "Computer Science";
    private int yearOfAdmission;
    private int currentYear = 1;
    private double gpa;
    private Homework[] hws;

    public void introduce() {
        System.out.println("Hi, my name is " + firstName + " " + lastName + ". I am " + age + " years old.");
    }

    public Homework[] getHws() {
        return hws;
    }

    public void setHws(Homework[] hws) {
        this.hws = hws;
    }

    public int getCurrentYear() {
        return currentYear;
    }

    public void setCurrentYear(int currentYear) {
        this.currentYear = currentYear;
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
        if (age < 0 || age > 150)
            System.out.println("Enter a valid age.");
        else
            this.age = age;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getYearOfAdmission() {
        return yearOfAdmission;
    }

    public void setYearOfAdmission(int yearOfAdmission) {
        this.yearOfAdmission = yearOfAdmission;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }
}
