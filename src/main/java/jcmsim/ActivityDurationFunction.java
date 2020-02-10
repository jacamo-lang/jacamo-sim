package jcmsim;

public interface ActivityDurationFunction {

	long computeTime(ECActivity act, ExecContext ctx);
		
}
