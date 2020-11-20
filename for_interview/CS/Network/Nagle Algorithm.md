# Nagle Algorithm

* 데이터를 보낼 때 여러 겹의 헤더로 캡슐화되어 목적지로 보내진다.
* 데이터가 적을 경우 배보다 배꼽이 커지는 경우가 있다.
* 고의로 작은 단위의 데이터를 전송하는 경우도 있겠지만 의도치않게 비효율적인 송신을 해야하는 경우도 있다.
  * 상대방의 **윈도우 크기(전송 받을 수 있는 크기)**가 매우 작은 경우
  * 보내는 패킷의 크기 자체가 작은 경우
* **보낼 수 있는 데이터를 바로 패킷으로 만들지 않고 가능한 모아서 더 큰 패킷으로 만들어 한 번에 보냄**
* Nagle Algorithm은 이 대안을 실제로 구현한 네트워크 전송 알고리즘

* ```pseudocode
  #define MSS "maximum segment size"
  
  
  if there is new data to send
    if the window size >= MSS and available data is >= MSS
      send complete MSS segment now
    else
      if there is unconfirmed data still in the pipe
        enqueue data in the buffer until an acknowledge is received
      else
        send data immediately
      end if
    end if
  end if
  
  
  출처: http://en.wikipedia.org/wiki/Nagle's_algorithm
  ```

  1. 상대방이 받을 수 있는 사이즈(window size)와 내가 보낼 데이터가 MSS보다 크다면 문제없이 바로 전송
  2. 더 이상 send 처리할 데이터가 없다면 현재 있는 것 그대로 보낸다.
  3. 둘 다 아니라면 아래의 그림(Nagle On)처럼 동작

* ![nagle_flow](https://t1.daumcdn.net/cfile/tistory/232E58465514FBDE3D)

* 최대한 작은 크기의 패킷 전송을 지연시키는 기능을 할 수 잇는 것은 3번 경우에 대한 처리 덕분
* 기존 네트워크 환경에서는 데이터가 버퍼에 조금씩 쌓이면 Nagle Off의 그림 처럼 상대방의 ACK를 기다리지 ㅇ낳고 바로바로 작은 패킷을 전송한다.
* 이런 작은 패킷을 가능한 지연시키기 위하여 ACK가 올 때까지 전송을 중지하고 ACK가 도착한 시점에 지금까지 버퍼에 모인 데이터를 패킷으로 만들어 전송한다.



## 네트워크 게임에서의 Nagle Algorithm

> 여기는 주관적인 생각이 가미됐음 좀 더 신뢰성있는 정보 찾아보기

* 게임에서는 입력 패킷의 크기가 작더라도 그 의미는 매우 크다.
* 뿐만 아니라 PC 클라이언트의 입장에서 네트워크의 효율 문제는 큰 이슈가 아님 - 빠른 반응성을 얻는 것이 유저 입장에서 좋다
* 따라서 클라이언트 쪽에서는 nagle을 사용하지 않는 것이 유리하다고 생각
* 서버의 경우는 다르지 않을까? - 네이글 알고리즘을 사용했을 때 얻는 이득이 더 크지 않을까?