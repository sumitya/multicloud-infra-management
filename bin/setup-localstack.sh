echo "Initializing the localstack"
#pull and start localstack-full docker image
docker run --rm -it -p 4566:4566 -p 4571:4571 localstack/localstack-full

#sample command with VARIABLES
docker run -p 4597:4597 -e SERVICES=ec2 -e DEFAULT_REGION=us-east-1 localstack/localstack:0.11.2

#configure aws-cli, type dummy credentials
aws configure

#S3 bucket tests
aws --endpoint-url http://localhost:4566 s3 mb s3://test
aws --endpoint-url http://localhost:4566 s3 ls

#Kinesis
aws --endpoint-url=http://localhost:4566 kinesis list-streams
aws --endpoint-url=http://localhost:4566 kinesis create-stream --stream-name my-stream --shard-count 2
aws --endpoint-url=http://localhost:4566 kinesis describe-stream --stream-name my-stream

#EC2
aws --endpoint-url=http://localhost:4566 ec2 describe-instances
aws --endpoint-url=http://localhost:4566 ec2 describe-instances --filters "Name=instance-type,Values=t1.micro" --query "Reservations[].Instances[].InstanceId"
aws --endpoint-url=http://localhost:4566 ec2 terminate-instances --instance-ids i-7e762f1e01c094970

#SNS
#https://docs.aws.amazon.com/AmazonCloudWatch/latest/monitoring/US_SetupSNS.html
aws --endpoint-url=http://localhost:4566 sns create-topic --name my-topic
aws --endpoint-url=http://localhost:4566 sns subscribe --topic-arn arn:aws:sns:us-east-1:000000000000:my-topic --protocol email --notification-endpoint test@gmail.com
aws --endpoint-url=http://localhost:4566  sns list-subscriptions-by-topic --topic-arn arn:aws:sns:us-east-1:000000000000:my-topic
aws --endpoint-url=http://localhost:4566 sns publish --message "Verification" --topic arn:aws:sns:us-east-1:000000000000:my-topic

