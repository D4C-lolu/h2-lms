package D4C.library.user;

/**
 * An enum representing the class of a user
 */
public enum Year {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7);

    final int val;

    Year(int val) {
        this.val = val;
    }
}