public class Book {
    //شماره کتاب
    public String ISBN;
    public String Name;
    public String Author;
    //خلاصه کتاب
    public String Abstract;
    public String AgeGroup;
    public BookStatus bookStatus;


    public Book(String ISBN, String Name, String Author, String Abstract, String AgeGroup, BookStatus bookStatus) {
        this.ISBN = ISBN;
        this.Name = Name;
        this.Author = Author;
        this.Abstract = Abstract;
        this.AgeGroup = AgeGroup;
        this.bookStatus = bookStatus;
    }
}
