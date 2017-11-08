package domain;

import java.math.BigDecimal;
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
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "RENT.TRANSFERCARD")
public class TransferCard {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;
	@Column(name = "STATUS")
	private String status;
	@Cascade({CascadeType.ALL})
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CARID")
	private Car car;
	@Cascade({CascadeType.ALL})
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EMPLOYEEID")
	private Employee employee;
	@Column(name = "DATEOUT")
	@DateTimeFormat(pattern = "dd.MM.yyyy.")
	private Date dateOut;
	@Column(name = "TIMEOUT")
	private Time timeOut;
	@Column(name = "DATEIN")
	@DateTimeFormat(pattern = "dd.MM.yyyy.")
	private Date dateIn;	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "OFFICEOUTID")
	private Office officeOut;	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "OFFICEINID")
	private Office officeIn;
	@Column(name = "NOTE")
	private String note;
	@Column(name = "TOTALKM")
	private int totalKm;
	@Column(name = "INVOICENR")
	private String invoiceNr;
	@Column(name = "INVOICEAMOUNT")
	private BigDecimal invoiceAmount;

	public TransferCard() {
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

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Date getDateOut() {
		return dateOut;
	}

	public void setDateOut(Date dateOut) {
		this.dateOut = dateOut;
	}

	public Time getTimeOut() {
		return timeOut;
	}

	public void setTimeOut(Time timeOut) {
		this.timeOut = timeOut;
	}

	public Date getDateIn() {
		return dateIn;
	}

	public void setDateIn(Date dateIn) {
		this.dateIn = dateIn;
	}

	public Office getOfficeOut() {
		return officeOut;
	}

	public void setOfficeOut(Office officeOut) {
		this.officeOut = officeOut;
	}

	public Office getOfficeIn() {
		return officeIn;
	}

	public void setOfficeIn(Office officeIn) {
		this.officeIn = officeIn;
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

	public String getInvoiceNr() {
		return invoiceNr;
	}

	public void setInvoiceNr(String invoiceNr) {
		this.invoiceNr = invoiceNr;
	}

	public BigDecimal getInvoiceAmount() {
		return invoiceAmount;
	}

	public void setInvoiceAmount(BigDecimal invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((car == null) ? 0 : car.hashCode());
		result = prime * result + ((dateIn == null) ? 0 : dateIn.hashCode());
		result = prime * result + ((dateOut == null) ? 0 : dateOut.hashCode());
		result = prime * result + ((employee == null) ? 0 : employee.hashCode());
		result = prime * result + id;
		result = prime * result + ((invoiceAmount == null) ? 0 : invoiceAmount.hashCode());
		result = prime * result + ((invoiceNr == null) ? 0 : invoiceNr.hashCode());
		result = prime * result + ((note == null) ? 0 : note.hashCode());
		result = prime * result + ((officeIn == null) ? 0 : officeIn.hashCode());
		result = prime * result + ((officeOut == null) ? 0 : officeOut.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((timeOut == null) ? 0 : timeOut.hashCode());
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
		TransferCard other = (TransferCard) obj;
		if (car == null) {
			if (other.car != null)
				return false;
		} else if (!car.equals(other.car))
			return false;
		if (dateIn == null) {
			if (other.dateIn != null)
				return false;
		} else if (!dateIn.equals(other.dateIn))
			return false;
		if (dateOut == null) {
			if (other.dateOut != null)
				return false;
		} else if (!dateOut.equals(other.dateOut))
			return false;
		if (employee == null) {
			if (other.employee != null)
				return false;
		} else if (!employee.equals(other.employee))
			return false;
		if (id != other.id)
			return false;
		if (invoiceAmount == null) {
			if (other.invoiceAmount != null)
				return false;
		} else if (!invoiceAmount.equals(other.invoiceAmount))
			return false;
		if (invoiceNr == null) {
			if (other.invoiceNr != null)
				return false;
		} else if (!invoiceNr.equals(other.invoiceNr))
			return false;
		if (note == null) {
			if (other.note != null)
				return false;
		} else if (!note.equals(other.note))
			return false;
		if (officeIn == null) {
			if (other.officeIn != null)
				return false;
		} else if (!officeIn.equals(other.officeIn))
			return false;
		if (officeOut == null) {
			if (other.officeOut != null)
				return false;
		} else if (!officeOut.equals(other.officeOut))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (timeOut == null) {
			if (other.timeOut != null)
				return false;
		} else if (!timeOut.equals(other.timeOut))
			return false;
		if (totalKm != other.totalKm)
			return false;
		return true;
	}

	
}
