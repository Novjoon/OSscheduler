public class FCFS implements SchedulingAlgorithm {

	//FCFS�˰���
	// �� �ð�
	int total_time = 0;
	// ���μ��� ����
	int n;
	// Ÿ�ӽ����̽�
	int timeslice;
	// ���μ��� �迭
	ProcessBlock[] process;
	// Ÿ����Ʈ
	int time_chart[];
	// ���ð� ��
	double waitsum;
	// �Ϸ�ð� ��
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
		// FCFS���� Ÿ�ӽ����̽� ������
		this.n = n;

		this.time_chart = new int[total_time];
		// ť ����
		FCFSQueue fcq = new FCFSQueue(n);

		// ť�� ���μ��� �迭 ����
		for (int i = 0; i < n; i++) {
			fcq.insert(process[i]);
		}

		// Ȯ��
		System.out.println();
		System.out.println("*************FCFS*************");
		System.out.println("�۾��ð� �� : " + total_time);
		System.out.println("------------------------------");
		for (int i = 0; i < n; i++) {
			System.out.println("���μ��� ��ȣ : " + (fcq.peek().getProcessNum()));
			System.out.println("�۾� �ð� : " + fcq.peek().getPerfomeTime());
			fcq.remove();
			System.out.println("------------------------------");
		}
		fcq.clear();

		// ť�� �迭 �ٽ� ����
		for (int i = 0; i < n; i++) {
			fcq.insert(process[i]);
		}
		
		//FCFS �����ٸ� ����
		for (int i = 0; i < total_time; i++) {
			if (!fcq.empty()) {
				//���ð� ����
				fcq.peek().setWaitTime(i);
				for (int j = 0; j < fcq.peek().getPerfomeTime(); j++) {
					time_chart[i] = fcq.peek().getProcessNum();
					i++;
					fcq.peek().setEndTime(i);
				}
				//���� ���μ��� ��� ť���� ����
				fcq.remove();
				i--;
				if (fcq.peek() == null) {
					// System.out.println("�����");
					break;
				}
			} else {
				System.out.println("ť�� �����");
			}
		}

	}

	public void showProcessInfo() {
		System.out.println("�۾��ð� �׷���");
		for (int i = 0; i < total_time; i++) {
			System.out.print("PC" + time_chart[i] + " ");
		}
		System.out.println();
		System.out.println("--------------------------");
		for (int i = 0; i < n; i++) {
			System.out.println("process" + i + "��  �۾��Ϸ�ð�  " + process[i].getEndTime());
			endsum += process[i].getEndTime();
		}
		System.out.println("��� �۾��Ϸ�ð�" + (endsum / n));
		System.out.println("-------------");
		for (int i = 0; i < n; i++) {
			System.out.println("process" + i + "��  ���ð� " + process[i].getWaitTime());
			waitsum += process[i].getWaitTime();
		}
		System.out.println("��� ���ð�" + (waitsum / n));
	}

}
