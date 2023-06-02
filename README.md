# TQS Project

Our project revolves around the idea of a platform that allows for a network of pickups points.
We will act as a middleman between eCommerce Stores and Pickups. We will have on one side eCommerce sites that partner
with us and on the other side Pickups.
This will enhance the economy of the physical stores that partner with us and allow for customer to have a broader
delivery choice. To access the project deployed on the Google Cloud platform, simply follow the links provided in the
[Bookmarks](#bookmarks) section.
Alternatively, if you prefer to run the project on your local machine, please follow the instructions below.

## Installation

Clone the repository:

```console
$ git clone https://github.com/leonardoflorido/TQS-Project.git
```

## How To Run (Docker)

Run the following commands in two separate terminals:

- Inside the `eStore` folder, run:

```console
$ docker-compose up
```

- Inside the `platform` folder, run:

```console
$ docker-compose up
```

Note:

- This process may take a significant amount of time.
- If you do not want to use the Docker method mentioned above, you can follow the alternative steps provided below.

## Prerequisites

To run the project, the following software must be installed on the system:

- [Node.js](https://nodejs.org/en/) (v18 or higher)
- [Python](https://www.python.org/) (v3.7 or higher)
- [Java](https://www.oracle.com/java/) (v17)
- [Maven](https://maven.apache.org/) (v3.9.0 or higher)
- [Docker](https://www.docker.com/)

## Requirements

Install requirements:

- Inside the `eStore/backend` folder, run:

 ```console
 $ pip install -r requirements.txt
 ```

- Inside the `platform/frontend` folder, run:

 ```console
 $ npm install
 ```

- Inside the `eStore/frontend` folder, run:

 ```console
 $ npm install
 ```

## How To Run The Database

Run the following command:

 ```console
 $ docker run -d -p 27017:27017 -name mongo mongo:latest
 ```

## How To Run The Platform

Run the following commands in two separate terminals:

1. Inside the `platform/backend` folder, run:

```console
$ mvn spring-boot:run
```

2. Inside the `platform/frontend` folder, run:

```console
$ npm start
```

## How To Run The eStore

Run the following commands in two separate terminals:

1. Inside the `eStore/backend` folder, run:

```console
$ python3 manage.py runserver
```

2. Inside the `eStore/frontend` folder, run:

```console
$ npm start
```

## How To Run The Tests

Run the following command:

- Inside the `platform/backend` folder, run:

```console
$ mvn test
```

## Bookmarks

- Link to the [eStore](http://34.175.95.229:3000/)
- Link to the [Platform](http://34.175.95.229:3001/)
- Link to the [Platform API Documentation](http://34.175.95.229:8080/swagger-ui/index.html#/)
- Link to the [GitHub Projects](https://github.com/users/leonardoflorido/projects/3)
- Link to the [SonarCloud](https://sonarcloud.io/summary/new_code?id=leonardoflorido_TQS-Project)
- Link to the [GitHub Actions](https://github.com/leonardoflorido/TQS-Project/actions)

Note:

- To view the API documentation, the backend must be running.

## Team

- DevOps - [Leonardo Flórido](https://github.com/leonardoflorido)
- Team Coordinator - [Gabriel Hall](https://github.com/GabrielHall02)
- Product Owner - [Tomás Almeida](https://github.com/TomasAlmeida8)
