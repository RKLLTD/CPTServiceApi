package com.leverage.cptapi.model;

import java.util.StringJoiner;

public class Service {

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSubsetId() {
		return subsetId;
	}

	public void setSubsetId(Integer subsetId) {
		this.subsetId = subsetId;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getSubsection() {
		return subsection;
	}

	public void setSubsection(String subsection) {
		this.subsection = subsection;
	}

	public Integer getDtkConceptId() {
		return dtkConceptId;
	}

	public void setDtkConceptId(Integer dtkConceptId) {
		this.dtkConceptId = dtkConceptId;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getDescriptor() {
		return descriptor;
	}

	public void setDescriptor(String descriptor) {
		this.descriptor = descriptor;
	}

	protected Integer id;
	public static final String ID = "id";
	protected Integer subsetId;
	public static final String SUBSETID = "subsetId";
	protected String section;
	public static final String SECTION = "section";
	protected String subsection;
	public static final String SUBSECTION = "subsection";
	protected Integer dtkConceptId;
	public static final String DTKCONCEPTID = "dtkConceptId"; 
	protected Integer code;
	public static final String CODE = "code";
	protected String descriptor;
	public static final String DESCRIPTOR = "descriptor";
	
	public Service(){}
	
	public Service(int id, int subsetId, String section, String subsection, Integer dtkConceptId, Integer code, String descriptor) {
		this.id = id;
		this.subsetId = subsetId;
		this.section = section;
		this.subsection = subsection;
		this.dtkConceptId = dtkConceptId;
		this.code = code;
		this.descriptor = descriptor;
	}
	
	public String toString() {
		
		StringJoiner stringJoiner = new StringJoiner(" ");
		StringBuilder stringBuilder = new StringBuilder();
		
		stringJoiner.add(stringBuilder.append(Service.ID).append(": ").append(Integer.toString(id)));
		stringBuilder.setLength(0);
		stringJoiner.add(stringBuilder.append(Service.SUBSETID).append(": ").append(Integer.toString(subsetId)));
		stringBuilder.setLength(0);
		stringJoiner.add(stringBuilder.append(Service.SECTION).append(": ").append(section));
		stringBuilder.setLength(0);
		stringJoiner.add(stringBuilder.append(Service.SUBSECTION).append(": ").append(subsection));
		stringBuilder.setLength(0);
		stringJoiner.add(stringBuilder.append(Service.DTKCONCEPTID).append(": ").append(Integer.toString(dtkConceptId)));
		stringBuilder.setLength(0);
		stringJoiner.add(stringBuilder.append(Service.CODE).append(": ").append(Integer.toString(code)));
		stringBuilder.setLength(0);
		
		return stringJoiner.toString();
	}
}
