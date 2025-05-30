package com.rmgx.assetmanagement.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "assets")
public class Asset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long assetId;

    private String assetName;
    private LocalDate purchaseDate;
    private String conditionNotes;
    private String assignmentStatus; // "Available", "Assigned", "Recovered"

    // Category relation
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

    // AssignedTo Employee relation
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id")
    private Employee assignedTo;

    // === Constructors ===

    public Asset() {}

    public Asset(Long assetId, String assetName, LocalDate purchaseDate, String conditionNotes, String assignmentStatus, Category category, Employee assignedTo) {
        this.assetId = assetId;
        this.assetName = assetName;
        this.purchaseDate = purchaseDate;
        this.conditionNotes = conditionNotes;
        this.assignmentStatus = assignmentStatus;
        this.category = category;
        this.assignedTo = assignedTo;
    }

    // === Getters & Setters ===

    public Long getAssetId() {
        return assetId;
    }

    public void setAssetId(Long assetId) {
        this.assetId = assetId;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getConditionNotes() {
        return conditionNotes;
    }

    public void setConditionNotes(String conditionNotes) {
        this.conditionNotes = conditionNotes;
    }

    public String getAssignmentStatus() {
        return assignmentStatus;
    }

    public void setAssignmentStatus(String assignmentStatus) {
        this.assignmentStatus = assignmentStatus;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Employee getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(Employee assignedTo) {
        this.assignedTo = assignedTo;
    }

    @Override
    public String toString() {
        return "Asset{" +
                "assetId=" + assetId +
                ", assetName='" + assetName + '\'' +
                ", purchaseDate=" + purchaseDate +
                ", conditionNotes='" + conditionNotes + '\'' +
                ", assignmentStatus='" + assignmentStatus + '\'' +
                ", category=" + (category != null ? category.getCategoryName() : "null") +
                ", assignedTo=" + (assignedTo != null ? assignedTo.getFullName() : "null") +
                '}';
    }
}
