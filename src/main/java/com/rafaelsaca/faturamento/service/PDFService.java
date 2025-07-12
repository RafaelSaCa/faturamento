package com.rafaelsaca.faturamento.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.time.format.DateTimeFormatter;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import com.rafaelsaca.faturamento.model.Cobranca;

@Service
public class PDFService {

  
    public String gerarCobrancaPDF(Cobranca cobranca) throws Exception {

        String nomeArquivo ="fatura_" + cobranca.getId() + ".pdf";
        String diretorio = "cobrancas/pdf";
        File pasta = new File (diretorio);
        if (!pasta.exists()) pasta.mkdirs();

        String caminhoCompleto = diretorio + "/" + nomeArquivo;

    
        Document doc = new Document();
        PdfWriter.getInstance(doc, new FileOutputStream(caminhoCompleto));
        doc.open();

        Font tituloFont = new Font(Font.HELVETICA, 16, Font.BOLD);
        Font normalFont = new Font(Font.HELVETICA, 12);

        doc.add(new Paragraph("COBRANÇA", tituloFont));
        doc.add(new Paragraph(""));


        doc.add(new Paragraph("Sacado: " + cobranca.getCliente().getNome(), normalFont));
        doc.add(new Paragraph("Valor: R$" + cobranca.getValor(), normalFont));
        doc.add(new Paragraph(
                "Vencimento: " + cobranca.getDataVencimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                normalFont));
        doc.add(new Paragraph("Tipo: " + cobranca.getTipo(), normalFont));
        doc.add(new Paragraph("Descrição: " + cobranca.getDescricao(), normalFont));
        doc.add(new Paragraph(" "));


        if (cobranca.getTipo().name().equals("BOLETO")) {
            doc.add(new Paragraph("Linha digitável: 00190.00009 01234.567899 12345.600000 1 12340000015000",
                    normalFont));
            doc.add(new Paragraph("Código para simular o pagamento do boleto.", normalFont));

        } else if (cobranca.getTipo().name().equals("PIX")) {
            String payLoadPix = "00020126580014BR.GOV.BCB.PIX0136email@pix.com";

            doc.add(new Paragraph("Código PIX:", normalFont));
            doc.add(new Paragraph(payLoadPix, new Font(Font.COURIER, 10)));
            doc.add(new Paragraph(" "));

            Image img = gerarImagemQRCode(payLoadPix);
            img.scalePercent(100);
            doc.add(img);

        }

        doc.close();
        return caminhoCompleto;
    }

    public Image gerarImagemQRCode(String texto) throws Exception {
        BitMatrix matrix = new MultiFormatWriter().encode(texto, BarcodeFormat.QR_CODE, 200, 200);
        BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(matrix);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", baos);
        baos.flush();

        return Image.getInstance(baos.toByteArray());
    }
}
