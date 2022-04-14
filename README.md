# CSV Normalizer

### How to build the project

This is a Java 11, Spring Framework, Gradle project written in Kotlin. 

To build it, run the following command at the project root directory:

```shell
./gradlew bootJar
```

If the above produces errors, you might need to specify the Java HOME directory with:
```shell
./gradlew -Dorg.gradle.java.home=<JAVA_11_HOME_DIR> bootJar
```


### How to run this project or command line

This project produces a jar. Please run a command following this pattern from the project root folder:

```shell
<JAVA_11_HOME_DIR>/bin/java -jar build/libs/csv-normalizer-0.0.1-SNAPSHOT.jar < <path_to_input_file>/sample.csv > output.csv
```

### Troubleshooting

If you have any problems building or running this project, please let me know and I'll be happy to assist.
