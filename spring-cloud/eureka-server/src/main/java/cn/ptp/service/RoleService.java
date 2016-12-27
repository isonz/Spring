package cn.ptp.service;

import cn.ptp.entity.Role;
import cn.ptp.entity.User;
import cn.ptp.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;
import java.util.*;

@Transactional
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))	
public class RoleService
{
	private final RoleRepository repository;

	public Role findOne(long id)
	{
		Assert.notNull(id, "id must not be null");
		return repository.findOne(id);
	}

	public Role findByName(String name)
	{
		return repository.findByName(name);
	}

	public List<Role> findByUser(User user)
	{
		Set<Role> roles =  repository.findByUsers(user);
		List<Role> roles2 = new ArrayList<Role>();
		roles2.addAll(roles);
		return roles2;
	}
}
