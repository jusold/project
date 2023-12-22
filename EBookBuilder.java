package example;

public class EBookBuilder {
    private String title;
    private String author;
    private String content;
    private String styles;

    public EBookBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public EBookBuilder setAuthor(String author) {
        this.author = author;
        return this;
    }

    public EBookBuilder setContent(String content) {
        this.content = content;
        return this;
    }

    public EBookBuilder setStyles(String styles) {
        this.styles = styles;
        return this;
    }

    public EBook build() {
        return new EBook(title, author, content, styles);
    }
}



















