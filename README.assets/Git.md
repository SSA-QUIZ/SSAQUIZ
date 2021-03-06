## :bookmark_tabs: Branch

`{flow}/{기능명}/구역`

ex: `feature/review/be`, `hotfix/dashboard/fe`

## :bookmark_tabs: Commit message

![git_convention](git_convention.png)

#### 그럼 어떻게 쓰나요?

> Add review function 
>
> 본문에는 {타입} (적용범위): 메시지 
>
> feat(pipeline) !: Add pipeline function 본문은 생략이 가능하지만, **Major버전에 대한 명시**필요하면 꼭 적어야 한다. 
>
> 추가적으로 Major 변화에 대한 메시지는 본문에 있으니까, 커밋 메시지에서 확인 못하고 넘어갈 가능성이 있기 때문에 : 앞에 !를 추가해서 본문에 내용이 있음을 알려줄 수 있다.

#### 커밋 메시지 7가지 규칙 

1. 제목과 본문을 한 줄 띄워 분리하기  
2. 제목은 영문 기준 50자 이내  
3. 제목 첫 글자를 대문자  
4. 제목 끝에 **. 금지**  
5. 제목은 **명령조**로 (git의 Built-in Convention을 따르는 것)  
6. 본문은 영문 기준 72자마다 줄 바꾸기  
7. 본문은 **어떻게**보다 **무엇을, 왜**에 맞춰 작성