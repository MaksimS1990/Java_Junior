package models;

import jakarta.persistence.*;

import java.util.Random;


@Entity
@Table(name = "Courses")
public class Courses {

    private static final String[] titles = new String[] {"Programming", "Economy", "Philosophy", "Management", "Marketing", "History", "Biology", "Drawing", "Geography", "Literature"};
    private static final Random random = new Random();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "title")
    private static String title;
    @Column(name = "duration")
    private static int duration;

    public Courses() {

    }

    public Courses(int id, String title, int duration) {
        this.id = id;
        this.title = title;
        this.duration = duration;
    }

    public Courses(String title, int duration) {
        this.title = title;
        this.duration = duration;
    }

    public static Courses create() {
        return new Courses(titles[random.nextInt(titles.length)], random.nextInt(1,6));
    }

    public void updateDuration() {
        duration = random.nextInt(1, 5);
    }

    public void updateTitle() {
        title = titles[random.nextInt(titles.length)];
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Courses{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", duration=" + duration +
                '}';
    }
}
