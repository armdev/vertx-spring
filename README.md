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
docker exec -i -u postgres postgres-node pg_restore -C -d postgres < ./postgres-node/db/dump.backup

