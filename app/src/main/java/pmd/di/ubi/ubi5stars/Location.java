package pmd.di.ubi.ubi5stars;

public class Location {
    private String name;
    private String description;
    private String imageURL;

    public Location() {
    }

    public Location(String name, String description, String imageURL) {
        if (name.trim().equals("")) {
            name = "No Name";
        }

        this.name = name;
        this.description = description;
        this.imageURL = imageURL;
    }

    public String getName() {
        return name;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
