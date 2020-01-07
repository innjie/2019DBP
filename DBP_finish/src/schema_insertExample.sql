insert into authority values ('�Ѱ���', 'Y', 'Y', 'Y', 'Y', 'Y');
insert into authority values ('�뿩�������', 'Y', 'N', 'N', 'N', 'N');
insert into authority values ('��ü����', 'N', 'Y', 'N', 'N', 'N');
insert into authority values ('ȸ������', 'N', 'N', 'Y', 'N', 'N');
insert into authority values ('�ڵ�������', 'N', 'N', 'N', 'Y', 'N');
insert into authority values ('������', 'N', 'N', 'N', 'N', 'Y');
 
insert into license values (TO_DATE('2022/08/05'), '2������', '����-12-345678-90');
insert into license values (TO_DATE('2021/05/06'), '1������', '����-12-345678-91');
insert into license values (TO_DATE('2019/12/11'), '2������', '��õ-12-345678-92');
insert into license values (to_date('2021/05/08'), '1������', '���-12-345678-93');
 
insert into customer values ('helloworld', '�达', '010-0000-0000', 'N', 901003, '��', 'printf', '����-12-345678-90', 1000);
insert into customer values ('hiworld', '�̾�', '010-1111-1111', 'N', 761225, '��', 'system', '����-12-345678-91', 1001);
insert into customer values ('cyworld', '�ھ�', '010-2222-2222', 'Y', 830416, '��', 'out', '��õ-12-345678-92', 1002);
insert into customer values ('world', '����', '010-3333-3333', 'N', 980702, '��', 'print', '���-12-345678-93', 1003);
 
insert into manager values ('google', '�־�', 'seo2012', '010-4444-4444', 'drink', '�뿩�������', 9000);
insert into manager values ('naver', '�ξ�', 'inc1012', '010-5555-5555', 'never', '�Ѱ���', 9001);
insert into manager values ('facebook', '����', 'seo1012', '010-6666-6666', 'fb', 'ȸ������', 9002);
insert into manager values ('instagram', '����', 'seo1012', '010-7777-7777', 'insta', '�ڵ�������', 9003);
insert into manager values ('daum', '����', 'seo1012', '010-8888-8888', 'next', '��ü����', 9004);
insert into manager values ('youtube', '����', 'seo1012', '010-9999-9999', 'utube', '�Ѱ���', 9005);
 
insert into company values ('����', 'seo1012', '����1ȣ��', '02-000-0000');
insert into company values ('����', 'seo2012', '����2ȣ��', '02-000-1111');
insert into company values ( '��õ', 'inc1012', '��õ1ȣ��', '032-000-0000');
insert into company values ('�λ�', 'bus1012', '�λ�1ȣ��', '051-000-0000');
insert into company values ('����', 'jej1012', '����1ȣ��', '064-000-0000');
 
insert into model values('�ƹݶ�', 35, '����', 5, TO_date('2017', 'YYYY'));
insert into model values('��Ÿ����', 11, '����', 12, TO_date('2017', 'YYYY'));
insert into model values('k7', 41, '���', 5, TO_date('2015', 'YYYY'));
insert into model values('����', 19, '����', 5, TO_date('2019', 'YYYY'));
insert into model values('���', 27, '���', 4, TO_date('2017', 'YYYY'));
insert into model values('bmw x6 m', 9, 'bmw', 5, TO_date('2018', 'YYYY'));
insert into model values('sm5', 12, '����Ｚ', 5, TO_date('2018', 'YYYY'));
 
insert into car values('11��1111', '70000', 'Y', 'white', '�ƹݶ�', 9003, 2);
insert into car values('22��2222', '100000', 'Y', 'white', '��Ÿ����', 9003, 15);
insert into car values('33��3333', '70000', 'N', 'white', 'k7', 9005, 3);
insert into car values('44��4444', '80000', 'N', 'white', '����', 9005, 2);
insert into car values('55��5555', '40000', 'Y', 'black', '���', 9003, 3);
insert into car values('66��6666', '38000', 'N', 'red', '���', 9003, 10);
insert into car values('77��7777', '170000', 'N', 'black', 'bmw x6 m', 9005, 8);
insert into car values('88��8888', '170000', 'N', 'white', 'bmw x6 m', 9001, 3);
insert into car values('99��9999', '150000', 'Y', 'white', 'sm5', 9001, 3);
 
insert into penalty values('��ü����', null, null, '0');
insert into penalty values('1ȸ, 3������', null, '�ϴ����*1.1', '1');
insert into penalty values('3ȸ����, 7������', null, '�ϴ����*1.3', '2');
insert into penalty values('5ȸ����, 15�� ����', '3�ϰŷ��Ұ�', '�ϴ����*1.5', '3');
insert into penalty values('7ȸ����, 20�� ����', '�����ϰŷ��Ұ�', '�ϴ����*2', '4');
insert into penalty values('10ȸ����, 30������', '�Ѵްŷ��Ұ�', '�ϴ����*3', '5');
insert into penalty values('10ȸ�ʰ�, 30���ʰ�', '����Ż��, �ŷ��Ұ�', '�ϴ����*5', 'blascklist');
 
insert into overdue values('7��', 3, '2', 1002, 9004);
insert into overdue values('2��', 1, '1', 1003, 9005);
 
insert into reserve values('rs1000', to_date('2019/09/25', 'YYYY/MM/DD'), to_date('2019/10/03', 'YYYY/MM/DD'), to_date('2019/10/08', 'YYYY/MM/DD'), '66��6666', 1001, 9000);
insert into reserve values('rs1001', to_date('2019/10/01', 'YYYY/MM/DD'), to_date('2019/10/02', 'YYYY/MM/DD'), to_date('2019/10/03', 'YYYY/MM/DD'), '44��4444', 1000, 9001);
insert into reserve values('rs1002', to_date('2019/10/03', 'YYYY/MM/DD'), to_date('2019/10/15', 'YYYY/MM/DD'), to_date('2019/10/18', 'YYYY/MM/DD'), '11��1111', 1000, 9000);
insert into rent values('rn1000', to_date('2019/10/01', 'YYYY/MM/DD'), to_date('2019/10/10', 'YYYY/MM/DD'), '��ȣ��', null, '33��3333', 1003, 9000);
insert into rent values('rn1001', to_date('2019/10/02', 'YYYY/MM/DD'), to_date('2019/10/03', 'YYYY/MM/DD'), '�̾�', 'rs1001', '44��4444', 1000, 9001);
insert into rent values('rn1002', to_date('2019/10/02', 'YYYY/MM/DD'), to_date('2019/10/02', 'YYYY/MM/DD'), '�ھ�', null, '77��7777', 1002, 9000);
insert into rent values('rn1003', to_date('2019/10/03', 'YYYY/MM/DD'), to_date('2019/10/08', 'YYYY/MM/DD'), '����', 'rs1000', '66��6666', 1001, 9000);
 
insert into QnA_manager values(1000, 9001, 3031);
insert into QnA_manager values(1002, 9001, 3032);
insert into QnA_manager values(1003, 9005, 3033);
 
insert into QnA values('�ڵ����� ���� ���� ������ �� �ִ� �ð��� �����ΰ���??', 'Y', null, to_date('2019/10/01', 'YYYY/MM/DD'), '�������� ���� �ð� 1�ð� ���ĺ��� �����մϴ�.', 3031);
 
insert into QnA values('�г�Ƽ�� Ż��� �� �������� �簡�� �����Ѱ���???', 'Y', 5, to_date('2019/10/03', 'YYYY/MM/DD'), '������ Ż��� ��¥�κ��� 1�� �ĺ��� �簡�� �����մϴ�.', 3032);
insert into QnA values('�뿩�� �����Ϸ��� ��� �ؾ��ϳ���??', 'N', null, to_date('2019/10/08', 'YYYY/MM/DD'), null, 3033);
