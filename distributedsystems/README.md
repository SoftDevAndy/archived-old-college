# Distributed Systems Vigenere Breaker

A college distributed-systems project that separates a Vigenere-cipher cracking service from a client web application. The client submits jobs, worker threads process requests, and the service scores candidate keys using quadgram data.

## Requirements

- Windows with Eclipse or another Java web-project IDE.
- JDK 8; the original project used JRE 1.8.0_65.
- Apache Tomcat 8.0.
- The project's Java EE, EAR, and web application libraries configured in the IDE.

## Run on Windows

Import the `Client` and `Service` folders as the two project components in Eclipse. Configure the client web project to run on Apache Tomcat 8.0 and confirm the project targets Java 8. Start the service before submitting a request from the client page.

The client web page is `Client/WebContent/index.jsp`. The service uses `Service/quadgrams.txt` and `Service/WarAndPeace-Tolstoy.txt` as its analysis data.

## Example

The sample encrypted text:

```text
UHTDUEIEJSAPSFPNUEYRMIDSUOOPNOYDURLEFWZCLIYRFNNCZPETPN
```

should decrypt to:

```text
THISTEXTISPERFECTENGLISHTODEMONSTRATEWORKINGENCRYPTION
```

## Components

### Client

- `CrackerHandler` — Web request handling and job coordination.
- `MessageHandler` — Thread pool, request queue, and result queue.
- `MessageRequest` — Job, key, and message data.
- `WorkerThread` — Performs the delegated decryption work.
- `VigenereBreaker` — Remote-service interface.

### Service

- `KeyEnumerator` — Scores keys and searches for a likely decryption.
- `Quadgram` — Loads and stores quadgram frequencies.
- `VigenerBreakerImpl` — Service implementation and binding entry point.
- `Vigenere` — Encryption helper.
- `VigenereBreaker` — Remote-service interface.
