# Panda HTTP tinny client

Your goal is let a library with less dependencies as possible, with default __RETRY__,__BACKOFF__,
__REQUESTS__ and so on. To do this we're using the Java 11 in package _java.net.http_. With
a library with a little of dependencies it probably can be placed into your project without 
treat problems with dependencies.
If you wanna change some behavior basically you should implement an interface.

### Features
- Use of codecs to serialize/deserialize requests and responses, by default it've been using Jackson library
- Retry police(if you can exists one with 3 retries)


### How to do requests

To build a __PandaClient__, you can use a builder like:
```
PandaClient client = PandaClientBuilder
    .of(new PandaRequest())
    .and()
    .retry(DefaultRetry.RETRY).build();
```
or instantiate the __PandaRequest__ :
``` 
PandaClientProxy pandaClientProxy = new PandaClientProxy(this.request, retryable, encoder, decoder);
```

### Following some examples

### GET

A simple GET
```
    private final PandaClient client = PandaClient client = PandaClientBuilder.of(new PandaRequest()).build();
    private final Response = client.request("http://www.google.com");
```

A simple GET using timeout
```
    private final PandaClient client = PandaClient client = PandaClientBuilder.of(new PandaRequest(Duration.ofSeconds(3)).build();
    private final Response = client.request("http://www.google.com");
```

### POST

## 
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

The examples above also it's much appeared with other **HTTP VERBS** the only difference is the
verb, example, instead put a __HttpMethod.POST__ you can put __HttpMethod.PUT__

### Roadmap
- backoff
- cache
- circuit-breaker
