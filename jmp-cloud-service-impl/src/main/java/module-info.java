import com.epam.jmp.cloud.service.ServiceImpl;
import com.epam.jmp.service.api.Service;

module jmp.cloud.service.impl {
	requires transitive jmp.service.api;
	requires jmp.dto;
	provides Service
		with ServiceImpl;
}
