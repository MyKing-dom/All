package entity;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable{
    private Integer rid;

    private String orderId;
    
    private String userId;
    
    private String sta;
    
    private String addressId;
    
    private double payment;

    private Date placed;
    
    private Date receipt;
    
    private Date deliver;
    
    private Date handover;
    
    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

    public String getSta() {
        return sta;
    }

    public void setSta(String sta) {
        this.sta = sta;
    }

	public String getAddressId() {
		return addressId;
	}

	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}

	public double getPayment() {
		return payment;
	}

	public void setPayment(double payment) {
		this.payment = payment;
	}

	public Date getPlaced() {
		return placed;
	}

	public void setPlaced(Date placed) {
		this.placed = placed;
	}

	public Date getReceipt() {
		return receipt;
	}

	public void setReceipt(Date receipt) {
		this.receipt = receipt;
	}

	public Date getDeliver() {
		return deliver;
	}

	public void setDeliver(Date deliver) {
		this.deliver = deliver;
	}

	public Date getHandover() {
		return handover;
	}

	public void setHandover(Date handover) {
		this.handover = handover;
	}
    
}