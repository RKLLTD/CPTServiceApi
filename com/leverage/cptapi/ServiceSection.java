package com.leverage.cptapi.model;

public enum ServiceSection {
	SURGERY(1, "Surgery"),
	MEDICINE_SERVICES_AND_PROCEDURES(2, "Medicine Services and Procedures"),
	EVALUATION_AND_MANAGEMENT_SERVICES(3, "Evaluation and Management Services");
	
	private int sectionId;
	private String label;
	
	private ServiceSection(int sectionId, String label) {
		this.sectionId = sectionId;
		this.label = label;
	}
	
	public int value() {
		return sectionId;
	}
	
	public String toString() {
		return label;
	}
}
