import com.epam.jmp.api.Bank;
import com.epam.jmp.cloud.bank.BankImpl;

module jmp.cloud.bank.impl {
	requires transitive jmp.bank.api;
	requires jmp.dto;
	provides Bank
		with BankImpl;

	// Required for tests
	requires java.desktop;
}
