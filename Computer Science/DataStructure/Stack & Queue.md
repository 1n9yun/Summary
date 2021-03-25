## 큐로 스택을 구현하기

큐 2개로 스택을 구현할 수 있다.

### Push

* 1번 큐가 비었다면 그대로 1번에 enqueue
* 비어있지 않다면 2번 큐에 모두 옮기고 1번에 enqueue
* 옮겨진 모두를 1번에 enqueue

### Pop

* 1번 큐에서 dequeue

### 개선

위와 같이 동작한다면 2번 큐는 계속 비어있고 전체를 2번씩 옮겨야 하므로 비효율적이다.

### Push

* main 큐가 비어있지 않다면 sub 큐와 swap
* main 큐에 enqueue
* sub 큐의 모든 값 main 큐로 옮기기

### Pop

* main 큐에서 dequeue

## 스택으로 큐를 구현하기

스택 2개로 큐를 구현할 수 있다.

### Enqueue

1번 스택에 push

### Dequeue

* 2번 스택이 비어있지 않다면 2번 스택에서 pop
* 비었다면 1번 스택이 빌 때까지 pop하면서 2번 스택에 push 후 마지막에 pop