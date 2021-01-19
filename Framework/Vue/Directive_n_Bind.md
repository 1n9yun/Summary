> #  Vue Directive

* #### v-model

  * 양방향 바인딩 처리를 위해 사용(폼의 input, textarea)

  * ``` html
    <!-- msg는 Vue instance가 관리하는 data 중 하나 겠구나 -->
    <input type="text" v-model="msg">
    <input type="text" v-model.trim="msg">
    ```

* #### v-bind

  * 엘리먼트 속성 바인딩 처리를 위해서 사용

  * 약어로 ':'로 사용 가능

  * ``` vue
    new Vue({
    	el: '#app',
        data: {
            idVal: 'test',
            key: 'id'
        }
    })
    
    <div v-bind:id="idVal"></div>
    
    <!-- [key] -> id : 동적 속성 바인딩 -->
    <div v-bind:[key]="idVal"></div>
    <div :id="idVal"></div>
    <div :[key]="idVal"></div>
    ```
  
* class 바인딩
  
  * ``` html
    <div v-bind:class="classes">
        
    </div>
    <div v-bind:class="active: isActive">
        
    </div>
    ```
  ```
  
    ``` javascript
    new Vue({
        ...
        data:{
            isActive: false,
            classes:{
                // class="a b ..." 
                'a' : true,
                'b' : false,
                ...
            }
        }
    })
  ```
  
* #### v-show

  * 조건에 따라 엘리먼트를 화면에 표시

  * style 속성의 display를 변경

  * boolean

    ``` vue
    <button v-show="isExist">검색</button>
    
    // v-show 가 false일 경우
    <button style="display: none">검색</button>
    ```

* #### v-if, v-else-if, v-else

  * 조건에 맞는 경우 화면에 요소들을 랜더링

  * 조건이 false이면 전혀 나타나지 않음 `<!-- -->`

  * ``` vue
    <span v-if="age < 10"></span>
    <span v-else-if="age < 20"></span>
    <span v_elft></span>
    ```

* #### v-for

  * 배열이나 객체의 반복에 사용

  * v-for="요소변수이름 in 배열" 

  * v-for="요소변수이름, 인덱스) in 배열"

  * ``` vue
    <ul>
        <li v-for="name in regions">
            지역 : {{name}}
        </li>
    </ul>
    ```

  * ``` vue
    <h2>범위지정(4)</h2>
    <span v-for="i in 4">
        {{i}}
    </span>
    
    <h2>문자열 배열</h2>
    <ul>
        <li v-for="name in regions">
            {{name}}
        </li>
    </ul>
    
    <h2>문자열 배열 - 위치</h2>
    <ul>
        <li v-for="(name, i) in regions">
            번호 : {{i}}, 지역 : {{name}}
        </li>
    </ul>
    
    <h2>객체배열 - 내용출력</h2>
    <ul>
        <li v-for="region in ssafy">
            지역 : {{region.name}}, {{region.count}}개반
        </li>
    </ul>
    <h2>객체배열 - 위치,내용 출력</h2>
    <ul>
        <li v-for="region, index in ssafy">
            번호 : {{index + 1}}, 지역 : {{region.name}}, {{region.count}}개반
        </li>
    </ul>
    <h2>객체배열 - 위치,내용 출력</h2>
    <ul>
        <!-- 묶인게 보기 좋아 그냥.. 가독성 -->
        <li v-for="(region, index) in ssafy">
            번호 : {{index + 1}}, 지역 : {{region.name}}, {{region.count}}개반
        </li>
    </ul>
    
    <label>반수 : </label>
    <input type="number" v-model.number="count">
    <ul>
        <li v-for="(region, index) in ssafy" v-if="region.count === count">
            지역 : {{region.name}}, {{region.count}}개반
        </li>
    </ul>
    ```

* #### template

  * 여러 개의 태그들을 묶어서 처리해야 한다면 template 이용하면 편리

  * v-if, v-for, component 등과 많이 사용

  * ``` vue
    <template v-for="(region, index) in ssafy">
    	<h2>
            지역 : {{region.name}}
        </h2>
    	<h3>
            {{region.count}}개 반
        </h3>
    </template>
    ```

* #### v-clock

  * Vue인스턴스가 준비될 때까지 mustache 바인딩을 숨기는데 사용

  * [v-clock] {display: none}와 같은 css 규직과 함께 사용

  * vue 인스턴스가 준비되면 v-clock은 제거됨

  * ``` vue 
    <style>
        [v-cloak] {
            display: none;
        }
    </style>
    
    <div v-clock>
        {{msg}}
    </div>
    ```

* #### v-on:event
