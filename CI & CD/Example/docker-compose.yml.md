```yaml
version: "3"
services:
        popo_mongo:
                container_name: popo_mongo
                image: mongo
                environment:
                        MONGO_INITDB_ROOT_USERNAME: root
                        MONGO_INITDB_ROOT_PASSWORD: Jin@hasg0ne!
                ports:
                        - 27017:27017
                volumes:
                        - mongo:/data/db

        popo_redis:
                container_name: popo_redis
                image: redis
                ports:
                        - 6379:6379
                volumes:
                        - redis:/data/redis

        popo_backend:
                container_name: popo_backend
                build: ./backend
                ports:
                        - 4000:4000
                depends_on:
                        - popo_mongo
                        - popo_redis
                volumes:
                        - static:/static

        popo_frontend:
                container_name: popo_frontend
                build: ./frontend
                ports:
                        - 80:80/tcp
                        - 443:443/tcp
                environment:
                        CERTBOT_EMAIL: jig7357@gmail.com
                        ENVSUBST_VARS: FQDN
                        FQDN: www.popo.dev
                depends_on:
                        - popo_backend
                volumes:
                        - ./frontend/nginx/conf.d:/etc/nginx/user.conf.d:ro
                        - letsencrypt:/etc/letsencrypt
                        - static:/static

volumes:
        letsencrypt:
        static:
        mongo:
        redis:
```

