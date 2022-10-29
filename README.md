# Panda is a library to work through HTTP/2.0

It uses Java 11 in package _java.net.http_ 

### Features
- Use of codecs to serialize/deserialize requests and responses, by default
it've been using Jackson

### Roadmap
 - backoff
- timeout
- circuit-breaker

### How to do requests
A simple GET
```
    private Response response = new PandaRequest().call("http://www.google.com");
```
A simple GET using timeout
```
    private Response response = new PandaRequest(Duration.ofSeconds(10))
        .call("http://www.google.com");
```
### 

---
## Mantainer
Ivan Rodrigues