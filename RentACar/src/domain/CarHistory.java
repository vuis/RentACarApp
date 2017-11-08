package domain;

import java.sql.Time;
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

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "RENT.CARHISTORY")
public class CarHistory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;
	@Cascade({CascadeType.ALL})
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CARID")
	private Car car;
	@Column(name = "CARKM")
	private int carKm;
	@Column(name = "CARFUEL")
	private int carFuel;
	@Column(name = "DATE")
	private Date date;
	@Column(name = "TIME")
	private Time time;	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "OFFICEID")
	private Office office;
	@Column(name = "VEHICLESTATUS")
	private String vehicleStatus;
	@Column(name = "MOVEDBY")
	private String movedBy;

	public CarHistory() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public int getCarKm() {
		return carKm;
	}

	public void setCarKm(int carKm) {
		this.carKm = carKm;
	}

	public int getCarFuel() {
		return carFuel;
	}

	public void setCarFuel(int carFuel) {
		this.carFuel = carFuel;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}

	public String getVehicleStatus() {
		return vehicleStatus;
	}

	public void setVehicleStatus(String vehicleStatus) {
		this.vehicleStatus = vehicleStatus;
	}

	public String getMovedBy() {
		return movedBy;
	}

	public void setMovedBy(String movedBy) {
		this.movedBy = movedBy;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((car == null) ? 0 : car.hashCode());
		result = prime * result + carFuel;
		result = prime * result + carKm;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + id;
		result = prime * result + ((movedBy == null) ? 0 : movedBy.hashCode());
		result = prime * result + ((office == null) ? 0 : office.hashCode());
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		result = prime * result + ((vehicleStatus == null) ? 0 : vehicleStatus.hashCode());
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
		CarHistory other = (CarHistory) obj;
		if (car == null) {
			if (other.car != null)
				return false;
		} else if (!car.equals(other.car))
			return false;
		if (carFuel != other.carFuel)
			return false;
		if (carKm != other.carKm)
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (id != other.id)
			return false;
		if (movedBy == null) {
			if (other.movedBy != null)
				return false;
		} else if (!movedBy.equals(other.movedBy))
			return false;
		if (office == null) {
			if (other.office != null)
				return false;
		} else if (!office.equals(other.office))
			return false;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		if (vehicleStatus == null) {
			if (other.vehicleStatus != null)
				return false;
		} else if (!vehicleStatus.equals(other.vehicleStatus))
			return false;
		return true;
	}

	
}
