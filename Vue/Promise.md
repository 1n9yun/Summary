> # Promise
>
> > axios가 내부적으로 사용
> >
> > * promise 객체는 비동기 작업을 마치 동기 작업처럼 값을 반환해서 사용
> > * 형태
> >   * new Promise(function(resolve, reject){})
> >   * resolve(성공 시) - then() , reject(실패 시) - catch();

* #### Usage

  * ``` javascript
    const promise = new Promise((resolve, reject) =>{
        
        /*
        	비동기 작업
        	성공 시 : resolve("성공햇지롱")
    		실패 시 : reject("실패햇지롱..")
        */
           
    })
    
    promise
    	.then((data) => {
        	// 성공 시 실행
        	...
        })
    	.catch((data) =>{
            // 실패 시 실행
        	...
    	})
        .finally(function(){
        // 무적권
            ...
        })
    ```
    

