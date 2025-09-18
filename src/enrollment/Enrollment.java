package enrollment;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Enrollment {
    private static int idCount;

    private int id;
    private LocalDateTime enrollmentTime;
    private boolean concluded;
    private Student student;
    private Course[] courses;
    private int qtdeCourses;

    public Enrollment(Student student) {
        if (!isValidEnrollment(student)) {
            concluded = true;
            return;
        }
        this.id = ++idCount;
        this.enrollmentTime = LocalDateTime.now();
        this.concluded = false;
        this.student = student;
        this.courses = new Course[20];
    }

    private boolean isValidEnrollment(Student student) {
        return student != null && student.getId() != null;
    }

    public boolean enroll(Course course) {
        if (course == null || course.getId() == 0) return false;
        if (concluded) return false;
        if (hasCollision(course)) return false;
        if (!hasAvailableHours(course)) return false;
        courses[qtdeCourses++] = course;
        return true;
    }

    private boolean hasCollision(Course course) {
        for (int i = 0; i < qtdeCourses; i++) {
            if (courses[i].getDayOfWeek() == course.getDayOfWeek()) {
                if (!course.getEndTime().isBefore(courses[i].getStartTime())) return true;
                if (!course.getStartTime().isAfter(courses[i].getEndTime())) return true;
            }
        }
        return false;
    }

    private boolean hasAvailableHours(Course course) {
        return (course.getWeeklyDurationInMinutes() + totalDuration()) / 60 <= 22;
    }

    private int totalDuration() {
        int totalDuration = 0;
        for (int i = 0; i < qtdeCourses; i++) {
            totalDuration = totalDuration + courses[i].getWeeklyDurationInMinutes();
        }
        return totalDuration;
    }

    public void removeWithoutOrdering(Course course) {
        if (course == null || course.getId() == 0) return;
        if (concluded) return;
        int i = findCourse(course);
        if (i == -1) return;
        courses[i] = courses[qtdeCourses - 1];
        courses[qtdeCourses - 1] = null;
        qtdeCourses--;
    }

    public void removeWithOrdering(Course course) {
        if (course == null || course.getId() == 0) return;
        if (concluded) return;
        for (int i = 0; i < qtdeCourses - 1; i++) {
            if (courses[i].getId() == course.getId()) {
                final Course aux = courses[i];
                courses[i] = courses[i + 1];
                courses[i + 1] = aux;
            }
        }
        courses[qtdeCourses - 1] = null;
        qtdeCourses--;
    }

    private int findCourse(Course course) {
        for (int i = 0; i < qtdeCourses; i++) {
            if (courses[i].getId() == course.getId()) return i;
        }
        return -1;
    }

    public void conclude() {
        concluded = true;
        enrollmentTime = LocalDateTime.now();
    }

    public String getStateAsString() {
        if (id == 0) return "Invalid enrollment!";
        if (!concluded) return "enrollment.Enrollment not concluded!";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < qtdeCourses; i++) {
            builder.append(courses[i].getStateAsString()).append("\n");
        }

        return String.format("""
                ====
                %s
                enrollment.Enrollment Time: %s
                ----
                Courses:
                %s
                ====
                """, student.getStateAsString(), formatter.format(enrollmentTime), builder);
    }

    public Course[] getCourses() {
        return courses;
    }

//    public String getStateAsString() {
//        if (id == 0) return "Invalid enrollment!";
//        if (!concluded) return "enrollment.Enrollment not concluded!";
//
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
//        StringJoiner joiner = new StringJoiner("\n");
//
//        for (enrollment.Course c : courses) {
//            joiner.add(c.getStateAsString());
//        }
//
//        return String.format("""
//                ====
//                %s
//                enrollment.Enrollment Time: %s
//                ----
//                Courses:
//                %s
//                ====
//                """, student.getStateAsString(), formatter.format(enrollmentTime), joiner);
//    }
}
