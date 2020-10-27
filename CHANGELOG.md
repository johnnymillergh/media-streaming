# [1.2.2](https://github.com/johnnymillergh/media-streaming/compare/v1.2.1...1.2.2) (2020-10-27)


### Features

* **MediaInfo:** support get media info ([23249f1](https://github.com/johnnymillergh/media-streaming/commit/23249f14f25390a3bf582a4f5dfd75a7201a194a))
* **Webflux:** migrate to annotation based Webflux ([65b2dbf](https://github.com/johnnymillergh/media-streaming/commit/65b2dbf3636be215ff83e8f0d1ce8a254444693c))


### Performance Improvements

* **Bash:** abstract functions into common.sh ([5e3fc6f](https://github.com/johnnymillergh/media-streaming/commit/5e3fc6f8d3e78d4b0e253010309b00731424ce85))
* **Concurrency:** improve Java object lock for FileWatcher::terminate() ([fe41844](https://github.com/johnnymillergh/media-streaming/commit/fe418446c2440cb41f76a3d36b7e8f82e95ac54d))
* **FileWatcher:** capture ClosedWatchServiceException ([cf8f4ca](https://github.com/johnnymillergh/media-streaming/commit/cf8f4caea0e92ab9a68470023d9e37fed16f1962))
* **FileWatcher:** close WatchService synchronously ([2e28596](https://github.com/johnnymillergh/media-streaming/commit/2e285961e1f136b860f1b1b8bbb53707243e2ead))
* **FileWatcher:** gracefully destroy FileWatcher ([f1b9720](https://github.com/johnnymillergh/media-streaming/commit/f1b972091434903526d5b5e4807c96ddf5487acb))
* **FileWatcher:** recursively monitor directory ([2195010](https://github.com/johnnymillergh/media-streaming/commit/2195010cecfa9a3f21ea015fee736c299a9d0d85))
* **Travis:** beautify bash command ([5750e06](https://github.com/johnnymillergh/media-streaming/commit/5750e06e4624146c112ce6ebc2e983d2c1b8220a))
* **Travis:** colorize bash command ([b6bd88d](https://github.com/johnnymillergh/media-streaming/commit/b6bd88d8904f265f9696a3111af6375583036ff5))
* **Travis:** display timestamp ([037e9b1](https://github.com/johnnymillergh/media-streaming/commit/037e9b1ce663cc54080a98fa8c9365ed9a5e51ab))
* **Travis:** make bash message bold ([73b7d97](https://github.com/johnnymillergh/media-streaming/commit/73b7d9798d15b3168b8b70b83d0fa3dd0b33ad4d))


### Reverts

* **Travis:** uncomment 'set -e' ([b4da625](https://github.com/johnnymillergh/media-streaming/commit/b4da62552d98d641887e7b4028b85cc56b5a7df9))


### BREAKING CHANGES

* **Concurrency:** improve Java object lock for FileWatcher::terminate()
* **FileWatcher:** recursively monitor directory; fixed file not deleted
under sub directory problem
* **Webflux:** migrate to annotation based Webflux



# [1.2.0](https://github.com/johnnymillergh/media-streaming/compare/v1.1.1...1.2.0) (2020-10-22)


### Build System

* **POM:** update POM (except groupId and package) for sonatype ([4c40ad9](https://github.com/johnnymillergh/media-streaming/commit/4c40ad96c7a51acd6c9f317f9c33847aabf62410))
* **Project:** change `groupId` and package name ([cbb793d](https://github.com/johnnymillergh/media-streaming/commit/cbb793dc81281745c06b73a7e7e7cdcea388e7b7))
* **Travis:** update Travis CI process ([fde74b4](https://github.com/johnnymillergh/media-streaming/commit/fde74b476593747db879b4db2e3ea545458a6f69))


### Performance Improvements

* **media-streaming:** release resources when shutdown ([44a54cc](https://github.com/johnnymillergh/media-streaming/commit/44a54cc4cd5b3f859c7cfb451ddf9db3a87dffca))
* **sample-app:** enable graceful shutdown ([58f423c](https://github.com/johnnymillergh/media-streaming/commit/58f423c5aa414dc87a965ad7fe6934a17c02cf60))


### BREAKING CHANGES

* **Travis:** deploy artifact based on Travis CI
* **Project:** change `groupId` and package name
* **POM:** update POM (except groupId and package) for sonatype



## 1.1.1 (2020-10-20)

* fix(README): correct README.md ([4d568b0](https://github.com/johnnymillergh/media-streaming/commit/4d568b0))
* chore: update issue templates ([3c56370](https://github.com/johnnymillergh/media-streaming/commit/3c56370))
* build(GitHub Actions): don't skip tests ([8254e1b](https://github.com/johnnymillergh/media-streaming/commit/8254e1b))



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



