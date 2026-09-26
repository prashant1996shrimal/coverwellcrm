package com.coverwell.crm.dto;

public class EmployeeCallStats {

    private Long employeeId;
    private String employeeName;
    private long totalCalls;
    private long outgoingCalls;
    private long incomingCalls;
    private long missedCalls;

   public EmployeeCallStats(
        Long employeeId,
        String employeeName,
        long totalCalls,
        long outgoingCalls,
        long incomingCalls,
        long missedCalls) {

    this.employeeId = employeeId;
    this.employeeName = employeeName;
    this.totalCalls = totalCalls;
    this.outgoingCalls = outgoingCalls;
    this.incomingCalls = incomingCalls;
    this.missedCalls = missedCalls;
}

    public Long getEmployeeId() {
        return employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public long getTotalCalls() {
        return totalCalls;
    }

    public long getOutgoingCalls() {
        return outgoingCalls;
    }

    public long getIncomingCalls() {
        return incomingCalls;
    }

    public long getMissedCalls() {
    return missedCalls;
}
}