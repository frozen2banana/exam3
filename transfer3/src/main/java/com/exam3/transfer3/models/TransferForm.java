package com.exam3.transfer3.models;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;


public class TransferForm {

    @Size(min=10, max=10, message="Your Account-Id must be exactly 10 characters")
    @NotEmpty(message = "From account is required")
    private String fromAccountId;

    @Size(min=10, max=10, message="Your Account-Id must be exactly 10 characters")
    @NotEmpty(message = "To account is required")
    private String toAccountId;

    @NotEmpty(message = "Amount Transferred is required")
    @DecimalMin(value = "10.00", message = "Amount Transferred must be greater than or equal to 10.00")
    private BigDecimal amount;

    private String comments;

    private Date date;

	
    private String transId;



    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getFromAccountId() {
        return fromAccountId;
    }

    public void setFromAccountId(String fromAccountId) {
        this.fromAccountId = fromAccountId;
    }

    public String getToAccountId() {
        return toAccountId;
    }

    public void setToAccountId(String toAccountId) {
        this.toAccountId = toAccountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

	public JsonObject toJSON(){
		return Json.createObjectBuilder()
			.add("transactionId", transId)
			.add("date", date)
			.add("from_account", fromAccountId)
			.add("to_account", toAccountId)
			.add("amount", amount)
			.build();
	}


    @Autowired
    private DetailsRepository detailsRepository;

	public Optional<TransferForm> getOrderByOrderId(String transId) {
		return detailsRepository.get(transId);
	}

	public TransferForm saveTransfer(Account fromAccount, Account toAccount, BigDecimal amount) {
		TransferForm transfer = createTransfer(fromAccount, toAccount, amount);
		calculateCost(transfer);
		detailsRepository.save(transfer);
		return transfer;
	}

	public TransferForm createTransfer(Account fromAccount, Account toAccount, BigDecimal amount) {
		String transId = UUID.randomUUID().toString().substring(0, 8);
		TransferForm transfer = new TransferForm(fromAccount, toAccount, amount);
		transfer.setOrderId(transId);
		return transfer;
	}

    public static TransferForm create(String str) {
		JsonObject json = toJSON(str);
		return order;
	}

}