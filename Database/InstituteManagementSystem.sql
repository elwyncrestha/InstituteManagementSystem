use imsDB;
show tables;

show fields from course_table;

select * from course_table;

create table student_table
(
	student_id int not null primary key auto_increment,
    student_name varchar(50),
    student_country varchar(20),
    student_gender varchar(10),
    student_hobbies varchar(100)
);

show fields from student_table;

create table subject_table
(
	subject_code varchar(100) not null primary key,
    subject_name varchar(500)
);

insert into subject_table values('JAVA','Java Programming Language'),('Database','Database Management System'),('Communication','Communication skills'),('Networking','Network & Security');
select * from subject_table;

create table exam_table
(
	subject_code varchar(100),
    question_id int not null primary key auto_increment,
    question_name varchar(100),
    question_answer1 varchar(200),
    question_answer2 varchar(200),
    question_answer3 varchar(200),
    question_answer4 varchar(200),
    question_correct_answer varchar(50),
    constraint fk_subject_code foreign key(subject_code) references subject_table(subject_code) on delete cascade
);
select * from exam_table;
show tables;
show fields from course_table;
show fields from student_table;

create table student_course
(
	student_course_id int not null primary key auto_increment,
    student_id int,
    course_id int,
    constraint fk_student_id foreign key(student_id) references student_table(student_id) on delete cascade,
    constraint fk_course_id foreign key(course_id) references course_table(course_id) on delete cascade
);

select * from student_course;
select * from course_table;
select * from student_table;
insert into student_course(student_course_id,student_id,course_id) values(1,3,4);


select sc.student_course_id, st.student_id, st.student_name, ct.course_id, ct.course_title from 
student_table st, course_table ct, student_course sc where 
st.student_id=sc.student_id and ct.course_id=sc.course_id;

create table login_table
(
	first_name varchar(255) not null,
    last_name varchar(255) not null,
    email varchar(255) not null,
	user_username varchar(255) not null primary key,
    user_password varchar(255) not null
);
select * from login_table;
select user_username, user_password from login_table;
show fields from login_table;

create table subscription_table
(
	subscription_name varchar(255) not null,
    subscription_email varchar(255) not null,
    constraint ck_name_email primary key(subscription_name,subscription_email)
);

show tables;
select * from subscription_table;