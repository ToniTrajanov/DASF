package mk.ukim.finki.dashw.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @OneToOne
    User user;
    Integer number;
    String comment;

    public Rating(User user, Integer number, String comment) {
        this.user = user;
        this.number = number;
        this.comment = comment;
    }

    public Rating(User user, Integer number) {
        this.user = user;
        this.number = number;
    }

    public Rating(User user, String comment) {
        this.user = user;
        this.comment = comment;
    }

    public Rating() {

    }
}

