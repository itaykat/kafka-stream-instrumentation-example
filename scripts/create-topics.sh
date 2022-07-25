echo "Waiting for Kafka to come online..."

cub kafka-ready -b kafka:9092 1 20

# create the users topic
kafka-topics \
  --bootstrap-server kafka:9092 \
  --topic hello \
  --replication-factor 1 \
  --partitions 4 \
  --create

sleep infinity
