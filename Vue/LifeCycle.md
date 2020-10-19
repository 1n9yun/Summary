> # Vue Instance Life Cycle
>
> > https://vuejs.org/v2/guide/instance.html#Lifecycle-Diagram
> >
> >
> > ``` javascript
> > const root = new Vue({
> > 	el: ...,
> >  beforeCreated() {
> >  
> > 	},
> > 	created: function(){
> > 
> > 	},
> > 	...
> > })
> > ```

* #### 초기화 과정

  * 데이터 관찰
  * 템플릿 컴파일
  * 인스턴스를 Dom에 마운트
  * 데이터가 변경되어 Dom을 업데이트

* ####  Life Cycle Hooks

  > test00.html

  * beforeCreate : vue인스턴스가 생성되고 각 정보의 설정 전에 호출
  * created : vue인스턴스가 생성된 후 데이터들의 설정이 완료 후 호출
  * beforeMount : 마운트가 시작되기 전에 호출
  * mounted : 지정된 엘리먼트에 vue 인스턴스 데이터가 마운트 된 후에 호출
  * beforeUpdate : 데이터가 변경될 때 virtual dom이 랜더링, 패치 되기 전에 호출
  * updated : vue에서 관리되는 데이터가 변경되어 dom이 업데이트 된 상태
  * beforeDestroy : vue instance가 제거되기 전에 호출
  * detroyed : vue instance가 제거된 후에 호출