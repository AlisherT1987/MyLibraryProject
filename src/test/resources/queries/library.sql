select book_categories.name,count(book_category_id) as "Total amount" from books left outer join book_borrow bb on books.id = bb.book_id left outer join book_categories on books.book_category_id = book_categories.id
where  is_returned=0
group by book_categories.name
order by 2 desc;
select count(*) from book_borrow
where is_returned=0;
