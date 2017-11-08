package domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "RENT.ACCESSORIES")
public class Accessories {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;
	@Column(name = "ADDITIONALDRIVER")
	private boolean additionalDriver;
	@Column(name = "ADDITIONALDRIVERPRICE")
	private BigDecimal additionalDriverPrice;
	@Column(name = "GPS")
	private boolean gps;
	@Column(name = "GPSPRICE")
	private BigDecimal gpsPrice;
	@Column(name = "WIFI")
	private boolean wifi;
	@Column(name = "WIFIPRICE")
	private BigDecimal wifiPrice;
	@Column(name = "BABYSEAT")
	private boolean babySeat;
	@Column(name = "BABYSEATPRICE")
	private BigDecimal babySeatPrice;
	@Column(name = "ONEWAYFEE")
	private boolean oneWayFee;
	@Column(name = "ONEWAYFEEPRICE")
	private BigDecimal oneWayFeePrice;

	public Accessories() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isAdditionalDriver() {
		return additionalDriver;
	}

	public void setAdditionalDriver(boolean additionalDriver) {
		this.additionalDriver = additionalDriver;
	}

	public BigDecimal getAdditionalDriverPrice() {
		return additionalDriverPrice;
	}

	public void setAdditionalDriverPrice(BigDecimal additionalDriverPrice) {
		this.additionalDriverPrice = additionalDriverPrice;
	}

	public boolean isGps() {
		return gps;
	}

	public void setGps(boolean gps) {
		this.gps = gps;
	}

	public BigDecimal getGpsPrice() {
		return gpsPrice;
	}

	public void setGpsPrice(BigDecimal gpsPrice) {
		this.gpsPrice = gpsPrice;
	}

	public boolean isWifi() {
		return wifi;
	}

	public void setWifi(boolean wifi) {
		this.wifi = wifi;
	}

	public BigDecimal getWifiPrice() {
		return wifiPrice;
	}

	public void setWifiPrice(BigDecimal wifiPrice) {
		this.wifiPrice = wifiPrice;
	}

	public boolean isBabySeat() {
		return babySeat;
	}

	public void setBabySeat(boolean babySeat) {
		this.babySeat = babySeat;
	}

	public BigDecimal getBabySeatPrice() {
		return babySeatPrice;
	}

	public void setBabySeatPrice(BigDecimal babySeatPrice) {
		this.babySeatPrice = babySeatPrice;
	}

	public boolean isOneWayFee() {
		return oneWayFee;
	}

	public void setOneWayFee(boolean oneWayFee) {
		this.oneWayFee = oneWayFee;
	}

	public BigDecimal getOneWayFeePrice() {
		return oneWayFeePrice;
	}

	public void setOneWayFeePrice(BigDecimal oneWayFeePrice) {
		this.oneWayFeePrice = oneWayFeePrice;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (additionalDriver ? 1231 : 1237);
		result = prime * result + ((additionalDriverPrice == null) ? 0 : additionalDriverPrice.hashCode());
		result = prime * result + (babySeat ? 1231 : 1237);
		result = prime * result + ((babySeatPrice == null) ? 0 : babySeatPrice.hashCode());
		result = prime * result + (gps ? 1231 : 1237);
		result = prime * result + ((gpsPrice == null) ? 0 : gpsPrice.hashCode());
		result = prime * result + id;
		result = prime * result + (oneWayFee ? 1231 : 1237);
		result = prime * result + ((oneWayFeePrice == null) ? 0 : oneWayFeePrice.hashCode());
		result = prime * result + (wifi ? 1231 : 1237);
		result = prime * result + ((wifiPrice == null) ? 0 : wifiPrice.hashCode());
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
		Accessories other = (Accessories) obj;
		if (additionalDriver != other.additionalDriver)
			return false;
		if (additionalDriverPrice == null) {
			if (other.additionalDriverPrice != null)
				return false;
		} else if (!additionalDriverPrice.equals(other.additionalDriverPrice))
			return false;
		if (babySeat != other.babySeat)
			return false;
		if (babySeatPrice == null) {
			if (other.babySeatPrice != null)
				return false;
		} else if (!babySeatPrice.equals(other.babySeatPrice))
			return false;
		if (gps != other.gps)
			return false;
		if (gpsPrice == null) {
			if (other.gpsPrice != null)
				return false;
		} else if (!gpsPrice.equals(other.gpsPrice))
			return false;
		if (id != other.id)
			return false;
		if (oneWayFee != other.oneWayFee)
			return false;
		if (oneWayFeePrice == null) {
			if (other.oneWayFeePrice != null)
				return false;
		} else if (!oneWayFeePrice.equals(other.oneWayFeePrice))
			return false;
		if (wifi != other.wifi)
			return false;
		if (wifiPrice == null) {
			if (other.wifiPrice != null)
				return false;
		} else if (!wifiPrice.equals(other.wifiPrice))
			return false;
		return true;
	}

	
}
