> # Vue Start
> > 200602_

* #### CDN

  * ``` html
    <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
    ```

  * ``` javascript
    // 객체 or 컴포넌트
    const vm = new Vue({
        // 옵션
    });
    ```

* #### 옵션

  > test02

  * EL

    * Vue를 적용할 DOM

    * HTML Element를 주는 경우는 거의 없음. 

        ``` javascript
        new Vue({
            el: "#app"
        });
        ```

  * Data

    * 객체 or 함수, Vue에서 사용되는 정보를 저장

      ``` javascript
      new Vue({
          el: '#app',
          data:{
              msg:'hello'
          }
      //    data(){
      //    	return{
      //            msg: 'hello'
      //        }
      //	}
      })
      ```

  * HTML

    * ``` html
      <div id="app">
          <h2>
              {{msg}}
          </h2>
      </div>
      ```

* #### 텍스트

  * 텍스트 보간법 {{ 속성명 }}

  * 데이터 바인딩의 기본 형태

  * v-once: 데이터 변경 시 업데이트 되지 않는 일회성 보간 수행

  * ``` html
    <span v-once>메시지 : {{msg}}</span>
    ```

* #### Raw HTML

  > test03

  * {{ 속성명 }}, v-text: 데이터 속성의 html을 escape 처리

  * v-html: 데이터 속성의 html을 파싱하여 처리

  * ``` Vue
    <div>{{msg}}</div>
    <div v-text="msg"></div>
    <div v-html="msg"></div>
    ```

* #### Javascript Expressions

  > test04
  
  * 데이터 바인딩 내에서  javascript 표현식 사용 가능
  
  * 하나의 단일 표현식만 포함될 수 있음
  
  * ``` Vue
    {{ number + 1}}
    {{ ok ? 'yes' : 'no'}}
    {{ message.split('-').reverse().join('') }}
    {{ if(ok) { return message } }} (X 여러 문장있는건 안됨)
    ```

* #### v-model

  * 양방향 바인딩
  * v-text, v-html : 단방향

* #### MVVM

  * 어딘가 REST 서버로 부터 온 data = Model
  * data가 없는 정적 html = View
  * Vue Root == View Model

