# 1.1.0 (2020-10-20)

* refactor(media-streaming): rename class `MediaStreamingBootstrap` ([0ca94d0](https://github.com/johnnymillergh/media-streaming/commit/0ca94d0))
* feat(FileWatcher): observe file changes on file system ([26ca2eb](https://github.com/johnnymillergh/media-streaming/commit/26ca2eb))
* feat(Reactive): delete file observation ([e9ea525](https://github.com/johnnymillergh/media-streaming/commit/e9ea525))
* perf(Exception): capture NoSuchFileException if the dir is wrong ([bd7c695](https://github.com/johnnymillergh/media-streaming/commit/bd7c695))
* perf(Log): adjust log level ([f5d3264](https://github.com/johnnymillergh/media-streaming/commit/f5d3264))
* perf(Log): print debug logs; enable log switch ([59f4789](https://github.com/johnnymillergh/media-streaming/commit/59f4789))
* perf(media-streaming): assemble all necessary beans in class `MediaStreamingAutoConfiguration` ([d122439](https://github.com/johnnymillergh/media-streaming/commit/d122439))
* perf(Route): reduce code warning ([a6530e1](https://github.com/johnnymillergh/media-streaming/commit/a6530e1))
* perf(WebFlux): reactive VIDEO_CACHE data ([05ec18b](https://github.com/johnnymillergh/media-streaming/commit/05ec18b))
* chore(starter): delete useless resource ([7c00b2b](https://github.com/johnnymillergh/media-streaming/commit/7c00b2b))
* build(GitHub Actions): update trigger actions ([20b69cd](https://github.com/johnnymillergh/media-streaming/commit/20b69cd))
* build(POM): update version to 1.1.0-SNAPSHOT ([4e69455](https://github.com/johnnymillergh/media-streaming/commit/4e69455))
* build(Travis): don't skip tests ([ce6c6b9](https://github.com/johnnymillergh/media-streaming/commit/ce6c6b9))
* test(sample-app): add unit tests for API ([888eb2c](https://github.com/johnnymillergh/media-streaming/commit/888eb2c))
* test(sample-app): correct unit test for API ([1a367a6](https://github.com/johnnymillergh/media-streaming/commit/1a367a6))
* fix(media-streaming): correct route bean factory ([bd9874e](https://github.com/johnnymillergh/media-streaming/commit/bd9874e))
* docs: update README.md ([f5f2c84](https://github.com/johnnymillergh/media-streaming/commit/f5f2c84))
* docs(sample-app): add comment for application.yml ([6d1336d](https://github.com/johnnymillergh/media-streaming/commit/6d1336d))


### BREAKING CHANGE

* observe file changes on file system
* print debug logs; enable log switch


## 1.0.1 (2020-10-19)

* fix(POM): update and fix conflict version number ([cc2a201](https://github.com/johnnymillergh/media-streaming/commit/cc2a201))



# 1.0.0 (2020-10-19)


### Bug Fixes

* add a public class ([c04008e](https://github.com/johnnymillergh/media-streaming/commit/c04008e472f85db9b3e19ef357d5622518d61e6e))
* add method comments ([0da2555](https://github.com/johnnymillergh/media-streaming/commit/0da25556a5f2a15cd56a29d8bafd5f8f7b1fa5b6))
* delete unknown tag `date` ([8297f99](https://github.com/johnnymillergh/media-streaming/commit/8297f99461cb65d82107e3a292cce7daad466c24))
* **Travis:** add file permission before install ([70496a9](https://github.com/johnnymillergh/media-streaming/commit/70496a93d587fc7885809b9c556ca8225a098ae5))


### Build System

* **Project:** initial project ([6a1d485](https://github.com/johnnymillergh/media-streaming/commit/6a1d485af78e802e4ccc8a3c147fdcba72516be7))


### Features

* **Exception:** add class `MediaStreamingExceptionHandler` ([c682c3a](https://github.com/johnnymillergh/media-streaming/commit/c682c3a6bb8c613c12cb646a8b95129d2a197f28))
* **Exception:** add customized exception class ([4cc6667](https://github.com/johnnymillergh/media-streaming/commit/4cc6667c1424e52e94622177d60c974ea9efeda2))
* **media-streaming:** implement basic media streaming ability ([2ff9cd0](https://github.com/johnnymillergh/media-streaming/commit/2ff9cd0e00bb3c8d3fe8f104a0d6145d1d2fbfcf))
* **WebFlux:** integrate and configure WebFlux ([4b14363](https://github.com/johnnymillergh/media-streaming/commit/4b143635f395ec69b60675524b21b91c9284b6cd))


### BREAKING CHANGES

* **media-streaming:** implement basic media streaming ability
* **Project:** built the basic structure for
media-streaming-spring-boot-starter



