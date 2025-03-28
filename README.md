# 프로젝트 주제

**취업 준비생들을 위한 멘토링 및 정보 공유 커뮤니티 플랫폼**

### 목적

취업 준비 과정에서 막막함을 느끼는 취준생들에게 실질적인 도움을 제공하기 위해,
**최신 정보와 노하우를 공유**하고 **전문가 멘토링 및 네트워킹을 지원**하는 커뮤니티를 구축하여 효율적이고 체계적인 취업 준비를 돕고자 합니다.

- **정보 공유**: 취업 관련 최신 정보와 노하우를 공유
- **멘토링 지원**: 실제 취업자 및 전문가와의 1:1 멘토링을 통해 조언과 피드백을 받을 수 있는 기회를 제공
- **커뮤니티 활성화**: 취준생들 간의 네트워킹과 상호작용을 통해 서로 동기부여와 지원이 가능한 커뮤니티를 구축

이를 통해 `취업 준비 과정에서의 불확실성`을 줄이고 `체계적이고 효율적인 취업 준비를 돕는 것`을 목표로 합니다.

### 차별점

기존 취준 커뮤니티와 멘토링 시스템을 결합하여 모든 유저가 멘토와 멘티로 상호작용이 가능하고 1:1 매칭 시스템을 제공하여 개인정보를 보호하면서 멘토링을 받을 수 있습니다.

1. **다분야 상호 멘토링 시스템**
- 기존의 일방적인 멘토-멘티 관계를 넘어, **한 사용자가 특정 분야에서는 멘토로 활동하고, 다른 분야에서는 멘티로 도움을 받을 수 있는 구조**를 제공
- 이는 업종 변경이나 새로운 분야에 도전하려는 사용자들에게 유용하며, 다양한 산업과 직무에 걸쳐 **지식과 경험의 교환**이 이루어질 수 있는 플랫폼 환경을 조성
- 예를 들어, IT 업계에서 경력을 쌓은 사용자가 금융 업계로 전환을 준비할 때, IT 관련 지식을 공유하며 멘토로 활동하고 동시에 금융 전문가에게 조언을 받는 멘티가 될 수 있습니다.

이러한 **다분야 상호 멘토링 시스템**은 사용자들이 자신의 전문성을 확장하고 새로운 커리어 기회를 모색하도록 돕는 데 중점을 둡니다.
    
이 기능은 단순히 취업 준비를 지원하는 것을 넘어 **커리어 전환과 평생 학습을 지원하는 플랫폼**으로 자리매김할 수 있는 중요한 차별화 요소가 될 것입니다.

---

## 요구사항 명세서
![허거덩스_요구사항명세서 - Google Sheets_page-0001](https://github.com/user-attachments/assets/8fe02292-5ab3-4e31-bb1b-3db5c609c7fd)

---

## ERD
![prototype (2)](https://github.com/user-attachments/assets/26f0d16c-7dfd-44fd-bf8f-5b20d24858c4)

---

# [플로우차트](https://www.figma.com/board/WFpTCWGFAQv6SYuRj1BlCr/Flow-Chart?node-id=0-1&t=6rcCj2lWCvrQJV2e-1)

# [API 명세서 및 테스트 결과 보고서](https://hexagonal-surf-ffe.notion.site/API-1b6a9f17aadc80f981a4f2c70ad2a8ab)   

---

<details><summary>
테이블 명세서
</summary>

## **User (사용자 정보)**

| **컬럼명** | **데이터 타입** | **제약 조건** |
| --- | --- | --- |
| userId | int(auto_increment) | NOT NULL (PK) |
| typeId | tinyint | NOT NULL |
| nickName | varchar | NOT NULL |
| email | varchar | NOT NULL |
| password | varchar | NOT NULL |
| created_at | date | NOT NULL |
| deleted_at | date | NULL |
| login_type | enum | NULL |

## **User_profiles (유저 프로필 정보)**

| **컬럼명** | **데이터 타입** | **제약 조건** |
| --- | --- | --- |
| profile_id | int(auto_increment) | NOT NULL (PK) |
| userId | int(auto_increment) | NOT NULL |
| current_job | varchar | NULL |
| desired_job | varchar | NULL |
| introduction | text | NULL |
| experience | text | NULL |
| rate | float | NULL |
| type | enum | NOT NULL |
| phoneNumv | varchar | NULL |
| gender | char | NULL |

## **Type (역할)**

| **컬럼명** | **데이터 타입** | **제약 조건** |
| --- | --- | --- |
| type_Id | int(auto_increment) | NOT NULL (PK) |
| role_type | enum | NOT NULL |

## **Social_accounts (소셜 로그인)**

| **컬럼명** | **데이터 타입** | **제약 조건** |
| --- | --- | --- |
| userId | int(auto_increment) | NOT NULL (PK) |
| provider | enum | NOT NULL |
| social_id | varchar | NOT NULL |

## **Apply_list (신청 대기열)**

| **컬럼명** | **데이터 타입** | **제약 조건** |
| --- | --- | --- |
| apply_list_id | long | NOT NULL (PK) |
| matching_text_id | int(auto_increment) | NOT NULL |
| userId | int(auto_increment) | NOT NULL |
| content | text | NULL |
| apply_date | date | NOT NULL |
| status | enum | NOT NULL |

## **Matching (매칭 게시판)**

| **컬럼명** | **데이터 타입** | **제약 조건** |
| --- | --- | --- |
| matching_text_id | int(auto_increment) | NOT NULL (PK) |
| userId | int(auto_increment) | NOT NULL |
| matching_title | varchar | NOT NULL |
| board_type | enum | NOT NULL |
| matching_date | date | NOT NULL |
| matching_text | text | NOT NULL |
| matching_number | int | NOT NULL(default=0) |
| tag | enum | NOT NULL |

## **Matching_comment (매칭 댓글)**

| **컬럼명** | **데이터 타입** | **제약 조건** |
| --- | --- | --- |
| matching_text_comment_id | int(auto_increment) | NOT NULL (PK) |
| matching_text_id | int(auto_increment) | NOT NULL |
| userId | int(auto_increment) | NOT NULL |
| apply_list_id | long | NOT NULL |
| comment_type | enum | NOT NULL |
| matching_comment_text | text | NOT NULL |
| matching_comment_date | date | NOT NULL |
| rate | float | NOT NULL |

## **Free (자유 게시판)**

| **컬럼명** | **데이터 타입** | **제약 조건** |
| --- | --- | --- |
| free_text_id | int(auto_increment) | NOT NULL (PK) |
| userId | int(auto_increment) | NOT NULL |
| board_type | enum | NOT NULL |
| free_title | varchar | NOT NULL |
| free_date | date | NOT NULL |
| free_text | text | NOT NULL |
| free_number | int | NOT NULL(default=0) |

## **Free_comment (자유 게시판 댓글)**

| **컬럼명** | **데이터 타입** | **제약 조건** |
| --- | --- | --- |
| free_text_comment_id | int(auto_increment) | NOT NULL (PK) |
| free_text_id | int(auto_increment) | NOT NULL |
| userId | int(auto_increment) | NOT NULL |
| comment_type | enum | NOT NULL |
| free_comment_text | text | NOT NULL |
| free_comment_date | date | NOT NULL |

## **Notice (공지사항 게시판)**

| **컬럼명** | **데이터 타입** | **제약 조건** |
| --- | --- | --- |
| notice_text_id | int(auto_increment) | NOT NULL (PK) |
| userId | int(auto_increment) | NOT NULL |
| board_type | enum | NOT NULL |
| notice_title | varchar | NOT NULL |
| notice_date | date | NOT NULL |
| notice_text | text | NOT NULL |
| notice_number | int | NOT NULL(default=0) |
</details>


# 프로젝트 기능


<details><summary>
유저 관리
</summary>

- **로그인**:
    - 이메일 로그인
- **회원가입**:
    - 이메일 회원가입(OTP 인증)
- **회원탈퇴**
- **회원정보 수정**:
    - 소개 수정
    - 현재 직무 수정
    - 희망 직무 수정
    - 닉네임 수정
    - 경력 수정
    - 비밀번호 수정
- **회원 정보 조회**:
    - 닉네임, 멘토/멘티 히스토리, 평점, 리뷰, 이메일, 작성한 게시글
</details>

<details><summary>
멘토링 히스토리
</summary>

- **멘토 히스토리**: 멘토로서 참여한 내역 조회
- **멘티 히스토리**: 멘티로서 참여한 내역 조회

</details>


<details><summary>
게시판 기능
</summary>

1. **공지 게시판**:
    - 관리자만 작성 가능
    - 댓글 및 좋아요 없음
2. **자유 게시판**:
    - 모든 사용자 조회 가능
    - 댓글 기능 제공
    - 정렬 기능
3. **취업정보 게시판**:
    - 자유 게시판과 동일한 로직 적용
4. **멘토/멘티 모집 게시판**:
    - 비밀 댓글 기능 (멘토만 댓글 확인 가능)
5. **멘토링 후기 게시판**:
    - 멘티만 작성 가능
    - 수정 및 삭제 가능
</details>


---
![image](https://github.com/user-attachments/assets/658b3f48-b471-41fa-a6d8-be120e57a42b)

# 팀원
|이름| [유재우 ](https://github.com/blooper20)|  [오영광](https://github.com/OhGlory)| [이성훈](https://github.com/YeAn475)|[염정운](https://github.com/ericyum)| [김범석](https://github.com/g00dbyul) |
|---|  -- | --- | --- | --- | --- |
|![image](https://github.com/user-attachments/assets/b9ecc18b-a9be-41a4-aaec-248c5f6e4d06)| <img src="https://github.com/user-attachments/assets/b74aed62-6bfd-467f-808a-d46241f26014" width="150" height="170"/>| <img src="https://github.com/user-attachments/assets/b74aed62-6bfd-467f-808a-d46241f26014" width="150" height="170"/> | <img src="https://github.com/user-attachments/assets/b74aed62-6bfd-467f-808a-d46241f26014" width="150" height="170"/> | <img src="https://github.com/user-attachments/assets/b74aed62-6bfd-467f-808a-d46241f26014" width="150" height="170"/> | <img src="https://github.com/user-attachments/assets/b74aed62-6bfd-467f-808a-d46241f26014" width="150" height="170"/> |
| 역할 | 팀장 | 팀원 | 팀원 | 팀원 |  팀원 |
Back-end|공지 게시판/멘토링 게시글 기능|로그인 / 로그아웃 기능|프로필 관리 기능|자유 게시판/멘토링 댓글 기능| 회원가입 기능, 리팩토링 및 기능 개선|

---

### 기술스택

- Spring JPA
- MariaDB
- Gradle
- Swagger UI
- jdk 21
- 스프링 부트
- 스프링 시큐리티
- redis
- figjam

---

# 디렉토리 구조

```jsx
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── example/
│   │           ├── config/                # 설정 관련 클래스
│   │           ├── entity/                # 전체 엔티티 클래스
│   │           ├── auth/                  # 인증 관련 기능 (로그인, 토큰 등)
│   │           ├── user/                  # 유저 정보 관련 기능
│   │           └── board/                 # 게시판 기능
│   │               ├── notice/            # 공지 게시판
│   │               ├── common/            # 자유 게시판
│   │               └── matching/          # 멘토/멘티 매칭 게시판
│   │                   ├── controller/    # 컨트롤러 클래스
│   │                   ├── dto/           # DTO 클래스 (요청 및 응답)
│   │                   │   ├── request/   # 요청 DTO
│   │                   │   └── response/  # 응답 DTO
│   │                   ├── repository/    # 리포지토리 인터페이스
│   │                   │   └── impl/      # 리포지토리 구현체
│   │                   ├── service/       # 서비스 인터페이스
│   │                   │   └── impl/      # 서비스 구현체
│   └── resources/
│       ├── application.properties         # 애플리케이션 설정 파일
│       └── static/                        # 정적 파일 (HTML, CSS, JS 등)
└── test/
    └── java/
        └── com/
            └── example/
                └── board/
                    └── matching/          # 매칭 게시판 테스트 코드

```

