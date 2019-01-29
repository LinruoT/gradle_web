package bitter;


import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import java.util.Date;

//推文类，包含消息内容、时间戳、经纬度
public class Bittle {
    private final Long id;
    private final String message;
    private final Date time;
    private Double latitude;
    private Double longitude;


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
