# FortiSandbox JSON-RPC Client

This repository provides a JSON-RPC client for interacting with FortiSandbox. The client allows you to make API calls to the FortiSandbox using JSON-RPC protocol.

## Prerequisites

Before using the JSON-RPC client, ensure that you have the following prerequisites in place:

- FortiSandbox instance: You should have access to a FortiSandbox instance with the necessary credentials.
- Java Development Kit (JDK): Make sure you have JDK installed on your system.
- Maven: Ensure that you have Maven installed on your system.

## Getting Started

### Clone the repository

```shell
git clone https://github.com/your-username/fortisandbox-jsonrpc-client.git
```

### Build the project
Find FortiSandboxClientTest.java and change all the paths to your testing files in related test cases. Then run below code to build and run the test cases.

```shell
cd fortisandbox-jsonrpc-client
mvn clean install
```

### Use in your project

- Build this project first
- Add target/fortisandbox-1.0-SNAPSHOT.jar in your project
