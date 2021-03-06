package cn.ptp.service;

import java.util.Optional;

import javax.transaction.Transactional;

import cn.ptp.entity.Message;
import cn.ptp.repository.MessageRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Transactional
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))	
public class MessageService
{
	private final MessageRepository repository;

	public static final String CACHE_KEY = "message";				//缓存的key

	@Cacheable(value = CACHE_KEY)
	public Message findOne(long id)
	{
		Assert.notNull(id, "id must not be null");
		return repository.findOne(id);
	}

	public int count()
	{
		return (int)repository.count();
	}

	@Cacheable(value = CACHE_KEY)
	public Page<Message> paged(Pageable pageable)
	{
		Assert.notNull(pageable, "Pageable must not be null!");
		Page<Message> page = repository.findAll(pageable);
		//for (Message p : page){	System.out.println(p.getName());}
		return page;
	}

	@Cacheable(value = CACHE_KEY)
	public Iterable<Message> findAll()
	{
		return repository.findAll();
	}

	@CachePut(value = CACHE_KEY)		//每次都会真是调用函数，所以主要用于数据新增和修改操作上
	public Message save(Message message)
    {
		Assert.hasText(message.getMsg(), "Mst must not be empty!");

		findByName(message.getName()).ifPresent(mesg -> {
			if(!mesg.equals(message)){
				throw new IllegalArgumentException("Name 重复!");
			}
		});

		Message tmp;
		try {
			long id = message.getId();
			tmp = findOne(id);	    //防止没更新的字段变空
			tmp.setName(message.getName());
			tmp.setMsg(message.getMsg());
		}catch (NullPointerException e){
			tmp = message;
		}
        tmp = repository.save(tmp);

		System.out.println(tmp.getId());		//插入的ID
		//repository.delete(Long.valueOf(1));	//测试Transactional成功
		return tmp;
	}

	@Cacheable(value = CACHE_KEY)
	public Optional findByName(String name)
	{
		Assert.hasText(name, "Name must not be empty!");
		return repository.findByName(name);
	}

	@CacheEvict(value = CACHE_KEY)		//清除缓存
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

	public Message sql(Message message)
	{
		return repository.findName(message.getName());
	}

}
