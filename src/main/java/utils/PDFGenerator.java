package utils;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import java.io.File;
import java.awt.Desktop;

public class PDFGenerator
{
    private static final String CARPETA_PDFS = "src/main/java/pdfs";

    private static String prepararRuta(String nombreArchivo)
    {
        File directorio = new File(CARPETA_PDFS);

        if (!directorio.exists())
        {
            directorio.mkdir();
        }

        return CARPETA_PDFS + File.separator + nombreArchivo;
    }

    public static void generarFicha(
        String id,
        String cliente,
        String producto,
        String monto,
        String fecha
    )
    {
        String path = prepararRuta("Ficha_Operacion_" + id + ".pdf");

        try
        {
            PdfWriter writer = new PdfWriter(path);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            Paragraph titulo = new Paragraph("REWINDS RENTAL")
                .setFontSize(20)
                .setTextAlignment(TextAlignment.CENTER)
                .setBold();

            document.add(titulo);

            document.add(
                new Paragraph("Ficha de Operacion #" + id)
                    .setTextAlignment(TextAlignment.CENTER)
            );

            document.add(new Paragraph("\n"));

            Table table = new Table(
                UnitValue.createPercentArray(new float[] {50, 50})
            ).setWidth(UnitValue.createPercentValue(100));

            table.addCell("Cliente:")
                 .addCell(cliente)
                 .addCell("Producto:")
                 .addCell(producto)
                 .addCell("Fecha:")
                 .addCell(fecha)
                 .addCell("Monto Total:")
                 .addCell(monto);

            document.add(table);

            document.close();

            Desktop.getDesktop().open(new File(path));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void generarFichaCliente(
        String id,
        String nombres,
        String apellidos,
        String telefono,
        String fecha
    )
    {
        String path = prepararRuta("Tarjeta_Cliente_" + id + ".pdf");

        try
        {
            PdfWriter writer = new PdfWriter(path);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            Paragraph titulo = new Paragraph("TARJETA DE CLIENTE")
                .setFontSize(20)
                .setTextAlignment(TextAlignment.CENTER)
                .setBold();

            document.add(titulo)
                    .add(new Paragraph("\n"));

            Table table = new Table(
                UnitValue.createPercentArray(new float[] {50, 50})
            ).setWidth(UnitValue.createPercentValue(100));

            table.addCell("ID Cliente:")
                 .addCell(id)
                 .addCell("Nombre:")
                 .addCell(nombres + " " + apellidos)
                 .addCell("Teléfono:")
                 .addCell(telefono)
                 .addCell("Fecha Nacimiento:")
                 .addCell(fecha);

            document.add(table);

            document.close();

            Desktop.getDesktop().open(new File(path));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void generarFichaJuego(
        String id,
        String titulo,
        String plataforma,
        String precio
    )
    {
        String path = prepararRuta("Ficha_Juego_" + id + ".pdf");

        try
        {
            PdfWriter writer = new PdfWriter(path);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            document.add(
                new Paragraph("FICHA DE JUEGO")
                    .setFontSize(20)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setBold()
            );

            Table table = new Table(
                UnitValue.createPercentArray(new float[] {50, 50})
            ).setWidth(UnitValue.createPercentValue(100));

            table.addCell("ID:")
                 .addCell(id)
                 .addCell("Titulo:")
                 .addCell(titulo)
                 .addCell("Plataforma:")
                 .addCell(plataforma)
                 .addCell("Precio:")
                 .addCell(precio);

            document.add(table);

            document.close();

            Desktop.getDesktop().open(new File(path));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void generarFichaPelicula(
        String id,
        String titulo,
        String director,
        String duracion
    )
    {
        String path = prepararRuta("Ficha_Pelicula_" + id + ".pdf");

        try
        {
            PdfWriter writer = new PdfWriter(path);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            document.add(
                new Paragraph("FICHA DE PELICULA")
                    .setFontSize(20)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setBold()
            );

            Table table = new Table(
                UnitValue.createPercentArray(new float[] {50, 50})
            ).setWidth(UnitValue.createPercentValue(100));

            table.addCell("ID:")
                 .addCell(id)
                 .addCell("Titulo:")
                 .addCell(titulo)
                 .addCell("Director:")
                 .addCell(director)
                 .addCell("Duracion:")
                 .addCell(duracion);

            document.add(table);

            document.close();

            Desktop.getDesktop().open(new File(path));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void generarFichaOperacion(
        String id,
        String cliente,
        String tipo,
        String monto
    )
    {
        String path = prepararRuta("Ficha_Operacion_" + id + ".pdf");

        try
        {
            PdfWriter writer = new PdfWriter(path);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            document.add(
                new Paragraph("DETALLE DE OPERACION")
                    .setFontSize(20)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setBold()
            );

            Table table = new Table(
                UnitValue.createPercentArray(new float[] {50, 50})
            ).setWidth(UnitValue.createPercentValue(100));

            table.addCell("ID:")
                 .addCell(id)
                 .addCell("Cliente:")
                 .addCell(cliente)
                 .addCell("Tipo:")
                 .addCell(tipo)
                 .addCell("Monto:")
                 .addCell(monto);

            document.add(table);

            document.close();

            Desktop.getDesktop().open(new File(path));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}