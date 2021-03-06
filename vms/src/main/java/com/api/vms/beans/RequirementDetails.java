package com.api.vms.beans;

import java.util.Arrays;

public class RequirementDetails {

	private String requirementID;
	private String requirementTitle;
	private String assignTo;
	private String createdDate;
	private String creatorName;
	private String documentUpload;
	private String documentUploadName;
	private String dueDate;
	private String eventType;
	private String initiator;
	private String priority;
	private String requirementDescription;
	private String requirementOther;
	private String[] requirementDetails;
	private String requirementType;
	private String reqParentID;
	private String reqDefinitionID;
	private String reqInstanceID;
	private String vendorName;
	private String username;
	private String topicId;
	private String taskName;
	private String commentMsg;
	private String attachment;
	private String attachments;
	private String attachmentName;
	private String acceptFlag;
	private String reviewFlag;
	private String reviewAcceptFlag;
	private String rejectedFlag;
	private String workerId;
	private String creatorCommentMsg;
	private String reviewerName;
	private String documentEncryptedName;
	private String encryptedAttachmentName;
	private String rating;

	public RequirementDetails(String requirementID, String requirementTitle, String assignTo, String createdDate,
			String creatorName, String documentUpload, String documentUploadName, String dueDate, String eventType,
			String initiator, String priority, String requirementDescription, String requirementOther,
			String[] requirementDetails, String requirementType, String reqParentID, String reqDefinitionID,
			String reqInstanceID, String vendorName, String username, String topicId, String taskName,
			String commentMsg, String attachment, String attachments, String attachmentName, String acceptFlag,
			String reviewFlag, String reviewAcceptFlag, String rejectedFlag, String workerId, String creatorCommentMsg,
			String reviewerName, String documentEncryptedName, String encryptedAttachmentName, String rating) {
		super();
		this.requirementID = requirementID;
		this.requirementTitle = requirementTitle;
		this.assignTo = assignTo;
		this.createdDate = createdDate;
		this.creatorName = creatorName;
		this.documentUpload = documentUpload;
		this.documentUploadName = documentUploadName;
		this.dueDate = dueDate;
		this.eventType = eventType;
		this.initiator = initiator;
		this.priority = priority;
		this.requirementDescription = requirementDescription;
		this.requirementOther = requirementOther;
		this.requirementDetails = requirementDetails;
		this.requirementType = requirementType;
		this.reqParentID = reqParentID;
		this.reqDefinitionID = reqDefinitionID;
		this.reqInstanceID = reqInstanceID;
		this.vendorName = vendorName;
		this.username = username;
		this.topicId = topicId;
		this.taskName = taskName;
		this.commentMsg = commentMsg;
		this.attachment = attachment;
		this.attachments = attachments;
		this.attachmentName = attachmentName;
		this.acceptFlag = acceptFlag;
		this.reviewFlag = reviewFlag;
		this.reviewAcceptFlag = reviewAcceptFlag;
		this.rejectedFlag = rejectedFlag;
		this.workerId = workerId;
		this.creatorCommentMsg = creatorCommentMsg;
		this.reviewerName = reviewerName;
		this.documentEncryptedName = documentEncryptedName;
		this.encryptedAttachmentName = encryptedAttachmentName;
		this.rating = rating;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getEncryptedAttachmentName() {
		return encryptedAttachmentName;
	}

	public void setEncryptedAttachmentName(String encryptedAttachmentName) {
		this.encryptedAttachmentName = encryptedAttachmentName;
	}

	public String getDocumentEncryptedName() {
		return documentEncryptedName;
	}

	public void setDocumentEncryptedName(String documentEncryptedName) {
		this.documentEncryptedName = documentEncryptedName;
	}

	public String getCreatorCommentMsg() {
		return creatorCommentMsg;
	}

	public void setCreatorCommentMsg(String creatorCommentMsg) {
		this.creatorCommentMsg = creatorCommentMsg;
	}

	public String getReviewerName() {
		return reviewerName;
	}

	public void setReviewerName(String reviewerName) {
		this.reviewerName = reviewerName;
	}

	public String getWorkerId() {
		return workerId;
	}

	public void setWorkerId(String workerId) {
		this.workerId = workerId;
	}

	public String getTopicId() {
		return topicId;
	}

	public void setTopicId(String topicId) {
		this.topicId = topicId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getCommentMsg() {
		return commentMsg;
	}

	public void setCommentMsg(String commentMsg) {
		this.commentMsg = commentMsg;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public String getAttachmentName() {
		return attachmentName;
	}

	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}

	public String getAcceptFlag() {
		return acceptFlag;
	}

	public void setAcceptFlag(String acceptFlag) {
		this.acceptFlag = acceptFlag;
	}

	public String getReviewFlag() {
		return reviewFlag;
	}

	public void setReviewFlag(String reviewFlag) {
		this.reviewFlag = reviewFlag;
	}

	public String getReviewAcceptFlag() {
		return reviewAcceptFlag;
	}

	public void setReviewAcceptFlag(String reviewAcceptFlag) {
		this.reviewAcceptFlag = reviewAcceptFlag;
	}

	public String getRejectedFlag() {
		return rejectedFlag;
	}

	public void setRejectedFlag(String rejectedFlag) {
		this.rejectedFlag = rejectedFlag;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getRequirementID() {
		return requirementID;
	}

	public void setRequirementID(String requirementID) {
		this.requirementID = requirementID;
	}

	public String getRequirementTitle() {
		return requirementTitle;
	}

	public void setRequirementTitle(String requirementTitle) {
		this.requirementTitle = requirementTitle;
	}

	public String getAssignTo() {
		return assignTo;
	}

	public void setAssignTo(String assignTo) {
		this.assignTo = assignTo;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public String getDocumentUpload() {
		return documentUpload;
	}

	public void setDocumentUpload(String documentUpload) {
		this.documentUpload = documentUpload;
	}

	public String getDocumentUploadName() {
		return documentUploadName;
	}

	public void setDocumentUploadName(String documentUploadName) {
		this.documentUploadName = documentUploadName;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getInitiator() {
		return initiator;
	}

	public void setInitiator(String initiator) {
		this.initiator = initiator;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getRequirementDescription() {
		return requirementDescription;
	}

	public void setRequirementDescription(String requirementDescription) {
		this.requirementDescription = requirementDescription;
	}

	public String getRequirementOther() {
		return requirementOther;
	}

	public void setRequirementOther(String requirementOther) {
		this.requirementOther = requirementOther;
	}

	public String[] getRequirementDetails() {
		return requirementDetails;
	}

	public void setRequirementDetails(String[] requirementDetails) {
		this.requirementDetails = requirementDetails;
	}

	public String getRequirementType() {
		return requirementType;
	}

	public void setRequirementType(String requirementType) {
		this.requirementType = requirementType;
	}

	public String getReqParentID() {
		return reqParentID;
	}

	public void setReqParentID(String reqParentID) {
		this.reqParentID = reqParentID;
	}

	public String getReqDefinitionID() {
		return reqDefinitionID;
	}

	public void setReqDefinitionID(String reqDefinitionID) {
		this.reqDefinitionID = reqDefinitionID;
	}

	public String getReqInstanceID() {
		return reqInstanceID;
	}

	public void setReqInstanceID(String reqInstanceID) {
		this.reqInstanceID = reqInstanceID;
	}

	public String getAttachments() {
		return attachments;
	}

	public void setAttachments(String attachments) {
		this.attachments = attachments;
	}

	@Override
	public String toString() {
		return "RequirementDetails [requirementID=" + requirementID + ", requirementTitle=" + requirementTitle
				+ ", assignTo=" + assignTo + ", createdDate=" + createdDate + ", creatorName=" + creatorName
				+ ", documentUpload=" + documentUpload + ", documentUploadName=" + documentUploadName + ", dueDate="
				+ dueDate + ", eventType=" + eventType + ", initiator=" + initiator + ", priority=" + priority
				+ ", requirementDescription=" + requirementDescription + ", requirementOther=" + requirementOther
				+ ", requirementDetails=" + Arrays.toString(requirementDetails) + ", requirementType=" + requirementType
				+ ", reqParentID=" + reqParentID + ", reqDefinitionID=" + reqDefinitionID + ", reqInstanceID="
				+ reqInstanceID + ", vendorName=" + vendorName + ", username=" + username + ", topicId=" + topicId
				+ ", taskName=" + taskName + ", commentMsg=" + commentMsg + ", attachment=" + attachment
				+ ", attachments=" + attachments + ", attachmentName=" + attachmentName + ", acceptFlag=" + acceptFlag
				+ ", reviewFlag=" + reviewFlag + ", reviewAcceptFlag=" + reviewAcceptFlag + ", rejectedFlag="
				+ rejectedFlag + ", workerId=" + workerId + ", creatorCommentMsg=" + creatorCommentMsg
				+ ", reviewerName=" + reviewerName + ", documentEncryptedName=" + documentEncryptedName
				+ ", encryptedAttachmentName=" + encryptedAttachmentName + ", rating=" + rating + "]";
	}

}
