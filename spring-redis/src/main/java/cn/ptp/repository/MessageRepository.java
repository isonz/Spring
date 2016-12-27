package cn.ptp.repository;

import java.util.Optional;

import cn.ptp.entity.Message;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

@CacheConfig(cacheNames = "message")
public interface MessageRepository extends PagingAndSortingRepository<Message, Long>
{
	Optional<Message> findById(long id);

	Optional<Message> findByName(String name);

	@Query(value = "select m.* from message m where m.name = ?1", nativeQuery = true)
	Message findName(String name);

	@Cacheable(value="message")
	Iterable<Message> findAll();

	@CacheEvict(value="message",allEntries=true)
	Message save(Message message);

}
