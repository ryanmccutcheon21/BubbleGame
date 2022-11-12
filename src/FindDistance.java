class FindDistance {
    private int x1, y1, x2, y2;
    private double distance;

    public FindDistance(int x1, int y1, int x2, int y2){
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
    }
    public double getDistance() {
        int xDiff = x1 - x2;
        int yDiff = y1 - y2;
        // make negative differences positive
        if(xDiff < 0){
            xDiff *= -1;
        } else if(yDiff < 0){
            yDiff += -1;
        }
        // D^2 = x^2 + y^2
        distance = Math.sqrt(Math.pow(xDiff, 2) + Math.pow(yDiff, 2));
        return distance;
    }
}
