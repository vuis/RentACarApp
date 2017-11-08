package domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "RENT.CAR")
public class Car {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "OFFICEID")
	private Office office;
	@Column(name = "NAME")
	private String name;
	@Column(name = "REGISTRATIONNR")
	private String registrationNr;
	@Column(name = "REGISTRATIONEXP")
	@DateTimeFormat(pattern = "dd.MM.yyyy.")
	private Date registrationExp;
	@Column(name = "KM")
	private int km;
	@Column(name = "FUEL")
	private Integer fuel;
	@Column(name = "STATUS")
	private String status;
	@Column(name = "OWNER")
	private String owner;
	@Column(name = "PICTURE")
	private String picture;

	public Car() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRegistrationNr() {
		return registrationNr;
	}

	public void setRegistrationNr(String registrationNr) {
		this.registrationNr = registrationNr;
	}

	public Date getRegistrationExp() {
		return registrationExp;
	}

	public void setRegistrationExp(Date registrationExp) {
		this.registrationExp = registrationExp;
	}

	public int getKm() {
		return km;
	}

	public void setKm(int km) {
		this.km = km;
	}

	public Integer getFuel() {
		return fuel;
	}

	public void setFuel(Integer fuel) {
		this.fuel = fuel;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getData() {
		return name + " " + registrationNr;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fuel == null) ? 0 : fuel.hashCode());
		result = prime * result + id;
		result = prime * result + km;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((office == null) ? 0 : office.hashCode());
		result = prime * result + ((owner == null) ? 0 : owner.hashCode());
		result = prime * result + ((picture == null) ? 0 : picture.hashCode());
		result = prime * result + ((registrationNr == null) ? 0 : registrationNr.hashCode());
		result = prime * result + ((registrationExp == null) ? 0 : registrationExp.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		Car other = (Car) obj;
		if (fuel == null) {
			if (other.fuel != null)
				return false;
		} else if (!fuel.equals(other.fuel))
			return false;
		if (id != other.id)
			return false;
		if (km != other.km)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (office == null) {
			if (other.office != null)
				return false;
		} else if (!office.equals(other.office))
			return false;
		if (owner == null) {
			if (other.owner != null)
				return false;
		} else if (!owner.equals(other.owner))
			return false;
		if (picture == null) {
			if (other.picture != null)
				return false;
		} else if (!picture.equals(other.picture))
			return false;
		if (registrationNr == null) {
			if (other.registrationNr != null)
				return false;
		} else if (!registrationNr.equals(other.registrationNr))
			return false;
		if (registrationExp == null) {
			if (other.registrationExp != null)
				return false;
		} else if (!registrationExp.equals(other.registrationExp))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}

}
