package example;



public class EBook {
    private String title;
    private String author;
    private String content;
    private String styles;

    public EBook() {
    }

    public EBook(String title, String author, String content, String styles) {
        this.title = title;
        this.author = author;
        this.content = content;
        this.styles = styles;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStyles() {
        return styles;
    }

    public void setStyles(String styles) {
        this.styles = styles;
    }

}

























