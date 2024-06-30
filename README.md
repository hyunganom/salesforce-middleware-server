# salesforce-middleware-server
세일즈포스와 chatGPT를 연동하기위한 중계서버
## 프로젝트 개요

<aside>
💡 Salesforce와 Chat GPT를 연동하는 MiddleWare Server를 개발을 목표로 하였습니다.
개발인원은 1인 개발이고, 기간은 2024년 5월 1일 부터 진행하고 있습니다.

</aside>

### **배경**

- **인공지능과 최신 기술 트렌드**: 최근 인공지능(AI)과 머신러닝(ML) 기술의 발전은 다양한 비즈니스 프로세스를 혁신하고 있습니다. Salesforce와 Chat GPT를 통합하여 고객 서비스, 마케팅, 판매 등 여러 분야에서 효율성과 생산성을 높일 수 있습니다. 이러한 최신 기술 트렌드를 반영한 시스템 통합은 경쟁력 확보에 중요한 역할을 합니다.
- **비즈니스 로직의 독립성**: 애플리케이션의 비즈니스 로직을 표현 로직이나 데이터 접근 로직과 분리하여 유지보수성과 테스트 용이성을 높이는 동시에 시스템의 확장성을 확보하는 것이 중요합니다. 비즈니스 로직의 독립성을 통해 변화하는 기술 환경에 유연하게 대응할 수 있습니다.

### 목표

- **Hexagonal Architecture** 적용
    - **유연성 향상**: 비즈니스 로직이 외부 시스템 변화에 영향을 받지 않도록 구성.
    - **테스트 용이성**: 독립적인 테스트가 가능하여 개발 품질을 높임.
    - **재사용성**: 비즈니스 로직을 다양한 어댑터에서 재사용 가능.
- **Message Queue** 활용
    - **비동기 처리**: 시스템의 응답성을 높이고, 부하 분산을 통해 성능을 최적화.
    - **낮은 결합도**: 시스템 간의 결합도를 낮추어 독립적인 개발과 배포가 가능.
    - **확장성**: 새로운 시스템 추가 시 최소한의 변경으로 통합 가능.
    - **탄력성**: 시스템 장애 시 메시지 큐를 통해 데이터 손실을 최소화하고 신뢰성 있는 데이터 처리 보장.
- 지속적으로 성능 최적화와 모니터링 개발진행 ← 개발 미구현

### 주요 사항

- **Salesforce LWC 컴포넌트 통합**: LWC 컴포넌트에서 발생하는 채팅 메시지를 RabbitMQ를 통해 처리하여 Salesforce와 ChatGPT 간의 원활한 데이터 통신 보장.
- **API 설계 및 구현**: RESTful API를 설계하여 외부 시스템과의 통신을 표준화하고, 확장성을 높임.
- **보안 및 인증**: 데이터 통신 과정에서의 보안 및 인증 강화하여 시스템의 안정성과 신뢰성 확보.
- **로그 및 모니터링**: 애플리케이션의 로그와 모니터링 시스템을 구축하여 실시간으로 시스템 상태를 파악하고, 이슈 발생 시 신속히 대응.

## 기술 스택

- **언어**: Java (버전 17)
- **프레임워크**: Spring Boot (버전 2.5.2)
- **메시지 큐**: RabbitMQ
- **인증 및 보안**: JWT OAuth 2.0
- **컨테이너화**: Docker
- **클라우드 환경**: AWS (향후 진행 계획)
- **운영 체제**:
    - **로컬 실행 환경**: Mac OS (Sonoma 14.1)
    - **클라우드 설정**: AWS Linux OS

## 패키지 구조

### **Hexagonal Architecture**

<aside>
💡 헥사고날 아키텍처(Hexagonal Architecture), 또는 포트와 어댑터 아키텍처(Ports and Adapters Architecture)는 소프트웨어 아키텍처 중 하나로, Alistair Cockburn에 의해 제안되었습니다.

이 아키텍처의 주요 목표는 응용 프로그램의 비즈니스 로직을 외부 세계로부터 격리시켜 유연하고 테스트하기 쉬운 구조를 만드는 것입니다.

이를 위해 핵심 비즈니스 로직은 중앙의 도메인 영역에 위치하며, 입력과 출력을 처리하는 포트와 어댑터를 통해 외부와 소통합니다.

</aside>

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/b66e7ba9-c26b-49f9-a7e4-1e279b9bef9e/7fba4092-262e-4113-a600-2b26dc97ea64/Untitled.png)

```
src/main/java
├── com
│   └── hyunganom
│       └── salesforcemiddleware
│           ├── adapters
│           │   ├── in
│           │   │   ├──web
│						│		│		└──message
│						│		└── out
│           ├── core
│           │   ├── domain
│						│		│		├──chatgpt
│           │   │   └──salesforce
│           │   └── service
│           │   └── ports
│						│				├──in
│						│				└──out  
│						├── utills
│						└──	interceptors
└── resources
    └── application.yml
```

- **adapters**: 외부 시스템과의 통신을 담당하는 어댑터
    - **in**: 입력 어댑터 (web, message 등)
    - **out**: 출력 어댑터
- **core**: 비즈니스 로직과 도메인 모델을 포함
    - **domain**: 도메인 모델 (chatgpt, salesforce 등)
    - **service**: 비즈니스 로직을 담당하는 서비스 클래스
    - **ports**: 입력 및 출력 포트 인터페이스
        - **in**: 내부에서 사용하는 포트
        - **out**: 외부에서 사용하는 포트
- **utils**: 공통적으로 사용되는 유틸리티 클래스들
- **interceptors**: 요청/응답을 가로채는 인터셉터 클래스들

## **주요 기능 및 워크플로우**

1. **Salesforce와 ChatGPT 통합**
- LWC 컴포넌트에서 사용자가 메시지를 입력하면, 해당 메시지가 RabbitMQ를 통해 중계 서버로 전송됨.
- 중계 서버는 메시지를 ChatGPT API로 전송하여 응답을 받아옴.
- ChatGPT의 응답을 Salesforce LWC 컴포넌트에 다시 전달하여 사용자에게 표시.
1. **비동기 메시징 처리**
- 사용자의 메시지를 RabbitMQ를 통해 비동기적으로 처리하여 시스템 응답성을 향상.
- 메시지 큐를 사용하여 높은 부하 상황에서도 안정적인 데이터 처리 보장.
1. **API 설계 및 구현**
- RESTful API를 통해 외부 시스템과의 통신을 표준화.
- API 보안을 위해 OAuth2와 JWT 토큰 인증 방식 사용.

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/b66e7ba9-c26b-49f9-a7e4-1e279b9bef9e/6e9b7dd1-3b45-473f-a0df-9c06c2344ff0/Untitled.png)

## **프로젝트 학습 및 개선 사항**

- **학습 내용**: Hexagonal Architecture의 적용 방법과 RabbitMQ의 비동기 처리에 대한 깊은 이해.
- **개선 사항**:
    - **RabbitMQ 모니터링 추가**: RabbitMQ의 상태와 성능을 실시간으로 모니터링하여 안정성을 더욱 강화.
    - **토큰 발급 및 저장**: JWT 토큰을 발급하여 Redis에 저장하고, Salesforce 해당 ID를 키로 사용하여 관리.
    - **질문과 GPT 응답 저장**: 질문과 GPT의 응답을 로컬 DB에 저장하여 데이터의 추적 및 분석 가능하게 함.
    - **AI 모델의 성능 개선**: ChatGPT API와의 통신 최적화 및 모델 성능 향상.
    - **추가 외부 시스템 통합**: 더 많은 외부 시스템과의 연동을 통해 확장성 강화.
    - **보안 강화**: 보안 정책 및 절차를 지속적으로 업데이트하고 강화.