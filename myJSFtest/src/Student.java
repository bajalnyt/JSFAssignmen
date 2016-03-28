/**
 * Created by 202824 on 3/8/2016.
 */


public class Student {

     String firstName;
     String lastName;
     String country;
     String message;


    boolean editable;

    public boolean isEditable() {
        System.out.println("iseditable " + editable + firstName );
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
        System.out.println("student editable " + firstName+":"+ editable );
    }



    public String getMessage() {

        if (firstName != null)
            message = "First Name = " + firstName;
        else
            message = "Please enter First Name";
        return message;
    }



    public Student(String firstName, String lastName, String country) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Student(){}

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


}
