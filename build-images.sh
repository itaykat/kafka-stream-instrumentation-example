echo 'kafka-stream - Build started'
cd ./app/kafka/stream && sh gradlew clean jibDockerBuild && cd ../../..
echo 'kafka-stream - Build completed'

echo 'kafka-producer - Build started'
cd ./app/kafka/producer && sh gradlew clean jibDockerBuild
echo 'kafka-producer - Build completed'