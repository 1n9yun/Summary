# Spring MVC

![MVC model](https://mdn.mozillademos.org/files/16042/model-view-controller-light-blue.png)

## MVC

### Model

어떤 데이터를 보여줄 지 정의 해준다.

Model은 '데이터'를 정의하는 부분으로, 데이터를 조회하는 것 뿐만 아니라 삽입, 변경, 삭제등의 작업을 수행하고 그 실제적인 과정이 일어나는 비즈니스 로직 전반적인 부분을 정의

### View

데이터의 보여주는 방식을 정의

View는 Client들이 실제로 보는 화면을 표시해주는 부분으로, 보여줄 데이터나 텍스트 데이터를 표시해주거나 Client들의 입력을 받기위한 입력 폼들을 정의해 줌

### Controller

Client의 Request에 따른 Response를 정의 해주며 모델 및 뷰를 업데이트하는 로직을 포함하고 있다.

Controller는 Client의 요청을 받아들여 각 요청에따라 데이터를 설정해주거나 화면을 출력해주는 등의 전체적인 연결을 관리해주는 응답을 정의해 줍니다.

### Process in Spring

