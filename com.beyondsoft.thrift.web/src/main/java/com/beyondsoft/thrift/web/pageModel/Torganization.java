package com.beyondsoft.thrift.web.pageModel;

import java.util.HashSet;
import java.util.Set;
import com.beyondsoft.thrift.web.pageModel.Torganization;

public class Torganization implements java.io.Serializable{

    private String id;

    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Torganization getTorganization() {
		return torganization;
	}

	public void setTorganization(Torganization torganization) {
		this.torganization = torganization;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Torganization> getTorganizations() {
		return torganizations;
	}

	public void setTorganizations(Set<Torganization> torganizations) {
		this.torganizations = torganizations;
	}

	public Set<Tuser> getTusers() {
		return tusers;
	}

	public void setTusers(Set<Tuser> tusers) {
		this.tusers = tusers;
	}

	private Torganization torganization;

    private String description;

    private String name;

    private Set<Torganization> torganizations = new HashSet<Torganization>(0);

    private Set<Tuser> tusers = new HashSet<Tuser>(0);
}
