# cheerHawks

## Run Docker

```
$ docker run --rm -itd -p 9000:9000 cheerhawks_web:latest sbt run
```

and you can connect the app using `curl localhost:9000`.

but when it build first, it takes many times...:(

you can know build status using `docker logs <container id>`
