package main.entity;

import lombok.Data;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "Book_types")
public class Book_types {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;

    @Column(name = "cnt")
    private Integer cnt;

    @Column(name = "fine")
    private Integer fine;

    @Column(name = "dayCount")
    private Integer dayCount;

    @OneToMany(targetEntity = Books.class, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "typeId", referencedColumnName = "id")
    private List<Books> books = new ArrayList<>();

    public Book_types() {
    }

    public Book_types(String name, Integer cnt, Integer fine, Integer dayCount) {
        this.name = name;
        this.cnt = cnt;
        this.fine = fine;
        this.dayCount = dayCount;
    }

}
