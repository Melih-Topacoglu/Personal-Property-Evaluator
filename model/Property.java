package model;

public class Property {
    private final int id;
    private String district;
    private double price;
    private double area;
    private int roomCount;
    private double distanceToMetro;
    private int commuteTime;
    private boolean quietArea;

    public Property(int id, String district, double price, double area, int roomCount, double distanceToMetro,
            int commuteTime, boolean quietArea) {

        if (id <= 0) {
            throw new IllegalArgumentException(
                    "ID must be greater than zero");
        }
        this.id = id;
        setDistrict(district);
        setPrice(price);
        setArea(area);
        setRoomCount(roomCount);
        setDistanceToMetro(distanceToMetro);
        if (commuteTime < 0) {
            throw new IllegalArgumentException(
                    "Commute time cannot be negative");
        }
        this.commuteTime = commuteTime;
        this.quietArea = quietArea;
    }

    public int getId() {
        return this.id;
    }

    public String getDistrict() {
        return this.district;
    }

    public double getPrice() {
        return this.price;
    }

    public double getArea() {
        return this.area;
    }

    public int getRoomCount() {
        return this.roomCount;
    }

    public double getDistanceToMetro() {
        return this.distanceToMetro;
    }

    public int getCommuteTime() {
        return this.commuteTime;
    }

    public boolean isQuietArea() {
        return this.quietArea;
    }

    public void setDistrict(String district) {
        if (district == null || district.isBlank()) {
            throw new IllegalArgumentException(
                    "District cannot be null or blank");
        }
        this.district = district;
    }

    public void setPrice(double price) {
        if (!Double.isFinite(price) || price <= 0) {
            throw new IllegalArgumentException(
                    "Price must be a finite positive number");
        }
        this.price = price;
    }

    public void setArea(double area) {
        if (!Double.isFinite(area) || area <= 0) {
            throw new IllegalArgumentException(
                    "Area must be a finite positive number");
        }
        this.area = area;
    }

    public void setRoomCount(int roomCount) {
        if (roomCount <= 0) {
            throw new IllegalArgumentException(
                    "Room count must be greater than zero");
        }
        this.roomCount = roomCount;
    }

    public void setDistanceToMetro(double distanceToMetro) {
        if (!Double.isFinite(distanceToMetro) || distanceToMetro < 0) {
            throw new IllegalArgumentException(
                    "Distance to metro must be a finite non-negative number");
        }
        this.distanceToMetro = distanceToMetro;
    }

    public void setQuietArea(boolean quietArea) {
        this.quietArea = quietArea;
    }

    @Override
    public String toString() {
        return "Property{" +
                "id=" + id +
                ", district='" + district + '\'' +
                ", price=" + price +
                ", area=" + area +
                ", roomCount=" + roomCount +
                '}';
    }

}