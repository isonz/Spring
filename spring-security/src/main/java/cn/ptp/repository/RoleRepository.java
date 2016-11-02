package cn.ptp.repository;

import cn.ptp.entity.Role;
import cn.ptp.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Set;


public interface RoleRepository extends PagingAndSortingRepository<Role, Long>
{
	List<Role> findByUsers_Username(String username);

	Set<Role> findByUsers(User user);

}
