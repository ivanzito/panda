# Panda is a library to work through HTTP/2.0

## Some features that will have

- retry
- circuit-breaker
- backoff
- codecs

### Important!

Panda it`s in wokring in progress. So, the list below shows the roadmap
- backoff
- circuit-breaker

### How to use
```
    private final Request request = new DefaultRequest(HttpMethod.GET, Map.of("Accept", List.of("*/*")));
    private final Client client = ClientBuilder.of()
            .request(request)
            .build();
Response response = client.request("http://www.google.com");
```
### 

---
## Mantainer
Ivan Rodrigues