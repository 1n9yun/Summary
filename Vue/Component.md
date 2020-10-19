> # Component
>
> > 200604

    1. Vue의 가장 강력한 기능 중 하나
    2. HTML 엘리먼트를 확장하여 재사용 가능한 코드를 캡슐화
    3. Vue 인스턴스의 옵션을 대부분 사용
    4. 라이프사이클 훅 사용 가능
    5. 전역 컴포넌트와 지역 컴포넌트
* #### 전역 컴포넌트 등록

  > test01.html

  * ```html
    <div id="app1">
        <my-comp></my-comp>
    </div>
    ```

    ```javascript
    Vue.component(tagName, options)
    
    Vue.component("my-comp", {
        template: '<div>전역 컴포넌트</div>'
    });
    
    new Vue({
        el: '#app1',
    })
    
    // Vue 공식 권장 컴포넌트 이름 : 케밥 표기법(소문자, -)
    // MyComp로 등록하더라도 사용할 땐 <my-comp>로 사용해야함
    ```




* #### 지역 컴포넌트 등록

  * ```html
    <div id="app1">
        <my-comp></my-comp>
    </div>
    ```

    ``` javascript
    let comp = {
        template: '<div>지역컴포넌트</div'
    }
    
    new Vue({
        el: '#app1',
        // 다른 인스턴스에서 사용 불가능
        components:{
            'my-comp': comp
        }
    })
    ```

* #### 컴포넌트 템플릿

  > test03.html

  * ``` html
    <template id="my-temp">
        <div>로컬</div>
    </template>
    ```

    ``` javascript
    Vue.component('my-comp',{
        template: '#my-temp',
        
        // data는 함수로 
        data(){
        	return{
                msg: 'hello component'
            }
    	}
    })
    ```

* #### 컴포넌트 data 공유 문제

  > test04.html

  * ``` javascript
    let data = { count:0 };
    Vue.component('CountView'),{
        data(){
            // 재사용하는 countview 컴포넌트에게 같은 data 참조를 줌
            // 따라서 data가 바뀌면 재사용된 모든 컴포넌트의 data가 바뀜
            return data;
            // 이렇게 해야 함
            return{
                count: 0,
                
            }
        }
    }
    ```

* #### 컴포넌트 간 통신
  * 부모에서 자식 : props
    * ``` javascript
      Vue.component('child',{
          props: ['message', ...],
          template: '<span>{{message}}</span>'
      })
      ```
    
      ``` html
      <child message="안녕하세요!!"></child>
      <div>
          <!-- 동적 props -->
          <child v-bind:message="parentMsg"></child>
      </div>
      ```
    
    * 객체 props : 객체의 모든 속성을 props로 전달할 경우 인자 없이 v-bind
    
      > test07.html
    
      ``` html
      todo : {text:'learn vue', iscomplete: false}
      
      <todo-item v-bind="todo"></todo-item>
      ```

  * 자식에서 부모 : event

    * 사용자 정의 이벤트
    
      * ``` javascript
        // 이벤트 발생
        vm.$emit("이벤트명", [...파라미터]);
        vm.$emit("speed", 100);
        // 이벤트 수신
        vm.on("이벤트명", 콜백함수);
        vm.on("speed", function(speed){});
        ```

  * 비 부모 자식 간 통신

    > test10.html

    * 비어있는 Vue 인스턴스 객체를 이벤트 버스로 사용
    
    * 복잡해질 경우 상태관리 라이브러리 사용 권장(Vuex)
    
    * ``` javascript
      const bus = new Vue();
      bus.$emit('event', param);
      bus.$on('event', function(param){});
      ```

* #### Module

  * 정의 및 사용
  * 가져오기

    * import 모듈명 from 모듈위치(파일위치)
  * 내보내기

    * export

      * import { 모듈 이름1, 이름2, 이름3 } from 모듈위치
    * export default

      * import cal(임의지정) from 모듈위치