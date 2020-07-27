package entity;

import javax.persistence.*;

@Entity
@Table(name = "instructor_detail")
public class InstructorDetail {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "youtube_channel")
    private String youtubeChannel;

    @Column(name = "hobby")
    private String hobby;

    /* //for deleting ONLY FROM InstructorDetail
 @OneToOne(mappedBy = "instructorDetail", cascade = {CascadeType.DETACH,
                                                     CascadeType.MERGE,
                                                     CascadeType.REFRESH,
                                                     CascadeType.PERSIST})*/

    @OneToOne(mappedBy = "instructorDetail", cascade = CascadeType.ALL)//refers to instructorDetail property
    // in Instructor class
    private Instructor instructor;

    public InstructorDetail() {
    }

    public InstructorDetail(String youtubeChannel, String hobby) {
        this.youtubeChannel = youtubeChannel;
        this.hobby = hobby;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getYoutubeChannel() {
        return youtubeChannel;
    }

    public void setYoutubeChannel(String youtubeChannel) {
        this.youtubeChannel = youtubeChannel;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    @Override
    public String toString() {
        return "InstructorDetails{" +
                "id=" + id +
                ", youtubeChannel='" + youtubeChannel + '\'' +
                ", hobby='" + hobby + '\'' +
                '}';
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }
}
