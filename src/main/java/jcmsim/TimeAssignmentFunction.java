package jcmsim;

public interface TimeAssignmentFunction {

	long computeTime(ECEvent ev, ExecContext ctx);
		
}
