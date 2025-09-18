import enrollment.Course;
import enrollment.Enrollment;
import enrollment.Student;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;

import static java.time.DayOfWeek.*;

public class Main {
    public static void main(String[] args) {
        Student valeria = new Student("SC3047822", "Vavá", LocalDate.now().minusDays(1));
        Course course = new Course(LocalTime.of(19, 0), LocalTime.of(22, 30), "POO", "POO", "Lucas", THURSDAY, Course.Room.C102);
        Course course2 = new Course(LocalTime.of(19, 0), LocalTime.of(22, 30), "APR", "APR", "Lucas", MONDAY, Course.Room.C102);
        Course course3 = new Course(LocalTime.of(19, 0), LocalTime.of(22, 30), "ESD", "ESD", "Lucas", SATURDAY, Course.Room.C102);
        Course course4 = new Course(LocalTime.of(19, 0), LocalTime.of(22, 30), "ING", "ING", "Lucas", WEDNESDAY, Course.Room.C102);
        Enrollment enrollment = new Enrollment(valeria);

        if (enrollment.enroll(course)) System.out.println("Disciplina adicionada com sucesso!");
        if (enrollment.enroll(course2)) System.out.println("Disciplina adicionada com sucesso!");
        if (enrollment.enroll(course3)) System.out.println("Disciplina adicionada com sucesso!");
        if (enrollment.enroll(course4)) System.out.println("Disciplina adicionada com sucesso!");

        System.out.println(Arrays.toString(enrollment.getCourses()));
        enrollment.removeWithOrdering(course2);
        System.out.println(Arrays.toString(enrollment.getCourses()));

        enrollment.conclude();
        System.out.println(enrollment.getStateAsString());
    }
}
