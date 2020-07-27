package entity;

import javax.persistence.*;

@Entity
@Table(name = "review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "comment")
    private String comment;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
                            CascadeType.PERSIST, CascadeType.REFRESH})
    private Course course;

    public Review() {
    }

    public Review(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                '}';
    }
}
