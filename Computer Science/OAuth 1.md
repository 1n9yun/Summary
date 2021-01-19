# OAuth 1.0

> Reference
>
> https://tools.ietf.org/html/rfc5849

> Description
>
> RFC5849를 읽으며 번/의역했으며 불필요해보이는 정보는 제외했을 수 있으므로 원문도 확인!



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



