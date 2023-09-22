# IES_108713

### Create Project

``` 
$ mvn archetype:generate -DgroupId=ua.deti.ies -DartifactId=lab1 -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false 
```

### Sincronizar o maven
```
$ mvn clean
$ mvn clean package
$ mvn install
```

### Correr o projeto
```
$ mvn package
$ mvn compile exec:java -Dexec.mainClass="ua.deti.ies.WeatherStarter.java"
```

### Simple pom
```
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <!-- define the context and the project id -->
    <groupId>ua.labs</groupId>
    <artifactId>ProjectX</artifactId>

    <version>1.0-SNAPSHOT</version>
    <packaging>jar</packaging>
    
    <properties>
        <!-- useful properties for any project -->
        <maven.compiler.source>11</maven.compiler.source>
        <maven.compiler.target>11</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>

</project>

```


### Add developers

```
<developers>
    <developer>
        <id>jason</id>
        <name>Jason Van Zyl</name>
        <email>jason@maven.org</email>
        <url>http://www.sonatype.com</url>
        <organization>Sonatype</organization>
        <organizationUrl>http://www.sonatype.com</organizationUrl>
        <roles>
            <role>developer</role>
        </roles>
        <timezone>-6</timezone>
    </developer>
</developers>
```

### Add properties (encoding and java version)

```
<properties>
<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
<maven.compiler.source>17</maven.compiler.source>
<maven.compiler.target>17</maven.compiler.target>

</properties>
```

### LOgging Facade
```
<!-- https://mvnrepository.com/artifact/org.slf4j/slf4j-api -->
<dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>slf4j-api</artifactId>
    <version>2.0.9</version>
</dependency>
```

# Docker

# Lab 1.3 Notes

## Some useful docker commands:
Check docker version
```
$ docker --version
```

Test Docker installation
```
$ docker run hello-world
```

List the Docker images on computer
```
$ docker image ls
```

List the existing Docker containers
```
$ docker ps --all
```

## Dockerfile
A **Dockerfile** describes how to assemble a private filesystem for a container, and can also contain some metadata describing how to run a container based on this image.

## Building your own images
Having come source code and a Dockerfile, one can build it's own image, and make sure the containers launched from it work as expected.

Being in the directory with the Dockerfile:
```
$ docker build -t postgresbasic .
```

This creates our own costum PostgreSQL image, using our Dockerfile.

## Running your own images
```
$ docker run --name pg-docker -d -p 5432:5432 -e PGDATA=/tmp -v $HOME/docker/volumes/postgres:/var/lib/postgresql/data postgresbasic
```

There are a couple of flags in the command above:
* **-p** or **--publis** asks Docker to forward traffic incoming on the host’s port 5432 to the container’s port 5432. Containers have their own private set of ports, so if you want to reach one from the network, you have to forward traffic to it in this way. Otherwise, firewall rules will prevent all network traffic from reaching your container, as a default security posture.
* **-d** or **--detach** asks Docker to run this container in the background.
* **--name** specifies a name with which you can refer to your container in subsequent commands, in this case postgresbasic.

While running, you can visit your application in a browser at localhost:5432.

Since this is a PostgreSQL instance, you can also connect to it with pqsl, with:
```
psql -h localhost
```

To delete your container:
```
$ docker rm --force postgresbasic
```

The **--force** option stops a running container, so it can be removed. If you stop the container running with *$ docker stop postgresbasic* first, then you do not need to use **--force** to remove it.

## Docker Compose
Docker Compose is a tool that was developed to help define and share multi-container applications. With Compose, we can create a YAML file to define the services and with a single command, can spin everything up or tear it all down.

Full tutorial: https://docs.docker.com/compose/gettingstarted/

Start docker-compose:
```
$ docker-compose up
```

Stop docker-compose:
```
$ docker-compose stop
```


# Create Executable Jar

## Apache Maven
```
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-dependency-plugin</artifactId>
    <executions>
        <execution>
            <id>copy-dependencies</id>
            <phase>prepare-package</phase>
            <goals>
                <goal>copy-dependencies</goal>
            </goals>
            <configuration>
                <outputDirectory>
                    ${project.build.directory}/libs
                </outputDirectory>
            </configuration>
        </execution>
    </executions>
</plugin>
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-jar-plugin</artifactId>
    <configuration>
        <archive>
            <manifest>
                <addClasspath>true</addClasspath>
                <classpathPrefix>libs/</classpathPrefix>
                <mainClass>
                    com.baeldung.executable.ExecutableMavenJar
                </mainClass>
            </manifest>
        </archive>
    </configuration>
</plugin>
```

## Apache Maven Plugin
```
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-shade-plugin</artifactId>
    <executions>
        <execution>
            <goals>
                <goal>shade</goal>
            </goals>
            <configuration>
                <shadedArtifactAttached>true</shadedArtifactAttached>
                <transformers>
                    <transformer implementation=
                      "org.apache.maven.plugins.shade.resource.ManifestResourceTransformer">
                        <mainClass>com.baeldung.executable.ExecutableMavenJar</mainClass>
                </transformer>
            </transformers>
        </configuration>
        </execution>
    </executions>
</plugin>
```