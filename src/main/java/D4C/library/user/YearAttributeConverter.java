package D4C.library.user;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class YearAttributeConverter implements AttributeConverter<Year, Integer> {
    @Override
    public Integer convertToDatabaseColumn(Year year) {
        if (year == null)
            return null;

        switch (year) {
            case ONE:
                return 1;

            case TWO:
                return 2;

            case THREE:
                return 3;

            case FOUR:
                return 4;

            case FIVE:
                return 5;

            case SIX:
                return 6;

            case SEVEN:
                return 7;

            default:
                throw new IllegalArgumentException(year + " not supported.");
        }
    }

    @Override
    public Year convertToEntityAttribute(Integer dbData) {
        if (dbData == null)
            return null;

        switch (dbData) {
            case 1:
                return Year.ONE;

            case 2:
                return Year.TWO;

            case 3:
                return Year.THREE;

            case 4:
                return Year.FOUR;

            case 5:
                return Year.FIVE;

            case 6:
                return Year.SIX;

            case 7:
                return Year.SEVEN;

            default:
                throw new IllegalArgumentException(dbData + " not supported.");
        }
    }

}