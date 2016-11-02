package cn.ptp.entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@RequiredArgsConstructor							//仅针对final系列强制初始化  
@AllArgsConstructor(access = AccessLevel.PACKAGE)	//构造包含所有参数的构造器,可指定access级别  
@EqualsAndHashCode(of = "id")
@Table(name="user")
@DynamicUpdate
@DynamicInsert
public class User
{
	@Getter @Setter private @GeneratedValue @Id Long id;
	@Getter @Setter private String openid;
	@Getter @Setter private String userid;
	@Getter private String username;

	@Getter @Setter
	@ManyToMany(cascade = CascadeType.MERGE, fetch=FetchType.LAZY)
	@JoinTable(name="user_role",joinColumns={@JoinColumn(name="users_id",referencedColumnName="id")},inverseJoinColumns={@JoinColumn(name="roles_id",referencedColumnName = "id")})
	private Set<Role> roles = new HashSet<Role>();

	@Getter @Setter @ManyToOne(fetch=FetchType.EAGER) 	//如果是EAGER，那么表示取出这条数据时，它关联的数据也同时取出放入内存中,如果是LAZY，那么取出这条数据时，它关联的数据并不取出来，在同一个session中，什么时候要用，就什么时候取(再次访问数据库)。但是，在session外，就不能再取了。用EAGER时，因为在内存里，所以在session外也可以取。一般只在一边设Eager，JPA接口默认为一对多为Lazy，多对一为Eager，但是Hibernate反向工程生成Entity时，多对一为Lazy，需要手动改为Eager。而两边都设Eager，那么代码中取一条记录时，会发2次SQL。
	@JoinColumn(name="dept_id", columnDefinition="INT(10) UNSIGNED COMMENT '部门ID，外键'")
	private Department department;

	@Getter @Setter
	@Column(name = "valid", columnDefinition="bit COMMENT '用户是否有效T,F'")
	private boolean valid = true;

	public void setUsername(String username) {
		if(username.length() > 20) username = username.substring(0,20);
		this.username = username;
	}

}
