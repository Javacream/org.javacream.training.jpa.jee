package org.javacream.publishing;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class Address implements Serializable{

	private static final long serialVersionUID = 1L;

	private String city;
	private String street;
	@Override
	public String toString() {
		return "Address [city=" + city + ", street=" + street + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((street == null) ? 0 : street.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (street == null) {
			if (other.street != null)
				return false;
		} else if (!street.equals(other.street))
			return false;
		return true;
	}
	protected Address() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Address(String city, String street) {
		super();
		this.city = city;
		this.street = street;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getCity() {
		return city;
	}
	public String getStreet() {
		return street;
	}
}
