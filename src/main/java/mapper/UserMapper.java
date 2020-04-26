package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import dto.User;
import dto.UserExample;

public interface UserMapper {

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table public.user
     * @mbg.generated  Sun Apr 26 16:43:16 JST 2020
     */
    long countByExample(UserExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table public.user
     * @mbg.generated  Sun Apr 26 16:43:16 JST 2020
     */
    int deleteByExample(UserExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table public.user
     * @mbg.generated  Sun Apr 26 16:43:16 JST 2020
     */
    int deleteByPrimaryKey(String emailaddress);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table public.user
     * @mbg.generated  Sun Apr 26 16:43:16 JST 2020
     */
    int insert(User record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table public.user
     * @mbg.generated  Sun Apr 26 16:43:16 JST 2020
     */
    int insertSelective(User record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table public.user
     * @mbg.generated  Sun Apr 26 16:43:16 JST 2020
     */
    List<User> selectByExample(UserExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table public.user
     * @mbg.generated  Sun Apr 26 16:43:16 JST 2020
     */
    User selectByPrimaryKey(String emailaddress);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table public.user
     * @mbg.generated  Sun Apr 26 16:43:16 JST 2020
     */
    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table public.user
     * @mbg.generated  Sun Apr 26 16:43:16 JST 2020
     */
    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table public.user
     * @mbg.generated  Sun Apr 26 16:43:16 JST 2020
     */
    int updateByPrimaryKeySelective(User record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table public.user
     * @mbg.generated  Sun Apr 26 16:43:16 JST 2020
     */
    int updateByPrimaryKey(User record);
}