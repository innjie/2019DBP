insert into authority values ('총관리', 'Y', 'Y', 'Y', 'Y', 'Y');
insert into authority values ('대여예약관리', 'Y', 'N', 'N', 'N', 'N');
insert into authority values ('연체관리', 'N', 'Y', 'N', 'N', 'N');
insert into authority values ('회원관리', 'N', 'N', 'Y', 'N', 'N');
insert into authority values ('자동차관리', 'N', 'N', 'N', 'Y', 'N');
insert into authority values ('상담관리', 'N', 'N', 'N', 'N', 'Y');
 
insert into license values (TO_DATE('2022/08/05'), '2종보통', '서울-12-345678-90');
insert into license values (TO_DATE('2021/05/06'), '1종보통', '서울-12-345678-91');
insert into license values (TO_DATE('2019/12/11'), '2종보통', '인천-12-345678-92');
insert into license values (to_date('2021/05/08'), '1종보통', '경기-12-345678-93');
 
insert into customer values ('helloworld', '김씨', '010-0000-0000', 'N', 901003, '여', 'printf', '서울-12-345678-90', 1000);
insert into customer values ('hiworld', '이씨', '010-1111-1111', 'N', 761225, '남', 'system', '서울-12-345678-91', 1001);
insert into customer values ('cyworld', '박씨', '010-2222-2222', 'Y', 830416, '남', 'out', '인천-12-345678-92', 1002);
insert into customer values ('world', '조씨', '010-3333-3333', 'N', 980702, '남', 'print', '경기-12-345678-93', 1003);
 
insert into manager values ('google', '최씨', 'seo2012', '010-4444-4444', 'drink', '대여예약관리', 9000);
insert into manager values ('naver', '민씨', 'inc1012', '010-5555-5555', 'never', '총관리', 9001);
insert into manager values ('facebook', '오씨', 'seo1012', '010-6666-6666', 'fb', '회원관리', 9002);
insert into manager values ('instagram', '유씨', 'seo1012', '010-7777-7777', 'insta', '자동차관리', 9003);
insert into manager values ('daum', '류씨', 'seo1012', '010-8888-8888', 'next', '연체관리', 9004);
insert into manager values ('youtube', '강시', 'seo1012', '010-9999-9999', 'utube', '총관리', 9005);
 
insert into company values ('서울', 'seo1012', '서울1호점', '02-000-0000');
insert into company values ('서울', 'seo2012', '서울2호점', '02-000-1111');
insert into company values ( '인천', 'inc1012', '인천1호점', '032-000-0000');
insert into company values ('부산', 'bus1012', '부산1호점', '051-000-0000');
insert into company values ('제주', 'jej1012', '제주1호점', '064-000-0000');
 
insert into model values('아반떼', 35, '현대', 5, TO_date('2017', 'YYYY'));
insert into model values('스타렉스', 11, '현대', 12, TO_date('2017', 'YYYY'));
insert into model values('k7', 41, '기아', 5, TO_date('2015', 'YYYY'));
insert into model values('베뉴', 19, '현대', 5, TO_date('2019', 'YYYY'));
insert into model values('모닝', 27, '기아', 4, TO_date('2017', 'YYYY'));
insert into model values('bmw x6 m', 9, 'bmw', 5, TO_date('2018', 'YYYY'));
insert into model values('sm5', 12, '르노삼성', 5, TO_date('2018', 'YYYY'));
 
insert into car values('11서1111', '70000', 'Y', 'white', '아반떼', 9003, 2);
insert into car values('22서2222', '100000', 'Y', 'white', '스타렉스', 9003, 15);
insert into car values('33서3333', '70000', 'N', 'white', 'k7', 9005, 3);
insert into car values('44서4444', '80000', 'N', 'white', '베뉴', 9005, 2);
insert into car values('55서5555', '40000', 'Y', 'black', '모닝', 9003, 3);
insert into car values('66서6666', '38000', 'N', 'red', '모닝', 9003, 10);
insert into car values('77서7777', '170000', 'N', 'black', 'bmw x6 m', 9005, 8);
insert into car values('88서8888', '170000', 'N', 'white', 'bmw x6 m', 9001, 3);
insert into car values('99서9999', '150000', 'Y', 'white', 'sm5', 9001, 3);
 
insert into penalty values('연체없음', null, null, '0');
insert into penalty values('1회, 3일이하', null, '일당원가*1.1', '1');
insert into penalty values('3회이하, 7일이하', null, '일당원가*1.3', '2');
insert into penalty values('5회이하, 15일 이하', '3일거래불가', '일당원가*1.5', '3');
insert into penalty values('7회이하, 20일 이하', '일주일거래불가', '일당원가*2', '4');
insert into penalty values('10회이하, 30일이하', '한달거래불가', '일당원가*3', '5');
insert into penalty values('10회초과, 30일초과', '강제탈퇴, 거래불가', '일당원가*5', 'blascklist');
 
insert into overdue values('7일', 3, '2', 1002, 9004);
insert into overdue values('2일', 1, '1', 1003, 9005);
 
insert into reserve values('rs1000', to_date('2019/09/25', 'YYYY/MM/DD'), to_date('2019/10/03', 'YYYY/MM/DD'), to_date('2019/10/08', 'YYYY/MM/DD'), '66서6666', 1001, 9000);
insert into reserve values('rs1001', to_date('2019/10/01', 'YYYY/MM/DD'), to_date('2019/10/02', 'YYYY/MM/DD'), to_date('2019/10/03', 'YYYY/MM/DD'), '44서4444', 1000, 9001);
insert into reserve values('rs1002', to_date('2019/10/03', 'YYYY/MM/DD'), to_date('2019/10/15', 'YYYY/MM/DD'), to_date('2019/10/18', 'YYYY/MM/DD'), '11서1111', 1000, 9000);
insert into rent values('rn1000', to_date('2019/10/01', 'YYYY/MM/DD'), to_date('2019/10/10', 'YYYY/MM/DD'), '강호동', null, '33서3333', 1003, 9000);
insert into rent values('rn1001', to_date('2019/10/02', 'YYYY/MM/DD'), to_date('2019/10/03', 'YYYY/MM/DD'), '이씨', 'rs1001', '44서4444', 1000, 9001);
insert into rent values('rn1002', to_date('2019/10/02', 'YYYY/MM/DD'), to_date('2019/10/02', 'YYYY/MM/DD'), '박씨', null, '77서7777', 1002, 9000);
insert into rent values('rn1003', to_date('2019/10/03', 'YYYY/MM/DD'), to_date('2019/10/08', 'YYYY/MM/DD'), '조씨', 'rs1000', '66서6666', 1001, 9000);
 
insert into QnA_manager values(1000, 9001, 3031);
insert into QnA_manager values(1002, 9001, 3032);
insert into QnA_manager values(1003, 9005, 3033);
 
insert into QnA values('자동차를 가장 빨리 수령할 수 있는 시간은 언제인가요??', 'Y', null, to_date('2019/10/01', 'YYYY/MM/DD'), '수령지의 오픈 시간 1시간 이후부터 가능합니다.', 3031);
 
insert into QnA values('패널티로 탈퇴된 후 언제부터 재가입 가능한가요???', 'Y', 5, to_date('2019/10/03', 'YYYY/MM/DD'), '강제로 탈퇴된 날짜로부터 1년 후부터 재가입 가능합니다.', 3032);
insert into QnA values('대여를 연장하려면 어떻게 해야하나요??', 'N', null, to_date('2019/10/08', 'YYYY/MM/DD'), null, 3033);
