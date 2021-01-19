# TCP & UDP

* ## Transport Layer

  * End Point간 신뢰성 있는 데이터 전송을 담당하는 계층
  * 신뢰성 : 데이터를 순차적, 안정적인 전달
  * 전송 : 포트 번호에 해당하는 프로세스에 데이터를 전달

* ## TCP

  * 신뢰성있는 데이터 통신을 가능하게 해주는 프로토콜

  * ### 특징

    * Connection(3-way-handshake) - 양방향 통신
      * Client가 SYN 비트를 1로 설정해 패킷 송신
      * Server가 SYN, ACK비트를 1로 설정해 패킷 송신
      * Client가 ACK비트를 1로 설정해 패킷 송신
    * 데이터 전송 방식
      * Client가 패킷 송신
      * Server에서 ACK 송신
      * Client는 ACK를 수신하지 못하면 재전송
    * 데이터의 순차 전송을 보장
    * 흐름제어
    * 혼잡제어
    * 오류감지
    * Connection close (4-way-handshake)
      * 데이터를 전부 송신한 Client가 FIN 송신
      * Server가 ACK 송신
      * Server에서 남은 패킷 송신
      * Server가 FIN 송신
      * Client가 ACK 송신

  * ### 문제점

    * 매번 Connection을 설정하여 시간 손실 발생
    * 패킷을 조금만 손실해도 재전송



* ## UDP

  * TCP보다 신뢰성이 떨어지지만 전송 속도가 일반적으로 빠른 프로토콜
  * Connectionless
  * 오류감지
  * 비교적 데이터의 신뢰성이 중요하지 않을 때 사용