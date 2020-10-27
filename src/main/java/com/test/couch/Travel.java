package com.test.couch;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;

@Document
public class Travel {
	
	@Id
	private Integer id;
	private String callsign;
	private String country;
	private String iata;
	private String icao;
	
	private String name;
	private String type;
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	
	public String getCallsign() {
	return callsign;
	}
	
	public void setCallsign(String callsign) {
	this.callsign = callsign;
	}
	
	public String getCountry() {
	return country;
	}
	
	public void setCountry(String country) {
	this.country = country;
	}
	
	public String getIata() {
	return iata;
	}
	
	public void setIata(String iata) {
	this.iata = iata;
	}
	
	public String getIcao() {
	return icao;
	}
	
	public void setIcao(String icao) {
	this.icao = icao;
	}
	
	public Integer getId() {
	return id;
	}
	
	public void setId(Integer id) {
	this.id = id;
	}
	
	public String getName() {
	return name;
	}
	
	public void setName(String name) {
	this.name = name;
	}
	
	public String getType() {
	return type;
	}
	
	public void setType(String type) {
	this.type = type;
	}
	
	public Map<String, Object> getAdditionalProperties() {
	return this.additionalProperties;
	}
	
	public void setAdditionalProperty(String name, Object value) {
	this.additionalProperties.put(name, value);
	}
	
}