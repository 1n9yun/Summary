> # Vuex
>
> * Vue.js 애플리케이션에 대한 상태관리 패턴 + 라이브러리
> * 애플리케이션 모든 컴포넌트들의 중앙 저장소 역할(데이터 관리)
> * 부모 자식 단계가 많이 복잡해 진다면 데이터의 전달하는 부분이 매우 복잡해짐
> * 애플리케이션이 여러 구성 요소로 구성되고 더 커지는 경우 데이터를 공유하는 문제가 발생
> * https://vuex.vuejs.org/kr/

* #### 상태관리패턴

  * 단방향 흐름
  * ![img](https://vuex.vuejs.org/vuex.png "핵심 컨셉")
  * Actions는 비동기에 적합

* #### 설치

  > vue create에서 vuex를 체크 안했다면?

  * cdn
    * `<script src='https://unpkg.com/vuex'></script>`
  * npm
    * npm install vuex --save



* #### Vuex 저장소 개념

  * state : 단일 상태 트리를 사용, 애플리케이션마다 하나의 저장소를 관리
  * Getters : Vue 인스턴스의 Computed같은 역할, State를 기반으로 계산(Computed)
  * Mutations : State의 상태를 변경하는 유일한 방법(methods)
  * Actions : 상태를 변이시키는 대신 액션으로 변이에 대한 커밋 처리(비동기 methods)

* #### Usage

  * 모듈 시스템과 함께 사용 시 Vue.use()를 통해 Vuex 설정

    * ``` javascript
      import Vue form 'vue'
      import Vuex from 'vuex'
      Vue.use(Vuex) // Vue 객체에 Vuex를 설정
      ```

  * store.js

    * ``` javascript
      import Vue from 'vue';
      import Vuex from 'vuex';
      
      Vue.use(Vuex);
      
      export default new Vuex.Store({
          state: {
              count: 0,
          },
      });
      ```

  * main.js

    * ``` javascript
      // 저장소가 관리할 내용 정의
      import Vue from 'vue';
      import App from './App.vue';
      import store from './store/store';
      Vue.config.productionTip = false;
      
      new Vue({
          render: (h) => h(App),
          store,
      }).$mount('#app');
      ```

* #### 저장소(Store) - State

  * 저장소에서 data 속성의 역할

  * 애플리케이셔넹서 공유해야 할 데이터를 관리

  * State에 접근하는 방식

  * `this.$store.state.데이터이름`

  * ``` javascript
    // 자식1
    computed: {
        total(){
            return this.$store.state.count;
        }
    }
    // 자식2
    addCount: function(){
        this.$store.state.count++;
    }
    ```

* #### 저장소 - Getters

  * 컴포넌트가 Vuex의 state를 직접 접근하는 코드가 중복된다면??

  * Store의 state를 참조하는 Getters를 활용

  * 등록

    * ``` javascript
      export default new Vuex.Store({
          state: {
              count: 0,
          },
          getters: {
              countMsg(state){
                  state.count += 1;
              },
      	}
})
      
      // 사용
      export default{
          computed:{
              total(){
                  return this.$store.state.count;
              },
              countMsg(){
                  return this.$store.getters.countMsg;
              }
          }
      }
      ```
      
    * ![vuex_flow](../img/vuex_flow.png)

* #### 저장소 - mapGetters

  * getters를 조금더 간단하게 호출

  * babel 관련 에러 발생 시 해결

    * ...mapGetters 관련

    * es6 spread operation ( ...~  ) 관련 에러 발생 시 해결

      * npm install --save core-js
      * Updated file babel.config.js:
      * ``` json
          presets: [
              [
                  '@vue/app',
                  {
                      useBuiltIns: 'entry',
                  },
              ],
          ],
        ```
      

  * Usage

    * ``` javascript
      // store
      export defalut new Vuex.Store({
          getters:{
              countMsg(state){
                  return this.$store.getters.countMsg;
              },
              msg1(state){
                  
              },
              msg2(state){
                  
              },
          },
      })
      
      // 자식2
      import { mapGetters } from 'vuex';
      export default{
          computed:{
              ...mapGetters(['countMsg', 'msg1', 'msg2', 'msg3']),
          }
      }
      ```

      ``` html
      <h2>{{ countMsg }}</h2>
      <h2>{{ msg1 }}</h2>
      ...
      ```

* #### 저장소 - Mutations

  * State값을 변경하기 위해 사용

  * 각 컴포넌트에서 State의 값을 직접 변경하는 것은 권장하지 않는 방식

  * State의 값의 추적을 위해 동기적 기능에 사용

  * Mutations는 직접 호출이 불가능하고 store.commit('정의된 이름')으로 호출

  * ![mutations](../img/vuex_mutations.png)

  * store

    ``` javascript
    export default new Vuex.Store({
        state:{
            count: 0,
        },
        mutations:{
            addOne(state){
                state.count += 1;
            },
            addCount(state, payload){
                state.count += payload;
            },
            addObjCount(state, payload){
                state.count += payload.num;
            },
        },
    })
    ```
    
  * Subject.vue
  
    ``` vue
    <template>
    	<div>
            <button v-on:click="addCount">{{ title }} - {{ count }}</button>
            <button v-on:click="addTenCount">{{ title }} - {{ count }}</button>
            <button v-on:click="addObjCount">{{ title }} - {{ count }}</button>
      </div>
    </template>
    
    <script>
        export default{
    		data(){
                return{
                    count: 0,
                }
            },
            methods:{
                addCount: function(){
                    this.count += 1;
                    this.$store.commit('addOne');
                },
                addTenCount: function(){
                    this.count += 10;
                    this.$store.commit('addCount', 10);
                },
                addObjCount: function(){
                    let num = Math.round(Math.random() * 100);
                    this.count += num;
                    this.$store.commit('addObjCount', { num });
                }
            }
        }
    </script>
    ```
  
  * 저장소 - mapMutations
  
    * Subject.vue
    
      ``` vue
      <script>
          import { mapMutations } from 'vuex';
          export default{
              methods:{
                  ...mapMutations({
                      addMOne: 'addOne',
      	            addMTenCount: 'addTenCount',
      	            addMObjCount: 'addObjCount',
                  }),
      			addCount: function(){
                      this.count += 1;
                      this.addMOne();
                  },
                  addTenCount: function(){
                      this.count += 10;
                      this.addMTenCount(10);
                  },
                  addObjCount: function(){
                      let num = 22;
                      this.count += num;
                      this.addMObjCount({ num });
                  }
              }
          }
      </script>
      ```
  
* #### 저장소 - Actions

  * 비동기 작업의 결과를 적용하려고 할 때 사용

  * Mutations는 상태 관리를 위해 동기적으로 처리하고 비동기적인 처리는 Actions가 담당

  * Actions는 비동기 로직의 처리가 종료되면 Mutations를 호출

  * ![vuex_actions](../img/vuex_actions.png)

  * 등록

    * ``` javascript
      export default new Vuex.Store({
          ...
          actions:{
              asyncAddOne(context){
                  setTimeout(() => {
                      context.commit('addOne');
                  }, 2000);
              }
          },
          ...
      })
      ```

    * ``` javascript
      // subject.vue
      export default{
          ...
          methods:{
              ...
              asyncCount(){
              	this.$store.dispatch('asyncAddOne');
      		},
          },
      }
      ```

  * mapActions

    * ``` javascript
      export default new Vuex.Store({
          ...
          actions:{
              asyncAddOne(context){
                  ...
              },
              ...
          }
      })
      ```

    * ``` javascript
      export default {
          ...
          
      	...mapActions(['asyncAddOne']),
          
          ...
      }
      ```

    

