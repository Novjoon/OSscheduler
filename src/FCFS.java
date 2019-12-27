public class FCFS implements SchedulingAlgorithm {

	//FCFS알고리즘
	// 총 시간
	int total_time = 0;
	// 프로세스 갯수
	int n;
	// 타임슬라이스
	int timeslice;
	// 프로세스 배열
	ProcessBlock[] process;
	// 타임차트
	int time_chart[];
	// 대기시간 합
	double waitsum;
	// 완료시간 합
	double endsum;

	public FCFS() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FCFS(ProcessBlock[] process, int total_time, int timeslice, int n) {
		super();

		this.process = process;
		this.total_time = total_time;
		this.timeslice = timeslice;
		// FCFS에선 타임슬라이스 사용안함
		this.n = n;

		this.time_chart = new int[total_time];
		// 큐 생성
		FCFSQueue fcq = new FCFSQueue(n);

		// 큐에 프로세스 배열 삽입
		for (int i = 0; i < n; i++) {
			fcq.insert(process[i]);
		}

		// 확인
		System.out.println();
		System.out.println("*************FCFS*************");
		System.out.println("작업시간 합 : " + total_time);
		System.out.println("------------------------------");
		for (int i = 0; i < n; i++) {
			System.out.println("프로세스 번호 : " + (fcq.peek().getProcessNum()));
			System.out.println("작업 시간 : " + fcq.peek().getPerfomeTime());
			fcq.remove();
			System.out.println("------------------------------");
		}
		fcq.clear();

		// 큐에 배열 다시 삽입
		for (int i = 0; i < n; i++) {
			fcq.insert(process[i]);
		}
		
		//FCFS 스케줄링 시작
		for (int i = 0; i < total_time; i++) {
			if (!fcq.empty()) {
				//대기시간 설정
				fcq.peek().setWaitTime(i);
				for (int j = 0; j < fcq.peek().getPerfomeTime(); j++) {
					time_chart[i] = fcq.peek().getProcessNum();
					i++;
					fcq.peek().setEndTime(i);
				}
				//현재 프로세스 블록 큐에서 제거
				fcq.remove();
				i--;
				if (fcq.peek() == null) {
					// System.out.println("비었음");
					break;
				}
			} else {
				System.out.println("큐가 비었음");
			}
		}

	}

	public void showProcessInfo() {
		System.out.println("작업시간 그래프");
		for (int i = 0; i < total_time; i++) {
			System.out.print("PC" + time_chart[i] + " ");
		}
		System.out.println();
		System.out.println("--------------------------");
		for (int i = 0; i < n; i++) {
			System.out.println("process" + i + "의  작업완료시간  " + process[i].getEndTime());
			endsum += process[i].getEndTime();
		}
		System.out.println("평균 작업완료시간" + (endsum / n));
		System.out.println("-------------");
		for (int i = 0; i < n; i++) {
			System.out.println("process" + i + "의  대기시간 " + process[i].getWaitTime());
			waitsum += process[i].getWaitTime();
		}
		System.out.println("평균 대기시간" + (waitsum / n));
	}

}
