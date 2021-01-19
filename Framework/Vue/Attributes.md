> # Vue 속성
>
> * 200603_vue_live
> * vue 인스턴스는 생성 관련된 데이터 및 메서드의 정의가 가능
> * 메서드 안에서 데이터를 this.데이터이름 으로 접근 

* #### Usage

  * ``` html
    <div id="app">
        <div>
            데이터 : {{name}}
      </div>
        <div>
         	메서드 : {{func1()}}     
        </div>
    </div>
    ```
  * ``` html
    <script>
        const root = new Vue({
            el: '#app',
            data:{
            	name: '홍길동'
    	    },
            methods: {
              func1(){
                    return this.name;
                },
            },
    	})
        // 가능
        root.func1();
    </script>
    ```
  
* #### Filters

  * filter를 이용하여 표현식에 새로운 결과 형식을 적용
  * 중괄호 보간법 ({{ }}) 또는 v-bind속성에서 사용이 가능

  * 전역 필터 : new Vue() 한 모든 인스턴스에서 사용 가능

    * ``` javascript
      Vue.filter(
          'count', (val) => {
              if (val.length = 0){
                  return;
              }
              return `${val} : ${val.length}자`;
          }
      )
      ```

  * 지역 필터 : 정의한 인스턴스에서 사용 가능

    * ``` javascript
      new Vue({
          el: '#app',
          filters: {
              count(val){
                  return `${val} : ${val.length}자`;
              },
              count_alter(val, alternative){
          		if(val.length == 0) return alternative;
                  return `${val} : ${val.length}자`;
      		}
          }
      })
      ```

  * 사용 ( 전역보다 지역이 우선 )

    * ``` html
      <h3>{{ msg | count }}</h3>
      <h3>{{ msg | count_alter("이걸 대신 표시")</h3>
      ```

* #### Computed

  * 특정 데이터의 변경사항을 실시간으로 처리할 수 있음

  * 캐싱을 이용하여 데이터의 변경이 없을 경우 캐싱된 데이터를 반환

  * Setter와 Getter를 직접 지정할 수 있음

  * 작성은 메서드 형태로 작성하지만 Vue에서 프록시 처리하여 프로퍼티처럼 사용

  * Usage

    * ``` html
      <p>원본 : "{{message}}"</p>
      <p>{{reversedMessage}}</p>
      ```

      ``` javascript
      var root = new Vue({
          el:,
          data: {
      		message: '하이'
      	},
          computed:{
      		// message의 수정을 실시간으로 계속 지켜보고있음
          	reversedMessage: function(){
      			// message가 바뀐게 없다면 reversedMessage호출 시 이전 결과를 캐싱한 값을 리턴함
      			console.log('run');
          		return this.message.split('').reverse().join('');
      		}
      		// 이렇게 쓸 일은 많이 없음
      		withGetSet: {
                  get: function(){
                      return this.a + 1;
                  },
                  set: function(v){
                      this.a = v;
                  }
              }
      	}
      })
      
      // getter 호출
      console.log(root.withGetSet);
      // setter 호출
      root.withGetSet = 3;
      ```
    
  * (참고) 원리?

    * ``` javascript
      const root = {};
      let model = 'data';
      const app = document.querySelector("#app");
      Object.defineProperty(root, "model", {
          get(){
              return model;
          },
          set(val){
              model = val;
              app.innerHTML = model;
          }
      });
      ```

* #### Watch

  * Vue 인스턴스의 특정 프로퍼티가 변경될 때 실행할 콜백 함수 설정

  * ``` javascript
    var root = new Vue({
        el:,
        data:{
        	message: 'hello',
        	reversedMessage: '',
    	},
        watch:{
        	message: function(newVal, oldVal){
    			this.reversedMessage = newVal.split('').resverse().join('');    	
    		}
    	}
    })
    ```

* #### Event

  * Dom 이벤트를 청취하기 위해 v-on 디렉티브 사용
  * Ex
  	``` html
    <div id="app">
    	<!-- v-on:click == @click -->    
        <button v-on:click="counter += 1">
            add 1
        </button>
        {{counter}}번 클릭했음
        <button v-on:click="greet">
            greet
    	</button>
        <button v-on:click="greet1('VueJS')">
            greet1
    	</button>
        <button v-on:click="greet2($event, 'VueJS')">
            greet2
    	</button>
    </div>
	  ```
  * 인라인 이벤트 핸들링
    ``` javascript
    new Vue({
    	el:,
    	data: {
        	counter : 0
  		}
  	})
    ```
    
  * 메서드를 이용한 이벤트 핸들링
    ``` javascript
    new Vue({
        ...
        methods:{
        greet: function(event){
            alert('hello' + this.name + '!');
            // 발생한 이벤트 객체 (여기선 버튼)
            console.dir(event.target);
            }
        }
    })
    ```
  
  * 인라인 메서드 핸들링
  
    ``` javascript
    new Vue({
        ...
        methods:{
            greet1: function(msg){
                // 이벤트 핸들러로 넘어와서 event를 그대로 사용 가능
    			console.dir(event.target);           
            },
            grret2: function(e, msg){
                // e는 명시적으로 써주는 것. 참고만 해
                // 둘 다 사용 가능
    			console.dir(e.target);
                console.dir(event.target);
            }
        }
    })
    ```
  
* #### ref, $refs

  * Vue 인스턴스 객체의 자식 컴포넌트 또는 DOM 엘리먼트 요소

  * ``` html
    <div id="app">
        아이디 : <input type="text" v-model="id" ref="id">
        <button @click="search">아이디 중복 체크</button>
    </div>
    ```

    ``` javascript
    new Vue({
        ...
    	methods: {
            search(){
                if(this.id.length == 0){
                    alert("아이디를 입력하시오!");
                    this.$refs.id.focus();
                    console.dir(this.$refs.id);
                    return;
                }
            }
        }
    })
    ```

* #### 이벤트 수식어와 키 수식어

  > test14.html

  * v-on 이벤트의 수식어
    * .stop
    * .prevent - a 태그의 이동 막기
    * .once
      * v-on:click.stop
      * v-on:submit.prevent
  * 키보드 이벤트 수식어
    * .13
    * .enter
    * .delete
      * v-on:keyup.enter

* #### 폼 입력 바인딩

  * v-model은 입력 요소에 대해 특정 속성과 이벤트를 사용함
    * text와 textarea: value, input 이벤트 사용

      > test16.html

      * ``` html
        <!-- 기본적으로 value에 input event -->
        <input v-model="id">
        <!-- lazy 수식어 붙이면 change event에 반응 -->
        <input v-model.lazy="id">
        ```

    * checkbox, radio: checked, change 이벤트 사용
      * 하나의 체크박스일 경우 boolean값을 표현

        > test17.html

      * 여러개 일 경우 같은 data이름으로 하면 value배열로  표현

        > test18.html

      * Radio는 선택된 하나의 value로 표현

        > test19.html

    * select: value, change 이벤트 사용, 선택된 value로 표현

      > test20.html

