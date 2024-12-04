프로젝트 설계

유즈케이스
![image](https://github.com/user-attachments/assets/c89fa085-fff5-4b57-99c9-14f31f569488)

ERD
![image](https://github.com/user-attachments/assets/0c258ab5-e1a5-4773-9efd-2e4abc53f82d)


API 명세
 
로그인 : post - /user/authenticate  > requestBody json : userId,password
회원가입 : post - /user/signup > requestBody json : userId,password
게시글리스트 : get - /post/list > requestParam page,size,searchQuery
게시글 작성 : post - /post/write > requestParam mutipartform : title, content, userId, file
게시글 내용 : get - /post/{id}/content > @PathVariable : id
게시글 수정 : put - /post/{id}/content > requestParam mutipartform : title, content, userId, file

프론트 페이지

로그인 /
![로그인](https://github.com/user-attachments/assets/7c3208b0-f11d-4a8b-843c-716b5deb05a9)

회원가입 /signup
![회원가입](https://github.com/user-attachments/assets/1b548443-a9e6-4cee-95a3-87451d78a507)

글목록 /list
![글목록](https://github.com/user-attachments/assets/1b8c44a4-da10-4f77-a2ac-ea65d783d6b2)

글작성 /write
![글쓰기](https://github.com/user-attachments/assets/61b370b2-a506-4b4c-a276-418dade3dda0)

글수정 /edit
![글수정](https://github.com/user-attachments/assets/5ed2d367-6e7a-4215-83e4-f4937fbd27b0)

글내용/post/:id
![글내용](https://github.com/user-attachments/assets/62450e1c-54ac-4cc2-9619-c7a6fc5e8d80)

