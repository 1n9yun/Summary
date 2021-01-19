> # Axios
>
> 자동으로 json으로 받아온다

* #### .get()

  * ``` javascript
    // 호출 세 가지 방법
    axios({
        method: 'get',
        url: '/board/10'
    })
    // default가 get
    axios('/board/10');
    axios.get('/board/10');
    ```

* #### .delete()

  * ``` javascript
    axios('/board/10', {
        method: 'delete',
    })
    // 나머지는 그냥 delete로 쓰면 됨
    ```

* #### .post()

  * ``` javascript
    axios({
        method: 'post',
        url: '/board',
        data:{
            writer: 'ssafy',
            title: '연습'
        }
    })
    axios.post('/board',{
        writer: 'ssafy',
        title: '연습'
    })
    ```

* #### .put()



* #### Usage

  * ``` javascript
    new Vue({
        el: '#app',
        data:{
            storeList: []
        },
        created(){
            axios.get(addr)
            	// 안에서 this를 사용할 거라면 arrow로 해야 함
            	.then((response) =>{
                	...
    	        })
                .catch((error) =>{
                    ...
                })
        }
    })
    ```

  * 