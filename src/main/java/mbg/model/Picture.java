package mbg.model;

import java.io.Serializable;
import java.util.Date;

public class Picture implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Picture.id
     *
     * @mbggenerated Sat Aug 03 23:23:36 CST 2019
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Picture.name
     *
     * @mbggenerated Sat Aug 03 23:23:36 CST 2019
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Picture.size
     *
     * @mbggenerated Sat Aug 03 23:23:36 CST 2019
     */
    private Long size;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Picture.bitter
     *
     * @mbggenerated Sat Aug 03 23:23:36 CST 2019
     */
    private Integer bitter;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Picture.created_at
     *
     * @mbggenerated Sat Aug 03 23:23:36 CST 2019
     */
    private Date createdAt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table Picture
     *
     * @mbggenerated Sat Aug 03 23:23:36 CST 2019
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Picture.id
     *
     * @return the value of Picture.id
     *
     * @mbggenerated Sat Aug 03 23:23:36 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Picture.id
     *
     * @param id the value for Picture.id
     *
     * @mbggenerated Sat Aug 03 23:23:36 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Picture.name
     *
     * @return the value of Picture.name
     *
     * @mbggenerated Sat Aug 03 23:23:36 CST 2019
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Picture.name
     *
     * @param name the value for Picture.name
     *
     * @mbggenerated Sat Aug 03 23:23:36 CST 2019
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Picture.size
     *
     * @return the value of Picture.size
     *
     * @mbggenerated Sat Aug 03 23:23:36 CST 2019
     */
    public Long getSize() {
        return size;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Picture.size
     *
     * @param size the value for Picture.size
     *
     * @mbggenerated Sat Aug 03 23:23:36 CST 2019
     */
    public void setSize(Long size) {
        this.size = size;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Picture.bitter
     *
     * @return the value of Picture.bitter
     *
     * @mbggenerated Sat Aug 03 23:23:36 CST 2019
     */
    public Integer getBitter() {
        return bitter;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Picture.bitter
     *
     * @param bitter the value for Picture.bitter
     *
     * @mbggenerated Sat Aug 03 23:23:36 CST 2019
     */
    public void setBitter(Integer bitter) {
        this.bitter = bitter;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Picture.created_at
     *
     * @return the value of Picture.created_at
     *
     * @mbggenerated Sat Aug 03 23:23:36 CST 2019
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Picture.created_at
     *
     * @param createdAt the value for Picture.created_at
     *
     * @mbggenerated Sat Aug 03 23:23:36 CST 2019
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Picture
     *
     * @mbggenerated Sat Aug 03 23:23:36 CST 2019
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", size=").append(size);
        sb.append(", bitter=").append(bitter);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}