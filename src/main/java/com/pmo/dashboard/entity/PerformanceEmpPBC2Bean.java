package com.pmo.dashboard.entity;

public class PerformanceEmpPBC2Bean{
	String PBC1;
	String PBC2;
	String PBC3;
	String PBC4;
	String PBC5;
	public String getPBC1() {
		return PBC1;
	}
	public void setPBC1(String pBC1) {
		PBC1 = pBC1;
	}
	public String getPBC2() {
		return PBC2;
	}
	public void setPBC2(String pBC2) {
		PBC2 = pBC2;
	}
	public String getPBC3() {
		return PBC3;
	}
	public void setPBC3(String pBC3) {
		PBC3 = pBC3;
	}
	public String getPBC4() {
		return PBC4;
	}
	public void setPBC4(String pBC4) {
		PBC4 = pBC4;
	}
	public String getPBC5() {
		return PBC5;
	}
	public void setPBC5(String pBC5) {
		PBC5 = pBC5;
	}
	public PerformanceEmpPBC2Bean(String PBC1,String PBC2,String PBC3,String PBC4,String PBC5) {
		super();
		this.PBC1=PBC1;
		this.PBC2=PBC2;
		this.PBC3=PBC3;
		this.PBC4=PBC4;
		this.PBC5=PBC5;
		
	}
	public String toString() {
		return "PerformanceEmpPBCBean [PBC1=" + PBC1 + ", PBC2=" + PBC2 + ", PBC3=" + PBC3
				+ ", PBC4=" + PBC4 + ",PBC5=" + PBC5 +"]";
	}
}