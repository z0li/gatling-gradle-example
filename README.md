# gatling-gradle-example

A sample Gatling project built with Gradle.

Tested with:
- Gatling v3.1.1 (using Scale v1.12.8)
- Gradle v5.4.1

## Usage

Build:
```
gradle clean build
```

Run with Gradle:
```
gradle run --args="-s io.github.z0li.ExampleSimulation"
```

Package as zip:
```
gradle distZip
```

Run the distributed package:
```
./bin/gatling-gradle-example -s io.github.z0li.ExampleSimulation
```