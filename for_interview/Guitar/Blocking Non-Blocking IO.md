# Blocking, non-Blocking I/O

## Blocking

![Blocking IO Model](https://mblogthumb-phinf.pstatic.net/20140718_68/joebak_1405647716368SFg7v_JPEG/%BD%BD%B6%F3%C0%CC%B5%E53.jpg?type=w2)

* stream oriented
* stream으로부터 한 번에 한 개 이상의 byte를 읽는다는 의미
* Java IO의 많은 stream들은 blocking이다
* 쓰레드가 read(), write() 동작 시키면 쓰레드는 data를 읽을 때 까지 block



## non-Blocking

![Non-blocking IO Model](https://mblogthumb-phinf.pstatic.net/20140718_165/joebak_14056477165486yA48_JPEG/%BD%BD%B6%F3%C0%CC%B5%E54.jpg?type=w2)

* buffer oriented
* Java NIO는 non-blocking이다
* write 동작이 끝날 때까지 기다리지 않는다.



## WAS를 예로 들어보자

### Blocking IO Web Server - Spring

* 하나의 호출마다 thread를 생성
* 요청이 적은 서비스에서는 최적의 성능(병렬 처리의 장점)
* thread를 생성하는 부분에서 부하가 크다
* context-switching이 빈번하게 발생할 수 있다

### non-Blocking IO Web Server

* 요청을 받는 thread는 하나
* worker thread pool (tomcat에선 시스템의 코어 수)
* 요청을 받으면서 동시에 다른 작업도 진행한다.
* IO가 끝나면 polling 또는 Callback Function 방식으로 작업을 처리한다.