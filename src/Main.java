import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String args[]) throws IOException {

		// �� �ð�
		int total_time = 0;
		// ���μ��� ����
		int n;
		// Ÿ�ӽ����̽�
		int timeslice;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Time Quantum �ú��ҽð��� ���ϼ���(�����Է�): ");
		timeslice = Integer.parseInt(br.readLine());
		System.out.println("�۾��� ���μ����� ���� ���ϼ��� (�����Է�): ");
		n = Integer.parseInt(br.readLine());

		// ���� ������ �ε����� �ϴ� ���μ��� �迭 ����
		ProcessBlock[] process = new ProcessBlock[n];
		ProcessBlock[] process2 = new ProcessBlock[n];

		// ProcessBlock���� �� �۾��ð� ����
		for (int i = 0; i < n; i++) {
			process[i] = new ProcessBlock();
			process2[i] = new ProcessBlock();
			System.out.println(i + 1 + " ���� ���μ����� �۾��ð���? (Burst Time) (�����Է�): ");
			process[i].setPerfomeTime(Integer.parseInt(br.readLine()));
			process2[i].setPerfomeTime(process[i].getPerfomeTime());
			// Process �� �ð�(total time)��� &process number�ο�
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
