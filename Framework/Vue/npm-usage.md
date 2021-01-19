> # npm Usage
> > nodejs.org LTS
> >
> > npm init
> >
> > npm i package



* #### ex) prompt-sync

  * java의 scanner 처럼 사용가능
  * npm i prompt-sync

  * ``` javascript
    // test.js
    var prompt = require('prompt-sync')();
    var name = prompt('name : ');
    console.log(`당신의 이름은 ${name}입니다`);
    ```

  * node test