# 나 혼자 먹는다 (1인 가구를 위한 레시피 서비스)

### 신한DS 금융SW 아카데미 JDBC CRUD Project

개인 프로젝트 기간 : 2024/04/08 ~ 2024/04/11

<br>

## 📌 Contents

1. 개발 목적
2. 개발 환경
3. 시스템 구조
4. 테이블 구조
5. 프로젝트 기능 구현

<br>

## 📌 개발 목적

- 상품 구매, 취소 / 금액 충전, 간단한 결제 기능을 지원하는 프로젝트 구현
- Oracle DB를 기반으로 한 JDBC 구현 및 Java, SQL 문법 학습

<br>

## 📌 개발 환경

<div>
  <img src="https://img.shields.io/badge/Windows-0078D6?style=for-the-badge&logo=windows&logoColor=white" />
  <img src="https://img.shields.io/badge/HTML-239120?style=for-the-badge&logo=html5&logoColor=white" />
  <img src="https://img.shields.io/badge/CSS-239120?style=for-the-badge&logo=css3&logoColor=white" />
  <img src="https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=JavaScript&logoColor=white" />
  <img src="https://img.shields.io/badge/jQuery-0769AD?style=for-the-badge&logo=jquery&logoColor=white" />
  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" />
  <img src="https://img.shields.io/badge/Oracle-F80000?style=for-the-badge&logo=oracle&logoColor=black" />
</div>

<br><br>

## 📌 주요 기능

> ### *메인화면*
> 
- slick slider 라이브러리를 사용하여 이미지 슬라이드 구현
- 판매량이 높은 순서대로 3개의 상품을 조회할 수 있고 마우스 hover 효과로 상품의 설명을 조회할 수 있도록 구현

<p align="center">
  <img src="https://github.com/user-attachments/assets/d80431f3-e514-4982-a1c4-fdd44dc43d2a">
</p>

<br><br>

> ### *회원가입*
> 
- 아이디 중복 확인 및 항목 별로 정규 표현식을 사용하여 조건 체크

<p align="center">
  <img src="https://github.com/user-attachments/assets/d1e1d4e0-a76e-470f-aefd-5536b3bd1fd7" width="49%" height="49%">
  <img src="https://github.com/user-attachments/assets/4e54b95b-de6a-4b89-98c9-2d8e75656a38" width="49%" height="49%">
</p>

<br><br>

> ### *상품 검색*
> 
- 검색 키워드를 입력하지 않고 돋보기 아이콘 클릭 시 전체 상품이 조회 되도록 구현
- 존재하지 않는 상품을 검색한 경우와 존재하는 상품을 검색한 경우의 결과 화면을 다르게 구현

<p align="center">
  <img src="https://github.com/user-attachments/assets/f30dbcf5-9fb5-4a4a-b6ac-5601cf306449" width="80%" height="80%">
</p>

<br>

<p align="center">
  <img src="https://github.com/user-attachments/assets/d0270a67-1e02-4a76-8a05-cf900c85f656" width="49%" height="49%">
  <img src="https://github.com/user-attachments/assets/3fd694cc-2f20-4ef8-9020-0e90017840b6" width="49%" height="49%">
</p>

<br><br>

> ### *상품 구매*
> 
- 바로 구매하기 버튼을 클릭하면 AJAX 비동기 통신으로 상품 구매가 가능하지만 사용자의 현재 잔액이 구매하려는 상품의 판매 가격 보다 클 경우에만 구매가 가능하도록 구현
- 상품 구매가 완료되면 사용자의 현재 잔액이 구매한 상품의 가격만큼 차감 되도록 구현

<br>

<p align="center">
  <img src="https://github.com/user-attachments/assets/e2ce66ab-5588-4a3f-b8f0-8242982db9ae" width="49%" height="49%">
  <img src="https://github.com/user-attachments/assets/0e5a2e51-55a4-458d-bdaa-9fd90045a9af" width="49%" height="49%">
</p>

<br>

<p align="center">
  <img src="https://github.com/user-attachments/assets/c4471fdc-0371-472b-8879-48681b944417" width="49%" height="49%">
  <img src="https://github.com/user-attachments/assets/f9b1907a-b1f3-4685-bb22-56a4bdfdd1aa" width="49%" height="49%">
</p>

<br><br>

> ### *마이페이지 (내프로필)*
> 
- 내 프로필에서 회원가입 시 입력했던 아이디, 전화번호, 주소 조회 및 현재 잔액 확인 가능
- 주소 칸에 변경할 주소를 입력하고 수정 버튼을 클릭하면 AJAX 비동기 통신으로 변경한 주소가 바로 반영되도록 구현
- 충전 버튼 클릭 시 팝업창이 뜨고 0원 이상의 숫자만 입력이 가능하며 충전 완료 시 팝업이 자동으로 닫히고 페이지를 새로고침 하여 잔액이 갱신되도록 구현

<p align="center">
  <img src="https://github.com/user-attachments/assets/784c9466-8bb3-4311-a996-3604e52437c7" width="80%" height="80%">
</p>

<p align="center">
  <img src="https://github.com/user-attachments/assets/14be8016-8518-495f-9079-d7ca7bb10c5e" width="80%" height="80%">
</p>

<br>

<p align="center">
  <img src="https://github.com/user-attachments/assets/87bdb7ad-2065-400c-8c13-0ecae31895f1" width="49%" height="49%">
  <img src="https://github.com/user-attachments/assets/b1369ad7-6045-4ded-bd19-96fabe9463f0" width="49%" height="49%">
</p>

<p align="center">
  <img src="https://github.com/user-attachments/assets/cb5101ee-f394-4723-b33b-492b2ddd2eba" width="80%" height="80%">
</p>

<br><br>

> ### *마이페이지 (주문내역)*
> 
- 구매한 상품이 없는 경우와 구매한 상품이 있는 경우의 결과 화면을 다르게 구현
- 구매한 상품의 상세 보기에 있는 취소 버튼을 클릭하면 AJAX 비동기 통신으로 상품이 삭제되고 주문 내역에서 보이지 않도록 구현
- 상품 취소가 완료되면 사용자의 현재 잔액이 취소한 상품의 가격만큼 증가 되도록 구현

<p align="center">
  <img src="https://github.com/user-attachments/assets/e7b16cdd-97ef-4bb9-b86e-907459e90de1" width="80%" height="80%">
</p>

<p align="center">
  <img src="https://github.com/user-attachments/assets/4d788562-0666-417a-984f-7142243f484f" width="80%" height="80%">
</p>

<br>

<p align="center">
  <img src="https://github.com/user-attachments/assets/8722e801-b251-4986-b178-ce217f944cc8" width="80%" height="80%">
</p>

<br>

<p align="center">
  <img src="https://github.com/user-attachments/assets/1a714c4f-6d5e-4ed8-82d3-7806d5663024" width="49%" height="49%">
  <img src="https://github.com/user-attachments/assets/a13c7550-25e5-4813-8ba1-354bcdf8d8d4" width="49%" height="49%">
</p>

<br><br>

---

📌 이미지, 상품 내용 사용 출처 (상업적으로 사용하지 않음)

[모두의 집밥](https://zipbab.com/)
