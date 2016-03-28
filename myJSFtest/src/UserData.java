import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "userData", eager = true)
@SessionScoped
public class UserData implements Serializable {

    private static final long serialVersionUID = 1L;

    String firstName;
    String lastName;
    String country;



    private static ArrayList<Student> students;


    @PostConstruct
    public void initStudentsList(){
        students= new StudentDataHelper().loadArray();
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public String addStudent() {
        Student Student = new Student(firstName,lastName,country);
        students.add(Student);
        return null;
    }

    public String deleteStudent(Student Student) {
        students.remove(Student);
        return null;
    }

    public String editStudent(Student Student){
        Student.setEditable(true);
        return null;
    }

    public String saveStudents(){
        //set "canEdit" of all Students to false
        for (Student Student : students){
            Student.setEditable(false);
        }
        return null;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}