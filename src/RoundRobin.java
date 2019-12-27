public class RoundRobin implements SchedulingAlgorithm {
	// 타임슬라이스 카운터
	int slice_counter = 0;
	// 총 시간
	int total_time = 0;
	// 프로세스 갯수
	int n;
	// 타임슬라이스
	int timeslice;
	// 프로세스 배열
	ProcessBlock[] process;
	// 임시 프로세스 블록
	ProcessBlock temp;
	// 타임차트
	int time_chart[];
	// 대기시간 합
	double waitsum;
	// 완료시간 합
	double endsum;

	public RoundRobin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RoundRobin(ProcessBlock[] process, int total_time, int timeslice, int n) {
		super();

		this.process = process;
		this.total_time = total_time;
		this.timeslice = timeslice;
		this.n = n;

		this.time_chart = new int[total_time];
		// 원형큐 생성
		RRCircleQueue rcq = new RRCircleQueue(n);

		// 원형큐에 프로세스 배열 삽입
		for (int i = 0; i < n; i++) {
			rcq.insert(process[i]);
		}

		// 확인
		System.out.println();
		System.out.println("*********RoundRobin**********");
		System.out.println("작업시간 합 : " + total_time);
		System.out.println("------------------------------");
		for (int i = 0; i < n; i++) {
			System.out.println("프로세스 번호 : " + (rcq.peek().getProcessNum()));
			System.out.println("작업 시간 : " + rcq.peek().getPerfomeTime());
			rcq.remove();
			System.out.println("------------------------------");
		}

		// 원형큐에 프로세스 다시 배열 삽입
		for (int i = 0; i < n; i++) {
			rcq.insert(process[i]);
		}

		for (int i = 0; i < total_time; i++) {
			if (!rcq.empty()) {
				// 실행된 시간이 타임슬라이스 보다 작을때랑 지금 pcb의 작업시간이 있을때만 반복문 안에서 작동
				for (slice_counter = 0; slice_counter < timeslice; slice_counter++) {
					// pc의 작업시간이 0일때 반복문 탈출

					if (rcq.peek().getPerfomeTime() == 0) {
						break;
					}
					// process 시작되었는지 확인
					if (rcq.peek().isWorkStart() == false) {
						rcq.peek().setWaitTime(rcq.peek().getWaitTime() + (i - rcq.peek().getEndTime()));
					}
					rcq.peek().setWorkStart(true);

					// Process 자원에 할당
					time_chart[i] = rcq.peek().getProcessNum();
					i++;

					// 실행시간 1씩 줄이기
					rcq.peek().setPerfomeTime(rcq.peek().getPerfomeTime() - 1);
					// 현재시간 엔드타임에 설정
					rcq.peek().setEndTime(i);
				}

				// 다음 프로세스로

				// 지금 PCB의 작업시간이 0일때
				if (rcq.peek().getPerfomeTime() == 0) {
					// 프로세스 교환시 타임차트 인덱스 넘어가는 것을 막음
					i--;
					rcq.peek().setWorkStart(false);
					rcq.remove();
					if (rcq.peek() != null) {
						// 다음 큐로 넘어감
					} else {
						// System.out.println("다음큐 비었음");
					}
				}
				// 작업시간이 남았다면 다시 원형큐 삽입
				else {
					// 프로세스 교환시 타임차트 인덱스 넘어가는 것을 막음
					i--;
					rcq.peek().setWorkStart(false);
					temp = rcq.peek();
					rcq.remove();
					rcq.insert(temp);
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
			System.out.println("process" + i + "의  작업완료시간 " + process[i].getEndTime());
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
