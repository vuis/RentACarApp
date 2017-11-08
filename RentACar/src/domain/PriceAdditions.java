package domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "RENT.PRICEADDITIONS")
public class PriceAdditions {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;
	@Column(name = "APTFEE")
	private String aptFee;
	@Column(name = "ROADFEE")
	private BigDecimal roadFee;
	@Column(name = "VEHICLELICENSEFEE")
	private BigDecimal vehicleLicenseFee;

	public PriceAdditions() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAptFee() {
		return aptFee;
	}

	public void setAptFee(String aptFee) {
		this.aptFee = aptFee;
	}

	public BigDecimal getRoadFee() {
		return roadFee;
	}

	public void setRoadFee(BigDecimal roadFee) {
		this.roadFee = roadFee;
	}

	public BigDecimal getVehicleLicenseFee() {
		return vehicleLicenseFee;
	}

	public void setVehicleLicenseFee(BigDecimal vehicleLicenseFee) {
		this.vehicleLicenseFee = vehicleLicenseFee;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aptFee == null) ? 0 : aptFee.hashCode());
		result = prime * result + id;
		result = prime * result + ((roadFee == null) ? 0 : roadFee.hashCode());
		result = prime * result + ((vehicleLicenseFee == null) ? 0 : vehicleLicenseFee.hashCode());
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
		PriceAdditions other = (PriceAdditions) obj;
		if (aptFee == null) {
			if (other.aptFee != null)
				return false;
		} else if (!aptFee.equals(other.aptFee))
			return false;
		if (id != other.id)
			return false;
		if (roadFee == null) {
			if (other.roadFee != null)
				return false;
		} else if (!roadFee.equals(other.roadFee))
			return false;
		if (vehicleLicenseFee == null) {
			if (other.vehicleLicenseFee != null)
				return false;
		} else if (!vehicleLicenseFee.equals(other.vehicleLicenseFee))
			return false;
		return true;
	}

	

}
