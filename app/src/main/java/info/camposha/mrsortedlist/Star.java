package info.camposha.mrsortedlist;

public class Star {

    String name;
    int comments,favorites,views;

    public Star(String name,int comments,int favorites,int views) {
        this.name = name;
        this.comments=comments;
        this.favorites=favorites;
        this.views=views;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public int getFavorites() {
        return favorites;
    }

    public void setFavorites(int favorites) {
        this.favorites = favorites;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }
}
