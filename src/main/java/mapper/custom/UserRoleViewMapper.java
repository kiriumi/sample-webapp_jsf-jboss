package mapper.custom;

import java.util.List;

import org.mybatis.cdi.Mapper;

import dto.custom.UserRole;

@Mapper
public interface UserRoleViewMapper {

    List<UserRole> select();

}
