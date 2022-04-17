package model.inheritance;

import com.guilhermepalma.exampleJPA.model.DAO.DAO;
import com.guilhermepalma.exampleJPA.model.inheritance.ScholarshipStudent;
import com.guilhermepalma.exampleJPA.model.inheritance.Student;

public class InheritancedStudentsTest {

    private static DAO<Student> studentDAO;

    public static void main(String[] args) {

        studentDAO = new DAO<>();

        insertScholarshipStudent();

        studentDAO.close();
    }

    private static void insertScholarshipStudent() {
        Student student = new Student(1L, "Jão Roverto");
        ScholarshipStudent scholarshipStudent = new ScholarshipStudent(2L, "Luana Rui", 1000.0);

        studentDAO.registerAtomic(student);
        studentDAO.registerAtomic(scholarshipStudent);
    }

}
