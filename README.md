# 👋🏻Intro
2023 대구를 빛내는 해커톤 E세션 출품작 [Be:SIDE] 입니다.


<br>

## 팀명

비사이드(Be:SIDE)

<br>

## 제출 타입 및 주제

[E타입: 청년을 위한 SW개발] - 사회초년생들이 사회를 살아가며 꼭 필요하지만 학교에서는 알려주지 않는 지식을 쉽게 전달해주는 서비스를 만들어 보자

<br>

## 프로젝트 한 줄 설명

사회 초년생들의 사회지식 부족 문제를 카드 뉴스와 퀴즈 시스템으로 해결

<br>

## 프로젝트에 활용된 기술



### Android
- jetpack compose + xml 혼용하여 구글 권장 앱 아키텍처 적용
  
|     Layer     | Description |
| ------------- | ------------- |
| di | dagger-hilt를 사용하여 의존성 주입 |
| repository | persentaion-layer에서 필요한 모든 데이터는 repository를 통하여 전달 |
| remoteDatasource | okhttp3-retrofit2을 사용하여 api 연결 |
| views | single-activity를 사용, navigation을 통해 fragment를 교체하도록 구성 |
| viewModel | repository에서 가져온 데이터를 livedata, flow, state로 데이터 상태 구성 |

### Server

- 스프링부트 프레임워크 사용, JPA + JWT
- 데이터베이스 : mysql 사용


![Untitled](https://github.com/bayy1216/Beside-App/assets/78216059/2335c551-578d-486f-8025-2195e8acdae6)


<br>

## 시연영상
https://youtu.be/rqKCiqaVCAE

<br>

## 백엔드 Git Repository
https://github.com/J-MU/Beside-server
