**nginx with certbot from [staticfloat/nginx-certbot]**

```
server {

  listen 80;
  listen [::]:80;
  return	301 https://www.popo.dev$request_uri;
    
  # server_name k3b307.p.ssafy.io;
  # server_name popo.dev;
  # server_name www.popo.dev;
}

server {
  listen 443 ssl;
  listen [::]:443 ssl;
  server_name ${FQDN};

  # Bad Example
  if ($host = "k3b307.p.ssafy.io") {
    return 301 https://www.popo.dev$request_uri;
  }

  ssl on;
  # ssl_certificate /etc/letsencrypt/live/k3b307.p.ssafy.io/fullchain.pem;
  # ssl_certificate_key /etc/letsencrypt/live/k3b307.p.ssafy.io/privkey.pem;
  ssl_certificate /etc/letsencrypt/live/${FQDN}/fullchain.pem;
  ssl_certificate_key /etc/letsencrypt/live/${FQDN}/privkey.pem;

  ssl_session_timeout 5m;
  ssl_protocols SSLv2 SSLv3 TLSv1.2;
  ssl_ciphers HIGH:!aNULL:!MD5;
  ssl_prefer_server_ciphers on;

  location / {
    root   /usr/share/nginx/html;
    index  index.html index.htm;
    try_files $uri $uri/ /index.html;
  }

  location /api {
      proxy_pass http://popo_backend:4000/api;
      proxy_redirect off;
      charset utf-8;

      proxy_http_version 1.1;
      proxy_set_header Upgrade $http_upgrade;
      proxy_set_header Connection 'upgrade';
      proxy_set_header Host $host;
      proxy_cache_bypass $http_upgrade;
   }

   location /resource {
      alias /static;
   }

  error_page   500 502 503 504  /50x.html;

  location = /50x.html {
    root   /usr/share/nginx/html;
  }

}
```

