package ru.skypro.lessons.springboot.weblibrary.pojo;
import jakarta.persistence.*;

@Entity
@Table(name = "reportPath")
public class ReportPath {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String Path;

    public ReportPath() {
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getPath() {
        return Path;
    }
    public void setPath(String path) {
        Path = path;
    }
}
