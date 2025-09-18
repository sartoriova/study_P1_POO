package enrollment;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Course {
    public enum Room {C102, C104, C105, C106, C107, C209}
    private static int idCount;

    private int id;
    private LocalTime startTime;
    private LocalTime endTime;
    private String name;
    private String code;
    private String professor;
    private DayOfWeek dayOfWeek;
    private Room room;

    public Course(LocalTime startTime, LocalTime endTime, String name, String code, String professor, DayOfWeek dayOfWeek, Room room) {
        if (!isValidCourse(startTime, endTime, name, code, professor, dayOfWeek, room)) return;
        this.id = ++idCount;
        this.startTime = startTime;
        this.endTime = endTime;
        this.name = name;
        this.code = code;
        this.professor = professor;
        this.dayOfWeek = dayOfWeek;
        this.room = room;
    }

    private boolean isValidCourse(LocalTime startTime, LocalTime endTime, String name, String code, String professor, DayOfWeek dayOfWeek, Room room) {
        if (startTime == null || endTime == null || !startTime.isBefore(endTime)) return false;
        if (name == null || name.isBlank()) return false;
        if (code == null || code.isBlank()) return false;
        if (professor == null || professor.isBlank()) return false;
        return dayOfWeek != null && room != null;
    }

    public String getStateAsString() {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm");
        return String.format("| id = %d | %s (%s) | %s | Start = %s | End = %s | %s | Room = %s", id, name, code, dayOfWeek,
                formatter.format(startTime), formatter.format(endTime), professor, room);
    }

    public int getWeeklyDurationInMinutes() {
        return (int) Duration.between(startTime, endTime).toMinutes();
    }

    public int getId() {
        return id;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    @Override
    public String toString() {
        return "enrollment.Course{" +
                "id=" + id +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", professor='" + professor + '\'' +
                ", dayOfWeek=" + dayOfWeek +
                ", room=" + room +
                '}';
    }
}
