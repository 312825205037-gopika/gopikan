import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/analyze")
public class StudentPerformanceServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                           HttpServletResponse response)
            throws ServletException, IOException {

        // Student details
        String studentName =
                request.getParameter("studentName");

        String rollNumber =
                request.getParameter("rollNumber");

        // Subject marks
        double english =
                Double.parseDouble(
                        request.getParameter("english"));

        double mathematics =
                Double.parseDouble(
                        request.getParameter("mathematics"));

        double science =
                Double.parseDouble(
                        request.getParameter("science"));

        double computer =
                Double.parseDouble(
                        request.getParameter("computer"));

        double socialScience =
                Double.parseDouble(
                        request.getParameter("socialScience"));

        double attendance =
                Double.parseDouble(
                        request.getParameter("attendance"));

        // Calculate total
        double total =
                english
                + mathematics
                + science
                + computer
                + socialScience;

        // Calculate percentage
        double percentage = total / 5;

        // Grade
        String grade;

        if (percentage >= 90) {

            grade = "A+";

        } else if (percentage >= 80) {

            grade = "A";

        } else if (percentage >= 70) {

            grade = "B";

        } else if (percentage >= 60) {

            grade = "C";

        } else if (percentage >= 50) {

            grade = "D";

        } else {

            grade = "F";
        }

        // Check individual subject pass
        boolean passed =
                english >= 35
                && mathematics >= 35
                && science >= 35
                && computer >= 35
                && socialScience >= 35;

        String result;

        if (passed) {

            result = "PASS";

        } else {

            result = "FAIL";
        }

        // Performance analysis
        String performance;

        if (!passed) {

            performance = "Needs Improvement";

        } else if (percentage >= 90) {

            performance = "Excellent";

        } else if (percentage >= 80) {

            performance = "Very Good";

        } else if (percentage >= 70) {

            performance = "Good";

        } else if (percentage >= 60) {

            performance = "Average";

        } else {

            performance = "Needs Improvement";
        }

        // Find highest subject
        String highestSubject;
        double highestMark;

        highestSubject = "English";
        highestMark = english;

        if (mathematics > highestMark) {
            highestSubject = "Mathematics";
            highestMark = mathematics;
        }

        if (science > highestMark) {
            highestSubject = "Science";
            highestMark = science;
        }

        if (computer > highestMark) {
            highestSubject = "Computer Science";
            highestMark = computer;
        }

        if (socialScience > highestMark) {
            highestSubject = "Social Science";
            highestMark = socialScience;
        }

        // Attendance analysis
        String attendanceStatus;

        if (attendance >= 75) {

            attendanceStatus = "Good Attendance";

        } else {

            attendanceStatus = "Low Attendance";
        }

        // HTML response
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");

        out.println("<html>");

        out.println("<head>");

        out.println("<meta charset='UTF-8'>");

        out.println(
            "<meta name='viewport' " +
            "content='width=device-width, initial-scale=1.0'>"
        );

        out.println(
            "<title>Performance Result</title>"
        );

        out.println("<style>");

        out.println(
            "body {" +
            "font-family: Arial, sans-serif;" +
            "background:#eef2f7;" +
            "margin:0;" +
            "}"
        );

        out.println(
            ".result-container {" +
            "width:700px;" +
            "max-width:95%;" +
            "margin:40px auto;" +
            "background:white;" +
            "padding:35px;" +
            "border-radius:15px;" +
            "box-shadow:0 5px 20px rgba(0,0,0,0.15);" +
            "}"
        );

        out.println(
            "h1 {text-align:center;}"
        );

        out.println(
            "table {" +
            "width:100%;" +
            "border-collapse:collapse;" +
            "margin-top:25px;" +
            "}"
        );

        out.println(
            "th,td {" +
            "padding:12px;" +
            "border:1px solid #ccc;" +
            "text-align:left;" +
            "}"
        );

        out.println(
            "th {background:#f2f2f2;}"
        );

        out.println(
            ".summary {" +
            "margin-top:25px;" +
            "padding:20px;" +
            "border-radius:10px;" +
            "background:#f5f5f5;" +
            "}"
        );

        out.println(
            ".back {" +
            "display:block;" +
            "margin-top:25px;" +
            "text-align:center;" +
            "}"
        );

        out.println("</style>");

        out.println("</head>");

        out.println("<body>");

        out.println(
            "<div class='result-container'>"
        );

        out.println(
            "<h1>Student Performance Report</h1>"
        );

        out.println("<table>");

        out.println(
            "<tr><th>Student Name</th><td>"
            + studentName
            + "</td></tr>"
        );

        out.println(
            "<tr><th>Roll Number</th><td>"
            + rollNumber
            + "</td></tr>"
        );

        out.println(
            "<tr><th>English</th><td>"
            + english
            + "</td></tr>"
        );

        out.println(
            "<tr><th>Mathematics</th><td>"
            + mathematics
            + "</td></tr>"
        );

        out.println(
            "<tr><th>Science</th><td>"
            + science
            + "</td></tr>"
        );

        out.println(
            "<tr><th>Computer Science</th><td>"
            + computer
            + "</td></tr>"
        );

        out.println(
            "<tr><th>Social Science</th><td>"
            + socialScience
            + "</td></tr>"
        );

        out.println(
            "<tr><th>Total Marks</th><td>"
            + String.format("%.2f", total)
            + " / 500</td></tr>"
        );

        out.println(
            "<tr><th>Percentage</th><td>"
            + String.format("%.2f", percentage)
            + "%</td></tr>"
        );

        out.println(
            "<tr><th>Grade</th><td>"
            + grade
            + "</td></tr>"
        );

        out.println(
            "<tr><th>Attendance</th><td>"
            + String.format("%.2f", attendance)
            + "%</td></tr>"
        );

        out.println("</table>");

        out.println("<div class='summary'>");

        out.println(
            "<h2>Performance Analysis</h2>"
        );

        out.println(
            "<p><strong>Result:</strong> "
            + result
            + "</p>"
        );

        out.println(
            "<p><strong>Performance:</strong> "
            + performance
            + "</p>"
        );

        out.println(
            "<p><strong>Highest Subject:</strong> "
            + highestSubject
            + " ("
            + highestMark
            + " marks)</p>"
        );

        out.println(
            "<p><strong>Attendance Status:</strong> "
            + attendanceStatus
            + "</p>"
        );

        out.println("</div>");

        out.println(
            "<div class='back'>"
        );

        out.println(
            "<a href='index.html'>"
            + "Analyze Another Student"
            + "</a>"
        );

        out.println("</div>");

        out.println("</div>");

        out.println("</body>");

        out.println("</html>");
    }
}
