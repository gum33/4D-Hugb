/*Category class, contains one category
A trip includes many Catogry classes in array*/

public class Category {
    private String keyword;
    public Category(String k) {
        keyword = k;
    }

    public void setKeyword(String k) {
        keyword=k;
    }

    public String getCategory() {
        return keyword;
    }

    public static void main(String[] args) {
        
    }
}