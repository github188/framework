package com.beyondsoft.thrift.web.pageModel;

import java.util.HashSet;
import java.util.Set;


public class Tuser implements java.io.Serializable {
	
	private String id;

	private String roleIds;

	private Torganization torganization;

	private String createTime;

	private String password;

	private String status;

	private String username;

	private String email;

	private String realname;

	private String phone;

	private String ids;

	private Set<Trole> troles = new HashSet<Trole>(0);

	private String roleName;

	private String orgName;

	private String orgId;

	public String getCreateTime() {
		return createTime;
	}

	public String getEmail() {
		return email;
	}

	public String getId() {
		return id;
	}

	public String getIds() {
		return ids;
	}

	public String getOrgId() {
		return orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public String getPassword() {
		return password;
	}

	public String getPhone() {
		return phone;
	}

	public String getRealname() {
		return realname;
	}

	public String getRoleIds() {
		return roleIds;
	}

	public String getRoleName() {
		return roleName;
	}

	public String getStatus() {
		return status;
	}

	public Torganization getTorganization() {
		return torganization;
	}

	public Set<Trole> getTroles() {
		return troles;
	}

	public String getUsername() {
		return username;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	public void setId(String id) {
		this.id = id;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}


	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setTorganization(Torganization torganization) {
		this.torganization = torganization;
	}

	public void setTroles(Set<Trole> troles) {
		this.troles = troles;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
