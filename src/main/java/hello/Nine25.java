package hello;

import java.util.Date;

import org.springframework.data.annotation.Id;

public class Nine25 {

	@Id
	private String id;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public float getShvol() {
		return shvol;
	}

	public void setShvol(float shvol) {
		this.shvol = shvol;
	}

	public float getSzvol() {
		return szvol;
	}

	public void setSzvol(float szvol) {
		this.szvol = szvol;
	}

	private Date date;
	//yi unit
	private float shvol;
	private float szvol;

}
