import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.layout.element.Text;

import java.io.IOException;

public class SetFont {
    private PdfFont font;
    private Text text;

    public SetFont(Text text, PdfFont font) {
        this.text = text;
        this.font = font;
    }

    public Text getText() {
        return this.text;
    }

    public PdfFont getFont() {
        return this.font;
    }

    public static SetFont fontSelection(String titleOfFont, Text text, PdfFont font) throws IOException {
        switch (titleOfFont) {
            case "1" -> {
                text.setFont(PdfFontFactory.createFont(FontConstants.TIMES_ROMAN));
                font = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);
            }
            case "2" -> {
                text.setFont(PdfFontFactory.createFont(FontConstants.COURIER));
                font = PdfFontFactory.createFont(FontConstants.COURIER);
            }
            case "3" -> {
                text.setFont(PdfFontFactory.createFont(FontConstants.COURIER_BOLD));
                font = PdfFontFactory.createFont(FontConstants.COURIER_BOLD);
            }
            case "4" -> {
                text.setFont(PdfFontFactory.createFont(FontConstants.COURIER_BOLDOBLIQUE));
                font = PdfFontFactory.createFont(FontConstants.COURIER_BOLDOBLIQUE);
            }
            case "5" -> {
                text.setFont(PdfFontFactory.createFont(FontConstants.COURIER_OBLIQUE));
                font = PdfFontFactory.createFont(FontConstants.COURIER_OBLIQUE);
            }
            case "6" -> {
                text.setFont(PdfFontFactory.createFont(FontConstants.HELVETICA));
                font = PdfFontFactory.createFont(FontConstants.HELVETICA);
            }
            case "7" -> {
                text.setFont(PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD));
                font = PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD);
            }
            case "8" -> {
                text.setFont(PdfFontFactory.createFont(FontConstants.HELVETICA_BOLDOBLIQUE));
                font = PdfFontFactory.createFont(FontConstants.HELVETICA_BOLDOBLIQUE);
            }
            case "9" -> {
                text.setFont(PdfFontFactory.createFont(FontConstants.HELVETICA_OBLIQUE));
                font = PdfFontFactory.createFont(FontConstants.COURIER_OBLIQUE);
            }
            case "10" -> {
                text.setFont(PdfFontFactory.createFont(FontConstants.TIMES_BOLD));
                font = PdfFontFactory.createFont(FontConstants.TIMES_BOLD);
            }
            case "11" -> {
                text.setFont(PdfFontFactory.createFont(FontConstants.TIMES_BOLDITALIC));
                font = PdfFontFactory.createFont(FontConstants.TIMES_BOLDITALIC);
            }
            case "12" -> {
                text.setFont(PdfFontFactory.createFont(FontConstants.TIMES_ITALIC));
                font = PdfFontFactory.createFont(FontConstants.TIMES_ITALIC);
            }
            default -> System.out.println("Ошибка! Команда введена неверно.");
        }
        return new SetFont(text, font);
    }
}
