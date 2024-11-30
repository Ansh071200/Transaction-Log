# Transaction Log Application

## Overview

This is a Spring Boot-based Transaction Log Application that tracks transactions, calculates balance, and provides functionalities such as updating and filtering transactions. The application interacts with a MySQL database to store transaction records and allows users to perform operations such as adding transactions, checking the balance, and calculating the average debit value. The app is containerized using Docker and deployed on Kubernetes.

## Features

- **Get all transactions**: Retrieves a list of all transactions with timestamps.
- **Get balance**: Returns the current balance based on transaction history.
- **Add a transaction**: Allows users to add a new transaction with details like product name, transaction value, description, and transaction type (Credit or Debit).
- **Update a transaction**: Allows users to update transaction details by ID.
- **Get average debit value**: Calculates the average debit transaction value.
- **Filter transactions by type**: Filters transactions based on their type (Credit/Debit).

## Technology Stack

- **Spring Boot**: For building the backend application.
- **MySQL**: For storing transaction data.
- **Docker**: To containerize the application.
- **Kubernetes**: For deployment and orchestration.
- **Postman**: For testing API endpoints.

## Setup

### Prerequisites

- Java 17 or higher
- MySQL Database
- Docker (optional for containerization)
- Kubernetes (optional for deployment)

### Running the Application Locally

1. Clone the repository.
2. Set up a MySQL database and configure it in your local `application.properties` file or export env variables, db_password and db_username.
3. Run the application using the following command:

```bash
mvn spring-boot:run
```
4. Access the application at http://localhost:9000.

## API Endpoints

- **GET `/transactionlog/transactions`**: Retrieve all transactions.
- **GET `/transactionlog/balance`**: Retrieve the current balance.
- **POST `/transactionlog/addtransaction`**: Add a new transaction.
- **PUT `/transactionlog/updatetransaction/{id}`**: Update a transaction by ID.
- **GET `/transactionlog/avgdebitvalue`**: Get the average debit value.
- **GET `/transactionlog/filterbytype/{type}`**: Filter transactions by type (Credit or Debit).

## Docker Setup

### Build the Docker image:

```bash
docker build -t imagename --build-arg=YOUR_DS_URL .
```
##### NOTE: Use host.docker.internal in the datasource url to access local resources.

### Push the Docker image:

1. Docker Login

```bash
docker login -u USERNAME -p PASSWORD .
```
2. Tag the Image and Push

```bash
docker tag imagename USERNAME/imagename:TAG .
docker push USERNAME/imagename:TAG
```
3. Run and access the application (For local testing)

```bash
docker run -p 8080:8080 -d imagename .
```

## Kubernetes Setup

1. Create a Kubernetes ConfigMap for the application.
   
   You can create a ConfigMap to store your database URL and other configurations. Replace the placeholder `<your-database-url>` with your actual database URL.

   Example ConfigMap template:

   ```yaml
   apiVersion: v1
   kind: ConfigMap
   metadata:
     name: tl-config
   data:
     SPRING_DATASOURCE_URL: "<your-database-url>"
    ```
##### NOTE: Use host.minikube.internal in the db url for accessing host resources from within the cluster.

2. Start the Minikube Cluster

```bash
minikube start --driver=YOUR_DRIVER
minikube status
```

NOTE: Change the driver to virtual box in case of connection issues. Use command with the flag --no-vtx-check for removing unnecessary errors.

3. Apply k8s manifests and check configurations.

```bash
kubectl apply -f configMap.yaml
kubectl apply -f deployment.yaml
kubectl apply -f service.yaml
kubectl get pods
kubectl get deploy
kubectl get svc
kubectl logs -f POD_NAME -n default
```
4. Accessing the Application

```bash
minikube ip
```
-- Access the app at https://MINIKUBE_IP:30001
