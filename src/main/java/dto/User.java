package dto;

import javax.annotation.Generated;
import javax.persistence.Id;

import util.Dto;

@Dto
public class User {

    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column public.user.emailaddress
     * @mbg.generated  Fri May 01 10:38:34 JST 2020
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-12T12:59:05.831+09:00", comments = "Source field: USER.emailAddress")
    @Id
    private String emailaddress;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column public.user.name
     * @mbg.generated  Fri May 01 10:38:34 JST 2020
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-12T12:59:05.835+09:00", comments = "Source field: USER.name")
    private String name;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column public.user.password
     * @mbg.generated  Fri May 01 10:38:34 JST 2020
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-12T12:59:05.835+09:00", comments = "Source field: USER.password")
    private String password;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column public.user.createdtime
     * @mbg.generated  Fri May 01 10:38:34 JST 2020
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-12T12:59:05.835+09:00", comments = "Source field: USER.createdTime")
    private String createdtime;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column public.user.updatedtime
     * @mbg.generated  Fri May 01 10:38:34 JST 2020
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-12T12:59:05.835+09:00", comments = "Source field: USER.updatedTime")
    private String updatedtime;

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column public.user.emailaddress
     * @return  the value of public.user.emailaddress
     * @mbg.generated  Fri May 01 10:38:34 JST 2020
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-12T12:59:05.834+09:00", comments = "Source field: USER.emailAddress")
    public String getEmailaddress() {
        return emailaddress;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column public.user.emailaddress
     * @param emailaddress  the value for public.user.emailaddress
     * @mbg.generated  Fri May 01 10:38:34 JST 2020
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-12T12:59:05.835+09:00", comments = "Source field: USER.emailAddress")
    public void setEmailaddress(String emailaddress) {
        this.emailaddress = emailaddress;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column public.user.name
     * @return  the value of public.user.name
     * @mbg.generated  Fri May 01 10:38:34 JST 2020
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-12T12:59:05.835+09:00", comments = "Source field: USER.name")
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column public.user.name
     * @param name  the value for public.user.name
     * @mbg.generated  Fri May 01 10:38:34 JST 2020
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-12T12:59:05.835+09:00", comments = "Source field: USER.name")
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column public.user.password
     * @return  the value of public.user.password
     * @mbg.generated  Fri May 01 10:38:34 JST 2020
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-12T12:59:05.835+09:00", comments = "Source field: USER.password")
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column public.user.password
     * @param password  the value for public.user.password
     * @mbg.generated  Fri May 01 10:38:34 JST 2020
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-12T12:59:05.835+09:00", comments = "Source field: USER.password")
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column public.user.createdtime
     * @return  the value of public.user.createdtime
     * @mbg.generated  Fri May 01 10:38:34 JST 2020
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-12T12:59:05.835+09:00", comments = "Source field: USER.createdTime")
    public String getCreatedtime() {
        return createdtime;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column public.user.createdtime
     * @param createdtime  the value for public.user.createdtime
     * @mbg.generated  Fri May 01 10:38:34 JST 2020
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-12T12:59:05.835+09:00", comments = "Source field: USER.createdTime")
    public void setCreatedtime(String createdtime) {
        this.createdtime = createdtime;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column public.user.updatedtime
     * @return  the value of public.user.updatedtime
     * @mbg.generated  Fri May 01 10:38:34 JST 2020
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-12T12:59:05.835+09:00", comments = "Source field: USER.updatedTime")
    public String getUpdatedtime() {
        return updatedtime;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column public.user.updatedtime
     * @param updatedtime  the value for public.user.updatedtime
     * @mbg.generated  Fri May 01 10:38:34 JST 2020
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-12T12:59:05.835+09:00", comments = "Source field: USER.updatedTime")
    public void setUpdatedtime(String updatedtime) {
        this.updatedtime = updatedtime;
    }
}