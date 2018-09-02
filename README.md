# 카카오 ix 간이 쇼핑몰 프로젝트

## git
### git url : https://github.com/JongHyuckLee/Shop.git

## 구현 기능
1. 회원가입 기능
2. 로그인 기능
3. 상품 구매 기능 ( 결제기능은 가상으로 처리 )
4. 구매이력 조회

## 기술 스택
1. Back-End Framework : Spring Boot
2. Front-End Framework : jQuery
3. DBMS : mongo DB
4. data 및 front css - kakao friends site
5. os : mac os
6. version control - git
7. 의존성 관리 : gradle

## mongo db 설치 및 실행

reference site : https://docs.mongodb.com/manual/tutorial/install-mongodb-on-os-x/

### mongo db 설치

1. terminal 실행

2. 명령어 입력

```
$ brew install mongodb
```

3. mongo db 설치 확인

```
$ mongo --version
```

### mongo db 실행

1. 데이터 저장 디렉토리 생성

```
$ mkdir -p /data/db
```

2. 데이터 디렉토리 퍼미션 변경

```
$ chmod 755 /data/db
```

3. run mongodb

```
$ mongod
```

## 프로젝트 시작하기

1. mongodb local 실행하기

- 터미널 실행 후 명령어를 통해 local mongodb 실행하기

```
$ mongod
```

2. terminal project root 로 이동

```
$ cd "프로젝트 경로"/Shop

```

3-1. gradle을 통해 프로젝트 run

```
$ gradle bootRun
```


3-2. IDE를 통해 시작 할때

- `src/main/java/com.dev.shop` > Application 오른 쪽 마우스 클릭
- `run Application.main()` 클릭