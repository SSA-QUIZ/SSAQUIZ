#### Back-End 실행 방법
> 총 3개의 Spring boot를 실행시켜야 합니다.
>
> LOCAL과 UBUNTU에서 접속 방법을 설명해드립니다.

**LOCAL에서 실행**

ssaquiz-auth(회원), ssaquiz-production(퀴즈제작), ssaquiz-progress(퀴즈진행)

* src > main > resources > 각 프로젝트에 맞는 application.yml 파일 추가

- 프로젝트 open 후 Build

**UBUNTU에서 실행**

ssaquiz-auth (회원)

```bash
$ cd /home/ubuntu/s04p31a304/backend/
$ java -jar ssaquiz-0.0.1-SNAPSHOT.jar --server.servlet.context-path=/api-auth
```

ssaquiz-production (퀴즈제작)

```bash
$ cd /home/ubuntu/s04p31a304/backend/
$ java -jar ssaquiz-1.0.1-SNAPSHOT.jar --server.servlet.context-path=/api-quiz --server.port=8081
```

ssaquiz-progress (퀴즈진행)

```bash
$ cd /home/ubuntu/s04p31a304/backend/
$ java -jar ssaquiz-2.0.1-SNAPSHOT.jar --server.servlet.context-path=/api-play --server.port=8082
```

<br />



#### Back-End 배포 방법

> 총 3개의 jar파일을 배포해야 합니다.

1. IntelliJ에서 jar 파일 추출
- 우측 Gradle 탭 클릭 > Tasks > build > bootjar 더블 클릭
- 좌측 build > libs > ssaquiz-0.0.1-SNAPSHOT.jar

2. mobaXterm에 jar 파일 업로드
- mobaXterm 접속
- jar 파일이 있는 경로로 이동해서 파일 mobaXterm으로 드래그 (자동업로드 됨)

3. jar 파일 업로드한 경로로 이동
```bash
$ cd /home/ubuntu/s04p31a304/backend/
```

4. jar 파일 실행
```bash
$ java -jar [파일명].jar --server.servlet.context-path=[패스명] --server.port=[포트번호]
```

<br />



#### DB 접속 방법

> 총 3개의 DB를 사용하고 있습니다.

MySQL (회원)

![image-20210521100908203](Back-End 실행 방법/MySQL.PNG)

MongoDB (퀴즈제작)

* mongodb://ssaquiz:*****@k4a304.p.ssafy.io:27017/?authSource=ssaquiz&readPreference=primary&appname=MongoDB%20Compass&ssl=false
* Connect 클릭

![image-20210521101249310](Back-End 실행 방법/image-20210521101249310.png)

Redis (퀴즈진행)

```bash
$ redis-cli
$ auth r#e%d#i%s#1q2w3e4r%r#e%d#i%s
```

<br />