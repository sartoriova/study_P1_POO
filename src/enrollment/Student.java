package enrollment;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Student {
    private String id;
    private String name;
    private LocalDate admissionDate;

    public Student(String id, String name, LocalDate admissionDate) {
        if (!isValidStudent(id, name, admissionDate)) return;
        this.id = id;
        this.name = name;
        this.admissionDate = admissionDate;
    }

    private boolean isValidStudent(String id, String name, LocalDate admissionDate) {
        if (id == null || id.isBlank() || !isValidId(id)) return false;
        if (name == null || name.isBlank()) return false;
        return admissionDate != null && !admissionDate.isAfter(LocalDate.now());
    }

    private boolean isValidId(String id) {
        final String lowerCaseId = id.toLowerCase();
        if (lowerCaseId.length() != 9) return false;
        if (!lowerCaseId.startsWith("sc")) return false;
        final String middle = lowerCaseId.substring(2, 8);
        for (char character : middle.toCharArray()) {
            if (!Character.isDigit(character)) return false;
        }
        final char dv = lowerCaseId.charAt(8);
        return dv == 'x' || Character.isDigit(dv);
    }

    public int getSemester() {
        final Period period = Period.between(admissionDate, LocalDate.now());
        final int monthsInPeriod = (int) period.toTotalMonths();
        return monthsInPeriod/6 + 1;
    }

    public String getStateAsString() {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return String.format("%s | %s | Admission date = %s", id, name, formatter.format(admissionDate));
    }

    public String getId() {
        return id;
    }
}
