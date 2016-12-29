/*
 * implements Serializable 最重要的两个原因是：
 * 1、将对象的状态保存在存储媒体中以便可以在以后重新创建出完全相同的副本；
 * 2、按值将对象从一个应用程序域发送至另一个应用程序域。
 */
package cn.ptp.entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@RequiredArgsConstructor                            //仅针对final系列强制初始化
@AllArgsConstructor(access = AccessLevel.PACKAGE)	//构造包含所有参数的构造器,可指定access级别
@EqualsAndHashCode(of = "id")
@Table(name="dept")
@DynamicUpdate
@DynamicInsert
public class Department
{

	@Getter @Setter private @GeneratedValue @Id Long id;

	@Getter private String name;

	@Getter @Setter
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="parent_id", columnDefinition="INT(10) UNSIGNED COMMENT '父级ID'")
	private Department parent;

	public void setName(String name) {
		if(name.length() > 20) name = name.substring(0,20);
		this.name = name;
	}
	
}
