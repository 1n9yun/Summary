# Docker

## Install

> https://hiseon.me/linux/ubuntu/install-docker/

* Docker Compose를 이용한 무중단 배포

* http://jaynewho.com/post/21
  
* docker shell

  * ```shell
    docker exec -it container bash
    ```



## Docker-Compose

* ```shell
  docker exec -it popo_frontend sh
  sudo netstat -plant | grep 8
  ```

### Dockerfile

### docker-compose.yml

### Commands

### Volumes

* /var/lib/docker/volumes

### Networks



## Install Jenkins

* ```shell
    $ mkdir -p /app/jenkins
    $ chmod 777 /app/jenkins
    $ docker run -d --name jenkins -p 8080:8080 -p 50000:50000 -v /app/jenkins:/var/jenkins_home \
      -u root -e JAVA_OPTS='-Duser.timezone=Asia/Seoul -Dfile.encoding=UTF-8 -Dsun.jnu.encoding=UTF-8' jenkins/jenkins
    ```

