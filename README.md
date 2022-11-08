# Panda is a library to work through HTTP/2.0

It uses Java 11 in package _java.net.http_ 

### Features
- Use of codecs to serialize/deserialize requests and responses, by default
it've been using Jackson

### Roadmap
- retry
- backoff
- cache
- circuit-breaker

### How to do requests
A simple GET
```
    private final PandaClient client = new PandaClient(new PandaRequest());
    private final Response = client.request("http://www.google.com");
```
A simple GET using timeout
```
    private final PandaClient client = new PandaClient(new PandaRequest(Duration.ofSeconds(10)));
    private final Response = client.request("http://www.google.com");
```
### 
A POST sample

```
    private final static PandaClient client = new PandaClient(new PandaRequest())
            .and()
            .decoder(new JacksonCodec());
    private final Response response = client.request("https://your-domain.com", HttpMethod.POST,
        client.decode(anyObject()),
        Map.of(
            "content-type", "application/json",
            "Accept-Encoding", "application/gzip",
            "api-key", "aa2884e7-0f45-48c6-973f-734bd08d146a"
        )
    );
```

---
## Mantainer
Ivan Rodrigues