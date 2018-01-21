package com.eec.entity;

import java.util.Date;
/**
 * ÊµÌåÀà
 * @author SJF18
 *
 */
public class FounderUser {
	private int userid;
	private String username;
	private String userpsw;
	private String usersex;
	private String usertel;
	private Date userbirth;

	public FounderUser() {
		super();
	}

	public FounderUser(int userid, String username, String userpsw,
			String usersex, String usertel, Date userbirth) {
		super();
		this.userid = userid;
		this.username = username;
		this.userpsw = userpsw;
		this.usersex = usersex;
		this.usertel = usertel;
		this.userbirth = userbirth;
	}

	@Override
	public String toString() {
		return "FounderUser [userid=" + userid + ", username=" + username
				+ ", userpsw=" + userpsw + ", usersex=" + usersex
				+ ", usertel=" + usertel + ", userbirth=" + userbirth + "]";
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpsw() {
		return userpsw;
	}

	public void setUserpsw(String userpsw) {
		this.userpsw = userpsw;
	}

	public String getUsersex() {
		return usersex;
	}

	public void setUsersex(String usersex) {
		this.usersex = usersex;
	}

	public String getUsertel() {
		return usertel;
	}

	public void setUsertel(String usertel) {
		this.usertel = usertel;
	}

	public Date getUserbirth() {
		return userbirth;
	}

	public void setUserbirth(Date userbirth) {
		this.userbirth = userbirth;
	}

}
