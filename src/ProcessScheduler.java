public class ProcessScheduler {
	SchedulingAlgorithm sa;
	
	public ProcessScheduler() {
	}
	
	public void setAlgorithm (SchedulingAlgorithm sa) {
		this.sa = sa;
	}
	
	public void showProcessInfo() {
		this.sa.showProcessInfo();
	}
}
