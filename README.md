# vertx-spring
vertx-spring

1. ./run.sh

Access urls

Swagger works !!!

http://192.168.99.100:8080/

http://192.168.99.100:8080/socket.html

http://192.168.99.100:8080/api/health

http://192.168.99.100:8080/api/users

http://192.168.99.100:8080/products

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

docker stack deploy --compose-file=docker-compose.yml vertx

docker ps -a

## restore database dump

docker exec -i -u postgres vertx_postgres-node.1.zcx53rhuv9h88txrhdgo4ymcp pg_restore -C -d socnet < ./postgres-node/db/dump.backup

## test

http://192.168.99.100:8080/api/users

docker service ls

#scale
docker service scale vertx_vertx-node=2

docker service scale vertx_postgres-node=2

docker service ls

## remove all services
docker stack rm vertx
#
docker service ls




