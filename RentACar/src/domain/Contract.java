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

@Entity
@Table(name = "RENT.CONTRACT")
public class Contract {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;
	@Column(name = "STATUS")
	private String status;
	@Column(name = "RESERVATIONNR")
	private String reservationNr;
	@Cascade({CascadeType.ALL})
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CUSTOMERID")
	private Customer customer;
	@Cascade({CascadeType.ALL})
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CUSTOMER2ID")
	private Customer customer2;
	@Cascade({CascadeType.ALL})
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CARID")
	private Car car;
	@Cascade({CascadeType.ALL})
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CAR2ID")
	private Car car2;	
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
	@Column(name = "OUTAGENTNR")
	private int outAgentNr;
	@Column(name = "INAGENTNR")
	private int inAgentNr;
	@Column(name = "AUTHORIZATIONAMOUNT")
	private Integer authorizationAmount;
	@Column(name = "AUTHORIZATIONNR")
	private String authorizationNr;
	@Column(name = "BASERATE")
	private BigDecimal baseRate;
	@Column(name = "PRICE")
	private BigDecimal price;
	@Cascade({CascadeType.ALL})
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PRICEADDITIONSID")
	private PriceAdditions priceAdditions;
	@Column(name = "NOTE")
	private String note;	
	@Column(name = "TOTALKM")
	private int totalKm;
	@Column(name = "DAMAGE")
	private boolean damage;
	@Column(name = "DAMAGEPRICE")
	private BigDecimal damagePrice;	
	@Column(name = "CARCHANGE")
	private boolean carChange;
	

	public Contract() {
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


	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	public Customer getCustomer2() {
		return customer2;
	}


	public void setCustomer2(Customer customer2) {
		this.customer2 = customer2;
	}


	public Car getCar() {
		return car;
	}


	public void setCar(Car car) {
		this.car = car;
	}


	public Car getCar2() {
		return car2;
	}


	public void setCar2(Car car2) {
		this.car2 = car2;
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


	public int getOutAgentNr() {
		return outAgentNr;
	}


	public void setOutAgentNr(int outAgentNr) {
		this.outAgentNr = outAgentNr;
	}


	public int getInAgentNr() {
		return inAgentNr;
	}


	public void setInAgentNr(int inAgentNr) {
		this.inAgentNr = inAgentNr;
	}


	public Integer getAuthorizationAmount() {
		return authorizationAmount;
	}


	public void setAuthorizationAmount(Integer authorizationAmount) {
		this.authorizationAmount = authorizationAmount;
	}


	public String getAuthorizationNr() {
		return authorizationNr;
	}


	public void setAuthorizationNr(String authorizationNr) {
		this.authorizationNr = authorizationNr;
	}


	public BigDecimal getBaseRate() {
		return baseRate;
	}


	public void setBaseRate(BigDecimal baseRate) {
		this.baseRate = baseRate;
	}


	public BigDecimal getPrice() {
		return price;
	}


	public void setPrice(BigDecimal price) {
		this.price = price;
	}


	public PriceAdditions getPriceAdditions() {
		return priceAdditions;
	}


	public void setPriceAdditions(PriceAdditions priceAdditions) {
		this.priceAdditions = priceAdditions;
	}


	public String getNote() {
		return note;
	}


	public void setNote(String note) {
		this.note = note;
	}


	public int getTotalKm() {
		return totalKm;
	}


	public void setTotalKm(int totalKm) {
		this.totalKm = totalKm;
	}


	public boolean isDamage() {
		return damage;
	}


	public void setDamage(boolean damage) {
		this.damage = damage;
	}


	public BigDecimal getDamagePrice() {
		return damagePrice;
	}


	public void setDamagePrice(BigDecimal damagePrice) {
		this.damagePrice = damagePrice;
	}


	public boolean isCarChange() {
		return carChange;
	}


	public void setCarChange(boolean carChange) {
		this.carChange = carChange;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accessories == null) ? 0 : accessories.hashCode());
		result = prime * result + ((authorizationAmount == null) ? 0 : authorizationAmount.hashCode());
		result = prime * result + ((authorizationNr == null) ? 0 : authorizationNr.hashCode());
		result = prime * result + ((baseRate == null) ? 0 : baseRate.hashCode());
		result = prime * result + ((car == null) ? 0 : car.hashCode());
		result = prime * result + ((car2 == null) ? 0 : car2.hashCode());
		result = prime * result + (carChange ? 1231 : 1237);
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + ((customer2 == null) ? 0 : customer2.hashCode());
		result = prime * result + (damage ? 1231 : 1237);
		result = prime * result + ((damagePrice == null) ? 0 : damagePrice.hashCode());
		result = prime * result + ((dropOffDate == null) ? 0 : dropOffDate.hashCode());
		result = prime * result + ((dropOffOffice == null) ? 0 : dropOffOffice.hashCode());
		result = prime * result + ((dropOffTime == null) ? 0 : dropOffTime.hashCode());
		result = prime * result + id;
		result = prime * result + inAgentNr;
		result = prime * result + ((insurance == null) ? 0 : insurance.hashCode());
		result = prime * result + ((note == null) ? 0 : note.hashCode());
		result = prime * result + outAgentNr;
		result = prime * result + ((pickUpDate == null) ? 0 : pickUpDate.hashCode());
		result = prime * result + ((pickUpOffice == null) ? 0 : pickUpOffice.hashCode());
		result = prime * result + ((pickUpTime == null) ? 0 : pickUpTime.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((priceAdditions == null) ? 0 : priceAdditions.hashCode());
		result = prime * result + ((reservationNr == null) ? 0 : reservationNr.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + totalKm;
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
		Contract other = (Contract) obj;
		if (accessories == null) {
			if (other.accessories != null)
				return false;
		} else if (!accessories.equals(other.accessories))
			return false;
		if (authorizationAmount == null) {
			if (other.authorizationAmount != null)
				return false;
		} else if (!authorizationAmount.equals(other.authorizationAmount))
			return false;
		if (authorizationNr == null) {
			if (other.authorizationNr != null)
				return false;
		} else if (!authorizationNr.equals(other.authorizationNr))
			return false;
		if (baseRate == null) {
			if (other.baseRate != null)
				return false;
		} else if (!baseRate.equals(other.baseRate))
			return false;
		if (car == null) {
			if (other.car != null)
				return false;
		} else if (!car.equals(other.car))
			return false;
		if (car2 == null) {
			if (other.car2 != null)
				return false;
		} else if (!car2.equals(other.car2))
			return false;
		if (carChange != other.carChange)
			return false;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (customer2 == null) {
			if (other.customer2 != null)
				return false;
		} else if (!customer2.equals(other.customer2))
			return false;
		if (damage != other.damage)
			return false;
		if (damagePrice == null) {
			if (other.damagePrice != null)
				return false;
		} else if (!damagePrice.equals(other.damagePrice))
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
		if (inAgentNr != other.inAgentNr)
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
		if (outAgentNr != other.outAgentNr)
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
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (totalKm != other.totalKm)
			return false;
		return true;
	}


	


	
}
