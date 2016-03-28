import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;


public class StudentDataHelper implements Serializable{

    private static final long serialVersionUID = 1L;
    ArrayList<Student> studentList;

    public void insertStudent(Student stnt){
        String fname =  stnt.getFirstName();
        String lname = stnt.getLastName();
        String cntry = stnt.getCountry();

        connDataBaseInsert(fname,lname,cntry);
        System.out.println("Insert student = " + fname + lname + cntry);
    }


    public void deleteStudent(Student stntdel){
        String fnamed = stntdel.getFirstName();
        String lnamed = stntdel.getLastName();
        String cntryd = stntdel.getCountry();

        connDataBaseDelete(fnamed,lnamed,cntryd);
        getStudentList();
        System.out.println("Delete student = " + fnamed + lnamed + cntryd);
    }

    public String updateStudent(Student stntupd){
        stntupd.setEditable(true);
        System.out.println("Setting "+ stntupd.firstName +" editable to " + stntupd.editable);
        return null;
    }

    public String saveStudent(){

       for (Student s:studentList) {
           s.setEditable(false);
       }

       return null;
    }



     public void connDataBaseInsert(String firstName, String lastName, String country) {

         PreparedStatement pst1 = null;
         if(firstName==null || firstName.equalsIgnoreCase("null"))
             return;
         String stm1 = "INSERT INTO STUDENTS (firstname,lastname,country) VALUES" +
                 "('"+ firstName + "','" + lastName + "','"  + country + "')";
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


    public ArrayList<Student> loadArray(){
        studentList = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement pst = null;
        String stm = "Select * from Students";

        try (Connection con = getConnection()){
            pst = con.prepareStatement(stm);
            pst.execute();
            rs = pst.getResultSet();

            while(rs.next()){
                Student student = new Student();

                student.setFirstName(rs.getString("firstname"));
                student.setLastName(rs.getString("lastname"));
                student.setCountry(rs.getString("country"));

                studentList.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentList;

    }
    public ArrayList<Student> getStudentList() {
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
