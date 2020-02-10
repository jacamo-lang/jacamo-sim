package jcmsim;

public class CausalLink {

	private ECEvent causeEv;
	private String ctx;
	
	public CausalLink(ECEvent causeEv, String ctx) {
		this.causeEv = causeEv;
		this.ctx = ctx;
	}
	
	public ECEvent getCausingEvent() {
		return causeEv;
	}
		
	public String getCausingContext() {
		return ctx;
	}
}
