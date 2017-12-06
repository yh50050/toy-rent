package com.toy.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_md5")
public class MD5 {

	private long md5Id;

	private String md5Str;

	private String md5Bit32;

	private String md5Bit16;

	public long getMd5Id() {
		return md5Id;
	}

	public void setMd5Id(long md5Id) {
		this.md5Id = md5Id;
	}

	public String getMd5Str() {
		return md5Str;
	}

	public void setMd5Str(String md5Str) {
		this.md5Str = md5Str;
	}

	public String getMd5Bit32() {
		return md5Bit32;
	}

	public void setMd5Bit32(String md5Bit32) {
		this.md5Bit32 = md5Bit32;
	}

	public String getMd5Bit16() {
		return md5Bit16;
	}

	public void setMd5Bit16(String md5Bit16) {
		this.md5Bit16 = md5Bit16;
	}

}
