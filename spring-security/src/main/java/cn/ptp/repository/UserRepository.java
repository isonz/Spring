package cn.ptp.repository;

import cn.ptp.entity.Department;
import cn.ptp.entity.Role;
import cn.ptp.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends PagingAndSortingRepository<User, Long>
{
	Optional<User> findById(long id);

	Optional<User> findByUsername(String name);

	@Query(value = "select d.* from user u, dept d where u.dept_id=d.id AND u.id= ?1", nativeQuery = true)
	Department findUserDeptName(long id);

	Department findDepartmentById(long id);

	Iterable<User> findAllByOrderByIdDesc();

	@Query(value = "select u.*, d.id as did,d.name as dname, r.id as rid, r.name as rname from user u, dept d, role r, user_role ur where u.dept_id=d.id AND u.id=ur.users_id AND ur.roles_id=r.id ORDER by u.id DESC", nativeQuery = true)
	Iterable<User> findAllSql();

}
