# 명세서
## 엔티티
- Team
  - 팀명
  - 연차사전신청허용일기준
- Employee
  - 직원명
  - 매니저 여부
  - 입사일자
  - 생일
- WorkHistory
  - 직원(외래키)
  - 일자
  - 출근시간
  - 퇴근시간
  - 연차사용여부
- DayOff
  - 직원(외래키)
  - 전체 연차 일수
  - 남은 연차 일수
- DayOffHistory
  - 직원(외래키)
  - 신청일자
  - 연차일자

## 요구사항
### TEAM
- 팀 등록
  - POST
  - team/
- 팀 전체 조회
  - GET
  - team/
### EMPLOYEE
#### 직원
- 직원 등록
  - POST
  - employee/
- 직원 전체 조회
  - GET
  - employee/all
#### 근무
- 각 직원 날짜별 근무시간 조회
  - GET
  - work/?{employeeId}&{year}&{month}
  - 날짜별 근무시간 및 해당 년월의 근무시간 총합 조회 가능
- 출근 기능
  - POST
  - work/in
- 퇴근 기능
  - POST
  - work/out
- 초과근무 전체 조회
  - GET
  - work/overtime
  - 공휴일/주말 제외한 일자를 기준으로 직원별 초과근무(분 단위) 조회
#### 연차
- 연차신청
  - POST
  - dayoff/
- 각 직원 별 남은 연차조회
  - GET
  - dayoff?{employeeId}