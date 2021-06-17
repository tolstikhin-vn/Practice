import com.itextpdf.io.IOException;
import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfString;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.action.PdfAction;
import com.itextpdf.kernel.pdf.annot.*;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.property.TextAlignment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class CreatePdf {

    public void listOfKeyword() {
        System.out.println("""
                ФУНКЦИОНАЛ
                new-pdf - создать PDF файл
                make-pdf - конвертировать TXT в PDF
                add-text - добавить текст
                add-picture - добавить изображение
                add-list - добавить список
                add-table - добавить таблицу
                add-annotation - добавить аннотацию
                add-page-break - добавить разрыв страницы
                add-page - добавить новую страницу
                end - завершить работу с файлом""");
    }

    public void waysToFormatText() {
        System.out.println("""
                СПИСОК КОММАНД ДЛЯ ФОРМАТИРОВАНИЯ ТЕКСТА
                font - добавить шрифт
                size - изменить размер шрифта
                color-text - добавить цвет шрифта
                color-fill - добавить цвет заливки текста""");
    }

    public void listOfColors() {
        System.out.println("""
                СПИСОК ЦВЕТОВ
                1 - синий (blue)
                2 - черный (black)
                3 - бирюзовый (cyan)
                4 - темно-серый (dark grey)
                5 - серый (gray)
                6 - зеленый (green)
                7 - светло-серый (light grey)
                8 - маджента (magenta)
                9 - оранжевый (orange)
                10 - розовый (pink)
                11 - красный (red)
                12 - белый (white)
                13 - желтый (yellow)""");
    }

    public void listOfFonts() {
        System.out.println("""
                СПИСОК ШРИФТОВ
                1 - Times New Roman
                2 - Courier
                3 - Courier Bold
                4 - Courier Bold Oblique
                5 - Courier Oblique
                6 - Helvetica
                7 - Helvetica Bold
                8 - Helvetica Bold Oblique
                9 - Helvetica Oblique
                10 - Times Bold
                11 - Times Bold Italic
                12 - Times Italic""");
    }

    public void convertTxtToPdf(String pdfFilePath, String txtFilePath) throws IOException, java.io.IOException {
        PdfDocument pdf = new PdfDocument(new PdfWriter(pdfFilePath));
        Document document = new Document(pdf).setTextAlignment(TextAlignment.JUSTIFIED);
        File file = new File(txtFilePath);
        BufferedReader br = new BufferedReader(new FileReader(file));
        PdfFont normal = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);
        PdfFont bold = PdfFontFactory.createFont(FontConstants.TIMES_BOLD);
        String line;
        boolean title = true;
        while ((line = br.readLine()) != null) {
            document.add(new Paragraph(line).setFont(title ? bold : normal));
            title = line.isEmpty();
        }
        document.close();
    }

    public Color setColorOfAnnotation(String colorOfAnnotation) {
        Color color = null;
        switch (colorOfAnnotation) {
            case "1" -> color = ColorConstants.BLUE;
            case "2" -> color = ColorConstants.BLACK;
            case "3" -> color = ColorConstants.CYAN;
            case "4" -> color = ColorConstants.DARK_GRAY;
            case "5" -> color = ColorConstants.GRAY;
            case "6" -> color = ColorConstants.GREEN;
            case "7" -> color = ColorConstants.LIGHT_GRAY;
            case "8" -> color = ColorConstants.MAGENTA;
            case "9" -> color = ColorConstants.ORANGE;
            case "10" -> color = ColorConstants.PINK;
            case "11" -> color = ColorConstants.RED;
            case "12" -> color = ColorConstants.WHITE;
            case "13" -> color = ColorConstants.YELLOW;
            default -> System.out.println("Ошибка! Команда введена неверно.");
        }
        return color;
    }

    public void addingAnAnnotation(Scanner in, PdfPage page, PdfAnnotation annotation) {
        String title;
        String textOfAnnotation;
        System.out.print("Введите заголовок аннотации.\nВвод: ");
        title = in.nextLine();
        annotation.setTitle(new PdfString(title));
        System.out.print("Введите текст аннотации.\nВвод: ");
        textOfAnnotation = in.nextLine();
        annotation.setContents(textOfAnnotation);
        page.addAnnotation(annotation);
    }

    public void annotationType1(Scanner in, Document document) {
        String textOfAnnotation;
        // Создание объекта PdfLinkAnnotation
        final int width = 0;
        final int height = 0;
        Rectangle rect = new Rectangle(width, height);
        PdfLinkAnnotation annotation = new PdfLinkAnnotation(rect);
        System.out.print("Введите ссылку: ");
        String linkSite = in.nextLine();
        // Настройка действия аннотации
        PdfAction action = PdfAction.createURI(linkSite);
        annotation.setAction(action);
        // Создание ссылки
        Link link = new Link("Click here", annotation);
        System.out.print("Введите текст к ссылочной аннотации.\nВвод: ");
        textOfAnnotation = in.nextLine();
        // Создание абзаца
        Paragraph paragraph = new Paragraph(textOfAnnotation);
        // Добавление ссылки в абзац
        paragraph.add(link.setUnderline());
        // Добавление абзаца в документ
        document.add(paragraph);
        System.out.println("Аннотация добавлена.");
    }

    public void annotationType2(Scanner in, PdfPage page) {
        final int xCoord = 20;
        final int yCoord = 800;
        final int width = 0;
        final int height = 0;
        // Создание объекта PdfTextAnnotation
        Rectangle rect = new Rectangle(xCoord, yCoord, width, height);
        PdfAnnotation annotation = new PdfTextAnnotation(rect);
        addingAnAnnotation(in, page, annotation);
        // Устанавливаем текст
        page.addAnnotation(annotation);
    }

    public void annotationType3(Scanner in, PdfPage page, Color color) {
        final int xCoord = 105;
        final int yCoord = 790;
        final int width = 64;
        final int height = 10;
        Rectangle rect = new Rectangle(xCoord, yCoord, width, height);
        float[] floatArray = new float[]{169, 790, 105, 790, 169, 800, 105, 800};
        PdfAnnotation annotation = PdfTextMarkupAnnotation.createHighLight(rect, floatArray);
        annotation.setColor(color);
        String textOfAnnotation;
        System.out.print("Введите текст аннотации.\nВвод: ");
        textOfAnnotation = in.nextLine();
        annotation.setContents(textOfAnnotation);
        page.addAnnotation(annotation);
        System.out.println("Аннотация добавлена.");
    }

    public void annotationType4(Scanner in, PdfPage page, Color color) {
        final int xCoord = 105;
        final int yCoord = 770;
        final int width = 50;
        final int height = 50;
        Rectangle rect = new Rectangle(xCoord, yCoord, width, height);
        PdfAnnotation annotation = new PdfCircleAnnotation(rect);
        annotation.setColor(color);
        addingAnAnnotation(in, page, annotation);
        System.out.println("Аннотация добавлена.");
    }

    public static void main(String[] args) throws Exception {
        CreatePdf CreatePdf = new CreatePdf();
        Scanner in = new Scanner(System.in);
        Document document = null;
        PdfDocument pdfDoc = null;
        PdfPage page = null;
        Style style = null;
        Paragraph paragraph;

        CreatePdf.listOfKeyword();

        boolean endOfWork;
        boolean fileCreated = false;
        do {
            String yesOrNo;
            endOfWork = true;
            System.out.print("Введите название команды.\nВвод: ");
            String command = in.nextLine();
            if (command.equalsIgnoreCase("new-pdf")) {
                System.out.print("Введите путь к файлу.\nВвод: ");
                String dest = in.nextLine();
                PdfWriter writer = new PdfWriter(dest);
                pdfDoc = new PdfDocument(writer);
                page = pdfDoc.addNewPage();
                document = new Document(pdfDoc);
                fileCreated = true;
            } else if (command.equalsIgnoreCase("make-pdf") && fileCreated) {
                System.out.print("Введите путь к txt файлу.\nВвод: ");
                String txtFilePath = in.nextLine();
                File file = new File(txtFilePath);
                if (!file.exists()) {
                    System.out.println("Файл не существует!");
                } else {
                    System.out.print("Введите путь к pdf файлу.\nВвод: ");
                    String pdfFilePath = in.nextLine();
                    CreatePdf.convertTxtToPdf(pdfFilePath, txtFilePath);
                    System.out.println("Файл успешно конвертирован.");
                }
            } else if (command.equalsIgnoreCase("add-text") && fileCreated) {
                String textToAdd;
                StringBuilder result = new StringBuilder();
                System.out.println("Введите текст. После окончания ввода текста введите команду \"end\".");
                do {
                    String formatSelect;
                    textToAdd = in.nextLine();
                    if (textToAdd.equalsIgnoreCase("end")) {
                        Text text = new Text(result.toString());
                        System.out.println("""
                                Введите команду:
                                1 - Задать форматирование текста
                                2 - Применить сохраненный стиль""");
                        boolean formattingIsSelected = false;
                        do {
                            System.out.print("Ввод: ");
                            formatSelect = in.nextLine();
                            if (formatSelect.equals("1")) {
                                String formatCommand;
                                PdfFont font;
                                SetFont fontResult = null;
                                SetColor resultColorOfText = null;
                                SetColor resultColorOfFill = null;
                                int sizeOfFont = 0;
                                CreatePdf.waysToFormatText();
                                System.out.println("Введите команду. После окончания форматирования введите команду \"end-input\".");
                                do {
                                    System.out.print("Ввод команды: ");
                                    formatCommand = in.nextLine();
                                    if (formatCommand.equalsIgnoreCase("font")) {
                                        CreatePdf.listOfFonts();
                                        System.out.println();
                                        System.out.println("Ведите номер шрифта.");
                                        do {
                                            System.out.print("Ввод: ");
                                            String titleOfFont = in.nextLine();
                                            // Применяем шрифт
                                            fontResult = SetFont.fontSelection(titleOfFont, text, null);
                                        } while (fontResult.getFont() == null);
                                        text = fontResult.getText();
                                    } else if (formatCommand.equalsIgnoreCase("size")) {
                                        boolean theSizeIsCorrect;
                                        final int minFontSize = 1;
                                        final int maxFontSize = 1638;
                                        System.out.println("Введите размер шрифта.");
                                        do {
                                            System.out.print("Ввод: ");
                                            if (in.hasNextInt()) {
                                                sizeOfFont = in.nextInt();
                                                in.nextLine();
                                                theSizeIsCorrect = true;
                                                if (sizeOfFont < minFontSize || sizeOfFont > maxFontSize) {
                                                    System.out.println("Число должно находиться в диапозоне от 1 до 1638.");
                                                    theSizeIsCorrect = false;
                                                }
                                            } else {
                                                System.out.print("Ошибка! Введено не число.");
                                                theSizeIsCorrect = false;
                                            }
                                        } while (!theSizeIsCorrect);
                                        // Применяем размер шрифта
                                        text.setFontSize(sizeOfFont);
                                    } else if (formatCommand.equalsIgnoreCase("color-text")) {
                                        CreatePdf.listOfColors();
                                        System.out.println();
                                        System.out.println("Введите номер цвета для шрифта.");
                                        do {
                                            System.out.print("Ввод: ");
                                            String colorOfText = in.nextLine();
                                            // Применяем цвет шрифта
                                            resultColorOfText = SetColor.setColorForText(colorOfText, text, null);
                                        } while (resultColorOfText.getColor() == null);
                                        text = resultColorOfText.getText();
                                    } else if (formatCommand.equalsIgnoreCase("color-fill")) {
                                        CreatePdf.listOfColors();
                                        System.out.println();
                                        System.out.println("Введите номер цвета для заливки текста.");
                                        do {
                                            System.out.print("Ввод: ");
                                            String colorOfFilling = in.nextLine();
                                            // Применяем цвет заливки
                                            resultColorOfFill = SetColor.setColorForFilling(colorOfFilling, text, null);
                                        } while (resultColorOfFill.getColor() == null);
                                        text = resultColorOfFill.getText();
                                    } else if (formatCommand.equalsIgnoreCase("end-input")) {
                                        System.out.println("Текст добавлен!");
                                    } else {
                                        System.out.println("Ошибка! Команда введена неверно.");
                                    }
                                } while (!formatCommand.equalsIgnoreCase("end-input"));
                                System.out.println("Хотите ли Вы сохранить этот стиль для повторного использования? Введите \"yes\", " +
                                        "если да, иначе - \"no\".");
                                boolean theCommandIsCorrect = false;
                                do {
                                    System.out.print("Ввод: ");
                                    yesOrNo = in.nextLine();
                                    if (yesOrNo.equalsIgnoreCase("yes") || yesOrNo.equalsIgnoreCase("y")) {
                                        font = fontResult.getFont();
                                        assert resultColorOfText != null;
                                        Color color = resultColorOfText.getColor();
                                        assert resultColorOfFill != null;
                                        Color backgroundColor = resultColorOfFill.getColor();
                                        // Добавляем в стиль шрифт, его цвет и размер
                                        style = new Style().setFont(font).setFontColor(color).setBackgroundColor(backgroundColor).setFontSize(sizeOfFont);
                                        System.out.println("Стиль сохранён!");
                                        theCommandIsCorrect = true;
                                    } else if (yesOrNo.equalsIgnoreCase("no") || yesOrNo.equalsIgnoreCase("n")) {
                                        System.out.println("Стиль не сохранён.");
                                        theCommandIsCorrect = true;
                                    } else {
                                        System.out.println("Ошибка! Команда введена неверно.");
                                    }
                                } while (!theCommandIsCorrect);
                                paragraph = new Paragraph(text);
                                document.add(paragraph);
                                formattingIsSelected = true;
                            } else if (formatSelect.equals("2")) {
                                if (style == null) {
                                    System.out.println("Сохранённых стилей нет.");
                                } else {
                                    System.out.println("Стиль успешно применён к текущему тексту!");
                                    text.addStyle(style);
                                    paragraph = new Paragraph(text);
                                    document.add(paragraph);
                                    formattingIsSelected = true;
                                }
                            } else {
                                System.out.println("Ошибка! Команда введена неверно.");
                            }
                        } while (!formattingIsSelected);
                    } else {
                        // Добавляем в текст перенос на новую строку
                        result.append(textToAdd).append("\n");
                    }
                } while (!textToAdd.equalsIgnoreCase("end"));
            } else if (command.equalsIgnoreCase("add-picture") && fileCreated) {
                System.out.print("Введите путь к файлу с картинкой.\nВвод: ");
                String imageFile = in.nextLine();
                if (!new File(imageFile).exists()) {
                    System.out.println("Файл не существует!");
                } else {
                    ImageData data = ImageDataFactory.create(imageFile);
                    Image img = new Image(data);
                    String numOfCommand;
                    System.out.println("""
                            Введите:
                            1 - Добавить изображение с параметрами по умолчанию
                            2 - Добавить изображение и задать свои параметры (номер страницы для добавления, отступ слева, отступ снизу, размер)""");
                    do {
                        System.out.print("Ввод: ");
                        numOfCommand = in.nextLine();
                        if (numOfCommand.equals("1")) {
                            System.out.println("Выбраны параметры по умолчанию.");
                        } else if (numOfCommand.equals("2")) {
                            System.out.print("Введите номер страницы.\nВвод: ");
                            int numOfPage = in.nextInt();
                            in.nextLine();
                            System.out.print("Введите значение отступа слева.\nВвод: ");
                            int leftMargin = in.nextInt();
                            in.nextLine();
                            System.out.print("Введите значение отступа снизу.\nВвод: ");
                            int downMargin = in.nextInt();
                            in.nextLine();
                            System.out.print("Введите размер картинки.\nВвод: ");
                            int sizeOfPicture = in.nextInt();
                            in.nextLine();
                            // Применяем параметры для изображения
                            img.setFixedPosition(numOfPage, leftMargin, downMargin, sizeOfPicture);
                            System.out.println("Заданы пользовательские параметры.");
                        } else {
                            System.out.println("Ошибка! Команда введена неверно.");
                        }
                    } while (!(numOfCommand.equals("1") || numOfCommand.equals("2")));
                    System.out.println("Желаете повернуть изображение? Введите \"yes\", если да, иначе - \"no\".");
                    do {
                        int angleOfRotation = 0;
                        System.out.print("Ввод: ");
                        yesOrNo = in.nextLine();
                        if (yesOrNo.equalsIgnoreCase("yes") || yesOrNo.equalsIgnoreCase("y")) {
                            System.out.println("Введите числовое значение угла поворота в градусах.");
                            boolean theAngleIsCorrect;
                            do {
                                System.out.print("Ввод: ");
                                theAngleIsCorrect = true;
                                if (in.hasNextInt()) {
                                    angleOfRotation = in.nextInt();
                                    in.nextLine();
                                    final int minAngle = 1;
                                    final int maxAngle = 360;
                                    if (angleOfRotation < minAngle || angleOfRotation > maxAngle) {
                                        System.out.println("Ошибка! Введенное число должно быть в диапозоне от 1 до 360.");
                                        theAngleIsCorrect = false;
                                    }
                                } else {
                                    System.out.println("Ошибка! Введено не число.");
                                    in.next();
                                    theAngleIsCorrect = false;
                                }
                            } while (!theAngleIsCorrect);
                            // Применяем изменение угла поворота изображения
                            img.setRotationAngle(angleOfRotation);
                        } else if (yesOrNo.equalsIgnoreCase("no") || yesOrNo.equalsIgnoreCase("n")) {
                            System.out.println("Изображение добавлено.");
                        } else {
                            System.out.println("Ошибка! Команда введена неверно.");
                        }
                        document.add(img);
                    } while (!(yesOrNo.equalsIgnoreCase("yes") || yesOrNo.equalsIgnoreCase("y") ||
                            yesOrNo.equalsIgnoreCase("no") || yesOrNo.equalsIgnoreCase("n")));
                }
            } else if (command.equalsIgnoreCase("add-list") && fileCreated) {
                // Список с будущими элементами
                List list = new List();
                String listItem;
                System.out.println("После окончания ввода введите команду \"end\".");
                do {
                    System.out.print("Ввод элемента списка: ");
                    listItem = in.nextLine();
                    if (!listItem.equalsIgnoreCase("end")) {
                        // Добавляем элемент в список
                        list.add(listItem);
                    } else {
                        System.out.println("Список добавлен.");
                    }
                } while (!listItem.equalsIgnoreCase("end"));
                // Добавляем список в документ
                document.add(list);
            } else if (command.equalsIgnoreCase("add-table") && fileCreated) {
                System.out.println("Введите количество ячеек в таблице.");
                boolean numOfCellsIsCorrect;
                int numOfCells = 0;
                do {
                    System.out.print("Ввод: ");
                    numOfCellsIsCorrect = true;
                    if (in.hasNextInt()) {
                        numOfCells = in.nextInt();
                        in.nextLine();
                        final int minNumOfCells = 1;
                        if (numOfCells < minNumOfCells) {
                            System.out.println("Ошибка! Введенное число не может быть меньше 1.");
                            numOfCellsIsCorrect = false;
                        }
                    } else {
                        System.out.println("Ошибка! Введено не число.");
                        in.next();
                        numOfCellsIsCorrect = false;
                    }
                } while (!numOfCellsIsCorrect);
                float[] pointColumnWidths = new float[numOfCells];
                Table table = new Table(pointColumnWidths);
                String cellContents;
                StringBuilder resultCellContents = new StringBuilder();
                System.out.println("Введите содержимое ячейки (ячеек). Нажатие клавиши Enter добавляет перенос строки." +
                        " Для окончания ввода введите команду \"end\".");
                for (int i = 0; i < numOfCells; ++i) {
                    System.out.println("Ввод содержимого " + (i + 1) + " ячейки: ");
                    Cell cell = new Cell();
                    do {
                        cellContents = in.nextLine();
                        if (cellContents.equalsIgnoreCase("end")) {
                            Paragraph paragraphCell = new Paragraph();
                            paragraphCell.add(resultCellContents.toString());
                            // Очищаем содержимое строки после добавления текста
                            final int start = 0;
                            resultCellContents.delete(start, resultCellContents.length());
                            // Добавляем текст в ячейку
                            cell.add(paragraphCell);
                            // Добавляем заполненную ячейку в таблицу
                            table.addCell(cell);
                            System.out.println("Ячейка заполнена.");
                        } else {
                            resultCellContents.append(cellContents);
                            resultCellContents.append("\n");
                        }
                    } while (!cellContents.equalsIgnoreCase("end"));
                }
                System.out.println("Таблица добавлена!");
                // Добавляем таблицу в документ
                document.add(table);
            } else if (command.equalsIgnoreCase("add-annotation") && fileCreated) {
                String annotationType;
                System.out.println("""
                        Что бы Вы хотели добавить? Введите соответствующую команду.
                        1 - ссылочная аннотация
                        2 - текстовая аннотация
                        3 - аннотация разметки
                        4 - круговая аннотация""");
                System.out.print("Ввод: ");
                annotationType = in.nextLine();
                boolean annotationSelected = false;
                do {
                    Color color;
                    switch (annotationType) {
                        case "1" -> {
                            CreatePdf.annotationType1(in, document);
                            annotationSelected = true;
                        }
                        case "2" -> {
                            CreatePdf.annotationType2(in, page);
                            annotationSelected = true;
                        }
                        case "3" -> {
                            CreatePdf.listOfColors();
                            System.out.println();
                            System.out.println("Введите номер цвета аннотации.");
                            do {
                                System.out.print("Ввод: ");
                                String colorOfAnnotation = in.nextLine();
                                color = CreatePdf.setColorOfAnnotation(colorOfAnnotation);
                            } while (color == null);
                            assert page != null;
                            CreatePdf.annotationType3(in, page, color);
                            annotationSelected = true;
                        }
                        case "4" -> {
                            CreatePdf.listOfColors();
                            System.out.println();
                            System.out.println("Введите номер цвета аннотации.");
                            do {
                                System.out.print("Ввод: ");
                                String colorOfAnnotation = in.nextLine();
                                color = CreatePdf.setColorOfAnnotation(colorOfAnnotation);
                            } while (color == null);
                            assert page != null;
                            CreatePdf.annotationType4(in, page, color);
                            annotationSelected = true;
                        }
                        default -> System.out.println("Ошибка! Команда введена неверно.");
                    }
                } while (!annotationSelected);
            } else if (command.equalsIgnoreCase("add-page-break") && fileCreated) {
                AreaBreak aB = new AreaBreak();
                // Добавляем разрыв страницы в документ
                document.add(aB);
                System.out.println("Разрыв страницы добавлен!");
            } else if (command.equalsIgnoreCase("add-page") && fileCreated) {
                // Добавляем новую страницу в документ
                pdfDoc.addNewPage();
                System.out.println("Новая страница добавлена!");
            } else if (command.equalsIgnoreCase("end")) {
                if (fileCreated) {
                    // Закрываем файл
                    document.close();
                    System.out.println("PDF файл сохранён!");
                }
                endOfWork = false;
            } else {
                System.out.println("Ошибка! Некорректный ввод команды.");
            }
        } while (endOfWork);
    }
}