> # Router
>
> * 라우팅 - 웹페이지간의 이동 방법
> * Vue.js의 공식 라우터
> * 라우터는 컴포넌트와 매핑
> * Vue를 이용한 SPA을 제작할 때 유용
> * url에 따라 컴포넌트를 연결하고 설정된 컴포넌트를 보여준다.

* #### Installation

  * CDN 

    * ``` html 
      <script src="https://unpkg.com/vue/dist/vue.js"></script>
      <script src="https://unpkg.com/vue-router/dist/vue-router.js"></script>
      ```

  * NPM

    - npm install vue-router

* #### 연결

  * 'routes' 옵션과 함께 router 인스턴스 생성

    * ``` javascript
      const Main = {
          template: ``,
      };
      const Login = ...
      
      const router = new VueRouter({
          routes:[
              {path: '/', component: Main},
              {path: '/login', component: Login}
          ]
      })
      ```

* #### vue-router 이동 및 렌더링

  * 네비게이션을 위해 router-link 컴포넌트를 사용

  * 속성은 'to' prop을 이용

  * 기본적으로 `<router-link>` 는 `<a>`태그로 렌더링

  * ``` html
    <router-link to="/">메인 페이지 이동</router-link>
    ```

  * 현재 라우트에 맞는 컴포넌트가 렌더링 됩니다.

  * `<router-view></router-view>`

* #### $router, $route

  * $router : 모든 route 정보를 가지고 있는 router 객체

  * $route : 현재 route의 정보

  * ``` javascript
    라우터 설정
    {
        path: '/board/:no',
        component: BoardView,
    }
    ```

  * 

* #### 이름을 가지는 라우트

  * 라우트에 연결하거나 탐색을 수행할 때 이름이 있는 라우트를 사용

  * Router 인스턴스를 생성하는 동안 routes 옵션에 지정

  * 경로가 길고 복잡해질 때 ?

  * ``` javascript
    const router = new VueRouter({
        routes: [{
            path: '/board/:no',
            name: 'boardview',
            component: BoardView
        }]
    })
    ```

    ``` html
    <router-link:to="{name: 'boardview', params: {no: 2}}"></router-link:to>
    
    <a @click="$router.push({ name: 'boardview', params: {no: 2}});"
    ```

* #### 프로그래밍 방식 라우터

  * `<router-link>`를 사용하여 선언적 네비게이션용 anchor 태그를 만드는 것외에도 라우터의 인스턴스 메소드를 사용하여 프로그래밍으로 수행

  * ``` javascript
    // 왜 route가 아니고 router냐? 집어 넣는다는게 아니라 찾아서 이동한다고 생각해보면, 전체를 가져와서 찾아봐야하기 때문에
    $router.push('home');
    $router.push({path:'home'});
    $router.push({name:'user', params:{userId:123}});
    
    // 쿼리
    $router.push({path:'register', query:{plan:'private'}});
    ```

* #### 중첩된 라우트

  * 앱 UI는 일반적으로 여러 단계로 중첩 된 컴포넌트 구조임

  * URL의 세그먼트가 중첩 된 컴포넌트의 특정 구조와 일치하는 것을 활용

  * ``` javascript
    const router = new VueRouter({
        routes:[{
            path: '/board',
            component: Board,
            
            // '/board'로 들어오면 /board/list로 리다이렉트 하도록
            redirect: '/board/list',
            children:[ // Board컴포넌트의 내부 <router-view>에 표시
                {
                    path: 'list',
                    component: BoardList
                },
                {
                    path: 'write',
                    component: BoardWrite
                },
                ...
            ]
        }]
    })
    ```

  * ``` html
    <div id="app">
        <router-link to="/board/list">게시판 목록</router-link>
        <router-link to="/board/write">게시판 등록</router-link>
        <router-view></router-view>
    </div>
    ```

    ``` javascript
    const Board = {
        template: `
    		<div class="board">
    			<h2>자유게시판</h2>
    			
    			<!-- children 컴포넌트 표시 -->
    			<router-view></router-view>
    		</div>
    	`
    }
    ```

