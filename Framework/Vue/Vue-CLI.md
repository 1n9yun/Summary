> # Vue-CLI
>
> * Vue.js 개발을 위한 시스템으로 Vue-js에서 공식으로 제공하는 CLI
> * 개발의 필수는 아니지만 개발의 편리성을 위해 필수처럼 사용
> * Vue 프로젝트를 바르게 구성할 수 있는 스캐폴딩을 제공
> * Vue와 관련된 오픈 소스들이 대부분이 CLI를 통해 구성이 가능하도록 구현되어 있음
> * https://cli.vue.js.org/
> * 필수적으로 됐음



* #### init (2.x.x)

  * npm install -g vue-cli
  * vue -V
  * vue list
  * vue init (template-name) (project-name)
    * vue init webpack-simple my-webpack


* #### 바벨(BabelJS)

  * ECMA2015 이상의 문법을 ES5의 자바스크립트 코드로 변경하는 도구

* #### 웹팩(Webpack)

  * 웹팩은 오픈소스 자바스크립트 모듈 번들러이다.
  * npm run build

* #### init (latest) (@vue/cli)

  * npm install -g (-force) @vue/cli
  * vue create project-name

* #### SFC(Single File Component)


  * 확장자가 .vue인 파일

  * template + script + style = .vue

  * 구문 강조 가능

  * 컴포넌트에만 CSS의 범위를 제한할 수 있음

  * 전처리기를 사용해 기능의 확장이 가능

  * ###### template

    * 기본 언어 : html
    * 각 vue 파일은 한번에 최대 하나의 `<template>`블록을 포함할 수 있음
    * 내용은 문자열로 추출되어 컴파일 된 Vue 컴포넌트의 template 옵션으로 사용

  * ###### script

    * 기본 언어 : js
    * 각 vue 파일은 한번에 최대 하나의 `<script>`블록을 포함할 수 있음
    * ES2015(ES6)를 지원하여 import와 export를 사용할 수 있음

  * ###### style

    * 기본 언어 : css
    * 각 vue 파일은 여러 개의 `<style>` 태그를 지원
    * scoped 속성을 이용하여 현재 컴포넌트에서만 사용 가능한 css를 지정 가능
    * ``` html
        <style scoped></style>
        ```