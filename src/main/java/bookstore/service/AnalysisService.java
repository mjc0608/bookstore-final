package bookstore.service;

import bookstore.entity.AnalysisPair;
import bookstore.entity.Order;

import java.util.List;
import java.util.Map;

/**
 * Created by Jachin on 6/10/16.
 */
public interface AnalysisService {

    public boolean init();
    public boolean init(long userID);
    public boolean init(String startTime, String endTime);
    public boolean init(long userID, String startTime, String endTime);

    public Map<String, AnalysisPair> getAnalysisCategory();
    public Map<String, AnalysisPair> getAnalysisDay();
    public Map<String, AnalysisPair> getAnalysisMonth();
    public Map<String, AnalysisPair> getAnalysisYear();

    public Map<String, AnalysisPair> getAnalysisLimitDay();
    public Map<String, AnalysisPair> getAnalysisLimitMonth();
    public Map<String, AnalysisPair> getAnalysisLimitYear();

    public void print();

    public double getUserTotalSpent(long userID);
    public long getUserTotalOrder(long userID);

}
