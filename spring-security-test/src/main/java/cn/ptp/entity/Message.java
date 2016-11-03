package cn.ptp.entity;

import java.util.Date;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.AccessLevel;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@RequiredArgsConstructor							//仅针对final系列强制初始化  
@AllArgsConstructor(access = AccessLevel.PACKAGE)	//构造包含所有参数的构造器,可指定access级别  
@EqualsAndHashCode(of = "id")
@Table(name="message")
@DynamicUpdate
@DynamicInsert
public class Message
{
	@Getter @Setter private @GeneratedValue @Id Long id;
	@Getter private String name;
	@Getter @Setter private String msg;
	@Getter @Setter private int create_at;
	@Getter @Setter private String update_at;
	@Getter @Setter private Date date;
	@Getter @Setter private float days;
	@Getter @Setter private String ip;

	public void setName(String name) {
		if(name.length() > 20) name = name.substring(0,20);
		this.name = name;
	}

}
