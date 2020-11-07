# nginx

## 시작

* `sudo apt-get install nginx`
* `service nginx restart`
* `service nginx status`

## Configuration

* `vi /etc/nginx/sites-available/default`

* /etc/nginx/conf.d

* example from TK

  ```
  server {
      listen 80 default_server;
      listen [::]:80 default_server;
  
      root /var/www/dist;
  
      # Add index.php to the list if you are using PHP
      index index.html index.htm index.nginx-debian.html;
  
      server_name joinmeeting.tk;
  
      location /videoconference {
          proxy_pass http://13.124.80.168:8080;
          proxy_set_header Host $host:$server_port;
          proxy_set_header X-Forwarded-Host $server_name;
          proxy_set_header X-Real-IP $remote_addr;
          proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
  
          proxy_http_version 1.1;
          proxy_set_header Upgrade $http_upgrade;
          proxy_set_header Connection "upgrade";
  
      }
  
      location / {
          try_files $uri $uri/ /index.html;
      }
  
  
      # css, js, html, ico, 이미지들
      location /images {
          alias /home/jenkins/workspace/joinmeeting/backend/resources/profile/image;
      }
  
  
      # 인스톨 파일
      location /dist {
          alias /home/jenkins/workspace/joinmeeting/backend/resources;
      }
  
  }
  ```

* 