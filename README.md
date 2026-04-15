mvn clean install
mvn spring-boot:run

CRUD Operations-
CREATE
READ
UPDATE
DELETE

sql file to create db tables

1)POST 
http://localhost:9545/api/books/create
{
    "author" : "Sudha Murthy",
    "title" : "Grandmas Bag of stories",
    "isbn" : "sdfhb876mdb0987",
    "publishedYear":"2012",
    "availabilityStatus" : "AVAILABILITY_YES"

}
2)GET
http://localhost:9545/api/books?partialSearchText=jeffrey&title=&author=&publishedYear=&page=&size=

3)DELETE
http://localhost:9545/api/books/update/bookavailability?bookId=5&availabilityStatus=Y

4)PUT
http://localhost:9545/api/books/delete?bookId=10
