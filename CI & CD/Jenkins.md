# Jenkins

* ```shell
  docker exec <CONTAINER_NAME> cat /var/jenkins_home/secrets/initialAdminPassword
  ```

* host:8080으로 접속

* 위의 initialAdminPassword로 접속, 권장 플러그인 설치

* Jenkins 관리 - Global Tool Configuration (jdk, git, maven, gradle...)

* Github ssh 연동하기

  * https://jojoldu.tistory.com/442

  * 실행 중 jenkins 사용자 확인

    * `ps aux | grep jenkins`

  * 해당 사용자로 변경

    * `sudo -u user /bin/bash`

  * .ssh 디렉토리 생성 후 이동

    * ```shell
      mkdir /var/lib/jenkins/.ssh
      cd /var/lib/jenkins/.ssh
      ```

  * ssh 키 생성

    * `ssh-keygen -t rsa -f /var/lib/jenkins/.ssh/github_key`

  * Github - settings - Deploy keys에 공개키 등록

    * `cat /var/lib/jenkins/.ssh/github_key.pub`

  * 젠킨스 Credential - System - global credentials

  * 비밀키 등록

  * 젠킨스 - 새로운 Item - Freestyle Project

  * 소스 코드 관리 Git 설정

    * private repository의 경우 personal accesstoken을 생성 후 url에 추가
      * ex) http://username:personal_access_token@gitlab.yatra.com/xxxxxxxxxxxxxxxxxx.git

* 빌드 유발 - Github hook trigger ~

## Pipeline

