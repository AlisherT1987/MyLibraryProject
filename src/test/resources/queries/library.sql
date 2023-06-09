select book_categories.name,count(book_category_id) as "Total amount" from books left outer join book_borrow bb on books.id = bb.book_id left outer join book_categories on books.book_category_id = book_categories.id
where  is_returned=0
group by book_categories.name
order by 2 desc;
select count(*) from book_borrow
where is_returned=0;
select bc.name,count(*) from book_borrow bb inner join books b on bb.book_id = b.id
                                            inner join book_categories bc on b.book_category_id=bc.id group by name
order by 2 desc;
select count(distinct id) from users;
select *from users;
select *from books
where name like 'Stay Strong';
select *from book_borrow
where book_id=10198;
select name from book_categories;
select books.name,book_categories.name from library2.book_categories,library2.books;
select name from books
where name in ('Head First Java','The Scrum Field Guide')
group by name;