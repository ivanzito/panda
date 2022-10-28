# Panda is a library to work through HTTP/2.0

### Some features that will have

Panda it`s in working in progress. So, the list below shows the roadmap in sequence
- retry
- codecs
- backoff
- circuit-breaker

### How to do requests
```
    private final Request request = new DefaultRequest(HttpMethod.GET, Map.of("Accept", List.of("*/*")));
    private final Client client = ClientBuilder.of()
            .request(request)
            .build();
    private final Response response = client.request("http://www.google.com");
```
### 

---
## Mantainer
Ivan Rodrigues