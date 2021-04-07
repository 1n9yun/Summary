# 멀티 스레드

## Thread 상속

```java
public class MyThread extends Thread {
    @Override
    public void run() {
        // ...
    }
}
```

```java
public static void main(String[] args) {
    Thread t = new MyThread();
    t.start();
}
```

## Runnable

앞의 Thread 상속을 이용한 방법 보다는  Runnable 인터페이스를 이용하여 만드는 경우가 많다. 

java는 다중상속이 불가능하기 때문에 최대한 인터페이스를 이용하는 쪽으로 구현한다.

* Runnable 구현체 클래스를 하나 만든다
* 그 클래스에 run() 메소드를 정의하여 시킬 일을 정의한다.
* 위의 클래스를 정의하고 Thread 생성 시 인자로 넣어 생성
* Thread의 start 메소드 호출하여 실행되도록 한다.

```java
public class MyThread2 implements Runnable {
    @Override
    public void run() {
        // ...
    }
}
```

```java
public static void main(String[] args) {
    Runnable r = new MyThread2();
    Thread t = new Thread(r);
    t.start();
}
```

## 동시성 제어

이처럼 동시에 수행되는 프로그램의 경우에는 사용 순서의 제어가 필요한 경우가 많다.

동시성이 제어되는 클래스를 Thread-safe하다고 한다.

그러나 동시에 사용할 수 없게 되어지는 만큼 성능 저하가 일어난다.

### synchronized

Mutex(Mutual Exclusion)과 같다. lock을 이용한 방법.

```java
public synchronized void sync() {
    // 메소드에 쓰이는 경우
}
```

```java
// 별도 블록을 지정할 수도 있다.
@Override
public void run() {
    // ...
    synchronized(target) {
        for(int i=0;i<1000;i++){
            target.plus();
        }
        target.print();
    }
}
```

```java
// 아래와 같이 사용하지 않는 것에 주의,
// target이 key로 이용되며 스레드끼리 이를 공유해야 하는데
// 스레드가 접근할 때마다 새로 할당하여 각각 개별 키를 가지게 되어
// lock을 걸어둔 의미가 퇴색하게 된다.
@Override
public void run() {
    Target target = new Target();
     
   	synchronized(target) {
        for(int i=0;i<1000;i++){
            target.plus();
        }
        target.print();
    }
}
```

### wait(), notifyAll()

synchronized만을 사용하면, 특정 메소드를 순서대로 사용하도록 강제할 순 있지만, 어떤 조건에 따라 기다렸다가 실행되도록 할 수는 없다.

wait(), notifyAll() 모두 synchronized 구문 내에서만 호출되어야 한다. 이건 좀만 생각해보면 알 수 있다.

```java
boolean one = true;
boolean two = true;

public synchronized void use() {
    while(!one && !two) wait();
    
    if(one) {
        one = false;
    }else if(two) {
        two = false;
    }
}

public synchronized void done(String who) {
    if(who.equals("one")) {
        one = true;
    }else if(who.equals("two")) {
        two = true;
    }
    notifyAll();
}

// use에서 기다리고 있던 스레드들을 notifyAll()로 모두 깨우고
// 경쟁 상태에서 승리한 스레드를 제외한 스레드들은 다시 wait()
```

