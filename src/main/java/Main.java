import config.ConnectDB;
import domain.Student;
import persistency.StudentDao;
import persistency.StudentDaoImpl;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try(var connect = ConnectDB.getConnection()) {
            StudentDao studentDao = new StudentDaoImpl(connect);
            studentController(studentDao);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    private static void studentController(StudentDao studentDao) {
        var getStudent = studentDao.getStudent(0);
        if (getStudent.getName() == null) {
            System.out.printf("rollNo %s has not been found in the database\n",
                    getStudent.getRollNo());
        } else {
            System.out.println(getStudent.getName());
        }
    }
}
