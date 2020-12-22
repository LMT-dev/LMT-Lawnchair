package fr.letmethink.lawnchair.bugreport;

import fr.letmethink.lawnchair.bugreport.BugReport;

interface IBugReportService {

    void sendReport(in BugReport report);

    void setAutoUploadEnabled(boolean enable);
}
