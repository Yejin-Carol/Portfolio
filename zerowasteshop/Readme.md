# :shopping_cart: Zero Waste Shopping Mall :shopping_cart:

### Spring boot로 구현한 제로웨이스트 쇼핑몰
### [Demo 영상](https://drive.google.com/file/d/1X87e8Imd2t58-8d0RaVAg_IdjYCTVi9V/view?usp=sharing) 보기 click</br>

## 1. 제작 기간 & 참여인원

- 제작기간 : 2021년 10월 16일 ~ 11월 14일
- 개인 프로젝트</br>
 
## 2. 사용한 기술
**`Back-end`** 
* Java 11
* Spring Boot 2.5.2
* Maven
* Spring Data JPA
* H2
* MySQL 8.0.27
* Spring Security
* Thymeleaf

**`Front-end`**
* Bootstrap
* HTML/CSS
* Javascript</br>

## 3. ERD

![zerowasteshop ERD](https://user-images.githubusercontent.com/81130006/141724686-e9681d57-3d92-402e-b4c6-e341e4f30dc6.png)</br>

## 4. 주요 기능</br>
### 4.1 Overview flow

  - ![Untitled Diagram drawio](https://user-images.githubusercontent.com/81130006/141723882-a2e03ff4-58e8-42b0-a47f-eccab64bbb99.png)</br>

### 4.2 로그인 (Spring Security 설정) & 회원가입</br>
<details>
<summary><b>상세 설명 보기</b></summary>

* 로그인</br>
    * WebSecurityConfigurerAdapter 상속받는 클래스에 @EnableWebSecurity 선언시 SpringSecurityFilterChain 자동 포함
    * BCryptPasswordEncoder의 해시 함수를 이용해 비밀번호 암호화
    * ![image](https://user-images.githubusercontent.com/81130006/141791611-abe35f37-228a-4800-abc0-b543f0c99d4b.png) 
    </br>   
* 회원가입 </br>
    * Member 엔터티, 회원 유일성 위해 이메일 @Column(unique = true) 설정, MemberService 이미 가입된 회원인 경우 IllegalStateException 예외 발생시킴-> [코드 확인📑](https://github.com/Yejin-Carol/portfolio/blob/a5c12bb2c438c1f754def601d4118fef1aa6d3e4/zerowasteshop/src/main/java/com/zerowasteshop/service/MemberService.java#L25) 
    * 회원 Role의 경우, 최초 DB entity (table) 생성시 ADMIN으로 설정하고, USER로 변경

<div markdown="1">
</details>  
  </br>

### 4.3 상품 검색 (Main & Admin Page, Main 위주 설명)

<details>
<summary><b>상세 설명 보기</b></summary>

* 상품 조회 조건이 있는 ItemSearchDto 객체와 페이징 객체 담은 ItemRepositoryCustom인 사용자 정의 인터페이스를 구현하고 상속받는 ItemRepositoryCustomImpl 클래스 작성 (Querydsl Spring Data Jpa와 함께 사용)-> [코드 확인📑](https://github.com/Yejin-Carol/portfolio/blob/a5c12bb2c438c1f754def601d4118fef1aa6d3e4/zerowasteshop/src/main/java/com/zerowasteshop/repository/ItemRepositoryCustomImpl.java#L23)
* ItemService 클래스에 Main Page 보여줄 상품 데이터 조회 메소드 추가-> [코드 확인📑](https://github.com/Yejin-Carol/portfolio/blob/a5c12bb2c438c1f754def601d4118fef1aa6d3e4/zerowasteshop/src/main/java/com/zerowasteshop/service/ItemService.java#L86)
* 상품 데이터 보여주기 위해 MainController 클래스 업데이트 후 main.html SearchQuery를 통해 상품 검색시 확인 가능-> [코드 확인📑](https://github.com/Yejin-Carol/portfolio/blob/a5c12bb2c438c1f754def601d4118fef1aa6d3e4/zerowasteshop/src/main/resources/templates/main.html#L65)
    <br>

<div markdown="1">
</details>  
  </br>
  
### 4.4 주문 (order)

<details>
<summary><b>상세 설명 보기</b></summary>

  - 주문시 재고 감소를 위해 RuntimeException 상속받는 OutOfStockException 클래스 생성
  - Item 엔터티 상품 재고 감소시키는 비즈니스 로직 removeStock() 작성 후 OrderItem 객체에 주문할 상품과 주문 수량 세팅-> [코드 확인📑](https://github.com/Yejin-Carol/portfolio/blob/a5c12bb2c438c1f754def601d4118fef1aa6d3e4/zerowasteshop/src/main/java/com/zerowasteshop/entity/Item.java#L50)
  - Order/OrderItems -> OrderRepository -> OrderService -> OrderController 구현 후, 기존 상품 상세 페이지 view에 해당하는 itemDtl.html에서 주문 로직 Ajax 이용 비동기 방식으로 호출 (웹 페이지의 새로 고침 없이 필요한 부분만 불어와 사용)-> [코드 확인📑](https://github.com/Yejin-Carol/portfolio/blob/a5c12bb2c438c1f754def601d4118fef1aa6d3e4/zerowasteshop/src/main/resources/templates/item/itemDtl.html#L42)
<div markdown="1">
</details>  
  </br>
  
### 4.5 장바구니 (cart)

<details>
<summary><b>상세 설명 보기</b></summary>

  - 장바구니 상품 담기, 주문, 삭제 기능
  - Cart/CartItems -> Cart/CartItemRepository -> CartService -> CartController
  - CartService, 장바구니 이미 존재 여부 확인 후 있으면 수량 증가. 없으면 CartItem 객체 생성-> [코드 확인📑](https://github.com/Yejin-Carol/portfolio/blob/a5c12bb2c438c1f754def601d4118fef1aa6d3e4/zerowasteshop/src/main/java/com/zerowasteshop/service/CartService.java#L48)
  - CartController, 장바구니 최소 수량 1개 이상 되도록 설정
  - 장바구니 수량 변경시 AJAX PATCH TYPE으로 설정 ->[코드 확인📑](https://github.com/Yejin-Carol/portfolio/blob/a5c12bb2c438c1f754def601d4118fef1aa6d3e4/zerowasteshop/src/main/resources/templates/cart/cartList.html#L60)

<div markdown="1">
</details>  
  </br>

## 5. Troubleshooting

- spring.jpa.hibernate.ddl-auto 초기 설정시 create로 ADMIN로 설정과 상품 등록 후 validate 설정 변경하지 않아, 재설정 (create와 create-drop 차이로 착각)
- 상품 수정 업데이트 적용되지 않고 오류 그대로 발생되는 문제. Controller 업데이트시 상품 수정 로직 호출되는 try-catch 문 추가로 해결-> [코드 확인📑](https://github.com/Yejin-Carol/portfolio/blob/a5c12bb2c438c1f754def601d4118fef1aa6d3e4/zerowasteshop/src/main/java/com/zerowasteshop/controller/ItemController.java#L87)
- Thymeleaf layout 적용 문제가 생겨 버전 downgrade와 template engine 재설정으로 해결.
- Bootstrap 슬라이딩 컨트롤(Carousel) 연속 설정시 이미지 파일 깨짐 현상. 로컬 이미지 말고 호스팅된 이미지 주소 변경 후 해결됨.

## 6. 회고/아쉬운 점

- 상품 이미지 등록시 동일 이미지 중복 저장되는 처리 해결하지 못함.
- Thymeleaf 문법이 익숙치 않아 간단한 기능 (number Formation 등)구현에서 시간이 많이 걸렸음.
- 프론트가 많이 부족하다는 점을 인지하고 있으며 풀스택이 가능한 백엔드 개발자를 위해 프론트 지식도 쌓을 필요가 있음을 절실히 깨닫게 됨.
- 추후 게시판 기능 및 OAuth2.0, 주문 결제 기능까지 확장시켜 나가고 싶음.
