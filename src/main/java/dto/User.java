package dto;

import java.time.LocalDateTime;
import java.util.List;

import javax.annotation.Generated;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlRootElement;

import inject.Dto;
import lombok.Getter;
import lombok.Setter;

@Dto
@XmlRootElement
public class User {

    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column public.user.emailaddress
     * @mbg.generated  Sat Aug 01 16:58:00 JST 2020
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-12T12:59:05.831+09:00", comments = "Source field: USER.emailAddress")
    @Id
    private String emailaddress;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column public.user.last_name
     * @mbg.generated  Sat Aug 01 16:58:00 JST 2020
     */
    @Column(name = "last_name")
    private String lastName;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column public.user.first_name
     * @mbg.generated  Sat Aug 01 16:58:00 JST 2020
     */
    @Column(name = "first_name")
    private String firstName;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column public.user.password
     * @mbg.generated  Sat Aug 01 16:58:00 JST 2020
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-12T12:59:05.835+09:00", comments = "Source field: USER.password")
    private String password;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column public.user.postal_code
     * @mbg.generated  Sat Aug 01 16:58:00 JST 2020
     */
    @Column(name = "postal_code")
    private String postalCode;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column public.user.region
     * @mbg.generated  Sat Aug 01 16:58:00 JST 2020
     */
    private String region;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column public.user.locality
     * @mbg.generated  Sat Aug 01 16:58:00 JST 2020
     */
    private String locality;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column public.user.street_address
     * @mbg.generated  Sat Aug 01 16:58:00 JST 2020
     */
    @Column(name = "street_address")
    private String streetAddress;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column public.user.extended_address
     * @mbg.generated  Sat Aug 01 16:58:00 JST 2020
     */
    @Column(name = "extended_address")
    private String extendedAddress;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column public.user.version
     * @mbg.generated  Sat Aug 01 16:58:00 JST 2020
     */
    @Version
    private Integer version;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column public.user.createdtime
     * @mbg.generated  Sat Aug 01 16:58:00 JST 2020
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-12T12:59:05.835+09:00", comments = "Source field: USER.createdTime")
    private LocalDateTime createdtime;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column public.user.updatedtime
     * @mbg.generated  Sat Aug 01 16:58:00 JST 2020
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-12T12:59:05.835+09:00", comments = "Source field: USER.updatedTime")
    private LocalDateTime updatedtime;

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column public.user.emailaddress
     * @return  the value of public.user.emailaddress
     * @mbg.generated  Sat Aug 01 16:58:00 JST 2020
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-12T12:59:05.834+09:00", comments = "Source field: USER.emailAddress")
    public String getEmailaddress() {
        return emailaddress;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column public.user.emailaddress
     * @param emailaddress  the value for public.user.emailaddress
     * @mbg.generated  Sat Aug 01 16:58:00 JST 2020
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-12T12:59:05.835+09:00", comments = "Source field: USER.emailAddress")
    public void setEmailaddress(final String emailaddress) {
        this.emailaddress = emailaddress;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column public.user.last_name
     * @return  the value of public.user.last_name
     * @mbg.generated  Sat Aug 01 16:58:00 JST 2020
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column public.user.last_name
     * @param lastName  the value for public.user.last_name
     * @mbg.generated  Sat Aug 01 16:58:00 JST 2020
     */
    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column public.user.first_name
     * @return  the value of public.user.first_name
     * @mbg.generated  Sat Aug 01 16:58:00 JST 2020
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column public.user.first_name
     * @param firstName  the value for public.user.first_name
     * @mbg.generated  Sat Aug 01 16:58:00 JST 2020
     */
    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column public.user.password
     * @return  the value of public.user.password
     * @mbg.generated  Sat Aug 01 16:58:00 JST 2020
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-12T12:59:05.835+09:00", comments = "Source field: USER.password")
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column public.user.password
     * @param password  the value for public.user.password
     * @mbg.generated  Sat Aug 01 16:58:00 JST 2020
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-12T12:59:05.835+09:00", comments = "Source field: USER.password")
    public void setPassword(final String password) {
        this.password = password;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column public.user.postal_code
     * @return  the value of public.user.postal_code
     * @mbg.generated  Sat Aug 01 16:58:00 JST 2020
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column public.user.postal_code
     * @param postalCode  the value for public.user.postal_code
     * @mbg.generated  Sat Aug 01 16:58:00 JST 2020
     */
    public void setPostalCode(final String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column public.user.region
     * @return  the value of public.user.region
     * @mbg.generated  Sat Aug 01 16:58:00 JST 2020
     */
    public String getRegion() {
        return region;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column public.user.region
     * @param region  the value for public.user.region
     * @mbg.generated  Sat Aug 01 16:58:00 JST 2020
     */
    public void setRegion(final String region) {
        this.region = region;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column public.user.locality
     * @return  the value of public.user.locality
     * @mbg.generated  Sat Aug 01 16:58:00 JST 2020
     */
    public String getLocality() {
        return locality;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column public.user.locality
     * @param locality  the value for public.user.locality
     * @mbg.generated  Sat Aug 01 16:58:00 JST 2020
     */
    public void setLocality(final String locality) {
        this.locality = locality;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column public.user.street_address
     * @return  the value of public.user.street_address
     * @mbg.generated  Sat Aug 01 16:58:00 JST 2020
     */
    public String getStreetAddress() {
        return streetAddress;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column public.user.street_address
     * @param streetAddress  the value for public.user.street_address
     * @mbg.generated  Sat Aug 01 16:58:00 JST 2020
     */
    public void setStreetAddress(final String streetAddress) {
        this.streetAddress = streetAddress;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column public.user.extended_address
     * @return  the value of public.user.extended_address
     * @mbg.generated  Sat Aug 01 16:58:00 JST 2020
     */
    public String getExtendedAddress() {
        return extendedAddress;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column public.user.extended_address
     * @param extendedAddress  the value for public.user.extended_address
     * @mbg.generated  Sat Aug 01 16:58:00 JST 2020
     */
    public void setExtendedAddress(final String extendedAddress) {
        this.extendedAddress = extendedAddress;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column public.user.version
     * @return  the value of public.user.version
     * @mbg.generated  Sat Aug 01 16:58:00 JST 2020
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column public.user.version
     * @param version  the value for public.user.version
     * @mbg.generated  Sat Aug 01 16:58:00 JST 2020
     */
    public void setVersion(final Integer version) {
        this.version = version;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column public.user.createdtime
     * @return  the value of public.user.createdtime
     * @mbg.generated  Sat Aug 01 16:58:00 JST 2020
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-12T12:59:05.835+09:00", comments = "Source field: USER.createdTime")
    public LocalDateTime getCreatedtime() {
        return createdtime;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column public.user.createdtime
     * @param createdtime  the value for public.user.createdtime
     * @mbg.generated  Sat Aug 01 16:58:00 JST 2020
     */
    public void setCreatedtime(final LocalDateTime createdtime) {
        this.createdtime = createdtime;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column public.user.updatedtime
     * @return  the value of public.user.updatedtime
     * @mbg.generated  Sat Aug 01 16:58:00 JST 2020
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-12T12:59:05.835+09:00", comments = "Source field: USER.updatedTime")
    public LocalDateTime getUpdatedtime() {
        return updatedtime;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column public.user.updatedtime
     * @param updatedtime  the value for public.user.updatedtime
     * @mbg.generated  Sat Aug 01 16:58:00 JST 2020
     */
    public void setUpdatedtime(final LocalDateTime updatedtime) {
        this.updatedtime = updatedtime;
    }

    @OneToMany(cascade = CascadeType.ALL) // Userを変更しただけで、Roleも自動で変更が反映される
    @JoinColumn(name = "emailaddress")
    @Getter
    @Setter
    private List<Role> roles;

}