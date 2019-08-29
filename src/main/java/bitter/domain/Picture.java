package bitter.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

//图片类
@Entity
public class Picture {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private  Long id;

    @NotNull
    @Column
    private String name;

    @Column
    private Long size;

    @ManyToOne
    @JoinColumn(name="bitter")
    @JsonIgnore
    private Bitter bitter;

    @Column(name = "created_at")
    private Date time;

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Long getSize() {
        return size;
    }
    public void setSize(Long size) {
        this.size = size;
    }
    public Bitter getBitter() {
        return bitter;
    }
    public void setBitter(Bitter bitter) {
        this.bitter = bitter;
    }
    public Date getTime() {
        return time;
    }

    public Picture(){}
    public Picture(String name, Long size, Bitter bitter) {
        this.name = name;
        this.size = size;
        this.bitter = bitter;
    }

    @Override
    public String toString() {
        return "Picture{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", size=" + size +
                ", bitter=" + bitter +
                ", time=" + time +
                '}';
    }
}
