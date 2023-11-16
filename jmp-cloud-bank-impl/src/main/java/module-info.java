import com.epam.engx.jmp.api.Bank;
import com.epam.engx.jmp.cloud.bank.BankImpl;

module jmp.cloud.bank.impl {
	requires transitive jmp.bank.api;
	requires jmp.dto;
	provides Bank with BankImpl;
}
