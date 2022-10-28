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
private final RequestBuilder.newRequest()
        .method(HttpMethod.GET)
        .headers(Map.of("Accept", List.of("*/*")))
        .build();
Response response = request.doRequest("http://www.google.com");
```
### 

---
## Mantainer
Ivan Rodrigues