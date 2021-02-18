package be.dog.d.steven.exam1Z0_816and1Z0_817.chapter1;

public enum Season {
    SUMMER("high"),
    FALL("medium"),
    WINTER("low"),
    SPRING("medium");

    private final String visitors;

    Season(String visitors) {
        System.out.println("And god made a season...");
        this.visitors = visitors;
    }

    public String getVisitors(){
        return visitors;
    }
}
