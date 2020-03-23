package com.a528854302.mergefiles.utils;


import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;

import java.io.*;
import java.util.ArrayList;

public class MergeFiles {
    public static void merge(ArrayList<File> srcFiles, File destFile) throws IOException, DocumentException {
        FileOutputStream out=new FileOutputStream(destFile);
        Document document=new Document();
        PdfCopy copy = new PdfCopy(document, out);
        document.open();
        for (int i=0;i<srcFiles.size();i++){
            PdfReader reader = new PdfReader(new FileInputStream(srcFiles.get(i)));
            int n = reader.getNumberOfPages();
            for (int j = 1; j <= n; j++) {
                document.newPage();
                PdfImportedPage page = copy.getImportedPage(reader, j);
                copy.addPage(page);
            }
        }
        document.close();
    }
}
