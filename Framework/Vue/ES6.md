> # ES6 Grammar



* #### let

  * 중복 선언 불가능
  * ECMA6 부터 선언된 블럭에서만 사용할 수 있는 블록 변수가 제공
  * 호이스팅 대상에서 제외 : 선언 이후에만 사용 가능

* #### const

  * 선언할 때 초기화 하고 변경 불가 나머지 let과 같음

* #### property shorthand

  * ``` javascript
    const id = 'ssafy';
    name = '싸피';
    age = 3;
    
    const member = {
        id: id,
        name: name,
        age: age
    }
    // 이렇게 짧게 가능
    const member = {
        id,
        name,
        age
    }
    ```

* #### concise method

  * ``` javascript
    ...
    const member = {
        id: id,
        name: name,
        age: age
        info: function(){
            console.log('info');
        }
    }
    // 이렇게 가능
    const member = {
        id,
        name,
        age,
        info(){
            console.log('info');
        }
    }
    ```

* #### for ~ of

  * ``` javascript
    const data = [1, 10, 100];
    for(let element of data){
        console.log(element);
    }
    // in 은 index를 가져오는데 of는 데이터를 바로 가져옴
    // foreach같은 느낌
    ```

* #### template string

  * ` (백틱) 사용

  * jsp의 el과 문법 형식이 동일

  * 변수를 문자열에 합칠 때 편리함

  * multiline 지원

  * ``` javascript
    const name = "홍길동";
    const age = 22;
    console.log(`${name}의 나이는 ${age}세 입니다`)
    ```

* #### Arrow Function

  * function(param) {code} 형태의 축약
  * (param) => {코드}
  * 익명함수이므로 사용 시 변수에 담아서 사용

* #### Destructuring

  * 객체에 입력된 값을 개별적인 변수에 할당하는 간편 방식 제공

  * ``` javascript
    const member={
        id: 'aaa',
        name: 'bbb',
        age: 22
    }
    
    // 다양한 방식이 있지만 많이 쓰는 거
    // {} 안의 변수이름은 member의 프로퍼티 이름과 같아야 함
    let {id, name, age} = member;
    ```

  * 함수를 이용한 방법

  * ``` javascript
    function getMember(){
        return {
            id: 'aaa',
            name: 'bbb',
            age: 22,
        }
    }
    
    let {id, name, age} = getMember();
    ```

  * 함수의 매개변수로 사용

  * ``` javascript
    function showMember({id, name, age}){
       
    }
    ```

    