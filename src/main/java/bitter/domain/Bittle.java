package bitter.domain;

import javax.persistence.*;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import java.util.Date;
import java.util.List;

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

    @ManyToMany(fetch = FetchType.EAGER) //默认懒加载会导致session过期
    //@JoinTable(name = "T_Bittle_Picture")
    @OrderBy("id")
    private List<Picture> pictures; //这条推文的图片

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

    public List<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
    }
    //重新相等判定
    @Override
    public boolean equals(Object that) {
        return EqualsBuilder.reflectionEquals(this,that,"id","time");
    }
    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this,"id","time");
    }
}
