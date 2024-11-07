# 커뮤니티 SNS형 피드 프로젝트 

**프로젝트 설명** 
<br>

<a href="https://marmalade-canvas-697.notion.site/SNS-137bf0ca6d808015bc11f034aa36b300?pvs=4">![Notion](https://img.shields.io/badge/Notion-%23000000.svg?style=for-the-badge&logo=notion&logoColor=white)</a>

<br>
<br>



## **유저 메인 서비스 FLOW**
<br>

1. **유저 생성**
    - 유저는 id 를 통해 구분이 되어야 함
    - 이름, 프로필 이미지를 입력 받으면 저장되어야 함
        - 단, 이름은 빈 값이 입력되면 안됨

<br>

2. **팔로우 기능:**
    - 사용자는 다른 이용자를 팔로우 할 수 있음
    - 팔로우 취소 할 수 있음
    - 자기 자신을 팔로우 할 수 없음
  
<br>

3. **프로필 노출:**
    - 사용자 프로필을 누르면 다음과 같은 정보들이 노출 됨
        - 이름, 유저 프로필, 팔로잉 숫자, 팔로워 숫자
        - 팔로잉 팔로우 유저 리스트
            - 이름, 프로필
            - 단,  팔로잉, 팔로우 숫자를 클릭 했을 때에 각 유저 리스트가 노출됨

<br>
<br>

## **피드 서비스 메인 FLOW**
<br>

1. **게시물 작성:**
    - 사용자가 텍스트를 입력하고
    - 사용자가 공개 대상 (예: 모두 공개, 팔로워 전용)을 선택합니다.
    - 사용자가 게시물을 제출합니다.
        - 단, 게시물의 글자수는 5글자 이상 500자 이하여야 합니다.
     
<br>


2. **댓글 작성:**
    - 시용자기 텍스트를 입력하고
    - 사용자가 댓글을 제출합니다.
        - 단, 댓글의 글자수는 100자 이하여야 합니다.
     
<br>

3. **게시물 상호작용:**
    - 사용자는 '좋아요' 버튼을 눌러 게시물을 좋아할 수 있습니다.
        - 본인 게시글은 본인이 좋아요를 누를 수 없습니다.
    - 사용자는 댓글 섹션에 메시지를 입력하여 게시물에 댓글을 달 수 있습니다.
    - 좋아요 개수를 누르면 좋아요를 누른 인원들을 볼 수 있음
    - 댓글 개수를 누르면 댓글 리스트가 보일 수 있음
    - 글 수정 버튼을 누르면 글을 수정할 수 있음
        - 본인이 작성한 글이 아니면 수정 할 수 없음
        - 수정이 된 여부와 수정된 시간을 같이 저장해야 함
    - 댓글 수정 버튼을 누르면 댓글을 수정 할 수 있음
        - 본인이 작성한 댓글이 아니면 수정 할 수 없음
        - 수정이 된 여부와 수정된 시간을 같이 저장해야 함
     
<br>

4. **피드 보기:**
    - 사용자는 팔로우 한 사람이 작성한 글을 시간 순서대로 볼 수 있습니다.
    - 사용자는 피드를 스크롤하여 콘텐츠를 봅니다.
    - 게시글은 다음과 같은 정보를 제공합니다.
        - 글 내용, 작성자 이름, 작성자 프로필, 좋아요 개수, 댓글 개수, 수정 여부 , 발행 일자 (수정 시간), 나의 좋아요 여부
     
<br>

5. **댓글 리스트:**
    - 댓글 리스트에는 다음과 같은 정보들이 노출되어야 함
        - 작성자 이름, 작성자 프로필, 댓글 내용, 좋아요 개수, 수정 여부, 발행 일자 (수정 시간), 나의 좋아요 여부
    - 댓글에 좋아요를 누를 수 있음
  
<br>
<br>
<br>
