package pmd.di.ubi.ubi5stars;

public class LocationCollection {
    private String name;
    private String category;
    private String description;
    private double lat;
    private double lon;
    private double sum;
    private double total;
    private String imageURL;

    public LocationCollection() {
    }

    public LocationCollection(String name, String category, String description, double lat, double lon, double sum, double total, String imageURL) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.lat = lat;
        this.lon = lon;
        this.sum = sum;
        this.total = total;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
