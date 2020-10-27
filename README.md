<div style="position: relative; text-align: center;">
  <img src="https://raw.githubusercontent.com/johnnymillergh/MaterialLibrary/master/media-streaming/media-streaming-social-image.png" alt="Media Streaming Social Image"/>
  <!--<div style="position: absolute; top: 34%; left: 34%; transform: translate(-50%, -50%); color: rgb(7, 156, 58); font-size: 60px; font-weight: bolder;">
    Media Streaming<br>
    Spring Boot Starter
  </div>-->
</div>

[![GitHub release](https://img.shields.io/github/release/johnnymillergh/media-streaming.svg)](https://github.com/johnnymillergh/media-streaming/releases)
[![Build Status](https://travis-ci.com/johnnymillergh/media-streaming.svg?branch=master)](https://travis-ci.com/johnnymillergh/media-streaming)
[![Media Streaming](https://maven-badges.herokuapp.com/maven-central/com.github.johnnymillergh.boot/media-streaming-spring-boot-starter/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.johnnymillergh.boot/media-streaming-spring-boot-starter/)
[![GitHub issues](https://img.shields.io/github/issues/johnnymillergh/media-streaming)](https://github.com/johnnymillergh/media-streaming/issues)
[![GitHub forks](https://img.shields.io/github/forks/johnnymillergh/media-streaming)](https://github.com/johnnymillergh/media-streaming/network)
[![GitHub stars](https://img.shields.io/github/stars/johnnymillergh/media-streaming)](https://github.com/johnnymillergh/media-streaming)
[![GitHub license](https://img.shields.io/github/license/johnnymillergh/media-streaming)](https://github.com/johnnymillergh/media-streaming/blob/master/LICENSE)
[![GitHub code size in bytes](https://img.shields.io/github/languages/code-size/johnnymillergh/media-streaming.svg?style=popout)](https://github.com/johnnymillergh/media-streaming)
[![GitHub repo size](https://img.shields.io/github/repo-size/johnnymillergh/media-streaming.svg)](https://github.com/johnnymillergh/media-streaming)
[![Twitter](https://img.shields.io/twitter/url/https/github.com/johnnymillergh/media-streaming?style=social)](https://twitter.com/intent/tweet?text=Wow:&url=https%3A%2F%2Fgithub.com%2Fjohnnymillergh%2Fmedia-streaming)

# Media Streaming

**Media Streaming**, a Spring Boot Starter project, which makes media streaming easier in your Spring Boot based project.

## Features

Here is the highlights of **Media Streaming**:

1. Written in Java 11. Inherited from the most modern and newest Spring frameworks:

   `org.springframework.boot:spring-boot-starter-parent` - [![Spring Boot](https://maven-badges.herokuapp.com/maven-central/org.springframework.boot/spring-boot-starter-parent/badge.svg)](https://maven-badges.herokuapp.com/maven-central/org.springframework.boot/spring-boot-starter-parent/)

2. Media streaming over HTTP. (Basic media streaming ability was completed, more and more features is coming soon)

3. …

## Installation

The easiest way is to install the library via [Nexus Repository Manager](https://oss.sonatype.org/#nexus-search;gav~com.github.johnnymillergh.boot~media-streaming-spring-boot-starter~~~).

```xml
<dependency>
  <groupId>com.github.johnnymillergh.boot</groupId>
  <artifactId>media-streaming-spring-boot-starter</artifactId>
  <version>1.2.1</version>
</dependency>
```

Alternatively, download it from the [releases page](https://github.com/johnnymillergh/media-streaming/releases).

## Usage

1. Clone or download this project.

   ```shell
   $ git clone https://github.com/johnnymillergh/media-streaming.git
   ```

2. Build with the newest IntelliJ IDEA.

3. Click the green triangle to Run.

## Useful Commands

### Maven

1. Set project version:

   ```shell
   mvn versions:set -DgenerateBackupPoms=false -f pom.xml
   ```

2. Build project:

   ```shell
   mvn clean validate compile -f pom.xml
   ```

### Conventional Changelog CLI

1. Install global dependencies (optional if installed):

   ```shell
   npm install -g conventional-changelog-cli
   ```

2. This will *not* overwrite any previous changelogs. The above generates a changelog based on commits since the last semver tag that matches the pattern of "Feature", "Fix", "Performance Improvement" or "Breaking Changes".

   ```shell
   conventional-changelog -p angular -i CHANGELOG.md -s
   ```

3. If this is your first time using this tool and you want to generate all previous changelogs, you could do:

   ```
   conventional-changelog -p angular -i CHANGELOG.md -s -r 0
   ```

## CI (Continuous Integration)

- [Travis CI](https://travis-ci.com/github/johnnymillergh/media-streaming) is for deploying SNAPSHOT and RELEASE on Nexus Central Repository.
- [GitHub Actions](https://github.com/johnnymillergh/media-streaming/actions) is for checking dependency updates and tests.

## Maintainers

[@johnnymillergh](https://github.com/johnnymillergh).

## Contributing

Feel free to dive in! [Open an issue](https://github.com/johnnymillergh/media-streaming/issues/new).

### Contributors

This project exists thanks to all the people who contribute. 

- Johnny Miller [[@johnnymillergh](https://github.com/johnnymillergh)]
- …


### Sponsors

Support this project by becoming a sponsor. Your logo will show up here with a link to your website. [[Become a sponsor](https://become-a-sponsor.org)]

## License

[Apache License](https://github.com/johnnymillergh/media-streaming/blob/master/LICENSE) © Johnny Miller

2020 - Present


