package domain;

import java.math.BigDecimal;
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
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "RENT.RESERVATION")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler", "id" })
public class Reservation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;
	@Column(name = "STATUS")
	private String status;
	@Column(name = "RESERVATIONNR")
	private String reservationNr;
	@Column(name = "RESERVATIONTYPE")
	private String reservationType;
	@Column(name = "BOOKERNR")
	private String bookerNr;
	@Cascade({CascadeType.ALL})
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CUSTOMERID")
	private Customer customer;
	@Column(name = "VEHICLEGROUP")
	private String vehicleGroup;	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PICKUPOFFICEID")
	private Office pickUpOffice;	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DROPOFFOFFICEID")
	private Office dropOffOffice;
	@DateTimeFormat(pattern = "dd.MM.yyyy.")
	@Column(name = "PICKUPDATE")
	private Date pickUpDate;
	@Column(name = "PICKUPTIME")
	private String pickUpTime;
	@Column(name = "DROPOFFDATE")
	@DateTimeFormat(pattern = "dd.MM.yyyy.")
	private Date dropOffDate;
	@Column(name = "DROPOFFTIME")
	private String dropOffTime;
	@Cascade({CascadeType.ALL})
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "INSURANCEID")
	private Insurance insurance;
	@Cascade({CascadeType.ALL})
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ACCESSORIESID")
	private Accessories accessories;
	@Column(name = "BASERATE")
	private BigDecimal baseRate;
	@Cascade({CascadeType.ALL})
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PRICEADDITIONSID")
	private PriceAdditions priceAdditions;
	@Column(name = "PRICE")
	private BigDecimal price;
	@Column(name = "NOTE")
	private String note;

	public Reservation() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getReservationNr() {
		return reservationNr;
	}

	public void setReservationNr(String reservationNr) {
		this.reservationNr = reservationNr;
	}

	public String getReservationType() {
		return reservationType;
	}

	public void setReservationType(String reservationType) {
		this.reservationType = reservationType;
	}

	public String getBookerNr() {
		return bookerNr;
	}

	public void setBookerNr(String bookerNr) {
		this.bookerNr = bookerNr;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getVehicleGroup() {
		return vehicleGroup;
	}

	public void setVehicleGroup(String vehicleGroup) {
		this.vehicleGroup = vehicleGroup;
	}

	public Office getPickUpOffice() {
		return pickUpOffice;
	}

	public void setPickUpOffice(Office pickUpOffice) {
		this.pickUpOffice = pickUpOffice;
	}

	public Office getDropOffOffice() {
		return dropOffOffice;
	}

	public void setDropOffOffice(Office dropOffOffice) {
		this.dropOffOffice = dropOffOffice;
	}

	public Date getPickUpDate() {
		return pickUpDate;
	}

	public void setPickUpDate(Date pickUpDate) {
		this.pickUpDate = pickUpDate;
	}

	public String getPickUpTime() {
		return pickUpTime;
	}

	public void setPickUpTime(String pickUpTime) {
		this.pickUpTime = pickUpTime;
	}

	public Date getDropOffDate() {
		return dropOffDate;
	}

	public void setDropOffDate(Date dropOffDate) {
		this.dropOffDate = dropOffDate;
	}

	public String getDropOffTime() {
		return dropOffTime;
	}

	public void setDropOffTime(String dropOffTime) {
		this.dropOffTime = dropOffTime;
	}

	public Insurance getInsurance() {
		return insurance;
	}

	public void setInsurance(Insurance insurance) {
		this.insurance = insurance;
	}

	public Accessories getAccessories() {
		return accessories;
	}

	public void setAccessories(Accessories accessories) {
		this.accessories = accessories;
	}

	public BigDecimal getBaseRate() {
		return baseRate;
	}

	public void setBaseRate(BigDecimal baseRate) {
		this.baseRate = baseRate;
	}

	public PriceAdditions getPriceAdditions() {
		return priceAdditions;
	}

	public void setPriceAdditions(PriceAdditions priceAdditions) {
		this.priceAdditions = priceAdditions;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accessories == null) ? 0 : accessories.hashCode());
		result = prime * result + ((baseRate == null) ? 0 : baseRate.hashCode());
		result = prime * result + ((bookerNr == null) ? 0 : bookerNr.hashCode());
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + ((dropOffDate == null) ? 0 : dropOffDate.hashCode());
		result = prime * result + ((dropOffOffice == null) ? 0 : dropOffOffice.hashCode());
		result = prime * result + ((dropOffTime == null) ? 0 : dropOffTime.hashCode());
		result = prime * result + id;
		result = prime * result + ((insurance == null) ? 0 : insurance.hashCode());
		result = prime * result + ((note == null) ? 0 : note.hashCode());
		result = prime * result + ((pickUpDate == null) ? 0 : pickUpDate.hashCode());
		result = prime * result + ((pickUpOffice == null) ? 0 : pickUpOffice.hashCode());
		result = prime * result + ((pickUpTime == null) ? 0 : pickUpTime.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((priceAdditions == null) ? 0 : priceAdditions.hashCode());
		result = prime * result + ((reservationNr == null) ? 0 : reservationNr.hashCode());
		result = prime * result + ((reservationType == null) ? 0 : reservationType.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((vehicleGroup == null) ? 0 : vehicleGroup.hashCode());
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
		Reservation other = (Reservation) obj;
		if (accessories == null) {
			if (other.accessories != null)
				return false;
		} else if (!accessories.equals(other.accessories))
			return false;
		if (baseRate == null) {
			if (other.baseRate != null)
				return false;
		} else if (!baseRate.equals(other.baseRate))
			return false;
		if (bookerNr == null) {
			if (other.bookerNr != null)
				return false;
		} else if (!bookerNr.equals(other.bookerNr))
			return false;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (dropOffDate == null) {
			if (other.dropOffDate != null)
				return false;
		} else if (!dropOffDate.equals(other.dropOffDate))
			return false;
		if (dropOffOffice == null) {
			if (other.dropOffOffice != null)
				return false;
		} else if (!dropOffOffice.equals(other.dropOffOffice))
			return false;
		if (dropOffTime == null) {
			if (other.dropOffTime != null)
				return false;
		} else if (!dropOffTime.equals(other.dropOffTime))
			return false;
		if (id != other.id)
			return false;
		if (insurance == null) {
			if (other.insurance != null)
				return false;
		} else if (!insurance.equals(other.insurance))
			return false;
		if (note == null) {
			if (other.note != null)
				return false;
		} else if (!note.equals(other.note))
			return false;
		if (pickUpDate == null) {
			if (other.pickUpDate != null)
				return false;
		} else if (!pickUpDate.equals(other.pickUpDate))
			return false;
		if (pickUpOffice == null) {
			if (other.pickUpOffice != null)
				return false;
		} else if (!pickUpOffice.equals(other.pickUpOffice))
			return false;
		if (pickUpTime == null) {
			if (other.pickUpTime != null)
				return false;
		} else if (!pickUpTime.equals(other.pickUpTime))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (priceAdditions == null) {
			if (other.priceAdditions != null)
				return false;
		} else if (!priceAdditions.equals(other.priceAdditions))
			return false;
		if (reservationNr == null) {
			if (other.reservationNr != null)
				return false;
		} else if (!reservationNr.equals(other.reservationNr))
			return false;
		if (reservationType == null) {
			if (other.reservationType != null)
				return false;
		} else if (!reservationType.equals(other.reservationType))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (vehicleGroup == null) {
			if (other.vehicleGroup != null)
				return false;
		} else if (!vehicleGroup.equals(other.vehicleGroup))
			return false;
		return true;
	}

	
}
