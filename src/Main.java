import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String args[]) throws IOException {

		// 총 시간
		int total_time = 0;
		// 프로세스 갯수
		int n;
		// 타임슬라이스
		int timeslice;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Time Quantum 시분할시간을 정하세요(숫자입력): ");
		timeslice = Integer.parseInt(br.readLine());
		System.out.println("작업할 프로세스의 수를 정하세요 (숫자입력): ");
		n = Integer.parseInt(br.readLine());

		// 받은 개수를 인덱스로 하는 프로세스 배열 생성
		ProcessBlock[] process = new ProcessBlock[n];
		ProcessBlock[] process2 = new ProcessBlock[n];

		// ProcessBlock생성 및 작업시간 설정
		for (int i = 0; i < n; i++) {
			process[i] = new ProcessBlock();
			process2[i] = new ProcessBlock();
			System.out.println(i + 1 + " 번쨰 프로세스의 작업시간은? (Burst Time) (숫자입력): ");
			process[i].setPerfomeTime(Integer.parseInt(br.readLine()));
			process2[i].setPerfomeTime(process[i].getPerfomeTime());
			// Process 총 시간(total time)계산 &process number부여
			total_time += process[i].getPerfomeTime();
			process[i].setProcessNum(i);
			process2[i].setProcessNum(i);
		}
		System.out.println();

		ProcessScheduler rs = new ProcessScheduler();
		rs.setAlgorithm(new FCFS(process, total_time, timeslice, n));
		rs.showProcessInfo();
		rs.setAlgorithm(new RoundRobin(process2, total_time, timeslice, n));
		rs.showProcessInfo();

	}

}
