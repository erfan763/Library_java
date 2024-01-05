public class Book {
    //شماره کتاب
    public int ISBN;
    public String Name;
    public String Author;
    //خلاصه کتاب
    public String Abstract;
    public AgeGroup ageGroup;
    public BookStatus bookStatus;

    public Book() {
        this.ISBN = 0;
        this.Name = "";
        this.Author = "";
        this.Abstract = "";
        this.ageGroup = AgeGroup.ADULT;
        this.bookStatus = BookStatus.AVAILABLE;
    }

    public Book(int ISBN, String Name, String Author, String Abstract, AgeGroup ageGroup, BookStatus bookStatus) {
        this.ISBN = ISBN;
        this.Name = Name;
        this.Author = Author;
        this.Abstract = Abstract;
        this.ageGroup = ageGroup;
        this.bookStatus = bookStatus;
    }

    @Override
    public String toString() {
        return "ISBN: " + ISBN +
                ", Name: " + Name +
                ", Author: " + Author +
                ", Abstract: " + Abstract +
                ", AgeGroup: " + ageGroup +
                ", BookStatus: " + bookStatus;
    }

}
