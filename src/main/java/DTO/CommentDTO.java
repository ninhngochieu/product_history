package DTO;

public class CommentDTO extends Object{
    private String id;
    private String title;
    private String content;
    private int thank_count;
    private float rating;
    private String id_product;

    public CommentDTO(String id, String title, String content, int thank_count, float rating, String id_product) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.thank_count = thank_count;
        this.rating = rating;
        this.id_product = id_product;
    }

    @Override
    public String toString() {
        return "CommentDTO{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", thank_count=" + thank_count +
                ", rating=" + rating +
                ", id_product='" + id_product + '\'' +
                '}';
    }

    public String getId_product() {
        return id_product;
    }

    public void setId_product(String id_product) {
        this.id_product = id_product;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getThank_count() {
        return thank_count;
    }

    public void setThank_count(int thank_count) {
        this.thank_count = thank_count;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

}
