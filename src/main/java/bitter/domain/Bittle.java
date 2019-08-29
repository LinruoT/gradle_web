package bitter.domain;

import javax.persistence.*;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.util.Date;
import java.util.List;
import java.util.Set;

//推文类，包含消息内容、时间戳、经纬度
@Entity
public class Bittle {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private  Long id;
    @ManyToOne
    @JoinColumn(name="bitter")
    private Bitter bitter;
    @Column
    private  String message;
    @Column(name = "created_at")
    private  Date time;
    @Column
    private Double latitude;
    @Column
    private Double longitude;

    @ManyToMany() //默认懒加载会导致session过期
    @JoinTable(name = "Bittle_Picture",
            joinColumns = { @JoinColumn(name = "Bittle_id",
                    referencedColumnName = "id") },
            inverseJoinColumns = {
                    @JoinColumn(name = "pictures_id", referencedColumnName = "id") })
    @OrderBy("id")
    //在dataConfig中设置允许在事务外lazy查询，但picture开启懒加载会卡住
    //@LazyCollection(LazyCollectionOption.FALSE)
    private Set<Picture> pictures; //这条推文的图片

    @OneToMany() //默认懒加载会导致session过期
    @OrderBy("id")
    @JoinTable(name = "Bittle_Comment",
            joinColumns = { @JoinColumn(name = "Bittle_id",
                    referencedColumnName = "id") },
            inverseJoinColumns = {
                    @JoinColumn(name = "comments_id", referencedColumnName = "id") })
    //在dataConfig中设置允许在事务外lazy查询
    //@LazyCollection(LazyCollectionOption.FALSE)
    private Set<Comment> comments; //这条推文的评论

    @Column(name = "comment_count")
    private int commentCount;

    public Bittle() {}

    public Bittle(Long id,Bitter bitter,String message,Date time,Double longitude,Double latitude) {
        this.bitter=bitter;
        this.id=id;
        this.message=message;
        this.time=time;
        this.longitude=longitude;
        this.latitude=latitude;
    }

    public Bitter getBitter() {
        return bitter;
    }

    public void setBitter(Bitter bitter) {
        this.bitter = bitter;
    }

    public Long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public Date getTime() {
        return time;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Set<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(Set<Picture> pictures) {
        this.pictures = pictures;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    //重新相等判定，使用了apache commons
    @Override
    public boolean equals(Object that) {
        return EqualsBuilder.reflectionEquals(this,that,"id","time");
    }
    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this,"id","time");
    }
}
