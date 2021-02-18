package be.dog.d.steven.exam1Z0_816and1Z0_817.chapter1;

public enum Week {
    MONDAY{
        @Override
        public void greet() {
            System.out.println("It's monday, let's do this!");
        }
    },
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY{
        @Override
        public void greet() {
            System.out.println("Have a nice weekend!");
        }
    },
    SUNDAY{
        @Override
        public void greet() {
            System.out.println("Have a nice weekend!");
        }
    };

    public void greet(){
        System.out.println("Have a nice day!");
    }
}
