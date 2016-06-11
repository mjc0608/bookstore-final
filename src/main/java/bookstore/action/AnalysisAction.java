package bookstore.action;

import bookstore.entity.AnalysisPair;
import bookstore.service.AnalysisService;
import bookstore.service.implementation.AnalysisServiceImpl;
import bookstore.util.UserUtil;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.inject.Factory;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Jachin on 6/11/16.
 */
public class AnalysisAction extends ActionSupport {
    AnalysisService analysisService = new AnalysisServiceImpl();
    private Map<String, AnalysisPair> category = null;
    private Map<String, AnalysisPair> day = null;
    private Map<String, AnalysisPair> month = null;
    private Map<String, AnalysisPair> year = null;
    private long userID=-1;
    private boolean ifRange=false;
    private String start;
    private String end;

    public String execute() throws Exception {
        if (!UserUtil.isAdmin()) {
            return ERROR;
        }

        if ((start!=null && !start.equals("")) || (end!=null && !end.equals(""))) {
            if (start==null || start.equals("")) {
                start="1900-01-01";
            }
            if (end==null || end.equals("")) {
                end="2100-01-01";
            }

            ifRange=true;
            if (userID<0) {
                if (!analysisService.init(start, end)) {
                    return ERROR;
                }
            }
            else {
                if (!analysisService.init(userID, start, end)) {
                    return ERROR;
                }
            }

            category=analysisService.getAnalysisCategory();
            month=analysisService.getAnalysisLimitMonth();
            year=analysisService.getAnalysisLimitYear();
            day=analysisService.getAnalysisLimitDay();
        }
        else {
            ifRange=false;
            if (userID<0) {
                if (!analysisService.init()) {
                    return ERROR;
                }
            }
            else {
                if (!analysisService.init(userID)) {
                    return ERROR;
                }
            }

            category=analysisService.getAnalysisCategory();
            day=analysisService.getAnalysisDay();
            month=analysisService.getAnalysisMonth();
            year=analysisService.getAnalysisYear();


        }

        return SUCCESS;
    }


    public Map<String, AnalysisPair> getYear() {
        return year;
    }

    public Map<String, AnalysisPair> getMonth() {
        return month;
    }

    public Map<String, AnalysisPair> getDay() {
        return day;
    }

    public Map<String, AnalysisPair> getCategory() {
        return category;
    }

    public AnalysisService getAnalysisService() {
        return analysisService;
    }

    public long getUserID() {
        return userID;
    }

    public String getEnd() {
        return end;
    }

    public String getStart() {
        return start;
    }

    public boolean isIfRange() {
        return ifRange;
    }

    public void setYear(Map<String, AnalysisPair> year) {
        this.year = year;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public void setMonth(Map<String, AnalysisPair> month) {
        this.month = month;
    }

    public void setDay(Map<String, AnalysisPair> day) {
        this.day = day;
    }

    public void setAnalysisService(AnalysisService analysisService) {
        this.analysisService = analysisService;
    }

    public void setCategory(Map<String, AnalysisPair> category) {
        this.category = category;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public void setIfRange(boolean ifRange) {
        this.ifRange = ifRange;
    }

    public void setStart(String start) {
        this.start = start;
    }

}
