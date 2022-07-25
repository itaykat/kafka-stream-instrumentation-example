compile:
	./gradlew clean build
image:
	./gradlew jibDockerBuild
run:
	docker-compose up -d
start:
	./gradlew clean build
	./gradlew jibDockerBuild
	docker-compose up -d
stop:
	docker-compose down