# vertx-spring
vertx-spring


1. ./run.sh

Stop all containers:

```shell
docker stop $(docker ps -a -q)
```

Remove all containers :

```shell
docker rm $(docker ps -a -q)
```

Remove all images

```shell
docker rmi $(docker images -q)
```

Remove all images which has name none
```shell
docker rmi $(docker images | grep "^<none>" | awk "{print $3}")
```

Access urls

Swagger works !!!

http://192.168.99.100:8585/

http://192.168.99.100:8585/socket.html

http://192.168.99.100:8585/api/health

http://192.168.99.100:8585/api/users

http://192.168.99.100:8585/products

## set active docker-machine
eval $(docker-machine env default)
## docker swarm init
docker swarm init --advertise-addr=192.168.99.100

#run in docker compose 3
docker stack deploy --compose-file=docker-compose.yml vertx

For testing used Docker Toolbox

docker exec -u <your_postgres_user> <postgres_container_name> psql -c 'DROP DATABASE <your_db_name>'

## drop
docker exec -u postgres postgres-node psql -c 'DROP DATABASE socnet'

## backup
docker exec -u postgres postgres-node pg_dump -Fc socnet > ./postgres-node/db/dump.backup

## restore
docker exec -i -u postgres postgres-node pg_restore -C -d socnet < ./postgres-node/db/dump.backup

## run and scale

http://blog.arungupta.me/deploy-docker-compose-services-swarm/
https://docs.docker.com/compose/compose-file/#endpoint_mode


docker stack deploy --compose-file=docker-compose.yml vertx

docker ps -a

docker service ls

## restore database dump
docker ps -a

docker exec -i -u postgres vertx_postgres-node.1.zcx53rhuv9h88txrhdgo4ymcp pg_restore -C -d socnet < ./postgres-node/db/dump.backup

docker ps -a

docker exec -i -u postgres vertx_postgres-node.1.i2idnfywdz07vb7vgnwf9ugqh pg_restore -C -d socnet < ./postgres-node/db/dump.backup

docker exec -i -u postgres postgres-node pg_restore -C -d socnet < ./postgres-node/db/dump.backup



## test

http://192.168.99.100:8080/api/users

docker service ls

#scale
docker service scale vertx_vertx-node=2

docker service scale vertx_postgres-node=2

docker service ls

## remove all services
docker stack rm vertx
## docker service ls

## Create cluster from UI

https://opsani.com/skopos/docker-compose-generator/

```shell
docker run -d -p 8100:8100 --restart=unless-stopped -e skstkn=compose --name skopos -v /var/run/docker.sock:/var/run/docker.sock opsani/skopos:edge
```
Access 

http://192.168.99.100:8100

## monitoring
```shell
docker run \
  --volume=/:/rootfs:ro \
  --volume=/var/run:/var/run:rw \
  --volume=/sys:/sys:ro \
  --volume=/var/lib/docker/:/var/lib/docker:ro \
  --volume=/dev/disk/:/dev/disk:ro \
  --publish=9999:9999 \
  --detach=true \
  --name=cadvisor \
  google/cadvisor:latest
```

##YAML formatter online
https://jsonformatter.org/yaml-formatter

