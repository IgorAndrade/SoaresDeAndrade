package br.com.soaresdeandrade.advocacia.error;

import java.util.Arrays;
import java.util.List;

public class RNException extends Exception{
	private static final long serialVersionUID = 1L;
	private List<RN> rn;
	public RNException(RN... rn) {
		this.rn=Arrays.asList(rn);
	}
	public List<RN> getRn() {
		return rn;
	}
	public void setRn(List<RN> rn) {
		this.rn = rn;
	}
	
}
