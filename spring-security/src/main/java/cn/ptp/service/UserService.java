package cn.ptp.service;

import cn.ptp.entity.Department;
import cn.ptp.entity.Role;
import cn.ptp.entity.User;
import cn.ptp.repository.DepartmentRepository;
import cn.ptp.repository.RoleRepository;
import cn.ptp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Transactional
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))	
public class UserService
{
	private final UserRepository repository;
	private final RoleRepository roleRepository;
	private final DepartmentRepository departmentRepository;

	public User findOne(long id)
	{
		Assert.notNull(id, "id must not be null");
		return repository.findOne(id);
	}

	public int count()
	{
		return (int)repository.count();
	}

	public Page<User> paged(Pageable pageable)
	{
		Assert.notNull(pageable, "Pageable must not be null!");
		Page<User> page = repository.findAll(pageable);
		//for (Message p : page){	System.out.println(p.getName());}
		return page;
	}

	public Iterable<User> findAll()
	{
		/*
		User user = repository.findOne((long) 1);
		Set<Role> roles = user.getRoles();
		Iterator<Role> it = roles.iterator();
		while (it.hasNext()) {
			System.out.println(it.next().getName());
		}
		*/
		//return repository.findAll();
		return repository.findAllByOrderByIdDesc();
	}

	public User save(User user)
    {
		repository.findByUsername(user.getUsername()).ifPresent(tuser -> {
			if(!tuser.equals(user)){
				throw new IllegalArgumentException("Username 重复!");
			}
		});
		User tmp;
		try {
			tmp = findOne(user.getId());	    //防止没更新的字段变空
			tmp.setOpenid(user.getOpenid());
			tmp.setUsername(user.getUsername());
			tmp.setUserid(user.getUserid());
			tmp.setDepartment(user.getDepartment());
			tmp.setRoles(user.getRoles());
		}catch (NullPointerException e){
			tmp = user;
		}
		return repository.save(tmp);
	}

	public boolean delete(long id)
	{
		if(!repository.findById(id).isPresent()){
			//throw new IllegalArgumentException("ID: "+id+" is not exists!");
			return false;
		}
		repository.delete(id);
		if(!repository.findById(id).isPresent()) return true;
		return false;
	}

	public Iterable<User> findAllSql()
	{
		return repository.findAllSql();
	}

	public Iterable<Department> findAllDept()
	{
		return departmentRepository.findAll();
	}

	public Iterable<Role> findAllRole()
	{
		return roleRepository.findAll();
	}


}
