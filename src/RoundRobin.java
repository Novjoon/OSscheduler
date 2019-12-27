public class RoundRobin implements SchedulingAlgorithm {
	// Ÿ�ӽ����̽� ī����
	int slice_counter = 0;
	// �� �ð�
	int total_time = 0;
	// ���μ��� ����
	int n;
	// Ÿ�ӽ����̽�
	int timeslice;
	// ���μ��� �迭
	ProcessBlock[] process;
	// �ӽ� ���μ��� ���
	ProcessBlock temp;
	// Ÿ����Ʈ
	int time_chart[];
	// ���ð� ��
	double waitsum;
	// �Ϸ�ð� ��
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
		// ����ť ����
		RRCircleQueue rcq = new RRCircleQueue(n);

		// ����ť�� ���μ��� �迭 ����
		for (int i = 0; i < n; i++) {
			rcq.insert(process[i]);
		}

		// Ȯ��
		System.out.println();
		System.out.println("*********RoundRobin**********");
		System.out.println("�۾��ð� �� : " + total_time);
		System.out.println("------------------------------");
		for (int i = 0; i < n; i++) {
			System.out.println("���μ��� ��ȣ : " + (rcq.peek().getProcessNum()));
			System.out.println("�۾� �ð� : " + rcq.peek().getPerfomeTime());
			rcq.remove();
			System.out.println("------------------------------");
		}

		// ����ť�� ���μ��� �ٽ� �迭 ����
		for (int i = 0; i < n; i++) {
			rcq.insert(process[i]);
		}

		for (int i = 0; i < total_time; i++) {
			if (!rcq.empty()) {
				// ����� �ð��� Ÿ�ӽ����̽� ���� �������� ���� pcb�� �۾��ð��� �������� �ݺ��� �ȿ��� �۵�
				for (slice_counter = 0; slice_counter < timeslice; slice_counter++) {
					// pc�� �۾��ð��� 0�϶� �ݺ��� Ż��

					if (rcq.peek().getPerfomeTime() == 0) {
						break;
					}
					// process ���۵Ǿ����� Ȯ��
					if (rcq.peek().isWorkStart() == false) {
						rcq.peek().setWaitTime(rcq.peek().getWaitTime() + (i - rcq.peek().getEndTime()));
					}
					rcq.peek().setWorkStart(true);

					// Process �ڿ��� �Ҵ�
					time_chart[i] = rcq.peek().getProcessNum();
					i++;

					// ����ð� 1�� ���̱�
					rcq.peek().setPerfomeTime(rcq.peek().getPerfomeTime() - 1);
					// ����ð� ����Ÿ�ӿ� ����
					rcq.peek().setEndTime(i);
				}

				// ���� ���μ�����

				// ���� PCB�� �۾��ð��� 0�϶�
				if (rcq.peek().getPerfomeTime() == 0) {
					// ���μ��� ��ȯ�� Ÿ����Ʈ �ε��� �Ѿ�� ���� ����
					i--;
					rcq.peek().setWorkStart(false);
					rcq.remove();
					if (rcq.peek() != null) {
						// ���� ť�� �Ѿ
					} else {
						// System.out.println("����ť �����");
					}
				}
				// �۾��ð��� ���Ҵٸ� �ٽ� ����ť ����
				else {
					// ���μ��� ��ȯ�� Ÿ����Ʈ �ε��� �Ѿ�� ���� ����
					i--;
					rcq.peek().setWorkStart(false);
					temp = rcq.peek();
					rcq.remove();
					rcq.insert(temp);
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
			System.out.println("process" + i + "��  �۾��Ϸ�ð� " + process[i].getEndTime());
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
