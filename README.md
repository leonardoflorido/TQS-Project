# TQS Project

## Installation

Clone the repository:

```console
$ git clone https://github.com/leonardoflorido/TQS-Project.git
```

## How To Run (Docker)

Run the following command:

```console
$ docker-compose up
```

Note:

- This process may take a significant amount of time.
- If [Docker](https://www.docker.com/) is not installed, please install it or follow the steps below.

## Prerequisites

To run the project, the following software must be installed on the system:

- [Node.js](https://nodejs.org/en/) (v18 or higher)
- [Python](https://www.python.org/) (v3.7 or higher)
- [Java](https://www.oracle.com/java/) (v17)
- [Maven](https://maven.apache.org/) (v3.9.0 or higher)

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

## Bookmarks

- Link to the [eStore](http://localhost:3000/)
- Link to the [Platform](http://localhost:3001/)
- Link to the [Platform API Documentation](http://localhost:8080/swagger-ui/index.html#/)

Note:

- To view the API documentation, the backend must be running.
