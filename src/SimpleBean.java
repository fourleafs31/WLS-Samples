package examples.servlets;

import java.io.Serializable;
import java.util.Date;

public class SimpleBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id = null;
	private Date date = null;
	private String comment = null;
	
	public SimpleBean(Integer id, Date date, String comment){
		super();
		this.id = id;
		this.date = date;
		this.comment = comment;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}

	public String toString(){
		String rtn = "id: " + this.id + ", date: " + this.date.toString() + ", comment: " + this.comment;
		return rtn;
	}
}

