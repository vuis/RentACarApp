package domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@DynamicUpdate(value = true)
@Table(name = "RENT.CUSTOMER")
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;
	@Column(name = "FIRSTNAME")
	private String firstname;
	@Column(name = "LASTNAME")
	private String lastname;
	@Column(name = "DOB")
	@DateTimeFormat(pattern = "dd.MM.yyyy.")
	private Date dob;
	@Column(name = "ADDRESS")
	private String address;
	@Column(name = "CITY")
	private String city;
	@Column(name = "POSTALCODE")
	private String postalCode;
	@Column(name = "COUNTRY")
	private String country;
	@Column(name = "DRIVERSLICENSENR")
	private String driversLicenseNr;
	@Column(name = "PASSPORTNR")
	private String passportNr;
	@Column(name = "EMAIL")
	private String email;
	@Column(name = "PHONENR")
	private String phoneNr;
	@Column(name = "CARDNR")
	private String cardNr;
	@Column(name = "CARDEXP")
	private String cardExp;
	@Column(name = "CARDCVV")
	private String cardCvv;

	public Customer() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getDriversLicenseNr() {
		return driversLicenseNr;
	}

	public void setDriversLicenseNr(String driversLicenseNr) {
		this.driversLicenseNr = driversLicenseNr;
	}

	public String getPassportNr() {
		return passportNr;
	}

	public void setPassportNr(String passportNr) {
		this.passportNr = passportNr;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNr() {
		return phoneNr;
	}

	public void setPhoneNr(String phoneNr) {
		this.phoneNr = phoneNr;
	}

	public String getCardNr() {
		return cardNr;
	}

	public void setCardNr(String cardNr) {
		this.cardNr = cardNr;
	}

	public String getCardExp() {
		return cardExp;
	}

	public void setCardExp(String cardExp) {
		this.cardExp = cardExp;
	}

	public String getCardCvv() {
		return cardCvv;
	}

	public void setCardCvv(String cardCvv) {
		this.cardCvv = cardCvv;
	}

	public String getFullName() {
		return firstname + " " + lastname;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((cardCvv == null) ? 0 : cardCvv.hashCode());
		result = prime * result + ((cardExp == null) ? 0 : cardExp.hashCode());
		result = prime * result + ((cardNr == null) ? 0 : cardNr.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((dob == null) ? 0 : dob.hashCode());
		result = prime * result + ((driversLicenseNr == null) ? 0 : driversLicenseNr.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstname == null) ? 0 : firstname.hashCode());
		result = prime * result + id;
		result = prime * result + ((lastname == null) ? 0 : lastname.hashCode());
		result = prime * result + ((passportNr == null) ? 0 : passportNr.hashCode());
		result = prime * result + ((phoneNr == null) ? 0 : phoneNr.hashCode());
		result = prime * result + ((postalCode == null) ? 0 : postalCode.hashCode());
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
		Customer other = (Customer) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (cardCvv == null) {
			if (other.cardCvv != null)
				return false;
		} else if (!cardCvv.equals(other.cardCvv))
			return false;
		if (cardExp == null) {
			if (other.cardExp != null)
				return false;
		} else if (!cardExp.equals(other.cardExp))
			return false;
		if (cardNr == null) {
			if (other.cardNr != null)
				return false;
		} else if (!cardNr.equals(other.cardNr))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (dob == null) {
			if (other.dob != null)
				return false;
		} else if (!dob.equals(other.dob))
			return false;
		if (driversLicenseNr == null) {
			if (other.driversLicenseNr != null)
				return false;
		} else if (!driversLicenseNr.equals(other.driversLicenseNr))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstname == null) {
			if (other.firstname != null)
				return false;
		} else if (!firstname.equals(other.firstname))
			return false;
		if (id != other.id)
			return false;
		if (lastname == null) {
			if (other.lastname != null)
				return false;
		} else if (!lastname.equals(other.lastname))
			return false;
		if (passportNr == null) {
			if (other.passportNr != null)
				return false;
		} else if (!passportNr.equals(other.passportNr))
			return false;
		if (phoneNr == null) {
			if (other.phoneNr != null)
				return false;
		} else if (!phoneNr.equals(other.phoneNr))
			return false;
		if (postalCode == null) {
			if (other.postalCode != null)
				return false;
		} else if (!postalCode.equals(other.postalCode))
			return false;
		return true;
	}

	
}
