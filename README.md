# kafka-stream-instrumentation-example

# About

Sample Kafka Stream Hello world app with dokcer-compose

# Running Locally
The only dependency for running this example is [Docker Compose][docker].

[docker]: https://docs.docker.com/compose/install/

Once Docker Compose is installed, you can start the application using the following command:

```sh
$ make start
```
Once the dependencies are downloaded and the application is running (this may take a few minutes the first time you run the app, 
but will be much faster during subsequent runs), following the instructions under the __Producing Test Data__ section below.

# Producing Test Data
Once the Kafka Streams application is running, open a new shell tab and produce some 
data to the source topic (`hello`).

```sh
$ docker-compose exec kafka bash

$ kafka-console-producer \
    --bootstrap-server localhost:9092 \
    --topic hello
```

This will drop you in a prompt:

```sh
>
```

Now, type a few words, followed by `<ENTER>`.

```sh
>morning
>dreamer
```
You will see the output produced by the stream application in the console using the following command:
```sh
docker logs kafka-hello-stream -f
```

# Destory local Dokcer infrastructure

You can destroy the application using the following command


```sh
$ make stop
```
