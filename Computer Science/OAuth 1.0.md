# OAuth 1.0

> Reference
>
> https://tools.ietf.org/html/rfc5849

> Description
>
> RFC5849를 읽으며 번/의역했으며 불필요해보이는 정보는 제외했을 수 있으므로 원문도 확인!
>
> 또한 서투른 영어 실력으로 번역체와 직독직해가 난무함!



## Abstract

OAuth는 클라이언트에게 서버 리소스 소유자를 대신하여 서버 리소스에 액세스할 방법을 제공합니다.

> It also provides a process for end-users to authorize third-party access to their server resources without sharing their credentials (typically, a username and password pair), using user-agent redirections.
>
> 유저-에이전트 리디렉션을 통해 사용자의 인증 정보를 공유하지 않고 서드파티 리소스에 액세스할 권한을 부여하는 과정도 제공한다는 의미같은데
>
> 정확한 해석이라는 생각이 들면 한글로 수정!



## Status of this Memo

이 문서는 ietf의 문서이며 표준은 아니고 정보 제공 목적으로 작성되었다고 한다.



## Copyright Notice

> Copyright (c) 2010 IETF Trust and the persons identified as the document authors.  All rights reserved.
>
> This document is subject to BCP 78 and the IETF Trust's Legal Provisions Relating to IETF Documents (http://trustee.ietf.org/license-info) in effect on the date of publication of this document.  Please review these documents carefully, as they describe your rights and restrictions with respect to this document.  Code Components extracted from this document must include Simplified BSD License text as described in Section 4.e of the Trust Legal Provisions and are provided without warranty as described in the Simplified BSD License.



## 1. Introduction

OAuth 프로토콜은 2007년 10월에 1.0버전이 stabilize 되었으며 2009년에 수정(Revision A)되었다.

> OAuth 1.0a (Revision A) - <http://oauth.net/core/1.0a>

이 문서는 OAuth Core 1.0 Revision A의 정보를 제공하며 그 이후로 보고된 erreta와 수정 내용을 제공한다.

전통적인 client-server 인증 모델에서, 클라이언트는 서버에서 호스팅하는 리소스에 액세스하기 위해 credentials를 사용한다.

분산 웹 서비스 및 클라우드 사용이 증가함에 따라 서드파티 어플리케이션은 이러한 server-hosted 리소스에 액세스해야 한다.

OAuth는 전통적인 client-server 인증 모델에 세 번째 역할인 리소스 소유자를 도입한다.

OAuth 모델에서 클라이언트(리소스 소유자는 아니지만 대신 작동하는)는 리소스 소유자가 제어하지만 서버에서 호스팅하는 리소스에 대한 액세스을 요청한다.

게다가, OAuth를 통해 리소스 소유자의 권한뿐만 아니라 요청하는 클라이언트의 신원도 확인할 수 있다.



OAuth는 클라이언트에게 서버 리소스 소유자를 대신하여 서버 리소스에 액세스할 방법을 제공합니다.
또한 유저-에이전트 리디렉션을 통해 사용자의 인증 정보를 공유하지 않고 서드파티 리소스에 액세스할 수 있는 권한을 부여하는 과정도 제공한다.

예를 들어 웹 사용자 (자원 소유자)는 사용자 이름과 암호를 인쇄 서비스와 공유하지 않고 사진 공유 서비스 (서버)에 저장된 개인 사진에 대한 인쇄 서비스 (클라이언트) 액세스 권한을 부여 할 수 있습니다. 

> Instead, she authenticates directly with the photo sharing service which issues the printing service delegation-specific credentials.
> 
> 무슨 말일까



클라이언트가 리소스에 접근하기 위해서는 일단 리소스 소유자로부터 허가를 얻어야 한다. 이 허가는 토큰이나 matching shared-secret의 형태로 표현된다. 

토큰의 목적은 리소스 소유자가 자격증명을 클라이언트와 공유할 필요를 없애기 위함이다. 

리소스 소유자 credentials와는 다르게 토큰은 제한된 scope, limited lifetime, revoked independetly와 함께 발행된다.


> This specification consists of two parts. The first part defines a redirection-based user-agent process for end-users to authorize client access to their resources, by authenticating directly with the server and provisioning tokens to the client for use with the authentication method.  The second part defines a method for making authenticated HTTP [RFC2616] requests using two sets of credentials, one identifying the client making the request, and a second identifying the resource owner on whose behalf the request is being made.
>
> The use of OAuth with any transport protocol other than [RFC2616] is undefined.

라고도 한다.



### 1.1 Terminology

**client**

- OAuth-authenticated requests를 할 수 있는 HTTP 클라이언트

**server**

* OAuth-authenticated requests를 수락할 수 있는 HTTP 서버

**protected resource**

* OAuth-authenticated requests를 통해 서버에서 얻을 수 있는 접근 제한 리소스

**resource owner**

* credentials를 사용하여 서버에 인증함으로써 protected resources에 접근하고 제어할 수  있는 Entity 

**credentials**

* shared secret과 매칭되는 고유한 식별자 쌍
* OAuth는 client, temporary, token의 세 가지 credentials 클래스를 정의한다.
* 각각 요청을 만드는 클라이언트, 권한부여 요청, 액세스 권한을 식별하고 인증하는데 사용한다.

**token**

* server로부터 발행되는 고유한 식별자이며 client가 인증 요청과 resource owner(client에 의해 인증을 얻거나 인증을 요청받은)를 연관시키기(?) 위해 사용한다.

* 클라이언트가 토큰의 소유권을 설정하는데 사용되는 shared secret과 resource owner를 나타내는 권한을 가지고 있다.

The original community specification는 다른 terminology를 사용했으며 아래와 같이 매칭된다.

* Consumer : client
* Service Provider: server
* User: resource owner
* Comsumer Key and Secret : client credentials
* Request Token and Secret : temporary credentials
* Access Token and Secret : token credentials



### 1.2 Example



### 1.3 Notational Conventions

> [RFC2119](https://tools.ietf.org/html/rfc2119)
>
> **MUST** (Required, Shall) 은 절대적으로 필요한 specification
>
> **MUST NOT**(Shall Not) 은 절대적으로 금지되는 specification
>
> **SHOULD**(Recommended) 는 특정 상황에서 특정 항목을 무시할 타당한 이유가 있을 수 있지만 다른 방법을 선택하기 전에 전체 의미를 이해하고 신중하게 검토해야 함을 의미
>
> **SHOULD NOT**(Not Recommended) 은 특정 동작이 수용가능하거나 심지어 유용할 때 특정 상황에서 타당한 이유가 있을 수 있지만 이 Label로 설명된 모든 행동을 구현하기 이전에 전체 의미를 이하고 신중하게 검토해야 함을 의미
>
> **MAY**(Optional) 는 말그대로 옵션인 항목이며 옵션을 포함하지 않는 구현체는 옵션을 포함하는 구현체와의 상호운용을 기능상의 축소가 있더라도 반드시 준비해야 한다. 그 반대의 경우에도 다른 구현과 상호 운용되도록 준비해야 한다. (물론 옵션이 제공하는 기능에 대해서는 제외)



## 2 Redirection-Based Authorization

OAuth는 리소스 소유자가 클라이언트에게 부여한 권한을 나타내는 토큰을 사용한다.

일반적으로 토큰 credentials는 리소스 소유자의 신원을 인증한 이후에 리소스 소유자의 요청에 의해 서버로부터 발행된다.

이 section에서는 서버가 token credentials를 쉽게 공급할 수 있는 방법 중 하나를 정의한다.

HTTP Redirection과 리소스 소유자의 user-agent를 이용한 방법이다.

이 redirection-based authorization method는 아래 세 단계를 포함한다.

* 클라이언트는 a set of temporary credentials를 서버로부터 얻는다(identifier, shared-secret). temporary credentials는 권한 부여 프로세스 전체에서 액세스 요청을 식별한는데에 사용된다.
* 리소스 소유자는 서버가 클라이언트의 액세스 요청을 허용할 권한을 부여한다.
* 클라이언트는 token credentials(리소스 소유자의 보호된 리소스에 접근을 가능하게 할)를 서버에 요청하기 위해서 임시 자격증명을 사용한다.

서버는 token credentials를 얻기 위해 임시 자격증명을 한번 사용한 후에 (MUST) revoke해야 한다.  임시 자격증명이 제한된 lifetime을 가지도록 하는 것이 recommended.

서버는 token credentials가 클라이언트에 발행된 이후에 리소스 소유자가 revoke 가능하도록 해야한다(SHOULD).

클라이언트가 이런 steps를 수행하기 위해서는 서버는 아래 세 개의 endpoints URI들을 제공해야 한다.

* Temporary Credential Request
  * 클라이언트가 임시 자격 증명(section 2.1)을 얻는데 사용될 endpoint
* Resource Owner Authorization
  * 리소스 소유자가 인증(section 2.2)을 위해 redirect될 endpoint.
* Token Request
  * 클라이언트가 token credentials를 temporary credentials를 사용하여 요청할 때 사용하는 endpoint.

이 세 개의 URI는 서버에 의해 제공되며 query component(section 3)를 포함할 수 있다(MAY). 포함하고 있다면 query는 URI에 추가되는 프로토콜 파라미터들과의 충돌을 피하기 위해 "oauth_"로 시작하는 어떤 파라미터도 포함하면 안된다.

이 세 가지 URI를 알리고 문서화하는 방법은 이 문서의 범위를 벗어난다.

클라이언트는 이 스펙에서 정의하지 않은 다른 서버의 생성값과 토큰의 크기에 대한 가정을 피해야 한다.

게다가 프로토콜 파라미터는 전송할 때 필요한 인코딩이 필요한 값을 포함할 수 있다. 클라이언트와 서버는 값의 가능한 범위에 대한 가정을 피해야 한다.

### 2.1 Temporary Credentials

클라이언트는 Temporary Credentials Request Endpoint로 인증된 POST 요청을 보냄으로써 서버로부터 임시 자격증명셋을 얻는다.

클라이언트는 요청에 아래 (REQUIRED) parameter를 추가하여 요청을 생성한다.

* oauth_callback 
  * 리소스 소유자가 인증 과정(section 2.2)를 완료하면 서버가 리소스 소유자를 다시 redirection할 absolute URI
  * 만약 클라이언트가 callback을 받는 것이 불가능하거나 callback URI가 다른 방법을 통해 만들어져있으면 파라미터 값은 (out-of-band 구성임을 나타내기 위해)"oob"(대소문자 구분)으로 설정되어야 한다.(MUST)

서버는 추가적인 파라미터를 지정할 수 있다.(MAY)

요청을 생성할 때, 클라이언트는 오직 클라이언트 credentials만을 사용하여 인증한다. 클라이언트는 요청에서 빈 "oauth_token" 프로토콜 파라미터를 생략할 수 있으며 이 때 token secret value로 빈 스트링을 사용해야 한다.(MUST)

요청의 응답으로 평문 credential이 전송되기 때문에 서버는 TLS나 Secure Socket Layer(SSL)과 같은 전송 계층 메커니즘(또는 그와 동등한 보안 채널)을 필요로 한다.(MUST)

#### 2.1.1 Example

클라이언트는 아래와 같은 HTTPS 요청을 만든다.

```
POST /request_temp_credentials HTTP/1.1
Host: server.example.com
Authorization: OAuth realm="Example",
	oauth_consumer_key="jd83jd92dhsh93js",
	oauth_signature_method="PLAINTEXT",
	oauth_callback="http%3A%2F%2Fclient.example.net%2Fcb%3Fx%3D1",
	oauth_signature="ja893SD9%26"
```

서버는 이 요청을 검증(section 3.2)해야 하며(MUST) 유효하다면 클라이언트에 임시 자격증명셋(identifier, shared-secret 형태)를 응답한다. 임시 자격증명은 HTTP 응답 body에 `application/x-www-form-urlencoded` content type을 사용하여 코드 200으로 포함된다.(OK)

응답은 아래 REQUIRED 파라미터를 포함한다.

```
oauth_token
	임시 자격증명 identifier
oauth_token_secret
	임시 자격증명 shared-secret
oauth_callback_confirmed
	true로 존재해야 한다. 파라미터는 프로토콜의 이전 버전과 구별지어 사용한다.
```

파라미터 이름이 'token'이 포함되어 있음에도 불구하고 이 credentials는 토큰 credentials가 아님에 주의하라. 그러나 다음 두 step에서 유사한 방식으로 token credentials에 사용된다.

example

```
HTTP/1.1 200 OK
Content-Type: application/x-www-form-urlencoded

oauth_token=hdk48Djdsa&oauth_token_secret=xyz4992k83j47x0b&
oauth_callback_confirmed=true
```

## 2.2 Resource Owner Authorization

클라이언트가 서버에 token credentials를 요청하기 전에 요청을 인증하기 위해 사용자를 보내야(?) 한다(MUST)

클라이언트는 아래 REQUIRED 쿼리 파라미터를 리소스 소유자 인증 endpoint URI에 추가함으로써 요청 URI를 생성한다.

```
oauth_token
	section 2.1에서 얻은 "oauth_token"파라미터의 값인 임시 자격증명 identifier.
	서버는 이 파라미터를 OPTIONAL로 설정할 수 있으며(MAY) 이 때 서버는 다른 방법을 	통해 identifier를 나타낼 수 있는 방법을 리소스 소유자에게 제공해야 한다.
```

서버는 추가적인 파라미터를 지정할 수 있다.(MAY)

클라이언트는 HTTP redirection response를 이용하거나 리소스 소유자의 유저 agent를 통해 가능한 방법으로 구성된 URI로 리소스 소유자를 보낸다. 요청은 HTTP GET 메소드를 사용해야 한다.(MUST)

예를들어 클라이언트는 다음 HTTPS 요청을 수행하기 위해 리소스 소유자의 유저 agent를 redirect한다.

```
GET /authorize_access?oauth_token=hdk48Djdsa HTTP/1.1
Host: server.example.com
```

요청이 TLS/SSL과 같은 보안 채널을 사용하는지를 포함하여 서버가 인증 요청을 다루는 방식에 대해서는 이 스펙의 범위 밖이다. 하지만 서버는 리소스 소유자의 신원을 먼저 파악해야 한다.(MUST)(근데 SSL은 필수라고하지 않았었나??)

> When asking the resource owner to authorize the requested access, the server SHOULD present to the resource owner information about the client requesting access based on the association of the temporary credentials with the client identity.  When displaying any such information, the server SHOULD indicate if the information has been verified.
>
> 해석이 좀 어렵다.

리소스 소유자로부터 인증 결과를 받은 다음, 서버는 `oauth_callback` 파라미터나 다른 방법으로 제공받은 경우(?) 리소스 소유자를 callback URI로 리다이렉트한다.

접근을 승인하는 리소스 소유자가 프로세스를 완료하기 위해 클라이언트로 돌아가는 리소스 소유자와 같은지 확인하기 위해서는, 서버는 반드시 verification code를 생성해야 한다 : (프로세스를 완료하기 위해서 필요하며 리소스 소유자를 통해 클라이언트에게 전달된 추측 불가능한 값). 서버는 콜백 URI 쿼리 컴포넌트에 다음 REQUIRED 파라미터를 추가하여 요청 URI를 만든다.

```
oauth_token
	클라이언트로부터 받은 임시 자격 증명 identifier
oauth_verifier
	verification code
```

만약 callback URI가 이미 쿼리 컴포넌트에 포함되었다면 서버는 존재하는 쿼리의 끝에 `OAuth parameter`를 추가해야 한다(MUST).

예를 들어, 서버는 리소스 소유자의 user-agent를 다음 HTTP 요청을 만들기 위해 리다이렉트 한다.

```
GET /cb?x=1&oauth_token=hdk48Djdsa&oauth_verifier=473f82d3 HTTP/1.1
Host: client.example.net
```

만약 클라이언트가 callback URI를 제공하지 않는다면, 서버는 verification code를 나타내야 하며(SHOULD), 리소스 소유자에게 클라이언트에게 인증이 완료되었음을 수동으로 알리도록 해야한다.

만약 서버가 클라이언트가 제한된 장치에서 실행되고 있음을 알고 있다면, verifier 값이 수동입력에 적합한지 확인해야 한다(SHOULD).

## 2.3 Token Credentials

