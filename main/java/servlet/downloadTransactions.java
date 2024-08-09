package servlet;

import Dao.DatabaseUtil;
import Dao.serviceDao;
import Model.Transaction;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/downloadTransactions")
public class downloadTransactions extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String accountNo = (String) session.getAttribute("identifier");

        if (accountNo == null) {
            response.sendRedirect("login.jsp"); // Redirect to login if account number is not available
            return;
        }

        Connection connection = DatabaseUtil.getConnection();
        serviceDao dao = new serviceDao(connection);

        try {
            List<Transaction> transactions = dao.getTransactions(accountNo);
            if (transactions != null && !transactions.isEmpty()) {
                generatePDF(response, transactions);
            } else {
                response.sendRedirect("viewTransactions.jsp?message=No+transactions+found");
            }
        } catch (SQLException e) {
            throw new ServletException("Cannot retrieve transactions from DB", e);
        } finally {
            DatabaseUtil.closeConnection(connection);
        }
    }

    private void generatePDF(HttpServletResponse response, List<Transaction> transactions) throws IOException {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);
        PDPageContentStream contentStream = null;

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            contentStream = new PDPageContentStream(document, page);
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 16);
            contentStream.newLineAtOffset(50, 750);
            contentStream.showText("Transaction History");
            contentStream.endText();

            // Set up the table
            float margin = 50;
            float yPosition = 720;
            float tableWidth = 500;
            float rowHeight = 20;
            float tableTopY = page.getMediaBox().getHeight() - margin;
            float bottomMargin = 50;
            int rowsPerPage = (int) ((tableTopY - bottomMargin) / rowHeight);

            // Draw table header
            contentStream.setLineWidth(1f);
            contentStream.setNonStrokingColor(200, 200, 200); // Light gray fill color for header
            drawTableHeader(contentStream, margin, tableTopY, tableWidth, rowHeight);

            // Draw table rows
            contentStream.setNonStrokingColor(0, 0, 0); // Black color for text
            contentStream.setFont(PDType1Font.HELVETICA, 10);
            int currentRow = 0;
            for (Transaction transaction : transactions) {
                if (currentRow >= rowsPerPage) {
                    contentStream.close();
                    page = new PDPage();
                    document.addPage(page);
                    contentStream = new PDPageContentStream(document, page);
                    currentRow = 0;
                    yPosition = tableTopY;
                    drawTableHeader(contentStream, margin, yPosition, tableWidth, rowHeight);
                }
                yPosition -= rowHeight;
                drawTableRow(contentStream, transaction, margin, yPosition, tableWidth, rowHeight, dateFormat);
                currentRow++;
            }
        } finally {
            if (contentStream != null) {
                contentStream.close();
            }
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=transactions.pdf");
            document.save(response.getOutputStream());
            document.close();
        }
    }

    private void drawTableHeader(PDPageContentStream contentStream, float margin, float yPosition, float tableWidth, float rowHeight) throws IOException {
        contentStream.setLineWidth(1f);
        contentStream.setNonStrokingColor(200, 200, 200); // Light gray fill color for header

        // Draw header background
        contentStream.addRect(margin, yPosition, tableWidth, rowHeight);
        contentStream.fill();

        // Draw header text
        contentStream.setNonStrokingColor(0, 0, 0); // Black color for text
        contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 10);
        contentStream.newLineAtOffset(margin + 5, yPosition + 5);
        contentStream.showText("ID");
        contentStream.newLineAtOffset(70, 0);
        contentStream.showText("Account");
        contentStream.newLineAtOffset(100, 0);
        contentStream.showText("Date");
        contentStream.newLineAtOffset(70, 0);
        contentStream.showText("Type");
        contentStream.newLineAtOffset(70, 0);
        contentStream.showText("Amount");
        contentStream.newLineAtOffset(70, 0);
        contentStream.showText("Balance");
        contentStream.endText();

        // Draw header lines
        contentStream.moveTo(margin, yPosition);
        contentStream.lineTo(margin + tableWidth, yPosition);
        contentStream.stroke();
        contentStream.moveTo(margin, yPosition - rowHeight);
        contentStream.lineTo(margin + tableWidth, yPosition - rowHeight);
        contentStream.stroke();
    }

    private void drawTableRow(PDPageContentStream contentStream, Transaction transaction, float margin, float yPosition, float tableWidth, float rowHeight, SimpleDateFormat dateFormat) throws IOException {
        contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA, 10);
        contentStream.newLineAtOffset(margin + 5, yPosition + 5);
        contentStream.showText(String.valueOf(transaction.getTransactionId())); // Convert int to String
        contentStream.newLineAtOffset(70, 0);
        contentStream.showText(transaction.getAccountNo());
        contentStream.newLineAtOffset(100, 0);
        contentStream.showText(dateFormat.format(transaction.getTransactionDate())); // Convert Date to String
        contentStream.newLineAtOffset(70, 0);
        contentStream.showText(transaction.getTransactionType());
        contentStream.newLineAtOffset(70, 0);
        contentStream.showText(String.format("%.2f", transaction.getAmount())); // Convert float to String
        contentStream.newLineAtOffset(70, 0);
        contentStream.showText(String.format("%.2f", transaction.getBalanceAfter())); // Convert float to String
        contentStream.endText();

        // Draw row lines
        contentStream.moveTo(margin, yPosition);
        contentStream.lineTo(margin + tableWidth, yPosition);
        contentStream.stroke();
    }
}
