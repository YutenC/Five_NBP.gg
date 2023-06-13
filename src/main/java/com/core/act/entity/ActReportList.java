package com.core.act.entity;


import javax.persistence.*;

@Entity
@Table(name = "Act_report_list", schema = "five2")
public class ActReportList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Report_id", nullable = false)
    private Integer id;

    @Lob
    @Column(name = "Report_content", nullable = false)
    private String reportContent;

    @Column(name = "Report_image")
    private byte[] reportImage;

    @Column(name = "Review_state", nullable = false)
    private Byte reviewState;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Act_id")
    private Act act;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Message_id")
    private ActMsg message;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReportContent() {
        return reportContent;
    }

    public void setReportContent(String reportContent) {
        this.reportContent = reportContent;
    }

    public byte[] getReportImage() {
        return reportImage;
    }

    public void setReportImage(byte[] reportImage) {
        this.reportImage = reportImage;
    }

    public Byte getReviewState() {
        return reviewState;
    }

    public void setReviewState(Byte reviewState) {
        this.reviewState = reviewState;
    }

    public Act getAct() {
        return act;
    }

    public void setAct(Act act) {
        this.act = act;
    }

    public ActMsg getMessage() {
        return message;
    }

    public void setMessage(ActMsg message) {
        this.message = message;
    }

}