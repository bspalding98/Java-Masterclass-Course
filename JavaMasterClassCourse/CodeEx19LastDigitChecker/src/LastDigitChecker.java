public class LastDigitChecker {

    public static boolean hasSameLastDigit(int x, int y, int z) {

//        if((x < 10) || (x > 1000) || (y < 10) || (y > 1000) || (z < 10) || (z > 1000)) {
//            return false;
//        }
//
//        return (x % 10 == y % 10) || (x % 10 == z % 10) || (y % 10 == z % 10);
////        try other way

//        return ((x >= 10) && (x <= 1000) && (y >= 10) && (y <= 1000) && (z >= 10) && (z <= 1000)) &&
//                ((x % 10 == y % 10) || (x % 10 == z % 10) || (y % 10 == z % 10));

        return ((isValid(x)) && (isValid(y)) && (isValid(z))) &&
                ((x % 10 == y % 10) || (x % 10 == z % 10) || (y % 10 == z % 10));
    }

    public static boolean isValid(int x) {
        return (x >= 10 && x <= 1000);
    }
}
