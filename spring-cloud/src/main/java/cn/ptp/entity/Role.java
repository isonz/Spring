package cn.ptp.entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

@Entity
@RequiredArgsConstructor                            //仅针对final系列强制初始化
@AllArgsConstructor(access = AccessLevel.PACKAGE)	//构造包含所有参数的构造器,可指定access级别
@EqualsAndHashCode(of = "id")
@Table(name="role")
@DynamicUpdate
@DynamicInsert
public class Role
{
	//部门经理code
	public final static String DEPT_MANAGER_CODE = "01";

	//总经理code
	public final static String GENERALMANAGER_CODE = "02";

	//董事长code
	public final static String CHAIRMAN_CODE = "03";

	@Getter	@Setter private @GeneratedValue @Id Long id;
	@Getter @Setter private String name;

	@Getter @Setter
	@Column(name = "code", columnDefinition="varchar(20) COMMENT '角色代码'")
	private String code;

	@Getter @Setter
	@ManyToMany(cascade = CascadeType.MERGE, fetch=FetchType.LAZY)
	@JoinTable(name="user_role",joinColumns={@JoinColumn(name="roles_id",referencedColumnName="id")},inverseJoinColumns={@JoinColumn(name="users_id", referencedColumnName = "id")})
	private Set<User> users = new HashSet<User>();

}
