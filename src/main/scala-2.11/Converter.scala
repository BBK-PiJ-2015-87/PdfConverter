/**
  * Created by Workstation on 12/05/2016.
  */
object Converter extends App{
  val start1 = System.currentTimeMillis();
  val reader = new RasterImageReader
  val files = reader.getListOfFiles("/Users/Workstation/Desktop/JPGtoPDF/PdfConverter/src/main/resources/")
  val end1 = System.currentTimeMillis();

  print("GET LIST OF FILES " + (end1-start1))

  val start2 = System.currentTimeMillis();
  val images = reader.readImages(files)
  val end2 = System.currentTimeMillis();
  print("READ IMAGES " + (end2-start2))


  val start3 = System.currentTimeMillis();

  reader.convertToPdf(images)

  val end3 = System.currentTimeMillis();

  print("CONVERT TO PDF " + (end3-start3))

}
