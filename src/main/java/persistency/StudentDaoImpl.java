package persistency;

import domain.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements StudentDao {
    private final Connection CONN;
    private Student student;

    public StudentDaoImpl(Connection conn) {
        this.CONN = conn;
    }

    @Override
    public List<Student> getAllStudents() {
        student = new Student();
        List<Student> students = new ArrayList<>();
        String query = """
                SELECT name
                FROM students
                WHERE roll_no = ?
                """;
        try (PreparedStatement pst = CONN.prepareStatement(query);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                student.setName(rs.getString(1));
            }
        } catch (SQLException sqlexp) {
            sqlexp.printStackTrace();
        }
        return null;
    }

    @Override
    public Student getStudent(int rollNo) {
        student = new Student();
        String query = """
                SELECT name
                FROM students
                WHERE roll_no = ?
                """;
        try(PreparedStatement pst = CONN.prepareStatement(query)) {
            pst.setInt(1, rollNo);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                student.setName(rs.getString(1));
            }
        } catch (SQLException sqlexp) {
            sqlexp.printStackTrace();
        }
        return student;
    }

    @Override
    public void createStudent(Student student) {
        String query = "INSERT INTO students (roll_no, name) VALUES (DEFAULT, ?)";
        try(PreparedStatement pst = CONN.prepareStatement(query)) {
            pst.setString(1, student.getName());
            pst.executeUpdate();
        } catch (SQLException sqlexp) {
            sqlexp.printStackTrace();
        }
    }

    @Override
    public void updateStudent(Student student) {
        String query = """
                UPDATE students
                SET name = ?
                WHERE roll_no = ?""";
        try(PreparedStatement pst = CONN.prepareStatement(query)) {
            pst.setString(1, student.getName());
            pst.executeUpdate();
        } catch (SQLException sqlexp) {
            sqlexp.printStackTrace();
        }
    }

    @Override
    public void deleteStudent(Student student) {
        String query = "DELETE FROM students WHERE roll_no = ?";
        try(PreparedStatement pst = CONN.prepareStatement(query)) {
            pst.setInt(1, student.getRollNo());
            pst.executeUpdate();
        } catch (SQLException sqlexp) {
            sqlexp.printStackTrace();
        }
    }
}
