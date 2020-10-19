> # 협업 툴
>
> Trello, notion 등등 무료, jira 유료, 기업용?



git은 코드 자체 공유?

jira는 코드의 내용이나 그런거



https://hyunyulhenry.github.io/quant_cookbook/%EA%B8%88%EC%9C%B5-%EB%8D%B0%EC%9D%B4%ED%84%B0-%EC%88%98%EC%A7%91%ED%95%98%EA%B8%B0-%EC%8B%AC%ED%99%94.html
여기는 내가 처음 보고 따라한건데 안전하게 백테스팅 시작하기 좋은데야

https://wikidocs.net/3088
여기는 파이썬 기본부터 해서 키움 api쓰는덴데 유투브에 프로그램 동산 이사람이 쓴거 같애

그냥 api 요청하는건데 설명서가 부실해서 쌩으로 하기 어려운것 뿐이야



그리고 gui는 해도 되는데 나는 필요없을거 같애서 안만들었



1. KOA 스튜디오와 python 이용해서 자동매매 프로그램 만들기

2. 증권 관련 데이터를 크롤링해서 web 만들기
3. 실전 자동매매 돌리면서 우리만의 경험 데이터를 쌓기
4. 효율적인 자동매매를 위해 알고리즘 전략 세우기

그래프 분석 등등 여러 기법 이용해서

인공지능 학습을 이용한다거나

다른 다양한 데이터를 이용해서 판단한다거나 등등

# trello !! gogogoo



> # 서버는??



분석 도구 - Jupyter, Zeppelin
Aws S3 
Aws Athena
Aws Dynamo DB - > NoSql
Aws lambda



Lambda(콜백?)

DynamoDB -> Athena -> S3 (Parquet Partitioning)

DynamoDB - NoSQL, 저장용, 여기에 데이터 다 저장하고

Athena - DynamoDB에 SQL써서 써치 가능

S3 - Athena에서 써치한거 여기에 Parquet 형식으로 Partitioning(폴더 느낌) 저장 활용



API Gateway(HTTP 요청?) -> Lambda (함수 작성돼있음, dao라고 생각하셈)



Jupyter, Zeppelin 분석해서 이쁘게 



써보는데 중점