package cn.ptp.repository;

import cn.ptp.entity.Department;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface DepartmentRepository extends PagingAndSortingRepository<Department, Long>
{
	Optional<Department> findById(long id);

	Optional<Department> findByName(String name);

	//@Query(value = "select d.* from user u, dept d where u.dept_id=d.id AND u.id= ?1", nativeQuery = true)
	//Department findUserDeptName(long id);

}
