package main.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "Journal")
public class Journal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (name = "id")
    private Integer id;

    @Column(name = "bookId")
    private Integer bookId;

    @Column(name = "clientId")
    private Integer clientId;

    @Column(name = "dateBeg")
    private Timestamp dateBeg;

    @Column(name = "dateEnd")
    private Timestamp dateEnd;

    @Column(name = "dateRet")
    private Timestamp dateRet;

}
