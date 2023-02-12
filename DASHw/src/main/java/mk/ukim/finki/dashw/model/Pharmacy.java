package mk.ukim.finki.dashw.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Pharmacy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "name", length = 1024)
    String name;
    @Column(name = "rating")
    double rating;
    @Column(name = "numOfReviews")
    long numberOfReviews;
    @Column(name = "type")
    String hospitalType;
    @Column(name = "address", length = 1024)
    String address;
    @Column(name = "href", length = 1024)
    String href;
    @Column(name = "work_time", length = 1024)
    String workingTime;
    @Column(name = "municipality", length = 1024)
    String municipality;
    @Column(name = "phonen", length = 1024)
    String phonen;
    int searchCount;


    public Pharmacy() {
    }

    public Pharmacy(String name, double rating, long numberOfReviews, String hospitalType, String address, String href, String workingTime, String municipality, String phonen) {
        this.name = name;
        this.rating = rating;
        this.numberOfReviews = numberOfReviews;
        this.hospitalType = hospitalType;
        this.address = address;
        this.href = href;
        this.workingTime = workingTime;
        this.municipality = municipality;
        this.phonen = phonen;
        this.searchCount = 0;
    }
}
