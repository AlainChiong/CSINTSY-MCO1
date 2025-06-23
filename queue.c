struct node{
    char name[100]; // probably just a letter
    int childrenCount;
    Node children[100];
};

struct queue{
    Node queue[256];
    int headIndex;
    int tailIndex;
};

typedef struct node Node;
typedef struct queue Queue;

void createQueue(Queue *newQueue){
    newQueue->headIndex = 0;
	newQueue->tailIndex = 0;
}

void enqueue(Queue *q, Node newNode){
    q->queue[q->tailIndex] = newNode;
    q->tailIndex++;	
}

Node dequeue(Queue *q){
    Node dequeueVal = q->queue[q->headIndex];
    q->headIndex++;
    
    return dequeueVal;
}

int isQueueEmpty(Queue q){
    int empty = 0;

    if (q.headIndex == q.tailIndex)
        empty = 1;

    return empty;
}