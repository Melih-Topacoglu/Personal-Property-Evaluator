package model;

public class UserPreference {

    private final double maxPrice;
    private final int maxCommuteTime;
    private final double minArea;
    private final int minRoomCount;
    private final boolean wantsQuietArea;
    private final boolean wantsPublicTransport;

    public UserPreference(double maxPrice, int maxCommuteTime, double minArea, int minRoomCount, boolean wantsQuietArea,
            boolean wantsPublicTransport) {

        if (!Double.isFinite(maxPrice) || maxPrice <= 0) {
            throw new IllegalArgumentException(
                    "Price must be a finite positive number");
        }
        this.maxPrice = maxPrice;
        if (maxCommuteTime < 0) {
            throw new IllegalArgumentException(
                    "Maximum commute time cannot be negative");
        }
        this.maxCommuteTime = maxCommuteTime;
        if (!Double.isFinite(minArea) || minArea < 0) {
            throw new IllegalArgumentException(
                    "Minimum area must be a finite positive number");
        }
        this.minArea = minArea;
        if (minRoomCount < 0) {
            throw new IllegalArgumentException("Room count must be a non-negative number");
        }
        this.minRoomCount = minRoomCount;
        this.wantsQuietArea = wantsQuietArea;
        this.wantsPublicTransport = wantsPublicTransport;
    }

    public double getMaxPrice() {
        return maxPrice;
    }

    public int getMaxCommuteTime() {
        return maxCommuteTime;
    }

    public double getMinArea() {
        return minArea;
    }

    public int getMinRoomCount() {
        return minRoomCount;
    }

    public boolean isWantsQuietArea() {
        return wantsQuietArea;
    }

    public boolean isWantsPublicTransport() {
        return wantsPublicTransport;
    }

    @Override
    public String toString() {
        return "UserPreference{" +
                "maxPrice=" + maxPrice +
                ", maxCommuteTime=" + maxCommuteTime +
                ", minArea=" + minArea +
                ", minRoomCount=" + minRoomCount +
                ", wantsQuietArea=" + wantsQuietArea +
                ", wantsPublicTransport=" + wantsPublicTransport +
                '}';
    }
}
