# 기동중인 lunch 도커 중단 및 삭제
sudo docker ps -a -q --filter "name=salesforce-middleware" | grep -q . && docker stop salesforce-middleware && docker rm salesforce-middleware | true

sudo docker rmi rheonik/salesforce-middleware:1.0

sudo docker pull rheonik/salesforce-middleware:1.0

docker run -d -p 8889:8889 --name lunch rheonik/salesforce-middleware:1.0

docker rmi -f $(docker images -f "dangling=true" -q) || true