package bitter.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import java.util.Date;

//推文类，包含消息内容、时间戳、经纬度
@Entity
public class Bittle {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private  Long id;
    @Column
    private  String message;
    @Column(name = "created_at")
    private  Date time;
    @Column
    private Double latitude;
    @Column
    private Double longitude;

    public Bittle() {}
    public Bittle(String message,Date time) {
        this(null,message,time,null,null);
    }
    public Bittle(Long id,String message,Date time,Double longitude,Double latitude) {
        this.id=id;
        this.message=message;
        this.time=time;
        this.longitude=longitude;
        this.latitude=latitude;
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
