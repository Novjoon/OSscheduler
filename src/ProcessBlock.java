public class ProcessBlock {
	// 프로세스 넘버
	private int processNum;
	// 작업시간
	private int PerfomeTime = 0;
	// 대기시간
	private int WaitTime = 0;
	// 이 프로세스가 작업이 완료될 때까지 걸린 시간
	private int EndTime = 0;

	private boolean WorkStart = false;

	public ProcessBlock() {
	}

	public ProcessBlock(int processNum) {
		this.processNum = processNum;
	}

	public ProcessBlock(int pt, int wt, int et) {
		this.PerfomeTime = pt;
		this.WaitTime = wt;
		this.EndTime = et;
	}

	public int getProcessNum() {
		return processNum;
	}

	public void setProcessNum(int processNum) {
		this.processNum = processNum;
	}

	public int getPerfomeTime() {
		return PerfomeTime;
	}

	public void setPerfomeTime(int perfomeTime) {
		PerfomeTime = perfomeTime;
	}

	public int getWaitTime() {
		return WaitTime;
	}

	public void setWaitTime(int waitTime) {
		WaitTime = waitTime;
	}

	public int getEndTime() {
		return EndTime;
	}

	public void setEndTime(int endTime) {
		EndTime = endTime;
	}

	public boolean isWorkStart() {
		return WorkStart;
	}

	public void setWorkStart(boolean workStart) {
		WorkStart = workStart;
	}

	public void init() {
		this.WaitTime = 0;
		this.EndTime = 0;
		this.WorkStart = false;
	}

}
