
# 2.1

## Embedded Jetty Server

-> Pom.xml:
    Adicionar dependencias:

```xml
    <dependency>
    <groupId>org.eclipse.jetty</groupId>
    <artifactId>jetty-server</artifactId>
    <version>9.2.15.v20160210</version>
</dependency>
<dependency>
    <groupId>org.eclipse.jetty</groupId>
    <artifactId>jetty-servlet</artifactId>
    <version>9.2.15.v20160210</version>
</dependency>

```

-> Criar classe: EmbeddedJettyServer.java

```java
package com.javacodegeeks.example;

import org.eclipse.jetty.server.Server;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EmbeddedJettyExample {

        public static void main(String[] args) throws Exception {
            Server server = new Server(8680);
            try {
        server.start();
        server.dumpStdErr();
            server.join();
        } catch (Exception e) {           
            e.printStackTrace();
        }  
        }
}
```

## URL NOTES:

http://127.0.0.1:8680/?msg=%22Hard%20workers%20welcome!%22

msg é um parametro porque está antecedida de ? e separada por &

no código java usamos este parametro da seguinte maneira:

```java
String user = request.getParameter("msg");
```

Para mostrar no browser usamos:

```java
response.getWriter().println("<h1>Hello "  + user.toString() + " </h1>");
```

