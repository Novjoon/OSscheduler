public class FCFSQueue {

	// 큐 배열은 front와 rear 그리고 maxSize를 가진다.
	private int front;
	private int rear;
	private int maxSize;
	private ProcessBlock[] queueArray;

	// 큐 배열 생성
	public FCFSQueue(int maxSize) {

		this.front = 0;
		this.rear = -1;
		this.maxSize = maxSize;
		this.queueArray = new ProcessBlock[maxSize];
	}

	// 큐가 비어있는지 확인
	public boolean empty() {
		return (front == rear + 1);
	}

	// 큐가 꽉 찼는지 확인
	public boolean full() {
		return (rear == maxSize - 1);
	}

	// 큐 rear에 데이터 등록
	public void insert(ProcessBlock item) {

		if (full()) {
			System.out.println("꽉참");
		} else {
			queueArray[++rear] = item;
		}
	}

	// 큐에서 front 데이터 조회
	public ProcessBlock peek() {

		if (empty())
			return null;
		else
			return queueArray[front];
	}

	// 큐에서 front 데이터 제거
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
