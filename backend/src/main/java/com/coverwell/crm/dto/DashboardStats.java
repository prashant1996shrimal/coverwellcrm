package com.coverwell.crm.dto;

public class DashboardStats {

    private long totalEmployees;
    private long totalCalls;
    private long totalOutgoingCalls;
    private long totalIncomingCalls;
    private long missedCalls;

    public DashboardStats(
            long totalEmployees,
            long totalCalls,
            long totalOutgoingCalls,
            long totalIncomingCalls,
            long missedCalls) {

        this.totalEmployees = totalEmployees;
        this.totalCalls = totalCalls;
        this.totalOutgoingCalls = totalOutgoingCalls;
        this.totalIncomingCalls = totalIncomingCalls;
        this.missedCalls = missedCalls;
    }

    public long getTotalEmployees() {
        return totalEmployees;
    }

    public long getTotalCalls() {
        return totalCalls;
    }

    public long getTotalOutgoingCalls() {
        return totalOutgoingCalls;
    }

    public long getTotalIncomingCalls() {
        return totalIncomingCalls;
    }

    public long getMissedCalls() {
        return missedCalls;
    }
    
}