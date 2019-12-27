public class RRCircleQueue {

	// 큐 배열은 front와 rear 그리고 maxSize를 가진다.
	private int front;
	private int rear;
	private int maxSize;
	private ProcessBlock[] queueArray;

	// 큐 배열 생성
	public RRCircleQueue(int maxSize) {

		this.front = 0;
		this.rear = -1;

		// 실제 크기보다 하나 크게 지정한다 (공백과 포화를 막기 위함)
		this.maxSize = maxSize + 1;
		this.queueArray = new ProcessBlock[this.maxSize];
	}

	// 큐가 비어있는지 확인
	public boolean empty() {
		return (front == rear + 1) || (front + maxSize - 1 == rear);
	}

	// 큐가 꽉 찼는지 확인
	public boolean full() {
		return (rear == maxSize - 1) || (front + maxSize - 2 == rear);
	}

	// 큐 rear에 데이터 등록
	public void insert(ProcessBlock item) {

		// rear 가 배열의 마지막이면 rear 포인터를 앞으로 돌린다.
		if (rear == maxSize - 1) {
			rear = -1;
		}
		queueArray[++rear] = item;
	}

	// 큐에서 front 데이터 조회
	public ProcessBlock peek() {

		if (!empty())
			return queueArray[front];
		else
			return null;
	}

	// 큐에서 front 데이터 제거
	public ProcessBlock remove() {

		ProcessBlock item = peek();
		front++;

		// front의 다음 index가 배열크기+1 이면 처음으로 돌아간다
		if (front == maxSize) {
			front = 0;
		}
		return item;
	}

}
