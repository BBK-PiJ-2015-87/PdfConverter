import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

import org.apache.pdfbox.pdmodel.common.PDRectangle
import org.apache.pdfbox.pdmodel.graphics.image.LosslessFactory
import org.apache.pdfbox.pdmodel.{PDDocument, PDPage, PDPageContentStream}

class RasterImageReader {

  def getListOfFiles(dir: String):List[File] = {
    val d = new File(dir)
    if (d.exists && d.isDirectory) {
      d.listFiles.filter(_.isFile).toList
    } else {
      List[File]()
    }
  }

  def readImages(files: List[File]): List[BufferedImage] = {
    for (file <- files) yield ImageIO.read(file)
  }

  def getSizes(files: List[BufferedImage]) = {
    for (file <- files) {
      println("height" + file.getHeight + "  width " + file.getWidth)
    }
  }

  def convertToPdf(files: List[BufferedImage]) = {
    val doc = new PDDocument();
    val firstFile = files.head
    val rec = new PDRectangle(firstFile.getWidth,firstFile.getHeight); //set to image size
    val quality = 1.0f
    val dpi = 300

    for (file <- files) {
      val page = new PDPage(rec);
      doc.addPage(page);

      val contentStream = new PDPageContentStream(doc, page);
//      val image = JPEGFactory.createFromImage(doc,file, quality, dpi)
      val image = LosslessFactory.createFromImage(doc,file)
      contentStream.drawImage(image,0,0)
      contentStream.close()
    }
    doc.save("test.pdf");
    doc.close();
  }
}
