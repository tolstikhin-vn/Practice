import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.layout.element.Text;

public class SetColor {
    private Color color;
    private Text text;

    public SetColor(Text text, Color color) {
        this.text = text;
        this.color = color;
    }

    public Text getText() {
        return this.text;
    }

    public Color getColor() {
        return this.color;
    }

    public static SetColor setColorForText(String colorOfText, Text text, Color color) {

        switch (colorOfText) {
            case "1" -> {
                text.setFontColor(ColorConstants.BLUE);
                color = ColorConstants.BLUE;
            }
            case "2" -> {
                text.setFontColor(ColorConstants.BLACK);
                color = ColorConstants.BLACK;
            }
            case "3" -> {
                text.setFontColor(ColorConstants.CYAN);
                color = ColorConstants.CYAN;
            }
            case "4" -> {
                text.setFontColor(ColorConstants.DARK_GRAY);
                color = ColorConstants.DARK_GRAY;
            }
            case "5" -> {
                text.setFontColor(ColorConstants.GRAY);
                color = ColorConstants.GRAY;
            }
            case "6" -> {
                text.setFontColor(ColorConstants.GREEN);
                color = ColorConstants.GREEN;
            }
            case "7" -> {
                text.setFontColor(ColorConstants.LIGHT_GRAY);
                color = ColorConstants.LIGHT_GRAY;
            }
            case "8" -> {
                text.setFontColor(ColorConstants.MAGENTA);
                color = ColorConstants.MAGENTA;
            }
            case "9" -> {
                text.setFontColor(ColorConstants.ORANGE);
                color = ColorConstants.ORANGE;
            }
            case "10" -> {
                text.setFontColor(ColorConstants.PINK);
                color = ColorConstants.PINK;
            }
            case "11" -> {
                text.setFontColor(ColorConstants.RED);
                color = ColorConstants.RED;
            }
            case "12" -> {
                text.setFontColor(ColorConstants.WHITE);
                color = ColorConstants.WHITE;
            }
            case "13" -> {
                text.setFontColor(ColorConstants.YELLOW);
                color = ColorConstants.YELLOW;
            }
            default -> System.out.println("Ошибка! Команда введена неверно.");
        }
        return new SetColor(text, color);
    }

    public static SetColor setColorForFilling(String colorOfFilling, Text text, Color color) {

        switch (colorOfFilling) {
            case "1" -> {
                text.setBackgroundColor(ColorConstants.BLUE);
                color = ColorConstants.BLUE;
            }
            case "2" -> {
                text.setBackgroundColor(ColorConstants.BLACK);
                color = ColorConstants.BLACK;
            }
            case "3" -> {
                text.setBackgroundColor(ColorConstants.CYAN);
                color = ColorConstants.CYAN;
            }
            case "4" -> {
                text.setBackgroundColor(ColorConstants.DARK_GRAY);
                color = ColorConstants.DARK_GRAY;
            }
            case "5" -> {
                text.setBackgroundColor(ColorConstants.GRAY);
                color = ColorConstants.GRAY;
            }
            case "6" -> {
                text.setBackgroundColor(ColorConstants.GREEN);
                color = ColorConstants.GREEN;
            }
            case "7" -> {
                text.setBackgroundColor(ColorConstants.LIGHT_GRAY);
                color = ColorConstants.LIGHT_GRAY;
            }
            case "8" -> {
                text.setBackgroundColor(ColorConstants.MAGENTA);
                color = ColorConstants.MAGENTA;
            }
            case "9" -> {
                text.setBackgroundColor(ColorConstants.ORANGE);
                color = ColorConstants.ORANGE;
            }
            case "10" -> {
                text.setBackgroundColor(ColorConstants.PINK);
                color = ColorConstants.PINK;
            }
            case "11" -> {
                text.setBackgroundColor(ColorConstants.RED);
                color = ColorConstants.RED;
            }
            case "12" -> {
                text.setBackgroundColor(ColorConstants.WHITE);
                color = ColorConstants.WHITE;
            }
            case "13" -> {
                text.setBackgroundColor(ColorConstants.YELLOW);
                color = ColorConstants.YELLOW;
            }
            default -> System.out.println("Ошибка! Команда введена неверно.");
        }
        return new SetColor(text, color);
    }
}
