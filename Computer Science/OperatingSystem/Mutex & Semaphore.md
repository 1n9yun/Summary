# 뮤텍스와 세마포어

* 다수의 프로세스나 스레드가 공유 자원을 동시에 접근하는 것을 제어하는 것

* ## 뮤텍스

  * 한 스레드, 프로세스에 의해 소유될 수 있는 Key를 기반으로 한 상호배제 기법
  * 한 스레드가 임계 영역에 들어갈 때 lock을 걸어 다른 스레드가 접근하지 못하게 하고 임계 영역에서 나올 때 unlock 한다.

* ## 세마포어

  * 현재 공유 자원에 접근할 수 있는 스레드, 프로세스의 수를 나타내는 값을 두는 상호 배제 기법
