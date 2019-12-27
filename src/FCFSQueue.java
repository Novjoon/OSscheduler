public class FCFSQueue {

	// ť �迭�� front�� rear �׸��� maxSize�� ������.
	private int front;
	private int rear;
	private int maxSize;
	private ProcessBlock[] queueArray;

	// ť �迭 ����
	public FCFSQueue(int maxSize) {

		this.front = 0;
		this.rear = -1;
		this.maxSize = maxSize;
		this.queueArray = new ProcessBlock[maxSize];
	}

	// ť�� ����ִ��� Ȯ��
	public boolean empty() {
		return (front == rear + 1);
	}

	// ť�� �� á���� Ȯ��
	public boolean full() {
		return (rear == maxSize - 1);
	}

	// ť rear�� ������ ���
	public void insert(ProcessBlock item) {

		if (full()) {
			System.out.println("����");
		} else {
			queueArray[++rear] = item;
		}
	}

	// ť���� front ������ ��ȸ
	public ProcessBlock peek() {

		if (empty())
			return null;
		else
			return queueArray[front];
	}

	// ť���� front ������ ����
	public ProcessBlock remove() {

		ProcessBlock item = peek();
		front++;
		return item;
	}
	
	public void clear() {
		this.front = 0;
		this.rear = -1;
	}

}
