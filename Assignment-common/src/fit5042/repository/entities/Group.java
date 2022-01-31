package fit5042.repository.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the GROUPS database table.
 * 
 */
@Entity
@Table(name="GROUPS")
@NamedQuery(name="Group.findAll", query="SELECT g FROM Group g")
public class Group implements Serializable {
	private static final long serialVersionUID = 1L;

	private int groupId;

	private String groupType;

	private String userName;

	public Group() {
	}

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column(name="GROUP_ID")
	public int getGroupId() {
		return this.groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	@Column(name="GROUP_TYPE")
	public String getGroupType() {
		return this.groupType;
	}

	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}

	@Column(name="USER_NAME")
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}