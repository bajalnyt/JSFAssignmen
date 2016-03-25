import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//import static java.sql.DriverManager.getConnection;

/**
 * Created by 202824 on 3/9/2016.
 */
@ManagedBean(name = "studentdata")
@RequestScoped
public class Setupdata implements Serializable{

    private static final long serialVersionUID = 1L;
    ArrayList<StudentBean> studentList;

    public void insertStudent(StudentBean stnt){
        String fname =  stnt.getFirstName();
        String lname = stnt.getLastName();
        String cntry = stnt.getCountry();

        connDataBaseInsert(fname,lname,cntry);
        System.out.println("Insert student = " + fname + lname + cntry);
    }


    public void deleteStudent(StudentBean stntdel){
        String fnamed = stntdel.getFirstName();
        String lnamed = stntdel.getLastName();
        String cntryd = stntdel.getCountry();

        connDataBaseDelete(fnamed,lnamed,cntryd);
        getStudentList();
        System.out.println("Delete student = " + fnamed + lnamed + cntryd);
    }

    public String updateStudent(StudentBean stntupd){
        stntupd.setEditable(true);
        System.out.println("Edit set to true " + stntupd.editable);
        return null;
    }

    public String saveStudent(){

       for (StudentBean s:studentList)
                s.setEditable(false);
     //   connDataBaseDelete(fnameu,lnameu,cntryu);
     //  getStudentList();
       // System.out.println("Updated student = " + fnameu + lnameu + cntryu);
        return null;
    }



     public void connDataBaseInsert(String firstName, String lastName, String country) {

         PreparedStatement pst1 = null;
         String stm1 = "INSERT INTO STUDENTS (studentid, firstname,lastname,country) VALUES" +
                 "(6,'" +  firstName + "','" + lastName + "','"  + country + "')";
         System.out.println(stm1);
         try (Connection con = getConnection()) {

             pst1 = con.prepareStatement(stm1);
             pst1.execute();

         } catch (SQLException e) {
             e.printStackTrace();

         }
     }

    public void connDataBaseDelete(String firstName, String lastName, String country) {

        PreparedStatement pst2 = null;
        String stm2 = "Delete FROM STUDENTS WHERE " +
                "(lastname ='" +  lastName + "'" + " and firstname ='" + firstName + "' and country = '" + country+"')" ;
        System.out.println(stm2);
        try (Connection con = getConnection()) {

            pst2 = con.prepareStatement(stm2);
            pst2.execute();

        } catch (SQLException e) {
            e.printStackTrace();

        }


    }


    public ArrayList<StudentBean> loadArray(){
        studentList = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement pst = null;
        String stm = "Select * from Students";
        try (Connection con = getConnection()){

            pst = con.prepareStatement(stm);
            pst.execute();
            rs = pst.getResultSet();

            while(rs.next()){
                StudentBean student = new StudentBean();

                student.setFirstName(rs.getString(3));
                student.setLastName(rs.getString(2));
                student.setCountry(rs.getString(4));

                studentList.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
      //  System.out.println(studentList.size());
        return studentList;

     /*   StudentBean s1 = new StudentBean();

        s1.setFirstName("John");
        s1.setLastName("Smith");
        s1.setCountry("USA");

        StudentBean s2 = new StudentBean();

        s2.setFirstName("Barry");
        s2.setLastName("Ivanov");
        s2.setCountry("Russia");


        studentList.add(s1);
        studentList.add(s2);

        StudentBean s3 = new StudentBean("Peter","Ray","Israel");
        studentList.add(s3); */



    }
    public ArrayList<StudentBean> getStudentList() {
        studentList = loadArray();
        return studentList;
    }

    public Connection getConnection(){
        Connection con = null;
       //* System.out.println("getConnection..");

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            con = DriverManager.getConnection
                    ("jdbc:oracle:thin:@localhost:1521/xe", "aristo_owner", "ardvo1");

        } catch (Exception e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
        }
        return con;
    }
}


    //*   public static String basicToString(BasicEvent bse){
 //*       String out = bse.getLevel() + ";" + bse.getEtype() + ";" + bse.getxPos() + ";" + bse.getyPos() + ";" + bse.getDate().dateAsString() + ";" + bse.getTime().timeAsString() + ";" + bse.getRlb() + ";" + bse.getSig() + ";" + bse.getReserved();
 //*       return out;
