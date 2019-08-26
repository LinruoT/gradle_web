package bitter.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name="content")
    private String content;

    @ManyToOne
    @JoinColumn(name="bitter")
    //@JsonIgnore
    private Bitter bitter; //作者

    @ManyToOne
    @JoinColumn(name = "origin_bittle")
    @JsonIgnore
    private Bittle originBittle; //原帖

    @Column(name = "created_at")
    private Date time;

    public Comment(){}

    public Comment(String content, Bitter bitter,
                   Bittle originBittle
                   ) {
        this.content = content;
        this.bitter = bitter;
        this.originBittle = originBittle;
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Bitter getBitter() {
        return bitter;
    }

    public void setBitter(Bitter bitter) {
        this.bitter = bitter;
    }

    public void setOriginBittle(Bittle originBittle) {
        this.originBittle = originBittle;
    }

    public Bittle getOriginBittle() {
        return originBittle;
    }

    public Date getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", bitter=" + bitter +
//                ", originBittle=" + originBittle +
                ", time=" + time +
                '}';
    }
}
