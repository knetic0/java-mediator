# The mediator pattern implementing on Java Spring Boot

A lightweight, annotation‐driven Mediator pattern implementation for Spring Boot that decouples request objects from their handlers and centralizes the dispatch logic.

## Features

- Annotation-based configuration: Enable the mediator with a single annotation.

- Generic request/response support: Send commands and queries with typed responses.

- Automatic handler registration: All your RequestHandler beans are discovered and wired up.

- Spring Boot starter–style integration: Plug into any existing Spring Boot app.

## Getting Started

#### Maven
```
<dependency>
  <groupId>com.mehmetsolak</groupId>
  <artifactId>spring-boot-mediator</artifactId>
  <version>1.0.0</version>
</dependency>
```

#### Gradle

```
implementation 'com.mehmetsolak:spring-boot-mediator:1.0.0'
```

#### Enable the Mediator
Add `@EnableMediator` to your main Spring Boot application class:

![Enable-Mediator](/docs/enable-mediator.png)

#### Defining a Command
Create a class that implements your `Request<TResponse>` interface. For example, a CarCreateCommand that returns a CarResponse:

![Car-Command](/docs/car-command.png)

#### Implementing a Command Handler
Implement `RequestHandler<CommandType, ResponseType>` and annotate it as a Spring bean:

![Car-Command-Handler](/docs/car-command-handler.png)

#### Sending Commands from a Controller
Inject Mediator into your controller and `send()` your command:

![Car-Controller](/docs/car-controller.png)

#### Result

![Result](/docs/result.png)

## Contributing
Contributions are welcome! Feel free to open issues or submit pull requests for improvements.


### Contact

For questions or suggestions, please open an issue or contact the maintainer.

- **Email**: solakmehmet02@gmail.com
- **Github**: [Github](https://github.com/knetic0)
- **LinkedIn**: [Mehmet Solak](https://www.linkedin.com/in/mehmetsolak0/)