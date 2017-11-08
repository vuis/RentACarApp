package domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "RENT.INSURANCE")
public class Insurance {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;
	@Column(name = "CDW")
	private String cdw;
	@Column(name = "CDWPRICE")
	private BigDecimal cdwPrice;
	@Column(name = "TP")
	private String tp;
	@Column(name = "TPPRICE")
	private BigDecimal tpPrice;
	@Column(name = "PAI")
	private String pai;
	@Column(name = "PAIPRICE")
	private BigDecimal paiPrice;

	public Insurance() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCdw() {
		return cdw;
	}

	public void setCdw(String cdw) {
		this.cdw = cdw;
	}

	public BigDecimal getCdwPrice() {
		return cdwPrice;
	}

	public void setCdwPrice(BigDecimal cdwPrice) {
		this.cdwPrice = cdwPrice;
	}

	public String getTp() {
		return tp;
	}

	public void setTp(String tp) {
		this.tp = tp;
	}

	public BigDecimal getTpPrice() {
		return tpPrice;
	}

	public void setTpPrice(BigDecimal tpPrice) {
		this.tpPrice = tpPrice;
	}

	public String getPai() {
		return pai;
	}

	public void setPai(String pai) {
		this.pai = pai;
	}

	public BigDecimal getPaiPrice() {
		return paiPrice;
	}

	public void setPaiPrice(BigDecimal paiPrice) {
		this.paiPrice = paiPrice;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cdw == null) ? 0 : cdw.hashCode());
		result = prime * result + ((cdwPrice == null) ? 0 : cdwPrice.hashCode());
		result = prime * result + id;
		result = prime * result + ((pai == null) ? 0 : pai.hashCode());
		result = prime * result + ((paiPrice == null) ? 0 : paiPrice.hashCode());
		result = prime * result + ((tp == null) ? 0 : tp.hashCode());
		result = prime * result + ((tpPrice == null) ? 0 : tpPrice.hashCode());
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
		Insurance other = (Insurance) obj;
		if (cdw == null) {
			if (other.cdw != null)
				return false;
		} else if (!cdw.equals(other.cdw))
			return false;
		if (cdwPrice == null) {
			if (other.cdwPrice != null)
				return false;
		} else if (!cdwPrice.equals(other.cdwPrice))
			return false;
		if (id != other.id)
			return false;
		if (pai == null) {
			if (other.pai != null)
				return false;
		} else if (!pai.equals(other.pai))
			return false;
		if (paiPrice == null) {
			if (other.paiPrice != null)
				return false;
		} else if (!paiPrice.equals(other.paiPrice))
			return false;
		if (tp == null) {
			if (other.tp != null)
				return false;
		} else if (!tp.equals(other.tp))
			return false;
		if (tpPrice == null) {
			if (other.tpPrice != null)
				return false;
		} else if (!tpPrice.equals(other.tpPrice))
			return false;
		return true;
	}

	
}
