# Bean

## Scope

대부분의 경우 Singleton scope를 사용하겠지만..

### Singleton

싱글톤으로 사용

### Prototype

사용될 때마다 새로운 객체로 사용

#### Request

#### Session

#### WebSocket

#### ...

### Prototype의 Bean이 Singleton Bean을 사용

문제 없음

### Singleton Bean이 Prototype Bean을 사용하면?

Prototype Bean이 업데이트가 안됨

#### Prototype Bean을 업데이트하려면

#### scoped-proxy

proxy pattern을 사용해 bean을 proxy로 감싸 업데이트가 될 여지를 만든다.

#### object-provider

ObjectProvider\<Proto>

#### provider(표준)