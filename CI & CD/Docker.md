# Docker

## Install

> https://hiseon.me/linux/ubuntu/install-docker/

* Docker Compose를 이용한 무중단 배포
  * http://jaynewho.com/post/21

* docker shell
* `docker exec -u 0 -it container bash`

## Install Jenkins

* ```shell
    $ mkdir -p /app/jenkins
    $ chmod 777 /app/jenkins
    $ docker run -d --name jenkins -p 8080:8080 -p 50000:50000 -v /app/jenkins:/var/jenkins_home \
      -u root -e JAVA_OPTS='-Duser.timezone=Asia/Seoul -Dfile.encoding=UTF-8 -Dsun.jnu.encoding=UTF-8' jenkins/jenkins
    ```

